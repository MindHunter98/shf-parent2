package com.atguigu.entity.vo;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class HouseVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String communityName; //小区名字 hc 小区名字 hc.name
	private Long id; //房屋id 从此处开始往下都是hh表
	private String name; //房源名称
	private String buildArea; //房子面积
	private BigDecimal totalPrice; //房子总价
	private BigDecimal unitPrice; //每平价格
	private Long houseTypeId; //户型id
	private Long floorId; //楼层id
	private Long directionId; //朝向id
	private String defaultImageUrl; //默认图片
	private Date createTime; //创建时间 从此处往上都是hh表
	private String houseTypeName; //户型名 hd
	private String floorName; //楼层名 hd
	private String directionName; //朝向名 hd

	public String getCreateTimeString() {
		Date date = this.getCreateTime();
		if(null == date) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = df.format(date);
		return dateString;
	}
}

