package me.hangyeol.eatgo.region.controller;

import me.hangyeol.eatgo.region.service.RegionService;
import me.hangyeol.eatgo.region.Region;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {

    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/regions")
    public List<Region> list() {
        List<Region> regions = regionService.getRegions();
        regions.add(Region.builder()
                .name("Seoul")
                .build());
        return regions;
    }

}
