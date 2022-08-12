package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }


    /*
    * @ModelAttribute로 Item을 model에 심어주었기 때문에
    * 검증에서 걸러져서 addForm으로 반환 될때, 기존에 입력했던 Item의 값이 유지된채로 Form이 반환됩니다.!
    * */
    //PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        //검증 로직 만들기
        if (!StringUtils.hasText(item.getItemName())) { //itemName이 들어오지 않으면
            bindingResult.addError(new FieldError("item","itemName","상품 이름은 필수입니다."));
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.addError(new FieldError("item","price","가격은 1,000원 ~ 1,000,000 사이의 값이어야 합니다."));

        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999 || item.getQuantity() < 0) {
            bindingResult.addError(new FieldError("item","quantity","수량은 0보다 크고 9999개 보다는 커야 합니다."));

        }
        //특정 필드값 검증이 아닌 복합 필드 검증 (가격 + 수량)
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item","가격 * 주문 수량은 10,000이상 이어야합니다. 현재 값 = " + resultPrice));
            }
        }

        //검증에 실패하면 다시 입력 Form으로 이동
        if (bindingResult.hasErrors()) { //map에 요소가 있으면,즉 에러가 있으면
            log.info("error = {}",bindingResult);
            //검증을 통과하지 못하면 입력 Form으로 보내기
            return "validation/v2/addForm";
        }

        //성공 Logic
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    /*
    * Type Error가 처리되는 Flow
    * Controller 타기 전에 스프링에서 바인딩 에러 발생 시킴, 그래서 Controller가 호출 되었을 경우에는 이미 BindingResult에 에러가 존재함!
    * */
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.info("bindingRes = {}",bindingResult);

        //검증 로직 만들기
        if (!StringUtils.hasText(item.getItemName())) { //itemName이 들어오지 않으면
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품 이름은 필수입니다."));
        }
        log.info("item.getPrice = {}",item.getPrice() );
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            log.info("here-ValidationItemControllerV2.addItemV2");
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1,000원 ~ 1,000,000 사이의 값이어야 합니다."));

        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999 || item.getQuantity() < 0) {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, null, null, "수량은 0보다 크고 9999개 보다는 커야 합니다."));

        }
        //특정 필드값 검증이 아닌 복합 필드 검증 (가격 + 수량)
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item",null,null,"가격 * 주문 수량은 10,000이상 이어야합니다. 현재 값 = " + resultPrice));
            }
        }

        //검증에 실패하면 다시 입력 Form으로 이동
        if (bindingResult.hasErrors()) { //map에 요소가 있으면,즉 에러가 있으면
            log.info("error = {}",bindingResult);
            //검증을 통과하지 못하면 입력 Form으로 보내기
            return "validation/v2/addForm";
        }

        //성공 Logic
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        log.info("objectName = {} ", bindingResult.getObjectName());
        log.info("target = {} ", bindingResult.getTarget());

        //검증 로직 만들기
        if (!StringUtils.hasText(item.getItemName())) { //itemName이 들어오지 않으면
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
        }
        log.info("item.getPrice = {}",item.getPrice() );
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            log.info("here-ValidationItemControllerV2.addItemV2");
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1000,1000000}, null));

        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999 || item.getQuantity() < 0) {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999}, null));

        }
        //특정 필드값 검증이 아닌 복합 필드 검증 (가격 + 수량)
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice},"가격 * 주문 수량은 10,000이상 이어야합니다. 현재 값 = " + resultPrice));
            }
        }

        //검증에 실패하면 다시 입력 Form으로 이동
        if (bindingResult.hasErrors()) { //map에 요소가 있으면,즉 에러가 있으면
            log.info("error = {}",bindingResult);
            //검증을 통과하지 못하면 입력 Form으로 보내기
            return "validation/v2/addForm";
        }

        //성공 Logic
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {


        //검증 로직 만들기
        if (!StringUtils.hasText(item.getItemName())) { //itemName이 들어오지 않으면
            bindingResult.rejectValue("itemName","required");
        }
        log.info("item.getPrice = {}",item.getPrice() );
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            bindingResult.rejectValue("price","range",new Object[]{1000,1000000},null);
        }
        if (item.getQuantity() == null || item.getQuantity() >= 9999 || item.getQuantity() < 0) {
            bindingResult.rejectValue("quantity","max",new Object[]{9999},null);
        }
        //특정 필드값 검증이 아닌 복합 필드 검증 (가격 + 수량)
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }

        //검증에 실패하면 다시 입력 Form으로 이동
        if (bindingResult.hasErrors()) { //map에 요소가 있으면,즉 에러가 있으면
            log.info("error = {}",bindingResult);
            //검증을 통과하지 못하면 입력 Form으로 보내기
            return "validation/v2/addForm";
        }

        //성공 Logic
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

