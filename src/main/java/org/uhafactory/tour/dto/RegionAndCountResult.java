package org.uhafactory.tour.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.logging.log4j.util.Strings;
import org.uhafactory.tour.Name;

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
        @JsonIgnore
        private Name name;

        private Long count;

        @JsonProperty
        public String getName() {
            return name != null ? name.getWholeName() : Strings.EMPTY;
        }
    }
}
