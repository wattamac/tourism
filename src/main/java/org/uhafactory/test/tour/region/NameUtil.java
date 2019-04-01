package org.uhafactory.test.tour.region;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NameUtil {
    private static final String NAME_PATTERN =
            "\\s|,|~|(특별시)($|\\s|,)|(광역시)($|\\s|,)|[도구군동시]($|\\s|,)|(국립공원)($|\\s|,)";

    NameUtil() {
    }

    public static List<String> extractName(String wholeName) {
        return extract(wholeName).collect(Collectors.toList());
    }

    private static Stream<String> extract(String wholeName) {
        if(StringUtils.isBlank(wholeName)) {
            return Stream.empty();
        }
        return Arrays.stream(wholeName.split(NAME_PATTERN))
                .filter(StringUtils::isNotBlank);
    }

    public static String removePostfix(String wholeName) {
        return extract(wholeName).findFirst()
                .orElse(Strings.EMPTY);
    }

}
