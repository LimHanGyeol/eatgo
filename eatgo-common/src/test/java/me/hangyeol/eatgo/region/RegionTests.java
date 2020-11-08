package me.hangyeol.eatgo.region;

import me.hangyeol.eatgo.region.Region;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class RegionTests {

    @Test
    @DisplayName("리전 객체 생성")
    public void create() {
        Region region = Region.builder()
                .name("서울")
                .build();

        assertThat(region.getName()).isEqualTo("서울");
    }

}
