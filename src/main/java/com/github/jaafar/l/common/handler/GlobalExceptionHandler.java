package com.github.jaafar.l.common.handler;

import com.github.jaafar.l.common.VO.BaseResponse;
import com.github.jaafar.l.common.constants.CommonConstants;
import com.github.jaafar.l.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * ${DESCRIPTION}
 *
 * @author jaafaree
 * @create 2018/1/2 10:29
 */
@ControllerAdvice("com.github.jaafar.l")
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse baseExeptionHandler(HttpServletResponse response, BaseException ex){
        log.error("baseExeptionHandler catch ex: \n" + ex.getMessage(), ex);
        return new BaseResponse(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse otherExeptionHandler(HttpServletResponse response, Exception ex){
        log.error("otherExeptionHandler catch ex: \n" + ex.getMessage(), ex);
        return new BaseResponse(CommonConstants.EX_OTHER_ERROR_CODE, ex.getMessage());
    }

}
