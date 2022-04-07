package jpabook.jpashop2.repository;

import jpabook.jpashop2.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager entityManager;

    public void save(Member member) {
        entityManager.persist(member);
    }

    public Member findById(Long id) {
        return entityManager.find(Member.class,id);
    }

    public List<Member> findAll() {
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return entityManager.createQuery("select m from Member m where m.username = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
