package org.uhafactory.test.tourism.dto;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.uhafactory.test.tourism.Program;
import org.uhafactory.test.tourism.ProgramRepository;
import org.uhafactory.test.tourism.Region;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProgramCreatorTest {

    @InjectMocks
    private ProgramCreator creator;

    @Mock
    private ProgramRepository programRepository;

    @Mock
    private RegionService regionService;

    @Test
    void testConvert() {
        String name = "자연과 문화를 함께 즐기는 설악산 기행";
        String theme = "문화생태체험,자연생태체험,";
        String regionName = "강원도 속초";
        String description = "설악산 탐방안내소, 신흥사, 권금성, 비룡폭포";
        String detailDescription = " 설악산은 왜 설악산이고, 신흥사는 왜 신흥사일까요? 설악산에 대해 정확히 알고, 배우고, 느낄 수 있는 당일형 생태관광입니다.";

        ProgramInput input = new ProgramInput();
        input.setName(name);
        input.setTheme(theme);
        input.setRegion(regionName);
        input.setDescrition(description);
        input.setDetailDescription(detailDescription);

        Region region = new Region();
        given(regionService.getRegion(regionName)).willReturn(region);

        Program program = creator.convert(input);
        assertThat(program.getName()).isEqualTo(name);
        assertThat(program.getTheme()).isEqualTo(Lists.newArrayList("문화생태체험", "자연생태체험"));
        assertThat(program.getRegion()).isEqualTo(region);
        assertThat(program.getDescrition()).isEqualTo(description);
        assertThat(program.getDetailDescription()).isEqualTo(detailDescription);
    }
}