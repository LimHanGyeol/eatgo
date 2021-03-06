package me.hangyeol.eatgo.region.service;

import me.hangyeol.eatgo.region.Region;
import me.hangyeol.eatgo.region.RegionRepository;
import me.hangyeol.eatgo.region.service.RegionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class RegionServiceTests {

    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        regionService = new RegionService(regionRepository);
    }

    private List<Region> initMockRegions() {
        List<Region> mockRegions = new ArrayList<>();
        mockRegions.add(Region.builder()
                .name("Seoul")
                .build());
        return mockRegions;
    }

    @Test
    @DisplayName("전체 지역 정보 가져오기")
    public void getRegions() {
        List<Region> mockRegions = initMockRegions();

        given(regionRepository.findAll())
                .willReturn(mockRegions);

        List<Region> regions = regionService.getRegions();

        Region region = regions.get(0);
        assertThat(region.getName(), is("Seoul"));
    }

}
