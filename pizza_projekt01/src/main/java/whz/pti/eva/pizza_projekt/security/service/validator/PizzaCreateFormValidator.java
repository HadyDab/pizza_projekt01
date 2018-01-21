package whz.pti.eva.pizza_projekt.security.service.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import whz.pti.eva.pizza_projekt.Customer.service.PizzaService;
import whz.pti.eva.pizza_projekt.security.domain.PizzaCreateForm;

@Component
public class PizzaCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PizzaCreateFormValidator.class);

    private PizzaService pizzaService;


    @Autowired
    public PizzaCreateFormValidator(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }



    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PizzaCreateForm.class);
    }


    @Override
    public void validate(Object target, Errors errors) {
        PizzaCreateForm form = (PizzaCreateForm) target;
        validatePizzaType(errors,form);
    }

    private void validatePizzaType(Errors errors, PizzaCreateForm form){

        if(pizzaService.existsByName(form.getName())){
            errors.reject("pizzaname","Pizza with this name already exists");
        }
    }


}
