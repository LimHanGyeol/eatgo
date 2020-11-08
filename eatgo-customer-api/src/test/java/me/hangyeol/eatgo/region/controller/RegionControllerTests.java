package me.hangyeol.eatgo.region.controller;

import me.hangyeol.eatgo.region.service.RegionService;
import me.hangyeol.eatgo.region.Region;
import me.hangyeol.eatgo.region.controller.RegionController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegionController.class)
class RegionControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegionService regionService;

    private List<Region> initMockRegions() {
        List<Region> regions = new ArrayList<>();
        regions.add(Region.builder()
                .name("Seoul")
                .build());
        return regions;
    }

    @Test
    @DisplayName("/regions api 호출로 전체 지역 정보 가져오기")
    public void list() throws Exception {
        List<Region> regions = initMockRegions();

        given(regionService.getRegions()).willReturn(regions);

        mvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Seoul")));
    }

}
