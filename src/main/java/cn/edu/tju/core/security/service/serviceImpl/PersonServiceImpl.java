package cn.edu.tju.core.security.service.serviceImpl;

import cn.edu.tju.core.model.Authority;
import cn.edu.tju.core.model.Person;
import cn.edu.tju.core.model.User;
import cn.edu.tju.core.security.mappers.AuthorityMapper;
import cn.edu.tju.core.security.mappers.PersonMapper;
import cn.edu.tju.core.security.mappers.UserMapper;
import cn.edu.tju.core.security.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private AuthorityMapper authorityMapper;


    @Override
    public void savePerson(Person person) {
        User user = new User();
        user.setCreator(person.getCreator());
        user.setCreateTime(person.getCreateTime());
        user.setDeleted(person.getDeleted());
        user.setUpdateTime(person.getUpdateTime());
        user.setUpdater(person.getUpdater());
        user.setActivated(person.isActivated());
        user.setPassword(person.getPassword());
        user.setUsername(person.getUsername());
        user.setAuthorities(person.getAuthorities());
        userMapper.save(user);
        person.setId(user.getId());
        personMapper.savePerson(person);
        Set<Authority> authorities = user.getAuthorities();
        if(authorities!=null){
            for(Authority a : authorities){
                String name = a.getName();
                authorityMapper.insertAuth(name);
                authorityMapper.insert(name,user.getId());
            }
        }
    }
}
