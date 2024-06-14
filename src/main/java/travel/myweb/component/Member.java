package travel.myweb.component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String name;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<TravelPost> postList = new ArrayList<>();
}
