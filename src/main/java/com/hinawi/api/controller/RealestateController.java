package com.hinawi.api.controller;

import com.hinawi.api.domains.Flat;
import com.hinawi.api.dto.ApiResponse;
import com.hinawi.api.repository.PaymentsStatistics;
import com.hinawi.api.services.RealestateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/realestate/")
public class RealestateController {

    private static final Logger logger = LoggerFactory.getLogger(RealestateController.class);

    @Autowired
    RealestateService realestateService;

    //http://localhost:8091/api/realestate/flatList?name=
    @GetMapping(value = "/flatList")
    public ApiResponse<List<Flat>> getCUCList(@RequestParam("name") String name) {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "Flat list fetched successfully.", realestateService.getFlatList());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }

    @GetMapping(value = "/flatsByStatus")
    public ApiResponse<List<PaymentsStatistics>> findFlatsByStatus() {
        try {
            return new ApiResponse<>(HttpStatus.OK.value(), "Flat list fetched successfully.", realestateService.findFlatsByStatus());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ApiResponse<>(HttpStatus.NO_CONTENT.value(), ex.getMessage(), null);
        }
    }
}
