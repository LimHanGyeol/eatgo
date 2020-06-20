package me.hangyeol.eatgo.application;

import me.hangyeol.eatgo.domain.Region;
import me.hangyeol.eatgo.domain.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getRegions() {
        return regionRepository.findAll();
    }

}
