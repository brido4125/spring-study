package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            //비영속성 상태
            //영속 상태 => managed, DB에 저장되지 않고 1차 캐시에 저장. 이후 commit 시 DB에 저장
            Member member = em.find(Member.class, 150L);
            member.setName("대박이");
            em.detach(member);
            Member member2 = em.find(Member.class, 150L);
            //1차 캐쉬에서 검색 후 반환!
            em.flush();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
