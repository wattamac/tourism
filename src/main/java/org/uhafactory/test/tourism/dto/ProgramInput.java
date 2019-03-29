package org.uhafactory.test.tourism.dto;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramInput {
    private Long id; //?

    private String name;

    private String theme;

    private String region;

    private String descrition;

    private String detailDescription;

    public List<String> getTheme() {
        if(StringUtils.isBlank(theme)){
            return Collections.emptyList();
        }
        return Arrays.asList(theme.split(","));
    }
}
