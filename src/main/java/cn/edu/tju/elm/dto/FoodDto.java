package cn.edu.tju.elm.dto;

import cn.edu.tju.core.model.BaseEntity;
import lombok.Data;


import java.math.BigDecimal;

@Data
public class FoodDto extends BaseEntity {
    private String foodName;
    private String foodExplain;
    private String foodImg;
    private BigDecimal foodPrice;
    private Long businessId;
    private String remarks;
    private Boolean isDeleted;
}
