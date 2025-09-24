package cn.edu.tju.elm.dto;

import cn.edu.tju.core.model.BaseEntity;
import lombok.Data;


@Data
public class AddressDto extends BaseEntity {

    private String contactName;
    private Integer contactSex;
    private String contactTel;
    private String address;
    private Long userId;
    private Boolean isDeleted;
}
