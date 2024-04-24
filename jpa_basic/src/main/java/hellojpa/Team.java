package hellojpa;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.hibernate.annotations.CascadeType.*;

@Getter
@Setter
@Entity
public class Team extends BaseEntity{
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    @Cascade(ALL)
    private List<User> userList = new ArrayList<>();

}
