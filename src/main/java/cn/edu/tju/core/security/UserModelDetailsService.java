package cn.edu.tju.core.security;

//import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import cn.edu.tju.core.security.mappers.UserMapper;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import cn.edu.tju.core.model.User;


import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserModelDetailsService implements UserDetailsService {

   private final Logger log = LoggerFactory.getLogger(UserModelDetailsService.class);

   //private final UserRepository userRepository;

   @Autowired
   private UserMapper userMapper;


   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String login) {
      log.debug("Authenticating user '{}'", login);
      log.info("Authenticating user '{}'", login);
      String lowercaseLogin = login.toLowerCase(Locale.ENGLISH);
      return userMapper.findOneWithAuthoritiesByUsername(lowercaseLogin)
              .map(user -> createSpringSecurityUser(lowercaseLogin, user))
              .orElseThrow(() -> {                                    // ← 代码块
                 String msg = "User " + lowercaseLogin + " was not found in the database";
                 log.warn(msg);                                      // ← 手动打印
                 return new UsernameNotFoundException(msg);
              });
   }

   private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
      if (!user.isActivated()) {
         throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
      }
      List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
         .map(authority -> new SimpleGrantedAuthority(authority.getName()))
         .collect(Collectors.toList());
      return new org.springframework.security.core.userdetails.User(user.getUsername(),
         user.getPassword(),
         grantedAuthorities);
   }
}
