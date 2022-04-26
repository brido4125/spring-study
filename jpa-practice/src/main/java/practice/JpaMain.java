package practice;

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
            member.setId(130L);
            member.setName("brido2");
            member.setRoleType(RoleType.SUPER);

            //1차 캐시에 저장됨
            System.out.println("Before");
            em.persist(member);
            System.out.println("After");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
