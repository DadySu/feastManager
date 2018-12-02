package common;

/**
 * @author mengfanzhu
 * @ClassName: IConstant
 * @Description: 常量类
 * @date Aug 14, 2017 5:15:59 PM
 */
public class CoreConstant {

	public static final String SYSTEM_NAME = "FERRARI";
	public static final String MAX_TIME = "2079-01-01 00:00:00";

	public static final String IS_FALSE = "false";
	public static final String IS_TRUE = "true";
	public static final int REDIS_DEFAULT_TIME_OUT = 30 * 60; //30min

	/********************************API_USE**********************************/
	public static final String REQUESTBODY_NAME = "requestBody";
	public static final String SIGN_NAME = "sign";
	public static final String TOKEN_NAME = "token";
	public static final String SYSTEMID_NAME = "systemCode";
	public static final String REQ_NO_NAME = "reqNo";
	public static final String OPERATOR_NAME = "operatorId";
	public static final String OPERATOR_PREFIX_NAME = "USERAUTH";
	public static final String WECHAT_WEB_CODE = "WeChat_Web"; //用户端 用于上传附件区分
	public static final String MOVE_APP_SYSTEM = "MoveApp_System"; //安卓，IOS等移动端 用于上传附件区分
	public static final String SYSTEM_ADAM_ADMIN = "Adam_Admin"; //安卓，IOS等移动端 用于上传附件区分
	/********************************日期**********************************/
	public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_yyyyMMdd = "yyyyMMdd";
	public static final String DATE_FORMAT_yyyyMMdd2 = "yyyy.MM.dd";
	public static final String DATE_FORMAT_yyyy_MM_ddHH_mm = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_yyyy_MM_ddHH_mm_ss = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_yyyyMMddHHmmssS = "yyyyMMddHHmmssS";


	/*****************************字符编码**********************************/
	public static final String ENCODE_UTF8 = "utf-8";
	public static final String ENCODE_ISO88591 = "iso-8859-1";
	public static final String ENCODE_ISO88591_ = "ISO8859-1";
	public static final String ENCODE_GB2312 = "GB2312";
	public static final String ENCODE_GBK = "GBK";

	public static final String Y = "Y";//是
	public static final String N = "N";//否

	public static final String MD5 = "MD5";
	public static final String AES = "AES";

	public static final String G = "/";

	/********************************分页**********************************/
	/**
	 * @Fields DEFAULT_PAGE_NO : 默认页码
	 */
	public static final Integer DEFAULT_PAGE_NO = 1;
	/**
	 * @Fields DEFAULT_PAGE_SIZE : 默认页数
	 */
	public static final Integer DEFAULT_PAGE_SIZE = 10;
	public static final String PAGENUM_NAME = "page";
	public static final String PAGESIZE_NAME = "limit";


	/*****************************Redis KEY **********************************/

	public static final String REDIS_KEY_SESSION_MENU = "redis_session_menu";
	public static final String REDIS_KEY_SESSION_LABEL = "redis_session_label";
	public static final String REDIS_KEY_SESSION_ROLE = "redis_session_role";

	//token失效天数
	public static final Integer LOST_TIME_NUMBER = 15;
	//系统code,salt
	public static final String IOS_CODE = "IOS_V2.0";
	public static final String ANDROID_CODE = "ANDROID_V2.0";
	public static final String REDIS_KEY_ACCESS_TOKEN = "ACCESS_TOKEN";//access_token

	/*****************************业务编号 **********************************/
	public static final String BIZ_CODE_SMS = "SMS";


	/*****************************短信类型 **********************************/
	public static final String MESSAGE_TYPE_SMS = "SMS";
	public static final String MESSAGE_TYPE_EMAIL = "EMAIL";


	/*****************************参数类型 **********************************/
	public static final String PARAMS_APPID = "APPID";//APPID
	public static final String PARAMS_APPSECRET = "APPSECRET";//AppSecret

	/*****************************字典类型 **********************************/
	public static final String DICT_SEX = "SEX";                  //性别
	public static final String DICT_CUR_TYPE = "CUR_TYPE";//币种
	public static final String DICT_ROLES = "ROLES";  //角色类型
	public static final String DICT_ROLE_STATUS = "ROLE_STATUS";//角色状态
	public static final String DICT_HUMAN_TYPE = "HUMAN_TYPE";//用户类型
	public static final String DICT_CASE_APPROVE_STATUS = "APPROVE_STATUS"; // 认证状态
	public static final String BANK_ACCOUNT_TYPE = "BANK_ACCOUNT_TYPE"; // 用户账户类型


	/*****************************常用数字整型********************************/
	public static final String STR_0 = "0";
	public static final String STR_1 = "1";
	public static final String STR_2 = "2";


	public static final String REQ_TYPE_GET = "GET";
	public static final String REQ_TYPE_POST = "POST";

}
