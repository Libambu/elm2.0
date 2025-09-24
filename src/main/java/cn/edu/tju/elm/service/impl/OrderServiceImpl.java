package cn.edu.tju.elm.service.impl;

import cn.edu.tju.core.model.User;
import cn.edu.tju.core.security.mappers.UserMapper;
import cn.edu.tju.core.security.service.UserService;
import cn.edu.tju.elm.dto.*;
import cn.edu.tju.elm.mappers.*;
import cn.edu.tju.elm.model.*;
import cn.edu.tju.elm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private BusinessMapper businessMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private FoodMapper foodMapper;

    @Override
    public List<Integer> getDetailIdsById(Long orderId) {
        return orderMapper.getDetailIdsById(orderId);
    }

    @Override
    public List<Integer> getFoodIdsByDeIds(List<Integer> ids) {
        return orderMapper.getFoodIdsByDeIds(ids);
    }

    @Override
    public Long addOrders(Order order) {
        User me = userService.getUserWithAuthorities().get();
        order.setOrderDate(LocalDateTime.now());
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(LocalDateTime.now());
        order.setUpdater(me.getId());
        order.setCreator(me.getId());
        order.setCustomer(me);

        BigDecimal total = new BigDecimal(0);

        List<CartDto> list = cartMapper.getByBusIdCusId(me.getId(),order.getBusiness().getId());
        for(CartDto cartDto : list){
            Food food = foodMapper.selectById(cartDto.getFoodId());
            BigDecimal multiply = food.getFoodPrice().multiply(BigDecimal.valueOf(cartDto.getQuantity()));
            total = total.add(multiply);
        }
        order.setOrderTotal(total);
        orderMapper.addOrder(order);
        for(CartDto cartDto : list){
            Food food = foodMapper.selectById(cartDto.getFoodId());
            OrderDetailetDto orderDetailetDto = new OrderDetailetDto();
            orderDetailetDto.setOrderId(order.getId());
            orderDetailetDto.setFoodId(food.getId());
            orderDetailetDto.setQuantity(cartDto.getQuantity());
            orderDetailetDto.setCreateTime(LocalDateTime.now());
            orderDetailetDto.setUpdateTime(LocalDateTime.now());
            orderDetailetDto.setCreator(me.getId());
            orderDetailetDto.setUpdater(me.getId());
            orderDetailetDto.setDeleted(false);
            orderMapper.insertDetil(orderDetailetDto);
        }
        return order.getId();
    }

    @Override
    public Order getOrderById(Long id) {
        OrderDto orderDto = orderMapper.getOrderDto(id);
        Business businessById = businessMapper.getBusinessById(orderDto.getBusinessId());
        User user = userMapper.getUserById(orderDto.getCustomerId());
        AddressDto addressDto = addressMapper.getAddressDtoByid(orderDto.getAddressId());
        User addressUser = userMapper.getUserById(addressDto.getUserId());
        DeliveryAddress deliveryAddress = new DeliveryAddress();


        deliveryAddress.setId(addressDto.getId());
        deliveryAddress.setCreateTime(addressDto.getCreateTime());
        deliveryAddress.setCreator(addressDto.getCreator());
        deliveryAddress.setDeleted(addressDto.getIsDeleted());
        deliveryAddress.setUpdateTime(addressDto.getUpdateTime());
        deliveryAddress.setUpdater(addressDto.getUpdater());
        deliveryAddress.setAddress(addressDto.getAddress());
        deliveryAddress.setContactName(addressDto.getContactName());
        deliveryAddress.setContactSex(addressDto.getContactSex());
        deliveryAddress.setContactTel(addressDto.getContactTel());
        deliveryAddress.setCustomer(addressUser);

        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCreateTime(orderDto.getCreateTime());
        order.setCreator(orderDto.getCreator());
        order.setDeleted(orderDto.getIsDeleted());
        order.setUpdateTime(orderDto.getUpdateTime());
        order.setUpdater(orderDto.getUpdater());
        order.setOrderDate(orderDto.getOrderDate());
        order.setOrderState(order.getOrderState());
        order.setOrderTotal(orderDto.getOrderTotal());
        order.setBusiness(businessById);
        order.setCustomer(user);
        order.setDeliveryAddress(deliveryAddress);
        return order;
    }

    @Override
    public List<Order> listOrdersByUserId(Long userId) {
        List<Long> ids = orderMapper.getAllIdByUserid(userId);
        List<Order> list = new ArrayList<>();
        if(ids!=null){
            for(Long id:ids){
                Order orderById = getOrderById(id);
                list.add(orderById);
            }
        }
        return list;
    }
}
