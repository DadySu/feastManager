package common.result.err;


import common.result.ResultBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 统一错误异常处理
 * @author liub
 * @date 2018/12/2 12:33
 * @version 1.0
 */
@ControllerAdvice
@ResponseBody   // 返回json
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultBody errorHandlerOverJson(HttpServletRequest request,
                                           Exception exception) {
        log.error("======exception_request:",request.getRequestURL());
        log.error("======exception_message:",exception);
       return new ResultBody(ErrorInfoEnum.FAILED.getRetCode(),exception.getMessage());
    }

    @ExceptionHandler(value = BizException.class)
    public ResultBody errorHandlerAdam(HttpServletRequest request,
                                       BizException exception) {
        log.info("======Exception_request:"+request.getRequestURL());
        log.info("======Exception_message:"+exception.getMessage());
       return new ResultBody(exception.getCode(),exception.getMessage());
    }
}
