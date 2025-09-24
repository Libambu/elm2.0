package cn.edu.tju.elm.service.impl;

import cn.edu.tju.core.model.User;
import cn.edu.tju.core.security.service.UserService;
import cn.edu.tju.elm.dto.CartDto;
import cn.edu.tju.elm.mappers.CartMapper;
import cn.edu.tju.elm.mappers.FoodMapper;
import cn.edu.tju.elm.model.Cart;
import cn.edu.tju.elm.model.Food;
import cn.edu.tju.elm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private FoodMapper foodMapper;

    @Override
    public Long addCartItem(Cart cart) {
        CartDto cartDto = new CartDto();
        User user = userService.getUserWithAuthorities().get();
        cartDto.setCreator(cart.getCreator());
        cartDto.setUpdater(cart.getUpdater());
        cartDto.setCreateTime(LocalDateTime.now());
        cartDto.setUpdateTime(LocalDateTime.now());
        cartDto.setDeleted(cart.getDeleted());
        cartDto.setQuantity(cart.getQuantity());
        if(cart.getBusiness()!=null){
            cartDto.setBusinessId(cart.getBusiness().getId());
        }
        if(cart.getCustomer()!=null){
            cartDto.setCustomerId(cart.getCustomer().getId());
        }
        Food food = foodMapper.selectById(cart.getFood().getId());
        cartDto.setBusinessId(food.getBusiness().getId());
        cartDto.setCustomerId(user.getId());
        cartDto.setFoodId(cart.getFood().getId());
        cartMapper.insert(cartDto);
        return cartDto.getId();
    }
}
