package org.uhafactory.tour.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "loginId")
@Builder
@Entity
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "PASSWORD", nullable = false)
    private String password;
}
