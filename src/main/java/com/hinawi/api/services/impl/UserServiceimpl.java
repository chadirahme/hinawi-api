package com.hinawi.api.services.impl;

import com.hinawi.api.converter.UserConverter;
import com.hinawi.api.domains.*;
import com.hinawi.api.dto.UserDto;
import com.hinawi.api.repository.*;
import com.hinawi.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
        return customersRepository.findAll();
    }

    @Override
    public List<Prospective> getProspectives() {
        return prospectiveRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }

    @Override
    public List<Vendors> getVendors() {
        return vendorsRepository.findAll();
    }

    @Override
    public List<MobileAttendance> getMobileAttendance() {
        return mobileAttendanceRepository.findAll(new Sort(Sort.Direction.DESC, "attendId"));
    }

    @Override
    public List<Vendors> getVendorsBalance() {
        return vendorsRepository.findBalance();
    }

    @Override
    public List<Customers> getCustomersBalance() {
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
        java.util.Date currentDate = Calendar.getInstance().getTime();

        List<MobileAttendance>  lst= mobileAttendanceRepository.findLastVisit(mobileAttendance.getUserId());
        if(lst!=null && lst.size()>0){
            MobileAttendance mobileAttendance1=lst.get(0);
            mobileAttendance1.setCheckoutTime(currentDate);
            mobileAttendance1.setCheckoutNote(mobileAttendance.getCheckinNote());
            mobileAttendanceRepository.save(mobileAttendance1);
        }
       else {
            mobileAttendance.setCheckinTime(currentDate);
            mobileAttendanceRepository.save(mobileAttendance);
        }

        return mobileAttendance;
    }

    @Override
    public Prospective saveProspectives(Prospective prospective){
        if(prospective.getRecNo()==0){
            prospective.setRecNo(prospectiveRepository.getMaxId()+1);
        }
        prospectiveRepository.save(prospective);
        if(prospective.getLstProspectiveCotact()!=null) {
            for (ProspectiveCotact contact : prospective.getLstProspectiveCotact()) {
                contact.getProspectiveCotactId().setRecNo(prospective.getRecNo());
                saveProspectiveContacts(contact);
            }
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
}