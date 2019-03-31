package org.uhafactory.test.tourism.program;

import com.google.common.collect.Lists;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.util.StringUtils;
import org.uhafactory.test.jpa.SequenceId;
import org.uhafactory.test.tourism.region.Region;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PROGRAM")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program implements SequenceId {
    @GenericGenerator(name = "programIdGenerator", strategy = "org.uhafactory.test.jpa.SequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "PROGRAM_SEQ"),
            })
    @GeneratedValue(generator = "programIdGenerator", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "THEME")
    private String theme;

    @Column(name = "SERVICE_AREA")
    private String serviceArea;

    @ManyToMany
    @JoinTable(
            name = "REGION_PROGRAM",
            joinColumns = @JoinColumn(name = "PROGRAM_ID"),
            inverseJoinColumns = @JoinColumn(name = "REG_CODE")
    )
    @Builder.Default
    private List<Region> regions = Lists.newArrayList();

    @Column(name = "DESC", length = 1024)
    private String description;

    @Column(name = "DET_DESC", length = 4096)
    private String detailDescription;

    @Override
    public String createId(Number sequence) {
        if (StringUtils.isEmpty(id)) {
            return String.format("prg%s", sequence);
        }
        return id;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("name", name)
                .append("serviceArea", serviceArea)
                .append("regions", regions)
                .toString();
    }
}
