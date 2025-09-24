package cn.edu.tju.elm.mappers;

import cn.edu.tju.elm.dto.CartDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper {
    void insert(CartDto cartDto);

    @Select("select * from cart where business_id =#{id1} and customer_id = #{id}")
    List<CartDto> getByBusIdCusId(Long id, Long id1);
}
