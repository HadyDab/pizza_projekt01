package whz.pti.eva.pizza_projekt.security.service.currentuser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_projekt.security.domain.CurrentUser;
import whz.pti.eva.pizza_projekt.security.domain.User;
import whz.pti.eva.pizza_projekt.security.service.user.UserService;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);
    private UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String loginName) throws UsernameNotFoundException {
        LOGGER.debug("Authenticating user with loginName={}");
        User user = userService.getUserByLoginName(loginName);
        if (user == null) new UsernameNotFoundException("User with loginName= " + loginName + " cannot be not found");
        return new CurrentUser(user);
    }

}
