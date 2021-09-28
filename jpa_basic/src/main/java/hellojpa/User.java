package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false,updatable = false)
    private Team team;

    @OneToOne @JoinColumn(name = "LOCKER_ID")
    private Locker locker;
}
