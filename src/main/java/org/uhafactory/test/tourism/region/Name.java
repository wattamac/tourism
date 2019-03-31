package org.uhafactory.test.tourism.region;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.style.ToStringCreator;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    private static final String NAME_PATTERN = "[\\s,|도|시|군|~|특별시|광역시|]+";

    @Column(name = "LEVEL")
    private int level;

    @Column(name = "NAME")
    private String name; //시, 구, 군

    @Column(name = "SHT_NAME")
    private String shortName;

    @Column(name = "WHOLE_NAME")
    private String wholeName;

    public static Name create(String wholeName) {
        if (StringUtils.isBlank(wholeName)) {
            throw new IllegalArgumentException("invalid name ...");
        }
        Name result = new Name();
        result.setWholeName(wholeName);

        String[] names = wholeName.split(" ");
        result.setLevel(names.length);
        String name = names[names.length - 1];
        result.setName(name);
        result.setShortName(NameUtil.removePostfix(name));
        return result;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("level", level)
                .append("name", name)
                .append("shortName", shortName)
                .append("wholeName", wholeName).toString();
    }

}
