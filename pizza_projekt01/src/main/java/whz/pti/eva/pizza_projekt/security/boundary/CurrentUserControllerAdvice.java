package whz.pti.eva.pizza_projekt.security.boundary;

//import org.springframework.security.core.Authentication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import whz.pti.eva.pizza_projekt.security.domain.CurrentUser;

@ControllerAdvice
public class CurrentUserControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserControllerAdvice.class);

    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUser(Authentication authentication) {
//        LOGGER.debug("authentication = " + authentication);
        return (authentication == null) ? null : (CurrentUser) authentication.getPrincipal();
    }

}
