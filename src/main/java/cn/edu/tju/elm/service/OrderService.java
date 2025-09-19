package cn.edu.tju.elm.service;

import java.util.List;

public interface OrderService {
    List<Integer> getDetailIdsById(Long orderId);

    List<Integer> getFoodIdsByDeIds(List<Integer> ids);
}
