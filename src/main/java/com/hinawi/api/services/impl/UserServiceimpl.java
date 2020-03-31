package com.hinawi.api.services.impl;

import com.hinawi.api.converter.UserConverter;
import com.hinawi.api.domains.*;
import com.hinawi.api.dto.MailModel;
import com.hinawi.api.dto.UserDto;
import com.hinawi.api.repository.*;
import com.hinawi.api.services.MailService;
import com.hinawi.api.services.UserService;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    CustomersRepository customersRepository;
    @Autowired
    ProspectiveRepository prospectiveRepository;
    @Autowired
    VendorsRepository vendorsRepository;
    @Autowired
    StudentsRepository studentsRepository;
    @Autowired
    WebMessagesRepository webMessagesRepository;
    @Autowired
    WebDashboardRepository webDashboardRepository;
    @Autowired
    MobileAttendanceRepository mobileAttendanceRepository;
    @Autowired
    ProspectiveContactRepository prospectiveContactRepository;
    @Autowired
    QuotationRepository quotationRepository;
    @Autowired
    CompanySettingsRepository companySettingsRepository;

    @Autowired
    MailService mailService;

//    @PersistenceContext
//    private EntityManager _entityManager;

    @Override
    public UserDto loginUser(UserDto userDto) {

        return UserConverter.entityToDto(usersRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword()));
    }

    @Override
    public Users loginUsers(Users users) {
        return usersRepository.findByEmailAndPassword(users.getEmail(), users.getPassword());
    }

    @Override
    public List<Customers> getCustomers() {
        return customersRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<Prospective> getProspectives() {
        List<Prospective> lstPro =  prospectiveRepository.findAll(new Sort(Sort.Direction.DESC, "TimeCreated"));

        //return prospectiveRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
        //check HAS QUOTATION
        List<Quotation> lstQuotation =  quotationRepository.findAllHasQuotation();
        if(lstQuotation.size()>0){
            List<Long> ids = lstQuotation.stream()
                    .map(Quotation::getCustomerRefKey).distinct()
                    .map(Long::valueOf)
                    .collect(Collectors.toList());

            List<Prospective> lstUpdatedPro=  lstPro.stream().map((v) -> {
                if (ids.contains(v.getRecNo())) v.setHasQuotation("Yes") ; return v;
            }).collect(Collectors.toList());

            return lstUpdatedPro;
           //lstPro= lstPro.stream().map(p->p.setHasQuotation(ids.contains(p.getRecNo())?"Yes":"No")).collect(Collectors.toList());

            //lstPro.forEach(f -> f.setHasQuotation( ids.contains(f.getRecNo())?"Yes" :"No"));
        }
        return lstPro;
    }

    @Override
    public List<Prospective> getSortedProspectives() {
        return prospectiveRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<Vendors> getVendors() {
        return vendorsRepository.findAll();
    }

    @Override
    public List<Vendors> getSortedVendors() {
        return vendorsRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<MobileAttendance> getMobileAttendance(int month) {
        if(month==0)
        return mobileAttendanceRepository.findAll(new Sort(Sort.Direction.DESC, "attendId"));
        else
        return mobileAttendanceRepository.findDailyVisit(month);
    }

    @Override
    public List<Vendors> getVendorsBalance() {
        return vendorsRepository.findBalance();
    }

    @Override
    public List<Customers> getCustomersBalance() {
        customersRepository.findBalance().stream().map(Customers::getName)
                .collect(Collectors.toList());
        return customersRepository.findBalance();
    }

    @Override
    public List<WebMessages> getWebMessages() {
        return webMessagesRepository.findAll();
    }

    @Override
    public List<Students> getStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public Students addStudents(Students students) {
        return studentsRepository.save(students);
    }
    @Override
    public Integer addListStudents(List<Students> students) {
       // Iterable<Students> iterable = students;
         studentsRepository.saveAll(students);
         return students.size();
    }


    @Override
    public WebMessages addWebMessages(WebMessages webMessages) {
        return webMessagesRepository.save(webMessages);
    }

    @Override
    public WebDashboard addWebDashboard(WebDashboard webDashboard) {
        return webDashboardRepository.save(webDashboard);
    }

    @Override
    public WebDashboard deleteWebDashboard(WebDashboard webDashboard) {
        List<WebDashboard> lst= webDashboardRepository.findByUserIdAndDashName(webDashboard.getUserId() , webDashboard.getDashName());
        webDashboardRepository.deleteAll(lst);
        return null;
    }

    @Override
    public List<WebDashboard> getUserDashboards(Integer userid) {
        return webDashboardRepository.findByUserIdOrderByDashOrder(userid);
    }
    @Override
    public WebDashboard getWebDashboardByNameAndUser(WebDashboard webDashboard) {
        List<WebDashboard> lst= webDashboardRepository.findByUserIdAndDashName(webDashboard.getUserId(),webDashboard.getDashName());
        if(!lst.isEmpty()){
            return lst.get(0);
        }
        return null;
    }

    @Override
    public WebDashboard getWebDashboardByName(WebDashboard webDashboard) {
        return webDashboardRepository.findByDashName(webDashboard.getDashName());
    }
    @Override
    public MobileAttendance addMobileAttendance(MobileAttendance mobileAttendance){
        // java.util.Date
        //java.util.Date currentDate = Calendar.getInstance().getTime();
        LocalDateTime dateTime = LocalDateTime.parse(mobileAttendance.getLocalCheckinTime());
        if(mobileAttendance.getCheckoutNote()!=null) {
            List<MobileAttendance> lst = mobileAttendanceRepository.findLastVisit(mobileAttendance.getUserId());
            if (lst != null && lst.size() > 0) {
                MobileAttendance mobileAttendance1 = lst.get(0);
                // mobileAttendance1.setCheckoutTime(mobileAttendance.getCheckoutTime());// as I am passing only checkinTime from front-end
//                mobileAttendance1.setCheckoutTime(mobileAttendance.getLocalCheckinTime().toInstant()
//                        .atZone(ZoneId.systemDefault()).toLocalDateTime());
                mobileAttendance1.setCheckoutTime(dateTime);
                mobileAttendance1.setCheckoutNote(mobileAttendance.getCheckoutNote());
                mobileAttendance1.setCheckoutLatitude(mobileAttendance.getCheckoutLatitude());
                mobileAttendance1.setCheckoutLongitude(mobileAttendance.getCheckoutLongitude());
                mobileAttendance1.setCheckoutReasonId(mobileAttendance.getReasonId());
                mobileAttendance1.setCheckoutReasonDesc(mobileAttendance.getReasonDesc());
                mobileAttendanceRepository.save(mobileAttendance1);

            }
        }
       else {
//            mobileAttendance.setCheckinTime(mobileAttendance.getLocalCheckinTime().toInstant()
//                    .atZone(ZoneId.systemDefault()).toLocalDateTime());  //mobileAttendance.getCheckinTime());
            //LocalDateTime.parse( new SimpleDateFormat("yyyy-MM-dd").format(mobileAttendance.getLocalCheckinTime()));
            //.replace("T", "T").substring(0,19), formatter);
            mobileAttendance.setCheckinTime(dateTime);
            mobileAttendanceRepository.save(mobileAttendance);
        }
        addAttendanceMemo(mobileAttendance);
        return mobileAttendance;
    }

    private void addAttendanceMemo(MobileAttendance mobileAttendance){
        String type="Check In";
        if (mobileAttendance.getCheckoutNote() != null) {
            type="Check Out";
        }

        String note = "Date & Time: " + mobileAttendance.getLocalCheckinTime().replace("T"," @ ");
        note +="\n" + type + " by: " + mobileAttendance.getUserName();
        note +="\n" + " Reason: " + mobileAttendance.getReasonDesc();
        if (mobileAttendance.getCheckoutNote() != null) {
            note += "\n" + mobileAttendance.getCheckoutNote();
        } else {
            note += "\n" + mobileAttendance.getCheckinNote();
        }

        if(mobileAttendance.getCustomerType().equals("Prospective")) {
            List<Prospective> lst = prospectiveRepository.findByName(mobileAttendance.getCustomerName());
            if (lst != null && lst.size() > 0) {
                Prospective prospective = lst.get(0);
                //Prospective prospective=  prospectiveRepository.findById(mobileAttendance.getRecNo()).orElse(null);
                if (prospective != null) {
                    prospective.setNote(prospective.getNote()==null?"":prospective.getNote() + "\n"  + note);
                    prospectiveRepository.save(prospective);
                }
            }
        }

        else if(mobileAttendance.getCustomerType().equals("Customer")) {
            List<Customers> lst = customersRepository.findByName(mobileAttendance.getCustomerName());
            if (lst != null && lst.size() > 0) {
                Customers customer = lst.get(0);
                if (customer != null) {
                    customer.setNote(customer.getNote()==null?"":customer.getNote() + "\n"  + note);
                    customersRepository.save(customer);
                }
            }
        }

        else if(mobileAttendance.getCustomerType().equals("Vendor")) {
            List<Vendors> lst = vendorsRepository.findByName(mobileAttendance.getCustomerName());
            if (lst != null && lst.size() > 0) {
                Vendors vendor = lst.get(0);
                if (vendor != null) {
                    vendor.setNote(vendor.getNote()==null?"":vendor.getNote() + "\n"  + note);
                    vendorsRepository.save(vendor);
                }
            }
        }
    }

    @Override
    public List<MobileAttendance> checkIfUserCheckedIn(int userId){
       return mobileAttendanceRepository.findLastVisit(userId);
    }
    @Override
    public MobileAttendance findLastUserVisit(int userId){
        List<MobileAttendance>  lst=mobileAttendanceRepository.findLastUserVisit(userId);
        if(lst!=null && lst.size()>0)
        return lst.get(0);
        else return null;
    }

    @Override
    public Prospective saveProspectives(Prospective prospective){
        String content="",subject="",note="";
        Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");

        content="<h1>" +prospective.getName()+"</h1>";
        content+= "<br/><h3>" + "CompanyName: " + prospective.getCompanyName()+"</h3>";
        content+= "<br/><h3>" + "Contact: " + prospective.getContact()+"</h3>";
        //<a href="https://web.whatsapp.com/send?phone=${value}&text=Hi, I contacted you Through HinawiOnline."
//        data-text="Take a look at this awesome website:" class="wa_btn wa_btn_s"
//        target="_blank"
//                >${value}</a>

        content+= "<br/><h3>" + "Telephone: " +"<a href='https://web.whatsapp.com/send?phone="+
                prospective.getTelephone1()+"&text=Hi, I contacted you regarding Hinawi Online.' >"+prospective.getTelephone1() +"</a></h3>";

        content+= "<br/><h3>" + "Alt Telephone: " +"<a href='https://web.whatsapp.com/send?phone="+
                prospective.getTelephone2()+"&text=Hi, I contacted you regarding Hinawi Online.' >"+prospective.getTelephone2() +"</a></h3>";

        content+= "<br/><h3>" + "Email: " + prospective.getEmail()+"</h3>";
        content+= "<br/><h3>" + "Active: " + prospective.getActive()+"</h3>";
        content+= "<br/><h3>" + "Priority: " + prospective.getPriorityID()+"</h3>";
        note=prospective.getNote()==null?"":prospective.getNote()+"</h2>";
        Matcher m = CRLF.matcher(note);
        if (m.find()) {
            note = m.replaceAll("<br>");
        }
        content+= "<br/><h3>" + "Notes: " + note+"</h3>";

        if(prospective.getActive()==null)
            prospective.setActive("Y");

        if(prospective.getRecNo()==0){
            subject=prospective.getWebUserName() + " from " + prospective.getWebCompanyName() + " Added New Prospective " ;
            prospective.setRecNo(prospectiveRepository.getMaxId()+1);
            prospective.setTimeCreated(new Date());
            prospective.setFullName(prospective.getName());
            if(StringUtils.isEmpty(prospective.getActive()))
            prospective.setActive("N");
            if(prospective.getPriorityID()==null)
            {
                prospective.setPriorityID(0);
            }
        }else {
            subject=prospective.getWebUserName() + " from " + prospective.getWebCompanyName() + " Modified Existing Prospective ";
        }
        prospectiveRepository.save(prospective);
        if(prospective.getLstProspectiveCotact()!=null) {
            for (ProspectiveCotact contact : prospective.getLstProspectiveCotact()) {
                contact.getProspectiveCotactId().setRecNo(prospective.getRecNo());
                saveProspectiveContacts(contact);
            }
        }


        try {
            //send email
            MailModel mailModel=new MailModel();
            mailModel.setMailSubject(subject);
            //mailModel.setMailTo("info@hinawi.ae");
            mailModel.setMailTo("eng.chadi@gmail.com");
            mailModel.setMailCc("chadi@hinawi.ae");

            mailModel.setMailContent(content);

            mailService.sendEmail(mailModel);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return prospective;
    }

    @Override
    public List<ProspectiveCotact> getProspectiveContacts(long recNo) {
        ProspectiveCotact prospectiveCotact=new ProspectiveCotact();
        ProspectiveCotactId prospectiveCotactId=new ProspectiveCotactId();
        prospectiveCotactId.setRecNo(recNo);

        prospectiveCotact.setProspectiveCotactId(prospectiveCotactId);
        Example<ProspectiveCotact> songExample = Example.of(prospectiveCotact);

       // List<ProspectiveCotact> lst=  _entityManager.createQuery("select p from ProspectiveCotact p").getResultList();
               // .setParameter("fieldId", hrListValues.getFieldId())
                //.setParameter("lastModified", new Date())
                //.executeUpdate();


        return prospectiveContactRepository.findByMeProspectiveCotactIdRecNo(recNo);  //.findAll(songExample);
                //findByProspectiveCotactIdRecNoAndProspectiveCotactIdLineNo(recNo,1);
    }

    @Override
    public ProspectiveCotact saveProspectiveContacts(ProspectiveCotact prospectiveCotact){
        if(prospectiveCotact.getProspectiveCotactId().getRecNo()>0) {
            if (prospectiveCotact.getProspectiveCotactId().getLineNo() == 0) {
                List<ProspectiveCotact> lst = prospectiveContactRepository.findByMeProspectiveCotactIdRecNo(prospectiveCotact.getProspectiveCotactId().getRecNo());
                List<Long> lstLineNo = lst
                        .stream().map(x -> x.getProspectiveCotactId().getLineNo()).collect(Collectors.toList());

                Long maxLineNo = lstLineNo
                        .stream()
                        .mapToLong(v -> v)
                        .max().orElse(0);

                maxLineNo += 1;
                prospectiveCotact.getProspectiveCotactId().setLineNo(maxLineNo);

            }
            prospectiveContactRepository.save(prospectiveCotact);

        }
        return prospectiveCotact;
    }

    @Override
    public CompanySettings getCompanySettings(){
        List<CompanySettings> lst= companySettingsRepository.findAll();
        if(lst!=null && lst.size()>0)
            return lst.get(0);
        else
            return null;
    }
}