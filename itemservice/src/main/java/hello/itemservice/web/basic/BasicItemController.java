package hello.itemservice.web.basic;

import hello.itemservice.DTO.ItemDTO;
import hello.itemservice.domain.Item;
import hello.itemservice.persistance.MemoryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/basic/items")
public class BasicItemController {
    private final MemoryItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable("itemId") long itemId,Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }


    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(
            @RequestParam String itemName,
            @RequestParam Integer price,
            @RequestParam Integer quantity,
            Model model
    ) {
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item,Model model) {
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    //@PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    /**
     * PRG Post Redirect Get 처리
     * @param item
     * @return
     */
    //@PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item save = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", save.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("{itemId}/edit")
    public String editItem(@PathVariable Long itemId,
                           @ModelAttribute ItemDTO item
                           ) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping ("{itemId}/delete")
    public String deleteItem(@PathVariable Long itemId, Model model) {
        List<Item> items = itemRepository.delete(itemId);
        model.addAttribute("items", items);
        return "redirect:/basic/items";
    }

    @GetMapping("/delete")
    public String deleteAll() {
        itemRepository.clearStore();
        return "redirect:/basic/items";
    }

    /**
     * test용 data
     */
    @PostConstruct
    public void init() {

        itemRepository.save(new Item("testItem", 10000, 12));
        itemRepository.save(new Item("testItemB", 20000, 22));
    }
}
