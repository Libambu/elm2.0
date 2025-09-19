package cn.edu.tju.elm.service;
import cn.edu.tju.elm.model.Food;

public interface FoodService {
    Food getFoodById(Long id);

    Food getFoodByIdAndBus(Integer id, Long businessId);

    Long addFood(Food food);
}
