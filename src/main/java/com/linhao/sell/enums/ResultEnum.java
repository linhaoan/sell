package com.linhao.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),

    ORDER_CANCEL_SUCCESS(1,"取消订单成功"),

    ORDER_FINISH_SUCCESS(2,"完结订单成功"),

    PRODUCT_STATUS_ERROR(3,"更新产品状态异常"),

    WECHAT_MP_ERROR(4,"微信二维码登陆错误"),

    LOGIN_FAIL(5,"登陆信息不正确"),

    LOGOUT_SUCCESS(6,"退出登录成功"),

    PARAM_ERROR(90,"参数不正确"),

    CONVERTER_ERROR (91,"对象转换错误"),

    CART_EMPTY (92,"购物车不能为空"),

    OPENID_NULL (93,"OPENID为空"),

    ORDER_OWNER_ERROR(94,"订单查询人错误"),

    WECHAT_AUTH_ERROR (95,"微信授权异常"),

    PRODUCT_NOT_EXIST (100,"商品不存在"),

    PRODUCT_STOCK_ERROR (101,"商品库存不正确"),

    ORDER_NOT_EXIST (102,"订单不存在"),

    ORDER_DETAIL_NOT_EXISE (103,"订单详情为空"),

    ORDER_STATUS_ERROR (104,"订单状态不正确"),

    ORDER_UPDATE_ERROR (105,"更新订单失败"),

    ORDER_DETAIL_EMPTY (106,"订单商品详情为空"),

    ORDER_PAY_STATUS_ERROR (107,"订单支付状态不正确"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),

    ;
    private Integer code;

    private String message;
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
