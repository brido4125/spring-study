package hello.itemservice.persistance;

import hello.itemservice.DTO.ItemDTO;
import hello.itemservice.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryItemRepository {

    //HashMap => 다중 스레드에서 동시성 문제 해결하려면 ConcurrentHashMap
    //long도 atomicLong
    private static final Map<Long, Item> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence =  new AtomicLong(0);

    public Item save(Item item) {
        item.setId(sequence.incrementAndGet());
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long id, ItemDTO updateParam) {
        Item item = findById(id);
        item.setName(updateParam.getName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    public List<Item> delete(Long id) {
        store.remove(id);
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
