package jpabook.jpashop.service;

/*
*  변경 감지와 병합을 알아보기 위한 TEST
*  */

import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void update() throws Exception {
        Book book = em.find(Book.class, 1L);
        //TX
        //변경감지 == dirty checking
        //given

        //when

        //then
    }
}
