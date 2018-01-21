package whz.pti.eva.pizza_projekt.security.domain;


import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser  extends org.springframework.security.core.userdetails.User  {

    private User user;



    public CurrentUser(User user) {
        super(user.getLoginName(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getLoginName() {return user.getLoginName();}

    public String getFirstName(){ return user.getFirstName();}

    public Role getRole() {
        return user.getRole();
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                "} " + super.toString();
    }
}
