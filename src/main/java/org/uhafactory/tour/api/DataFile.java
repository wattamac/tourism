package org.uhafactory.tour.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataFile {
    private String resource;

    public static DataFile create(String resource) {
        return new DataFile(resource);
    }
}
