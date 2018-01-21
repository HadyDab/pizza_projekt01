package whz.pti.eva.pizza_projekt.security.service.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import whz.pti.eva.pizza_projekt.security.domain.UserCreateForm;
import whz.pti.eva.pizza_projekt.security.service.user.UserService;


/**
 * Created by wo on 12.11.2017.
 */

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserCreateForm form = (UserCreateForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password", "unterschiedliche passwörter eingegeben! vertippt?");
        }
    }

    private void validateEmail(Errors errors, UserCreateForm form) {
//        if (userService.existsByNickname(form.getLoginName())) {
//            errors.reject("nickname", "nutzer mit diesem nickname existiert bereits !!!");
//        }
//        else
        if (userService.existsByLoginName(form.getLoginName())) {
            errors.reject("email", "nutzer mit dieser email existiert bereits !!!");
        }
    }
}
