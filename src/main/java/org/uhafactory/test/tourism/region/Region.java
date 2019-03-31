package org.uhafactory.test.tourism.region;

import com.google.common.collect.Lists;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.util.StringUtils;
import org.uhafactory.test.jpa.SequenceId;
import org.uhafactory.test.tourism.program.Program;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "code")
@Entity
@Table(name = "REGION")
public class Region implements SequenceId {
    @GenericGenerator(name = "regionIdGenerator", strategy = "org.uhafactory.test.jpa.SequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "REG_SEQ"),
            })
    @GeneratedValue(generator = "regionIdGenerator", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "CODE")
    private String code;



    @Column(name = "NAME")
    private Name name;

    @Override
    public String createId(Number sequence) {
        // CREATE SEQUENCE IF NOT EXISTS REG_SEQ START WITH 1000
        if (StringUtils.isEmpty(code)) {
            return String.format("reg%s", sequence);
        }
        return code;
    }

//    @ElementCollection
//    @CollectionTable(name = "REG_KEYWORD")
//    private List<String> keyword = Lists.newArrayList();

    @ManyToMany(mappedBy = "regions")
    private List<Program> programs = Lists.newArrayList();

    public static Region create(String wholeName) {
        Region region = new Region();
        region.setName(Name.create(wholeName));
        return region;
    }

    public static Region create(String wholeName, String shortName) {
        Region region = create(wholeName);
        region.getName().setShortName(shortName);
        return region;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = Name.create(name);
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("code", code)
                .append("name", name)
                .toString();
    }
}
