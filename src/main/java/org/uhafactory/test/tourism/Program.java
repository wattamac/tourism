package org.uhafactory.test.tourism;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PROGRAM")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program {
    @Id
    @Column
    private Long id;

    @Column(name = "NAME")
    private String name;

//    @Column(name = "THEME")
    @Transient
    private List<String> theme;

//    @Column(name = "REGION")
    @Transient
    private Region region;

    @Column(name = "DESC")
    private String descrition;

    @Column(name = "DET_DESC")
    private String detailDescription;

}
