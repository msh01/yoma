package com.github.yoma.common.persistence;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * 批量操作的DTO
 * @author 马世豪
 * @version 2019-09-17
 */
@Data
@ApiModel(value = "批量操作的DTO")
public class BatchDTO {

	@ApiModelProperty(value = "批量id list")
	private List<Long> batchIdList;

	private String batchIdStr;

//	@ApiModelProperty(hidden = true)
//	private UserBean userBean;






}
