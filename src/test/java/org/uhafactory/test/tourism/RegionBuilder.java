package org.uhafactory.test.tourism;

import org.uhafactory.test.AbstractBuilder;

public class RegionBuilder extends AbstractBuilder<Region> {
    private String code;
    private String name;
    private RegionBuilder region;
    private String wholeName;

    public static RegionBuilder anRegion() {
        return new RegionBuilder();
    }

    RegionBuilder withCode(String code) {
        this.code = code;
        return this;
    }
}
