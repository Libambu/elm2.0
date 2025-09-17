package cn.edu.tju.core.security.mappers;

import cn.edu.tju.core.model.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonMapper {

    void savePerson(Person person);
}
