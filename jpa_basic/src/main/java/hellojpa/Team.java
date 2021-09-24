package hellojpa;

import hellojpa.domain.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Team {
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    /*
    * 양방향 연관 관계 매핑하기!
    * mappedBy :
    * 현재 매핑하려는 객체(여기서는 Team)의
    * 상대 객체(여기서는 User)와 연결되어 있는 객체 이름 넣어주기
    * */
    @OneToMany(mappedBy = "team")
    private List<User> userList = new ArrayList<>();

    // 연관관계 편의 메서드 생성 => 항상 양쪽에 값을 설정하는 것이 가능!
    // 양쪽 중 한쪽에 선택 해서 사용하기
    /*public void addMember(User user) {
        user.setTeam(this);
        userList.add(user);
    }*/
}
