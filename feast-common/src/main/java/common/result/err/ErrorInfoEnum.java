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
    INTERFACE_EMPTY("I00001","调用接口地址为空，请核对！"),
	BATCH_PAY_ANOTHER_NULL_ROW_NUM("B00001","插入批量放款批次数据为空，请核对！"),
    FAILED("999999", "失败"),
    REMOTE_TIMEOUT("T000001", "远程调用服务超时，请稍候访问!"),
    UN_DOWN("T000002", "处理状态不明!"),
    TIMES_MAX("T000003", "还款处理中，请稍候查看还款结果!"),
    ID_CARD_INFO_ERROR("R00001", "身份证信息有误，请重新上传身份证正反面照片"),
    ID_CARD_INFO_RECOGNITION_ERROR("R00002", "身份证信息扫描失败，请重新上传"),
    LIVING_BODY_DETECTION_FAIL("L00001", "人脸识别验证失败，请重新操作"),
    WITH_PAY_FOR_SUCCESS("0000","代付请求成功"),
    WITH_PAY_FOR_NOEXIST("0401","代付交易查证信息不存在"),
    ;
    private String retCode;

    private String retMessage;


}
