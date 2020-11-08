package me.hangyeol.eatgo.region.service;

import me.hangyeol.eatgo.region.Region;
import me.hangyeol.eatgo.region.RegionRepository;
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
