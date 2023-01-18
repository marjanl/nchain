package com.nchain.service;

import com.nchain.entity.NchainApp;
import com.nchain.util.AddScore;
import com.nchain.exception.NChainAppNotFoundException;
import com.nchain.repository.NchainAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository

@Service
public class NchainAppService {

    @Autowired
    private NchainAppRepository nchainAppRepository;

    public Page<NchainApp> getAllApps(Pageable pageable) {
        return nchainAppRepository.getAllApps(pageable);
    }

    public NchainApp get(Integer id) {
        return nchainAppRepository.findById(id)
                .orElseThrow(() -> new NChainAppNotFoundException("app not found. id=>"+id));
    }

    public NchainApp createScore(AddScore score){
        //make sure app exists, this throws ex if not existing
        NchainApp nchainApp = get(score.getId());
        nchainApp.setScore(score.getScore());

        return nchainAppRepository.save(nchainApp);
    }

    public Page<NchainApp> findBySpecs(Specification spec, Pageable pageable){
        return nchainAppRepository.findAll(spec, pageable);
    }

    public List<NchainApp> saveAll(List<NchainApp> nchainAppList) {
        return nchainAppRepository.saveAll(nchainAppList);
    }
}
