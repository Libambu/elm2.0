package cn.edu.tju.elm.mappers;

import cn.edu.tju.elm.dto.OrderDetailetDto;
import cn.edu.tju.elm.dto.OrderDto;
import cn.edu.tju.elm.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Integer> getDetailIdsById(Long orderId);

    List<Integer> getFoodIdsByDeIds(List<Integer> ids);

    void addOrder(Order order);

    OrderDto getOrderDto(Long id);

    List<Long> getAllIdByUserid(Long userId);


    void insertDetil(OrderDetailetDto orderDetailetDto);

    @Select("select customer_id from orders where id = #{id}")
    Long getUserIdById(Long id);
}
