package cn.edu.tju.core.security.service;

import cn.edu.tju.core.security.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.tju.core.security.SecurityUtils;
import cn.edu.tju.core.model.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

   @Autowired
   private UserMapper userMapper;

   @Transactional(readOnly = true)
   public Optional<User> getUserWithAuthorities() {
      return SecurityUtils.getCurrentUsername().flatMap(userMapper::findOneWithAuthoritiesByUsername);
   }

   public void addUser(User user) {
      userMapper.save(user);
   }

   public void updateUser(User user) {
       userMapper.update(user);
   }

   public Boolean isEmptyUserTable() {
      List<User> userList = userMapper.findAll();
      return userList.isEmpty();
   }


}
