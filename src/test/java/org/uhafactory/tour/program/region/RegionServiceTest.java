package org.uhafactory.tour.program.region;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.uhafactory.tour.api.dto.RegionAndCountResult;

import java.util.List;
import java.util.Optional;

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
        Optional<Region> expected = Optional.of(Region.create("AA"));
        given(regionRepository.findByCodeWithPrograms(code)).willReturn(expected);

        Optional<Region> region = regionService.getRegionByCode(code);
        assertThat(region).isEqualTo(expected);
    }

    @Test
    void testGetRegionByName() {
        String name = "!231";
        Optional<Region> expected = Optional.of(Region.create("AA"));
        given(regionRepository.findByNameWithPrograms(name)).willReturn(expected);

        Optional<Region> region = regionService.getRegionByName(name);
        assertThat(region).isEqualTo(expected);
    }

    @Test
    void testGetDescriptionKeywordCount() {
        String keyword = "keyword";
        List<RegionAndCountResult.RegionAndProgramCount> queryResult = Lists.newArrayList(new RegionAndCountResult.RegionAndProgramCount());
        given(regionRepository.findByProgramInContainsDescriptionKeyword(keyword))
                .willReturn(queryResult);

        RegionAndCountResult result = regionService.getRegionAndCountInDescriptionKeyword(keyword);
        assertThat(result.getKeyword()).isEqualTo(keyword);
        assertThat(result.getPrograms()).isEqualTo(queryResult);
    }

    @Test
    void testGetDescriptionKeywordCount_keyword_blank() {
        String keyword = " ";

        RegionAndCountResult result = regionService.getRegionAndCountInDescriptionKeyword(keyword);
        assertThat(result.getKeyword()).isNull();
        assertThat(result.getPrograms()).isNull();

        verify(regionRepository, never()).findByProgramInContainsDescriptionKeyword(any());
    }
}