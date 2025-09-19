package cn.edu.tju.elm.mappers;

import cn.edu.tju.elm.dto.FoodDto;
import cn.edu.tju.elm.model.Food;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FoodMapper {

    Food selectById(Long id);

    Food getFoodByIdAndBus(Integer id, Long businessId);

    void insert(FoodDto foodDto);
}
