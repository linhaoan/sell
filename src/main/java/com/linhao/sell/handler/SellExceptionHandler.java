package com.linhao.sell.handler;

import com.linhao.sell.Exception.SellException;
import com.linhao.sell.Exception.SellerAuthorizeException;
import com.linhao.sell.Utils.ResultVOUtil;
import com.linhao.sell.VO.ResultVO;
import com.linhao.sell.config.ProjectUrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellExceptionHandler {


    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO hanlderSellException(SellException e) {
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }
}
