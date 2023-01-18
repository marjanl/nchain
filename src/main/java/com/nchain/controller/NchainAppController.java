package com.nchain.controller;

import com.nchain.entity.NchainApp;
import com.nchain.repository.NchainAppSpecification;
import com.nchain.repository.SearchCriteria;
import com.nchain.service.NchainAppService;
import com.nchain.util.AddScore;
import com.nchain.util.NewNchainApp;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/nchain-app")
@RequiredArgsConstructor
public class NchainAppController {
    @Autowired
    private NchainAppService nchainAppService;

    @ApiOperation(value = "Get All Apps")
    @GetMapping
    public Page<NchainApp> getAllApps(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable) {
        Page<NchainApp> allApps = nchainAppService.getAllApps(pageable);
        return allApps;
    }

    @GetMapping("/{id}")
    public NchainApp get(@PathVariable Integer id) {

        return nchainAppService.get(id);
    }

    @PostMapping
    @ApiOperation(value = "create apps", response = NchainApp.class, responseContainer = "List")
    public List<NchainApp> createNewNchainApp(@RequestBody NewNchainApp newNchainApp) {
        return nchainAppService.saveAll(newNchainApp.createNchainAppList());
    }

    @GetMapping("/search")
    public Page<NchainApp> search(@RequestParam(value = "searchParams") String search,
                                  @PageableDefault(sort = "id", direction = Sort.Direction.ASC)
                                  Pageable pageable) {
        Pattern pattern = Pattern.compile("(\\w+?)(:)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        Specification<NchainApp> spec = null;
        while (matcher.find()) {
            if (spec == null) {
                spec = Specification.where(new NchainAppSpecification(new SearchCriteria(matcher.group(1), matcher.group(3))));
            } else {
                spec = spec.and(new NchainAppSpecification(new SearchCriteria(matcher.group(1), matcher.group(3))));
            }
        }
        return nchainAppService.findBySpecs(spec, pageable);
    }

    @ApiOperation(value = "Create new score for app")
    @PostMapping("/score")
    public NchainApp createScore(@RequestBody AddScore score) {
        return nchainAppService.createScore(score);
    }
}
