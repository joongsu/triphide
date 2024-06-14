package travel.myweb.component;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;
    @OneToOne
    @JoinColumn(name = "author_id")
    private Member author;

    @ManyToOne
    @JoinColumn(name = "post_id") // 하나의 게시글은 여러개의 comments
    private TravelPost post;


}
