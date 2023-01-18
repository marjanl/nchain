package com.nchain.repository;

import com.nchain.entity.NchainApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface NchainAppRepository extends JpaRepository<NchainApp, Integer>, JpaSpecificationExecutor<NchainApp> {

    @Query("SELECT n FROM NchainApp n")
    Page<NchainApp> getAllApps(Pageable pageable);

}
