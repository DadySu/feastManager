package common.result.err;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * user rule: 业务异常定义
 * 可自定义错误信息，从redis拿不到，就取默认提示信息
 * service errorEnum save to this errorInfoEnum
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum BizExceptionEnum implements ErrorInterface {


    BORROW_MONEY_NULL("000002","BORROW_MONEY_NULL","借款金额不能为空"),
    PRODUCT_CONFIG_MODEL_NULL("000003","PRODUCT_CONFIG_MODEL_NULL","获取借款产品参数错误NULL"),
    STATUS_MOBILE_DISCREPANCY("000004","STATUS_MOBILE_DISCREPANCY","手机认证状态跟失效日期不符合"),
    ;
    private String retCode;

    private String retKey;

    private String retMessage;


}
