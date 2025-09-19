package cn.edu.tju.elm.service;

import cn.edu.tju.elm.model.Cart;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface CartService {
    Long addCartItem(Cart cart);
}
