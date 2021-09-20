package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class Member {
    @Id //primary key 설정
    private Long id;
    @Column(name = "name", nullable = false)
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //java 8 이후는 @Temporal 보다 LocalDate, LocalDateTime 사용하면 됨
    private LocalDate testLocalDate;
    private LocalDateTime localDateTime;

    @Lob
    private String description;


}
