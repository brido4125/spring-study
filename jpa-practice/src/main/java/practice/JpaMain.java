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

            Employee employee = new Employee();
            employee.setName("송예빈");
            employee.setSalary(10000);

            Employee employee1 = new Employee();
            employee.setName("송예빈");
            employee.setSalary(10000);


            Team team = new Team();
            team.setName("영남대");
            team.setId(10L);

            Work work = new Work();
            work.setEmployee(employee);
            work.setTeam(team);

            em.persist(employee1);
            em.persist(team);
            em.persist(work);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
