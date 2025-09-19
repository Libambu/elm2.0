package cn.edu.tju.elm.controller;

import cn.edu.tju.core.model.User;
import cn.edu.tju.elm.model.Food;
import cn.edu.tju.core.model.HttpResult;
import cn.edu.tju.core.security.service.UserService;
import cn.edu.tju.elm.service.FoodService;
import cn.edu.tju.elm.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/foods")
@Tag(name = "管理商品")
public class FoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;


    @GetMapping("/{id}")
    @Operation(summary = "返回查询到的一条商品记录", method = "GET")
    public HttpResult<Food> getFoodById(@PathVariable Long id){
        Food food = foodService.getFoodById(id);
        return HttpResult.success(food);
    }

    @GetMapping("")
    public HttpResult<List<Food>> getAllFoods(@RequestParam(name = "business", required = false) Long businessId,
                                              @RequestParam(name = "order", required = false) Long orderId){
        List<Integer> ids = orderService.getDetailIdsById(orderId);
        List<Integer> foodIds = orderService.getFoodIdsByDeIds(ids);
        List<Food> list = new ArrayList<>();
        for(Integer id : foodIds){
            Food foodByIdAndBus = foodService.getFoodByIdAndBus(id, businessId);
            list.add(foodByIdAndBus);
        }
        return HttpResult.success(list);
    }

    @PostMapping("")
    public HttpResult<Food> addFood(@RequestBody Food food){
        User me =  userService.getUserWithAuthorities().get();
        food.setUpdater(me.getId());
        food.setCreator(me.getId());
        Long foodId = foodService.addFood(food);
        food.setId(foodId);
        return HttpResult.success(food);
    }
}
