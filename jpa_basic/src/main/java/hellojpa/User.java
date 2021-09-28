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
    //요까지만 연관관계 설정하면 단방향 연관관계
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    /*
    * 연관관계 편의 메서드 생성 => 항상 양쪽에 값을 설정하는 것이 가능!
    * */
    public void changeTeam(Team team) {
        this.team = team;
        team.getUserList().add(this);
    }
}
