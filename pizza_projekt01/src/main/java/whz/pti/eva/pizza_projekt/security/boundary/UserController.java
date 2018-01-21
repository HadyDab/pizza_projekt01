package whz.pti.eva.pizza_projekt.security.boundary;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import whz.pti.eva.pizza_projekt.Customer.domain.Address;
import whz.pti.eva.pizza_projekt.Customer.domain.Customer;
import whz.pti.eva.pizza_projekt.Customer.service.CustomerService;
import whz.pti.eva.pizza_projekt.security.domain.CurrentUser;
import whz.pti.eva.pizza_projekt.security.domain.User;
import whz.pti.eva.pizza_projekt.security.domain.UserCreateForm;
import whz.pti.eva.pizza_projekt.security.service.user.UserService;
import whz.pti.eva.pizza_projekt.security.service.validator.UserCreateFormValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private CustomerService customerService;
    private UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService,CustomerService customerService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.customerService = customerService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("myform")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping("/users")
    public String getUsersPage(Model model) {
        LOGGER.info("Getting users page");
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }


    @RequestMapping(value ="/register",method = RequestMethod.GET)
    public String getRegisterPage(Model model){
        model.addAttribute("myform", new UserCreateForm());
        return "new_user_create";
    }

    @RequestMapping(value = "/new/users/create")
    public String handleNewUser(@Valid @ModelAttribute("myform") UserCreateForm form,BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            return "user_create";
        }
        userService.create(form);
        return "redirect:/login";
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping(value = "/users/{id}", method = {RequestMethod.POST,RequestMethod.GET})
    public String getUserPage(@PathVariable Long id, Model model) {
        LOGGER.debug("Getting user page for user= " + id);
        User user = userService.getUserById(id);
        if (user != null) model.addAttribute("user", user);
        else model.addAttribute("user", new NoSuchElementException(String.format("User=%s not found", id)));
        return "user";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/users/managed", method = {RequestMethod.POST,RequestMethod.GET})
    public String getUserManegedPage(Model model) {
        LOGGER.debug("Getting user create form");
        model.addAttribute("myform", new UserCreateForm());
        model.addAttribute("users", userService.getAllUsers());
        return "user_create";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("myform") UserCreateForm form, BindingResult bindingResult, Model model) {
        LOGGER.info("Processing user create form= " + form + " bindingResult= " + bindingResult);
        model.addAttribute("users", userService.getAllUsers());
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getGlobalError().getDefaultMessage());
            return "user_create";
        }
            userService.create(form);
        return "redirect:/users/managed";
    }

    @RequestMapping(value = "/start_edit_User_data", method = RequestMethod.GET)
    public String startEditCustomerData(Model model, @RequestParam String loginName){
        User user = userService.getUserByLoginName(loginName);
        model.addAttribute("currentUser",user);
        return "edit_User_data";
    }



    @RequestMapping(value = "finishEditUserData", method = RequestMethod.POST)
    public String finishEditCustomerData(Model model,@RequestParam String firstName, @RequestParam String lastName,
                                         @RequestParam String loginName, @RequestParam String passwordHash){
        userService.editUser(firstName,lastName,loginName,passwordHash);
        String customer = getCurrentUser(model);
        List<Address> currentUserAddress = customerService.getAdressesForCustomer(customer);
        model.addAttribute("currentUserAddress",currentUserAddress);
        return "user_details";
    }



    private String getCurrentUser(Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        String currentUserLoginName = currentUser.getLoginName();
        model.addAttribute("currentUserLoginName", currentUserLoginName);
        return currentUserLoginName;
    }




}
