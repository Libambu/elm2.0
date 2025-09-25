package cn.edu.tju.elm.controller;

import cn.edu.tju.core.model.HttpResult;
import cn.edu.tju.core.model.ResultCodeEnum;
import cn.edu.tju.core.model.User;
import cn.edu.tju.elm.mappers.OrderMapper;
import cn.edu.tju.elm.model.Order;
//import cn.edu.tju.elb.service.OrderService;
import cn.edu.tju.core.security.service.UserService;
import cn.edu.tju.elm.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "管理订单", description = "对订单进行增删改查")
public class OrderController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService ordersService;

    @Autowired
    private OrderMapper orderMapper;

    @PostMapping(value = "")
    public HttpResult<Order> addOrders(@RequestBody Order order) throws Exception{
        Long id = ordersService.addOrders(order);
        order.setId(id);
        return HttpResult.success(order);
    }

    @GetMapping("/{id}")
    public HttpResult getOrderById(@PathVariable Long id) throws Exception{
        User user = userService.getUserWithAuthorities().get();
        Long meId =  orderMapper.getUserIdById(id);
        if(!user.getId().equals(meId)){
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR);
        }
        Order order =  ordersService.getOrderById(id);
        return HttpResult.success(order);
    }

    @GetMapping("")
    public HttpResult listOrdersByUserId(@RequestParam Long userId) throws Exception{
        User user = userService.getUserWithAuthorities().get();
        if(!user.getId().equals(userId)){
            return HttpResult.failure(ResultCodeEnum.SERVER_ERROR);
        }
        List<Order> list = ordersService.listOrdersByUserId(userId);
        return HttpResult.success(list);
    }

    
}
