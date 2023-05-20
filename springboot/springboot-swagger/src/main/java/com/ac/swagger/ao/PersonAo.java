package com.ac.swagger.ao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: 参数实体
 * @author: zhangyadong
 * @Date: 2020/7/22 0022 下午 9:02
 * @version: V1.0
 */
@ApiModel("Swagger请求参数")
@Data
public class PersonAo {

    @ApiModelProperty(value = "输入姓名例如:小明",required = true,example = "小明")
    private String userName;
}
