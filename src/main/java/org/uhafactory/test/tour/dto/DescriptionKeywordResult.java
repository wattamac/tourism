package org.uhafactory.test.tour.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.logging.log4j.util.Strings;
import org.uhafactory.test.tour.region.Name;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DescriptionKeywordResult {
    private String keyword;

    private List<RegionAndProgramCount> programs;

    public static DescriptionKeywordResult create(String keyword, List<RegionAndProgramCount> result) {
        return new DescriptionKeywordResult(keyword, result);
    }

    public static DescriptionKeywordResult empty() {
        return new DescriptionKeywordResult();
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
