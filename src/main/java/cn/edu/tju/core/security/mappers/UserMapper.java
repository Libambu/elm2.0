package cn.edu.tju.core.security.mappers;


import cn.edu.tju.core.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {


    Optional<User> findOneWithAuthoritiesByUsernamemyelm(String username);

    void save(User user);

    List<User> findAll();

    void update(User user);

    User getUserById(Long customerId);
}
