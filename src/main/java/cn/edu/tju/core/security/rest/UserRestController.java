package cn.edu.tju.core.security.rest;

import cn.edu.tju.core.model.Authority;
import cn.edu.tju.core.model.Person;
import cn.edu.tju.core.security.SecurityUtils;
import cn.edu.tju.core.security.UserModelDetailsService;
import cn.edu.tju.core.security.mappers.AuthorityMapper;
import cn.edu.tju.core.security.mappers.PersonMapper;
import cn.edu.tju.core.security.mappers.UserMapper;
import cn.edu.tju.core.security.rest.dto.LoginDto;
import cn.edu.tju.core.security.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cn.edu.tju.core.model.User;
import cn.edu.tju.core.security.service.UserService;

import java.time.LocalDateTime;
import java.util.Set;

@RestController
@RequestMapping("/api")
@Tag(name = "管理用户", description = "提供用户的增删改查操作")
public class UserRestController {

   @Autowired
   private UserService userService;
   @Autowired
   private PersonMapper personMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private PersonService personService;


   @PostMapping("/users")
   @Operation(summary = "新增用户（仅登录帐号）", description = "创建一个新的用户（仅登录帐号）")
   public ResponseEntity<User> createUser(@RequestBody User newUser) {
      User user = userService.getUserWithAuthorities().get();
      LocalDateTime now = LocalDateTime.now();
      newUser.setCreator(user.getId());
      newUser.setCreateTime(now);
      newUser.setUpdater(user.getId());
      newUser.setUpdateTime(now);
      newUser.setDeleted(false);
      newUser.setPassword(SecurityUtils.BCryptPasswordEncode("password"));
      newUser.setActivated(true);
      userMapper.save(newUser);
      Set<Authority> authorities = newUser.getAuthorities();
      if(authorities!=null){
         for(Authority a : authorities){
            String name = a.getName();
            authorityMapper.insertAuth(name);
            authorityMapper.insert(name,newUser.getId());
         }
      }

      return ResponseEntity.ok(newUser);
   }

   @GetMapping("/user")
   @Operation(summary = "判断当前登录的用户", description = "判断当前登录的用户")
   public ResponseEntity<User> getActualUser() {
      return ResponseEntity.ok(userService.getUserWithAuthorities().get());
   }

   @PostMapping("/password")
   @Operation(summary = "修改密码", description = "已登录的用户只可以修改自己的密码，Admin可以修改任何人的密码")
   public ResponseEntity<String> updateUserPassword(@RequestBody LoginDto loginDto) {
      User me =  userService.getUserWithAuthorities().get();
      boolean isAdmin = false;
      for (Authority authority : me.getAuthorities()) {
          if (authority.getName().equals("ADMIN")) {
              isAdmin = true;
              break;
          }
      }
      User updateUser = userMapper.findOneWithAuthoritiesByUsername(loginDto.getUsername()).get();
      //userModelDetailsService.loadUserByUsername(loginDto.getUsername());
      if (me.getUsername().equals(loginDto.getUsername()) || isAdmin ) {
         updateUser.setPassword(SecurityUtils.BCryptPasswordEncode(loginDto.getPassword()));
         updateUser.setUpdateTime(LocalDateTime.now());
         if(isAdmin){
            updateUser.setUpdater(me.getId());
         }else {
            updateUser.setUpdater(updateUser.getId());
         }
         userService.updateUser(updateUser);
         return ResponseEntity.ok().body("Update the password successfully.");
      } else {
         return ResponseEntity.unprocessableEntity().body("Failed to update the password.");
      }
   }

   @PostMapping("/persons")
   @Operation(summary = "新增用户（自然人）", description = "创建一个新的用户（自然人）")
   public Person addPerson(@RequestBody Person person){
      User user = userService.getUserWithAuthorities().get();
      LocalDateTime now = LocalDateTime.now();
      person.setCreator(user.getId());
      person.setCreateTime(now);
      person.setUpdater(user.getId());
      person.setUpdateTime(now);
      person.setDeleted(false);
      person.setPassword(SecurityUtils.BCryptPasswordEncode("password"));
      person.setActivated(true);
      personService.savePerson(person);
      return person;
   }
}
