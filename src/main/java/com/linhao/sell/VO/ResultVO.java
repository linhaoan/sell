package com.linhao.sell.VO;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {


    private static final long serialVersionUID = 1683224523044742056L;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;

    private T data;

}
