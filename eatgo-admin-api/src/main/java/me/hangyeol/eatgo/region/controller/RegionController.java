package me.hangyeol.eatgo.region.controller;

import me.hangyeol.eatgo.region.service.RegionService;
import me.hangyeol.eatgo.region.Region;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping("/regions")
    public ResponseEntity<?> create(@RequestBody Region resource) throws URISyntaxException {
        String name = resource.getName();
        Region region = regionService.addRegion(name);
        URI location = new URI("/regions/" + region.getId());
        return ResponseEntity.created(location).body("{}");
    }

}
