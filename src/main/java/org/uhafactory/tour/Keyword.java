package org.uhafactory.tour;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Keyword {
    private final Pattern pattern;

    public Keyword(String keyword) {
        if(StringUtils.isBlank(keyword)){
            throw new IllegalArgumentException("empty keyword");
        }
        pattern = Pattern.compile("(" + keyword + ")");
    }

    public static Keyword create(String keyword) {
        return new Keyword(keyword);
    }

    public int matchCount(String text) {
        if(StringUtils.isBlank(text)) {
            return 0;
        }

        Matcher matcher = pattern.matcher(text);
        int matchCount = 0;
        while (matcher.find()) {
            matchCount++;
        }
        return matchCount;
    }
}
