package jpabook.jpashop2.repository;

import jpabook.jpashop2.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager entityManager;

    //persist vs merge
    public void save(Item item) {
        //Item 객체는 DB에 저장이되어야 GeneratValue와 함께 ID값이 생긴다.
        //그래서 null이면 persist
        if (item.getId() == null) {
            entityManager.persist(item);
        } else {
            entityManager.merge(item);
        }
    }

    public Item findById(Long id) {
       return entityManager.find(Item.class, id);
    }

    public List<Item> findAll() {
       return entityManager
               .createQuery("select i from Item i", Item.class)
               .getResultList();
    }
}
