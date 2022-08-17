package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.SaveCheck;
import hello.itemservice.domain.item.UpdateCheck;
import hello.itemservice.web.validation.form.ItemSaveDto;
import hello.itemservice.web.validation.form.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/validation/v4/items")
@RequiredArgsConstructor
public class ValidationItemControllerV4 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v4/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v4/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveDto form,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        //특정 필드값 검증이 아닌 복합 필드 검증 (가격 + 수량)
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }
        //검증에 실패하면 다시 입력 Form으로 이동
        if (bindingResult.hasErrors()) { //map에 요소가 있으면,즉 에러가 있으면
            log.info("error = {}",bindingResult);
            //검증을 통과하지 못하면 입력 Form으로 보내기
            return "validation/v4/addForm";
        }
        //성공 Logic
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v4/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@Validated @ModelAttribute("item") ItemUpdateDto dto,
                       BindingResult bindingResult,
                       @PathVariable Long itemId) {
        //특정 필드값 검증이 아닌 복합 필드 검증 (가격 + 수량)
        if (dto.getPrice() != null && dto.getQuantity() != null) {
            int resultPrice = dto.getPrice() * dto.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }
        if (bindingResult.hasErrors()) { //BindingResult에 요소가 있으면,즉 에러가 있으면
            log.info("error = {}",bindingResult);
            log.info("global errors = {}",bindingResult.getGlobalError());
            //검증을 통과하지 못하면 입력 Form으로 보내기
            return "validation/v4/editForm";
        }
        Item item = new Item();
        item.setItemName(dto.getItemName());
        item.setPrice(dto.getPrice());
        item.setQuantity(dto.getQuantity());
        itemRepository.update(itemId, item);
        return "redirect:/validation/v4/items/{itemId}";
    }
}

