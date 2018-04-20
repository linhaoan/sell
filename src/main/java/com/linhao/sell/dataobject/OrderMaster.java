package com.linhao.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@Data
public class OrderMaster {

    /**
     * 订单ID
     */
    @Id
    private String orderId;
    /**
     * 买家名字
     */
    private String buyerName;
    /**
     * 买家电话号码
     */
    private String buyerPhone;
    /**
     * 买家地址
     */
    private String buyerAddress;
    /**
     * 买家微信openId
     */
    private String buyerOpenid;
    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;
    /**
     * 订单状态 默认为新下单 0
     */
    private Integer orderStatus;
    /**
     * 支付状态 未支付 0
     */
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;
}
