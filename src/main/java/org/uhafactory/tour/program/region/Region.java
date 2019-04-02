package org.uhafactory.tour.program.region;

import com.google.common.collect.Lists;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.util.StringUtils;
import org.uhafactory.tour.jpa.SequenceId;
import org.uhafactory.tour.program.Program;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "code")
@Entity
@Table(name = "REGION")
public class Region implements SequenceId {
    @GenericGenerator(name = "regionIdGenerator", strategy = "org.uhafactory.tour.jpa.SequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "REG_SEQ"),
            })
    @GeneratedValue(generator = "regionIdGenerator", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Override
    public String createId(Number sequence) {
        // CREATE SEQUENCE IF NOT EXISTS REG_SEQ START WITH 1000
        if (StringUtils.isEmpty(code)) {
            return String.format("reg%s", sequence);
        }
        return code;
    }

    @ElementCollection
    @CollectionTable(name = "REG_KEYWORD")
    private List<String> keyword = Lists.newArrayList();

    @ManyToMany(mappedBy = "regions")
    private List<Program> programs = Lists.newArrayList();

    public static Region create(String name, List<String> keyword) {
        Region region = new Region();
        region.setName(name);
        region.setKeyword(keyword);
        return region;
    }

    public static Region create(String name) {
        return Region.create(name, Collections.emptyList());
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("code", code)
                .append("name", name)
                .toString();
    }
}
