package whz.pti.eva.pizza_projekt.security.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.domain.CustomerRepository;
import whz.pti.eva.pizza_projekt.Customer.service.CustomerService;
import whz.pti.eva.pizza_projekt.security.domain.Role;
import whz.pti.eva.pizza_projekt.security.domain.User;
import whz.pti.eva.pizza_projekt.security.domain.UserCreateForm;
import whz.pti.eva.pizza_projekt.security.domain.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByLoginName(String loginName) {
        LOGGER.debug("Getting user by email={}", loginName.replaceFirst("@.*", "@***"));
        return userRepository.findOneByLoginName(loginName);
    }

//    @Override
//    public boolean existsByNickname(String nickname) {
//        return userRepository.existsByNickname(nickname);
//    }

    @Override
    public boolean existsByLoginName(String loginName) {
        return userRepository.existsByloginName(loginName);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll(new Sort("loginName"));
    }

    @Transactional
    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setLoginName(form.getLoginName());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        userRepository.save(user);
       Customer customer = new Customer();
       customer.setId(user.getId());
       customer.setFirstName(user.getFirstName());
       customer.setLastName(user.getLastName());
       customer.setLoginName(user.getLoginName());
        customerRepository.save(customer);
        return user;

    }

    @Transactional
    @Override
    public void editUser(String firstName, String lastName, String loginName, String passwordHash) {
     User user =   userRepository.findOneByLoginName(loginName);
     if(user.getRole().equals(Role.ADMIN)){
         user.setFirstName(firstName);
         user.setLastName(lastName);
         user.setPasswordHash(passwordHash);
         userRepository.save(user);
     } else{
         user.setFirstName(firstName);
         user.setLastName(lastName);
         user.setPasswordHash(passwordHash);
         userRepository.save(user);
         Customer customer = customerRepository.findByLoginName(loginName);
         customer.setFirstName(user.getFirstName());
         customer.setLastName(user.getLastName());
     }
    }
}
