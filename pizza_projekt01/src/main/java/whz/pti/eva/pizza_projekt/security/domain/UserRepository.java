package whz.pti.eva.pizza_projekt.security.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLoginName(String loginName);
    boolean existsByloginName(String loginName);
//    boolean existsByNickname(String nickname);
}
