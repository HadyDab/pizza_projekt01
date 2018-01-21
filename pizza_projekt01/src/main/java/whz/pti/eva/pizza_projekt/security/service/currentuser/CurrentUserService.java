package whz.pti.eva.pizza_projekt.security.service.currentuser;


import whz.pti.eva.pizza_projekt.security.domain.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
