package cn.edu.tju.elm.dto;

import cn.edu.tju.core.model.BaseEntity;
import cn.edu.tju.elm.model.Food;
import cn.edu.tju.elm.model.Order;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class OrderDetailetDto extends BaseEntity {

    private Long orderId;
    private Long foodId;
    private Integer quantity;
}
