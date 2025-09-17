package cn.edu.tju.core.security.mappers;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorityMapper {

    void insert(String name, Long id);

    void insertAuth(String name);
}
