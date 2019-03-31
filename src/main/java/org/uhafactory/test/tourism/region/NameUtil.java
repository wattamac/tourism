package org.uhafactory.test.tourism.region;

import com.querydsl.core.util.ArrayUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.List;

public class NameUtil {
    private static final String NAME_PATTERN = "[\\s,|도|시|군|구|~|특별시|광역시|]+";

    NameUtil() {
    }

    public static List<String> extractName(String wholeName) {
        return Arrays.asList(wholeName.split(NAME_PATTERN));
    }

    public static String removePostfix(String wholeName) {
        String names[] = wholeName.split(NAME_PATTERN);
        if(ArrayUtils.isEmpty(names)){
            return Strings.EMPTY;
        }
        return names[0];
    }

}
