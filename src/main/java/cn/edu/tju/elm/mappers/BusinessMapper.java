package cn.edu.tju.elm.mappers;

import cn.edu.tju.elm.model.Business;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BusinessMapper {
//    @Select("select * from business")
    List<Business> getBusinesses();

    int addBusiness(Business business);

    Business getBusinessById(Long id);

    Long getBusinessUserIdById(Long id);

    int editBusinessById(@Param("id") Long id, @Param("business") Business business);

    int patchBusinessById(@Param("id") Long id, @Param("business") Business business);

    int dropBusinessById(Long id);
}
