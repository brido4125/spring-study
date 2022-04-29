package practice;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import practice.domain.*;

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

            Member member = new Member();
            member.setName("brido");
            em.persist(member);

            Member member2 = new Member();
            member2.setName("brido2");
            em.persist(member2);

            em.flush();
            em.clear();

            Member findProxyMember = em.getReference(Member.class, member2.getId());

            System.out.println("findProxyMember is Loaded = " + emf.getPersistenceUnitUtil().isLoaded(findProxyMember));
            Hibernate.initialize(findProxyMember);
            System.out.println("findProxyMember is Loaded = " + emf.getPersistenceUnitUtil().isLoaded(findProxyMember));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }
}
