package cn.edu.tju.elm.dto;

import cn.edu.tju.core.model.BaseEntity;
import cn.edu.tju.core.model.User;
import cn.edu.tju.elm.model.Business;
import cn.edu.tju.elm.model.DeliveryAddress;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDto extends BaseEntity {

    private Long customerId;
    private Long businessId;
    private LocalDateTime orderDate;
    private BigDecimal orderTotal;
    private Long addressId;
    private Integer orderState;
    private Boolean isDeleted;
}
