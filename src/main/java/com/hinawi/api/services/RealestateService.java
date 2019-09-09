package com.hinawi.api.services;


import com.hinawi.api.domains.Flat;
import com.hinawi.api.repository.PaymentsStatistics;

import java.util.List;

public interface RealestateService {

    List<Flat> getFlatList();
    List<PaymentsStatistics> findFlatsByStatus();
}
