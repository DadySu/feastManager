package common.result.err;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * user rule:
 * service errorEnum save to this errorInfoEnum
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorInfoEnum implements ErrorInterface {
    ERROR_REPLACE("@@","错误信息占位符"),
	SUCCESS("000000","成功"),
    PARAM_ERR("000002","参数错误"),
    FAILED("999999", "失败"),
    ;
    private String retCode;

    private String retMessage;


}
