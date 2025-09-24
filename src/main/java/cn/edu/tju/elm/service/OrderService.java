package cn.edu.tju.elm.service;

import cn.edu.tju.elm.model.Order;

import java.util.List;

public interface OrderService {
    List<Integer> getDetailIdsById(Long orderId);

    List<Integer> getFoodIdsByDeIds(List<Integer> ids);

    Long addOrders(Order order);

    Order getOrderById(Long id);

    List<Order> listOrdersByUserId(Long userId);
}
