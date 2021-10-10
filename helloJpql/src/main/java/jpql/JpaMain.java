package jpql;

import javax.persistence.*;
import java.util.List;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("hello");
            member.setAge(10);
            em.persist(member);

            TypedQuery<Member> member_m = em.createQuery("select m from Member m", Member.class);
            List<Member> resultList = member_m.getResultList();
            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }
            Query query = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query.setParameter("username", "hello");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
