package travel.myweb.initdata;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import travel.myweb.component.Member;
import travel.myweb.component.TravelPost;
import travel.myweb.component.TravelStatus;
import travel.myweb.repository.MemberRepository;
import travel.myweb.repository.TravelPostRepository;


@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final TravelPostRepository travelPostRepository;

    @Transactional
    public void run(String... args) throws Exception {
        // 기본 멤버 생성
        Member member1 = new Member();
        member1.setUsername("user1");
        member1.setName("User One");
        member1.setPassword("password");

        Member member2 = new Member();
        member2.setUsername("user2");
        member2.setName("User Two");
        member2.setPassword("password");

        memberRepository.save(member1);
        memberRepository.save(member2);

        // 기본 게시글 생성
        TravelPost post1 = new TravelPost();
        post1.setAuthor("User One");
        post1.setTitle("First Post");
        post1.setContent("This is the content of the first post.");
        post1.setLikeCount(10);
        post1.setStatus(TravelStatus.HIDE);
        post1.setMember(member1);
        post1.setImagePath("/images/default1.jpg");

        TravelPost post2 = new TravelPost();
        post2.setAuthor("User Two");
        post2.setTitle("Second Post");
        post2.setContent("This is the content of the second post.");
        post2.setLikeCount(5);
        post2.setStatus(TravelStatus.NORMAL);
        post2.setMember(member2);
        post2.setImagePath("/images/default2.jpg");

        travelPostRepository.save(post1);
        travelPostRepository.save(post2);
    }
}