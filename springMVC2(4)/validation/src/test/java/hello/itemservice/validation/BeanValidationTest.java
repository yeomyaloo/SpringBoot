package hello.itemservice.validation;

import hello.itemservice.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.validation.annotation.Validated;

import javax.validation.*;
import java.util.Set;

public class BeanValidationTest {
    @Test
    void beanValidation(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();


        Item item = new Item();
        item.setItemName(" "); //공백 넣으면 오류
        item.setPrice(0); //범위 오류
        item.setQuantity(10000);

        Set<ConstraintViolation<Item>> violations = validator.validate(item);
        for (ConstraintViolation<Item> violation : violations) {
            System.out.println("violation=" + violation);
            System.out.println("violation.message=" + violation.getMessage());
        }
    }
}
