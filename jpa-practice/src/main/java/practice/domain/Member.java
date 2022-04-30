package practice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    //Member를 em.find 할 시 Team 엔티티는 Proxy로 조회
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id")

    private Team team;

    //연관관계 편의 메서드는 연관관계 주인 Entity에 하거나 반대쪽에 하던가 선택 필요
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
        /*
        * 추가로 getMembers()에서 빼주는 기능
        * */
    }
}
