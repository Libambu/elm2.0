package cn.edu.tju.elm.mappers;

import cn.edu.tju.elm.dto.CartDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {
    void insert(CartDto cartDto);
}
