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

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    /*
    * 이런식으로 Entity 간의 연관 관계 설정해야함!
    * */
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;



}
