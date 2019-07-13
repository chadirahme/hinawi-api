package com.hinawi.api.controller;

import com.hinawi.api.domains.HRListFields;
import com.hinawi.api.domains.HRListValues;
import com.hinawi.api.dto.ApiResponse;
import com.hinawi.api.dto.ChequeModel;
import com.hinawi.api.services.ListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/list/")
public class ListController {

    private static final Logger logger = LoggerFactory.getLogger(ListController.class);

    @Autowired
    ListService listService;

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

    @PostMapping(value = "/saveHRListValues")
    public ApiResponse<HRListValues> saveHRListValues(@RequestBody HRListValues hrListValues) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "HRListValues saved successfully.", listService.saveHRListValues(hrListValues));
            //List<ChequeModel> lst= accountService.getChequesUnderCollectionList();
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }
}
