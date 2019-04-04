package org.uhafactory.tour.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SimpleRegionDto {
    private String region;

    private List<SimpleProgramDto> programs;

    @Getter
    @Setter
    public static class SimpleProgramDto {
        @JsonProperty("prgm_name")
        private String name;

        private String theme;
    }
}
