package practice;

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
            team.setTeamName("Pizza");
            em.persist(team);

            Member member = new Member();
            member.setName("brido");
            member.changeTeam(team);
            em.persist(member);


            Team findTeam = em.find(Team.class, team.getId());
            List<Member> findMembers = findTeam.getMembers();

            System.out.println("====================");
            for (Member findMember : findMembers) {
                System.out.println("findMember.getName() = " + findMember.getName());
            }
            System.out.println("findMembers.size() = " + findMembers.size());
            System.out.println("====================");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
