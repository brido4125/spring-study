package hello.itemservice.persistance;

import hello.itemservice.DTO.ItemDTO;
import hello.itemservice.domain.Item;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    //HashMap => 다중 스레드에서 동시성 문제 해결하려면 ConcurrentHashMap
    //long도 atomicLong
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // TODO: 3/18/22 updateParam type => ItemDTO
    public void update(Long id, ItemDTO updateParam) {
        Item item = findById(id);
        item.setName(updateParam.getName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
