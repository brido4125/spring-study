package hello.itemservice.validation;

import hello.itemservice.domain.item.Item;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Locale;
import java.util.Set;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.KOREA;

public class BeanValidationTest {

    @Test
    void beanValidation() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setItemName(" ");
        item.setPrice(0);
        item.setQuantity(10000);

        Set<ConstraintViolation<Item>> validate = validator.validate(item);
        for (ConstraintViolation<Item> itemConstraintViolation : validate) {
            System.out.println("itemConstraintViolation.getMessage() = " + itemConstraintViolation.getMessage());
        }
    }

    /*
    * "널이어서는 안됩니다" => Locale Default가 KOREA면 자동으로 등록됨
    * javax.validation.constraints.NotNull.message
    * */
    @Test
    void nullErrorMsg() {
        Locale.setDefault(KOREA);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        for (ConstraintViolation<Item> violation : violations) {
            System.out.println("violation = " + violation);
        }

    }

}
