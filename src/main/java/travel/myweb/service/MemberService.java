package travel.myweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travel.myweb.component.Member;
import travel.myweb.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /*
     * 회원 가입
     * */
    @Transactional
    public Long join(Member member) {
        validateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    public Member login(String username, String password) {
        return memberRepository.findByUsernameAndPassword(username, password);
    }

    private void validateMember(Member member) {
        //EXCEPTION 중복인 경우
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {   // 리스트가 빈게 아니라면 중복이겠지? , 최적화는 카운트로 해서 0이상인걸로 하는게 최적화
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
