package hello.itemservice.persistance;

import hello.itemservice.DTO.ItemDTO;
import hello.itemservice.domain.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    MemoryItemRepository itemRepository = new MemoryItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("book",50000,10);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }


    @Test
    void findAll() {
        //given
        Item item1 = new Item("book",50000,10);
        Item item2 = new Item("hat",5000,10);
        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> items = itemRepository.findAll();
        //then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void update() {
        //given
        Item item = new Item("book",50000,10);
        Item savedItem = itemRepository.save(item);
        Long id = savedItem.getId();
        //when
        ItemDTO updateParam = new ItemDTO("bookUpdate", 30000, 100);
        itemRepository.update(id,updateParam);
        //then
        Item findItem = itemRepository.findById(id);
        assertThat(findItem.getName()).isEqualTo(updateParam.getName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

    @Test
    void delete() {
        //given
        Item item1 = new Item("book",50000,10);
        Item item2 = new Item("hat",5000,10);
        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> delete = itemRepository.delete(item1.getId());
        //then
        assertThat(delete.size()).isEqualTo(1);
    }
}