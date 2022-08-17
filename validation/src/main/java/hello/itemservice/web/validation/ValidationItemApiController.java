package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {
    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveDto dto,
                          BindingResult bindingResult) {
        log.info("API");
        //특정 필드값 검증이 아닌 복합 필드 검증 (가격 + 수량) => Object Error
        if (dto.getPrice() != null && dto.getQuantity() != null) {
            int resultPrice = dto.getPrice() * dto.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
            }
        }
        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생 = {}",bindingResult);
            log.info("global errors = {}",bindingResult.getGlobalError());
            return bindingResult.getAllErrors();
        }
        log.info("성공 로직");
        return dto;
    }
}
