package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Member {
    /*
     * @Entity => 기본 생성자 필수
     */
    public Member() {
    }
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Id //primary key 설정
    private Long id;
    private String name;
}
