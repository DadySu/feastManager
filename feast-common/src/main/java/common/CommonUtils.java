package common;


import base.BaseResponse;
import common.result.err.BizException;
import common.result.err.ErrorInfoEnum;
import common.utils.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * CommonUtils 工具类
 * Created by songpingping on 2017/9/1.
 */
@Slf4j
public class CommonUtils {



    /**
     * 校验requestMap参数
     * @param requestMap
     * @param clazz
     * @return BaseResponse
     * @throws Exception
     */
    public static BaseResponse validateRequest(Map requestMap, Class clazz) throws Exception{
        Object object = MapUtil.mapToObject(requestMap,clazz);
        String resultMes= validate(object);
        if(null == resultMes){
            return new BaseResponse(ErrorInfoEnum.SUCCESS);
        }
        return new BaseResponse(ErrorInfoEnum.FAILED.getRetCode(),resultMes);
    }
    public static BaseResponse validateRequest(Object object) throws Exception{
        String resultMes= validate(object);
        if(null == resultMes){
            return new BaseResponse(ErrorInfoEnum.SUCCESS);
        }
        return new BaseResponse(ErrorInfoEnum.FAILED.getRetCode(),resultMes);
    }


    /**
     * 校验Object 参数
     * @param object
     * @return
     */
    public static String validate(Object object) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        if (!constraintViolations.isEmpty()) {
            StringBuffer sb=new StringBuffer();
            for (ConstraintViolation constraintViolation : constraintViolations) {
                sb.append(constraintViolation.getMessage()+"|");
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 获取报文请求流水号，格式yyyyMMddHHmmssSSS + 4位随机数
     * @return
     */
    public static String getVerifyElemeentTransId(){
        StringBuilder userId = new StringBuilder();
        userId.append(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        userId.append(getUUIDMostSignificantBits().substring(0, 4));
        return userId.toString();
    }


    public static String getUUIDMostSignificantBits() {
        String significant = String.valueOf(UUID.randomUUID().getMostSignificantBits());
        return significant.replace("-", "");
    }

    /**
     * 抛异常
     * @param defBizExceptionMsg
     * @param redisBizExceptionMsg
     */
    public static void throwBizException(String defBizExceptionMsg,String redisBizExceptionMsg){
        if(StringUtils.isEmpty(redisBizExceptionMsg)) redisBizExceptionMsg = defBizExceptionMsg;
        throw new BizException(redisBizExceptionMsg);
    }

    public static String getCallScriptByRedis(String defCallScriptMsg,String redisCallScriptMsg){
        if(StringUtils.isEmpty(redisCallScriptMsg)) redisCallScriptMsg = defCallScriptMsg;
        return redisCallScriptMsg;
    }
}
