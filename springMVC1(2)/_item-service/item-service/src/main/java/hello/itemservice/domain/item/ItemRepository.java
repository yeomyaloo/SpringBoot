package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {
    //실무 멀티쓰레드 환경에서는 HashMap이나 long 타입이 아닌 다른 타입을 사용한다 ~
    private static final Map<Long, Item> store = new HashMap<>(); //실제로는 HashMap 사용 X
    private static long sequence = 0L; // static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }
    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    //실제로 프로젝트를 진행할 때 id를 사용하지 않기 때문에 id를 제외한 나머지 DTO를 만드는 게 설계상 명확한 것이 낫다.
    public void updateItem(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore(){
        store.clear();
    }
}
