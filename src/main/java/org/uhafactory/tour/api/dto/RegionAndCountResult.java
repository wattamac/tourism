package org.uhafactory.tour.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionAndCountResult {
    private String keyword;

    private List<RegionAndProgramCount> programs;

    public static RegionAndCountResult create(String keyword, List<RegionAndProgramCount> result) {
        return new RegionAndCountResult(keyword, result);
    }

    public static RegionAndCountResult empty() {
        return new RegionAndCountResult();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class RegionAndProgramCount {
        private String name;

        private Long count;
    }
}
