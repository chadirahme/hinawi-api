package com.hinawi.api.controller;

import com.hinawi.api.domains.Checkmast;
import com.hinawi.api.dto.ApiResponse;
import com.hinawi.api.dto.ChequeModel;
import com.hinawi.api.repository.PaymentsStatistics;
import com.hinawi.api.services.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by chadirahme on 6/13/19.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/accounting/")
public class AccountingController {

    private static final Logger logger = LoggerFactory.getLogger(AccountingController.class);

@Autowired
    AccountService accountService;

    //http://localhost:8091/api/accounting/cuc?name=s

    @GetMapping(value = "/cuc")
    public ApiResponse<List<ChequeModel>> getCUCList(@RequestParam("name") String name) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "CUC list fetched successfully.", accountService.getChequesUnderCollectionList());
            //List<ChequeModel> lst= accountService.getChequesUnderCollectionList();
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @GetMapping(value = "/po")
    public ApiResponse<List<ChequeModel>> getPurchaseOrderList() {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "PurchaseOrder list fetched successfully.", accountService.getPurchaseOrderList());
            //List<ChequeModel> lst= accountService.getChequesUnderCollectionList();
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }
    @PostMapping(value = "/approvepo")
    public ApiResponse<Integer> approvePurchaseOrder(@RequestBody ChequeModel chequeModel) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "PurchaseOrder saved successfully.", accountService.approvePurchaseOrder(chequeModel.getId(),"A"));
            //List<ChequeModel> lst= accountService.getChequesUnderCollectionList();
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }
    @PostMapping(value = "/disapprovepo")
    public ApiResponse<Integer> disapprovePurchaseOrder(@RequestBody ChequeModel chequeModel) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "PurchaseOrder saved successfully.", accountService.approvePurchaseOrder(chequeModel.getId(),"C"));
            //List<ChequeModel> lst= accountService.getChequesUnderCollectionList();
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    //petty chash
    @GetMapping(value = "/pettycash")
    public ApiResponse<List<Checkmast>> getPettyCashList() {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "PettyCash list fetched successfully.", accountService.getPettyCashList());
            //List<ChequeModel> lst= accountService.getChequesUnderCollectionList();
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @RequestMapping(value = "/pettycashchart", method = RequestMethod.GET)
    public ResponseEntity<HashMap<Integer,List<PaymentsStatistics>>> pettyCashChartByYear()
    {
//        logger.debug("This is a debug message");
//        logger.info("This is an info message");
//        logger.warn("This is a warn message");
//        logger.error("This is an error message");
        //List<PaymentsStatistics> list=paymnetRepository.findAllPaymentsByYear(2017);
        HashMap<Integer,List<PaymentsStatistics> > hashMap=new HashMap<>();
        hashMap.put(2018,accountService.findAllPettyCashByYear(2018));
        hashMap.put(2019,accountService.findAllPettyCashByYear(2019));
        return new ResponseEntity<HashMap<Integer,List<PaymentsStatistics> >>(hashMap, HttpStatus.OK);

    }

    @RequestMapping(value = "/quotationChartByYear", method = RequestMethod.GET)
    public ResponseEntity<HashMap<Integer,List<PaymentsStatistics>>> quotationChartByYear()
    {
        //List<PaymentsStatistics> list=paymnetRepository.findAllPaymentsByYear(2017);
        HashMap<Integer,List<PaymentsStatistics> > hashMap=new HashMap<>();
        hashMap.put(2018,accountService.findAllQuotationByYear(2018));
        hashMap.put(2019,accountService.findAllQuotationByYear(2019));
        return new ResponseEntity<HashMap<Integer,List<PaymentsStatistics> >>(hashMap, HttpStatus.OK);

    }

    //public int approvePurchaseOrder(int recNo)


//    #region "Cheques Under Collection Report"
//    public List<CheckModel> getChequesUnderCollectionList(string company)
//    {
//        facade = new HinawiCL.DataFacade.MainFacade.AccountingFacade(company);
//        List<CheckModel> lst = new List<CheckModel>();
//        lst = facade.getChequesUnderCollectionList();
//
//        //CheckModel checkModel = await db.CheckModels.FindAsync(id);
//        //if (checkModel == null)
//        //{
//        //    return NotFound();
//        //}
//
//        return lst;
//    }
//
//    //http://hinawi2.dyndns.org:8083/api/CheckAPI/getPostedDatedChequesList
//    public List<CheckModel> getPostedDatedChequesList(string company)
//    {
//        facade = new HinawiCL.DataFacade.MainFacade.AccountingFacade(company);
//        List<CheckModel> lst = new List<CheckModel>();
//        lst = facade.getPostedDatedChequesList();
//
//        //CheckModel checkModel = await db.CheckModels.FindAsync(id);
//        //if (checkModel == null)
//        //{
//        //    return NotFound();
//        //}
//
//        return lst;
//    }
//
//
//        #endregion
}
