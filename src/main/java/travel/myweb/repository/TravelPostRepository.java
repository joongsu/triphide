package travel.myweb.repository;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import travel.myweb.component.TravelPost;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TravelPostRepository {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void save(TravelPost travelPost) {
        em.persist(travelPost);
    }

    public List<TravelPost> findAllById(Long id) {
        return em.createQuery("SELECT tp FROM TravelPost tp WHERE tp.id = :id", TravelPost.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<TravelPost> findAllTravelPost() {
        return em.createQuery("SELECT tp From TravelPost tp", TravelPost.class)
                .getResultList();
    }

    public List<TravelPost> findAllByHide() {
        return em.createQuery("SELECT tp From TravelPost tp WHERE tp.status = 'HIDE'", TravelPost.class)
                .getResultList();
    }

    public TravelPost findOne(Long id) {
            return em.find(TravelPost.class, id);
    }
    public List<TravelPost> findAllByMemberId(Long memberId) {
        return em.createQuery("SELECT tp FROM TravelPost tp WHERE tp.member.id = :memberId", TravelPost.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
