package practice;

import practice.domain.Employee;
import practice.domain.Team;
import practice.domain.Work;

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

            Team team = em.find(Team.class, 10L);
            Employee employee = em.find(Employee.class, 2L);
            Work work = new Work();
            work.setId(1L);
            work.setTeam(team);
            work.setEmployee(employee);
            em.persist(work);

            System.out.println("employee.getName() = " + employee.getName());
            System.out.println("team.getName() = " + team.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
