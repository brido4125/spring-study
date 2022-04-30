package practice;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import practice.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setTeamName("starbucks");
            em.persist(team);

            Team team2 = new Team();
            team2.setTeamName("starbucks2");
            em.persist(team2);

            Member member = new Member();
            member.setName("brido");
            member.setTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setName("brido2");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            System.out.println("======CreateQuery N+1=========");
            List<Member> members = em.createQuery("select m From Member m", Member.class).getResultList();
            System.out.println("===============================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
