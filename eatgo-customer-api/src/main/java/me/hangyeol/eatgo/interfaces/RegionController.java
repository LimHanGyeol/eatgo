package me.hangyeol.eatgo.interfaces;

import me.hangyeol.eatgo.application.RegionService;
import me.hangyeol.eatgo.domain.Region;
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
        regions.add(Region.builder().name("Seoul").build());
        return regions;
    }


}
