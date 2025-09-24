package cn.edu.tju.elm.dto;

import cn.edu.tju.core.model.BaseEntity;
import lombok.Data;

@Data
public class CartDto extends BaseEntity {
    private Long foodId;
    private Long customerId;
    private Long businessId;
    private Integer quantity;
    private Boolean isDeleted;

}
