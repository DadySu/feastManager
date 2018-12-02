package base;

import common.result.err.BizException;
import common.result.err.ErrorInfoEnum;
import common.result.err.ErrorInterface;
import lombok.*;
import org.apache.commons.lang3.StringUtils;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseResponse<T> {

    /**
     * 响应码
     */
    private String retCode;
    /**
     * 响应信息描述
     */
    private String retMessage;
    private T body;

    public BaseResponse(String retCode,String retMessage){
       this.retCode = retCode;
       this.retMessage = retMessage;
    }
    public BaseResponse(BizException bizException){
        this(bizException.getCode(),bizException.getMessage());
    }
    /**
     * 传枚举参数的构造函数
     *
     * @param error
     */
    public BaseResponse(ErrorInterface error){
        this(error.getRetCode(), error.getRetMessage());
    }

    /**
     * 返回指定错误信息的响应枚举
     * @param errorMsg
     * @return
     */
    public static BaseResponse newInstanceError(String errorMsg){
        return StringUtils.isEmpty(errorMsg) ? new BaseResponse(ErrorInfoEnum.FAILED): new BaseResponse(ErrorInfoEnum.FAILED.getRetCode(),errorMsg);
    }

    /**
     * 返回成功信息
     * @return
     */
    public static BaseResponse newInstanceSuccess(){
        return new BaseResponse(ErrorInfoEnum.SUCCESS);
    }

    /**
     * @param body
     * @return
     */
    public static BaseResponse newInstanceSuccess(Object body){
        BaseResponse response = new BaseResponse(ErrorInfoEnum.SUCCESS);
        response.setBody(body);
        return response;
    }

    /**
     * 返回失败信息
     * @return
     */
    public static BaseResponse newInstanceFailed(){
        return new BaseResponse(ErrorInfoEnum.FAILED);
    }

    /**
     * 返回具体失败信息
     * @param errorInterface
     * @return
     */
    public static BaseResponse newInstanceError(ErrorInterface errorInterface){
        return new BaseResponse(errorInterface);
    }


}
