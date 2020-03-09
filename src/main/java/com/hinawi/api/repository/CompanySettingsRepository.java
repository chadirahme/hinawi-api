package com.hinawi.api.repository;

import com.hinawi.api.domains.CompanySettings;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by chadirahme on 2/23/20.
 */
public interface CompanySettingsRepository extends JpaRepository<CompanySettings,Long> {
}
