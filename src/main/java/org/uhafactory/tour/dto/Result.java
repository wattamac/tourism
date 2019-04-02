package org.uhafactory.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class Result {
    private Result() {
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class KeywordAndCountDto {
        private String keyword;
        private int count;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class ProgramIdDto {
        private String program;
    }
}
