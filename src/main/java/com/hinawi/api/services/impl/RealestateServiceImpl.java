package com.hinawi.api.services.impl;

import com.hinawi.api.domains.Flat;
import com.hinawi.api.repository.FlatInfoRepository;
import com.hinawi.api.repository.FlatRepository;
import com.hinawi.api.repository.PaymentsStatistics;
import com.hinawi.api.services.RealestateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealestateServiceImpl implements RealestateService {

    @Autowired
    FlatRepository flatRepository;

    @Autowired
    FlatInfoRepository flatInfoRepository;

    @Override
    public List<Flat> getFlatList() {
        return flatRepository.findAll();
    }

    @Override
    public List<PaymentsStatistics> findFlatsByStatus()
    {
        return flatInfoRepository.findAllFlats();
    }
}
