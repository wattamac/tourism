package org.uhafactory.test.tourism;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.StringUtils;
import org.uhafactory.test.jpa.SequenceId;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REGION")
public class Region implements SequenceId {
    @GenericGenerator(name = "regionIdGenerator", strategy = "org.uhafactory.test.jpa.SequenceIdGenerator",
            parameters = {
                @org.hibernate.annotations.Parameter(name = "sequence_name", value = "REG_SEQ"),
    })
    @GeneratedValue(generator = "regionIdGenerator", strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "PARENT")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Region parent;

    @Column(name = "wholeName")
    private String wholeName;

    @Override
    public String createId(Number sequence) {
        // CREATE SEQUENCE IF NOT EXISTS REG_SEQ START WITH 1000
        if(StringUtils.isEmpty(code)) {
            return String.format("reg%s", sequence);
        }
        return code;
    }
}
