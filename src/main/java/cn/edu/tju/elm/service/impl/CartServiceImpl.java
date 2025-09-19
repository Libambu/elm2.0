package cn.edu.tju.elm.service.impl;

import cn.edu.tju.elm.dto.CartDto;
import cn.edu.tju.elm.mappers.CartMapper;
import cn.edu.tju.elm.model.Cart;
import cn.edu.tju.elm.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public Long addCartItem(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCreator(cart.getCreator());
        cartDto.setUpdater(cart.getUpdater());
        cartDto.setCreateTime(LocalDateTime.now());
        cartDto.setUpdateTime(LocalDateTime.now());
        cartDto.setDeleted(cart.getDeleted());
        cartDto.setQuantity(cart.getQuantity());
        cartDto.setBusinessId(cart.getBusiness().getId());
        cartDto.setCustomerId(cart.getCustomer().getId());
        cartDto.setFoodId(cart.getFood().getId());
        cartMapper.insert(cartDto);
        return cartDto.getId();
    }
}
