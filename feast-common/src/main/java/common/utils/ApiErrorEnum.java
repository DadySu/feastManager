package common.utils;

import common.result.err.ErrorInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 4/15/18 16:10
 * @Version 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ApiErrorEnum implements ErrorInterface {


    /**
     * 0开头-成功相关
     **/
    SUCCESS("000000", "成功"),

    /**
     * 1开头-用户相关
     **/
    USER_NOT_EXIST("100000", "当前用户不存在"),
    LOGIN_USER_MOBILE_FOUND_OP("100001", "登录用户状态删除或者禁用"),
    LOGIN_ACCOUNT_PASSWORD_ERROR("100002", "登录用户名或密码错误"),
    USER_REGISTER_FAILED("100003", "用户注册失败"),
    USER_MESSAGE_SEND_FAILED("100004", "发送用户验证码失败"),
    USER_NOT_LONGING("100005", "当前用户未登录"),
    LOGIN_USER_CAPTCHA_ERROR("100006", "登录验证码不正确"),
    USER_VERIFY_NOT_ACCEPT("100007", "用户认证信息未完成"),
    USER_IMG_ID_ERROR("100008", "用户未认证身份证相关照片"),
    USER_CAPTCHA_ERROR("100009", "验证码不正确"),
    USER_ALIPAY_ERROR("100010", "上传用户花呗信息错误"),


    /**
     * 2开头-借款单相关
     **/
    BORROWED_EXIST("200000", "已申请借款"),
    BORROWED_BANK_ERROR("200001", "该银行暂不支持，请前往个人中心修改!"),
    BORROWED_USER_WECASH_ERROR("200002", "获取用户闪银积分失败"),
    BORROWED_NOT_EXIST("200003", "借款订单不存在"),
    BORROWED_USER_BLACK_INFO("200004", "您的资质暂不符合平台要求，无法借款！"),
    BORROWED_ERROR_ORDER("200005", "身份证号后四位@@下有一笔借款单，请处理后再借款！"),

    /**
     * 3开头-还款单相关
     **/
    REPAYMENT_CAPTCHA_ERROR("300000", "还款验证码不正确"),
    REPAYMENT_INFO_ERR("300001", "还款信息不存在"),
    REPAYMENT_PAY_FAILED("300002", "还款失败"),
    REPAYMENT_PAY_PROTOCOL_STATUS("300003", "未实名认证，请重新认证!"),

    /**
     * 4开头-统一检验相关
     **/
    SIGN_ERROR("400000", "签名错误！"),
    KEY_ERROR("400001", "公钥错误！"),

    /**
     * 5开头-上传图片相关
     **/
    UPLOAD_IMAGE_ERROR("500000", "上传图片失败"),

    /**
     * 6开头-手机运营商授权
     **/
    ORDER_NOT_EXIST("600001", "数据不存在"),
    VERIFY_ERROR("600002", "授权失败"),

    /**
     * 9开头-系统异常相关
     **/
    FAILED("999999", "网络错误，请稍后重试！"),
    SYSTEM_CODE_LOW("999991", "您的版本不是最新版！"),
    SYSTEM_CODE_ERROR("999991", "您的版本号不正确！"),
    REQUEST_REPEATING("999992", "重复提交!"),
    CHANNEL_ERROR("999993", "渠道不符!"),
    SYSTEM_DATA_ERROR("999994", "数据异常，请联系管理员！"),
    SYSTEM_SOURCE_ERROR("999995", "系统来源不符！"),;

    private String retCode;
    private String retMessage;
}
