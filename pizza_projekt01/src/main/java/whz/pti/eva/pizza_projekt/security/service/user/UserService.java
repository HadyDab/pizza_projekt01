package whz.pti.eva.pizza_projekt.security.service.user;



import whz.pti.eva.pizza_projekt.security.domain.User;
import whz.pti.eva.pizza_projekt.security.domain.UserCreateForm;

import java.util.Collection;

public interface UserService {

    User getUserById(long id);

    User getUserByLoginName(String loginName);

//    boolean existsByNickname(String nickname);

    boolean existsByLoginName(String loginName);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

    void editUser(String firstName, String lastName, String loginName, String passwordHash);
}
