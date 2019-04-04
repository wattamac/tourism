package org.uhafactory.tour.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ProgramDto {
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String theme;

    @NotBlank
    private String serviceArea;

    @NotBlank
    private String description;

    private String detailDescription;
}
