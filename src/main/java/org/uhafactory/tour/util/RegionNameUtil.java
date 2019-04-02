package org.uhafactory.tour.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegionNameUtil {
    private static final String NAME_PATTERN =
            "\\s|,|~|(특별시)($|\\s|,)|(광역시)($|\\s|,)|[도구군동시]($|\\s|,)|(국립공원)($|\\s|,)";

    private static final Map<String, String> SPECIAL_CASE = Maps.newHashMap();

    static {
        SPECIAL_CASE.put("경상남", "경남");
        SPECIAL_CASE.put("경상북", "경북");
        SPECIAL_CASE.put("충청북", "충북");
        SPECIAL_CASE.put("충청남", "충남");
        SPECIAL_CASE.put("전라북", "전북");
        SPECIAL_CASE.put("전라남", "전남");
    }

    private RegionNameUtil() {
    }

    public static List<String> extractName(String wholeName) {
        return extract(wholeName).collect(Collectors.toList());
    }


    private static Stream<String> extract(String wholeName) {
        if (StringUtils.isBlank(wholeName)) {
            return Stream.empty();
        }
        return Arrays.stream(wholeName.split(NAME_PATTERN))
                .filter(StringUtils::isNotBlank)
                .map(RegionNameUtil::specialCharRemove);
    }

    private static String specialCharRemove(String name) {
        String special = SPECIAL_CASE.get(name);
        return special != null ? special : name;
    }


    public static String removePostfix(String wholeName) {
        return extract(wholeName).findFirst()
                .orElse(Strings.EMPTY);
    }

}
