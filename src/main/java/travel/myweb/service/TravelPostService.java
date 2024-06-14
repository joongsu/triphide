package travel.myweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import travel.myweb.component.TravelPost;
import travel.myweb.repository.TravelPostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TravelPostService {
    private final TravelPostRepository travelPostRepository;

    @Transactional
    public void createPost(TravelPost travelPost) {
        travelPostRepository.save(travelPost);
    }

    public List<TravelPost> getAllPosts() {
        return travelPostRepository.findAllTravelPost();
    }
    public List<TravelPost> getAllHiddenPosts() {
        return travelPostRepository.findAllByHide();
    }
    public TravelPost findPostById(Long id) {
        return travelPostRepository.findOne(id);
    }

    public List<TravelPost> findPostsByMemberId(Long memberId) {
        return travelPostRepository.findAllByMemberId(memberId);
    }
}
