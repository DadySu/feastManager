package common.result.err;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 话术枚举 定义
 *
 * @author by Song
 * @date 2018/10/26 11:02
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum  CallScriptEnum  {

    STATUS_MOBILE_PAST_DUE_CALL_SCRIPT("STATUS_MOBILE_PAST_DUE_CALL_SCRIPT","已过期去认证"),
    COUNT_DOWN_CALL_SCRIPT("COUNT_DOWN_CALL_SCRIPT","有效期@@天"),
    STATUS_MOBILE_ING("STATUS_MOBILE_ING","认证中"),
    STATUS_MOBILE_HAVE_NOT("STATUS_MOBILE_HAVE_NOT","未认证"),
    STATUS_MOBILE_FINISH("STATUS_MOBILE_FINISH","已认证"),

    ;
    private String retCode;

    private String retMessage;
}

