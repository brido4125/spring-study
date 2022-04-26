package practice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "MBR")
public class Member {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;

    @Enumerated
    private RoleType roleType;
}
