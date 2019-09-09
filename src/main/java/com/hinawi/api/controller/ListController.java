package com.hinawi.api.controller;

import com.hinawi.api.domains.CustomerStatusHistory;
import com.hinawi.api.domains.HRListFields;
import com.hinawi.api.domains.HRListValues;
import com.hinawi.api.domains.SalesRep;
import com.hinawi.api.dto.ApiResponse;
import com.hinawi.api.dto.ChequeModel;
import com.hinawi.api.dto.HRListFieldsEnum;
import com.hinawi.api.repository.CustomerStatusHistoryRepository;
import com.hinawi.api.repository.HRListValuesRepository;
import com.hinawi.api.services.ListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/list/")
public class ListController {

    private static final Logger logger = LoggerFactory.getLogger(ListController.class);

    @Autowired
    ListService listService;

    @Autowired
    HRListValuesRepository hrListValuesRepository;

    @Autowired
    CustomerStatusHistoryRepository customerStatusHistoryRepository;

    //http://localhost:8091/api/list/maxid/1
    @RequestMapping(value = "maxid/{id}", method = RequestMethod.GET)
    public long getMax(@PathVariable Long id) {
        List<HRListFields> lst= listService.getHRListFields();
        HRListFields hrListFields= lst.stream()
                .max(Comparator.comparing(HRListFields::getFieldId))
                .orElse(null);

        long maxId=hrListValuesRepository.getMaxId();

        if(hrListFields!=null)
        return hrListFields.getFieldId();
        else
          return   maxId;
        //long maxId=hrListValuesRepository.getMaxId();
        //return maxId;
    }

    @GetMapping(value = "/hrListFields")
    public ApiResponse<List<HRListFields>> getHRListFields() {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "list fetched successfully.", listService.getHRListFields());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @GetMapping(value = "/hrListValues")
    public ApiResponse<List<HRListValues>> getHRListValues(@RequestParam("fieldId") long fieldId) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "list fetched successfully.", listService.getHRListValues(fieldId));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @GetMapping(value = "/hrSubListValues")
    public ApiResponse<List<HRListValues>> getHRSubListValues(@RequestParam("fieldId") long fieldId , @RequestParam("subId") Integer subId) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "list fetched successfully.", listService.getHRSubListValues(fieldId,subId));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }


    @PostMapping(value = "/saveHRListValues")
    public ApiResponse<HRListValues> saveHRListValues(@RequestBody HRListValues hrListValues) {
        try {
            //hrListValues.set_disclaimer();
            return new ApiResponse<>(HttpStatus.OK.value(), "HRListValues saved successfully.", listService.saveHRListValues(hrListValues));
            //List<ChequeModel> lst= accountService.getChequesUnderCollectionList();
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @PostMapping(value = "/deleteHRListValues")
    public ApiResponse<HRListValues> deleteHRListValues(@RequestBody HRListValues hrListValues) {
        try {

            if(hrListValues.getFieldName().equals(HRListFieldsEnum.POSITION.name()))
            return new ApiResponse<>(HttpStatus.NOT_ACCEPTABLE.value(), "HRListValues can't delete.", hrListValues);
            else
                return new ApiResponse<>(HttpStatus.OK.value(), "HRListValues can delete.", hrListValues);
            //List<ChequeModel> lst= accountService.getChequesUnderCollectionList();
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @GetMapping(value = "/salesRepList")
    public ApiResponse<List<SalesRep>> getHRListValues() {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "list fetched successfully.", listService.getSalesRepList());
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @GetMapping(value = "/prospectiveStatusHistory")
    public ApiResponse<List<CustomerStatusHistory>> getHRListValues(@RequestParam("custKey") int custKey) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "list fetched successfully.", customerStatusHistoryRepository.findProspectiveStatusHistory(custKey));
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }
}
