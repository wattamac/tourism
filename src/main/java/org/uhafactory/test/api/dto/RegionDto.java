package org.uhafactory.test.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegionDto {
    private String region;

    private List<ProgramDto> programs;

}
