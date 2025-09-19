package cn.edu.tju.elm.service.impl;

import cn.edu.tju.elm.dto.FoodDto;
import cn.edu.tju.elm.mappers.FoodMapper;
import cn.edu.tju.elm.service.FoodService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.edu.tju.elm.model.Food;

import java.time.LocalDateTime;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public Food getFoodById(Long id) {
        return foodMapper.selectById(id);
    }

    @Override
    public Food getFoodByIdAndBus(Integer id, Long businessId) {
        return foodMapper.getFoodByIdAndBus(id,businessId);
    }

    @Override
    public Long addFood(Food food) {
        FoodDto foodDto = new FoodDto();
        foodDto.setCreateTime(LocalDateTime.now());
        foodDto.setCreator(food.getCreator());
        foodDto.setDeleted(food.getDeleted());
        foodDto.setUpdateTime(LocalDateTime.now());
        foodDto.setUpdater(food.getUpdater());
        foodDto.setFoodExplain(food.getFoodExplain());
        foodDto.setFoodImg(food.getFoodImg());
        foodDto.setFoodName(food.getFoodName());
        foodDto.setFoodPrice(food.getFoodPrice());
        foodDto.setRemarks(food.getRemarks());
        foodDto.setBusinessId(food.getBusiness().getId());
        foodMapper.insert(foodDto);
        return foodDto.getId();
    }
}
