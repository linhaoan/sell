package com.linhao.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PRARM_ERROR(90,"参数不正确"),

    CONVERTER_ERROR (91,"对象转换错误"),

    CART_EMPTY (92,"购物车不能为空"),

    OPENID_NULL (93,"OPENID为空"),

    ORDER_OWNER_ERROR(94,"订单查询人错误"),

    PRODUCT_NOT_EXIST (100,"商品不存在"),

    PRODUCT_STOCK_ERROR (101,"商品库存不正确"),

    ORDER_NOT_EXIST (102,"订单不存在"),

    ORDER_DETAIL_NOT_EXISE (103,"订单详情为空"),

    ORDER_STATUS_ERROR (104,"订单状态不正确"),

    ORDER_UPDATE_ERROR (105,"更新订单失败"),

    ORDER_DETAIL_EMPTY (106,"订单商品详情为空"),

    ORDER_PAY_STATUS_ERROR (107,"订单支付状态不正确"),

    ;
    private Integer code;

    private String message;
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
