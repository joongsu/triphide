package travel.myweb.component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TravelPost {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_Id")
    private Member member;

    private String author;
    private String imagePath; // 이미지 파일 경로
    private String title;
    private String content;
    private int likeCount;

    @Enumerated(EnumType.STRING)
    private TravelStatus status;


    // 하나의 post는 여러개의 코멘트를
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
