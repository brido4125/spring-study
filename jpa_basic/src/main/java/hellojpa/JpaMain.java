package hellojpa;

import hellojpa.domain.Book;
import hellojpa.domain.Member;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;


public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            //flush -> commit 또는 query 날라갈때 발생
            List<User> resultList = em.createQuery("select m from User m where m.username like '%kim%'", User.class).getResultList();

            //Criteria 사용하기
            // ->  1) 자바 표준 스펙이라 컴파일 오류 잡기 편함 2) 동적쿼리짜기가 윗 방식보다 편함
            // 근데 queryDSL 사용!
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);

            Root<User> u = query.from(User.class);

            CriteriaQuery<User> criteriaQuery = query.select(u).where(cb.equal(u.get("username"), "kim"));
            List<User> resultList1 = em.createQuery(criteriaQuery).getResultList();

            //nativeQuery 작성
            List resultList2 = em.createNativeQuery("select *  from USER").getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }

}
