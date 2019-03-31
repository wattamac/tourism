package org.uhafactory.test.tourism.region;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegionServiceTest {
    @InjectMocks
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @Test
    void testGetRegion() {
        String name = "강원도";

        List<Region> expected = Lists.newArrayList(Region.create("Test"));
        given(regionRepository.findByNames(any())).willReturn(expected);

        List<Region> result = regionService.getRegions(name);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void testGetRegion_invalid_name() {
        String name = " ";
        List<Region> result = regionService.getRegions(name);

        assertThat(result).isEmpty();
        verify(regionRepository, never()).findByNames(any());
    }

    @Test
    void testGetRegionByCode() {
        String code = "!231";
        Region expected = Region.create("AA");
        given(regionRepository.getOneWithPrograms(code)).willReturn(expected);

        Region region = regionService.getRegionByCode(code);
        assertThat(region).isEqualTo(expected);
    }
}