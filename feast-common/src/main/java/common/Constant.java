package common;

public class Constant {


    /********************************系统**********************************/
    public static final String SESSION_KEY = "session_key";
    public static final String COOKIE_KEY = "cookie_key";

    public static final String SYSTEM_NAME= "FerrariAdmin";

    public static final String PRODUCT_NAME= "QLZ";
    //redis失效时间
    public static final int REDIS_DEFAULT_TIME_OUT = 30 * 60;

    public static final String INIT_PASSWORD = "111111";

    public static final String IS_FALSE = "false";


    /*****************************Redis KEY **********************************/
    public static final String REDIS_KEY_SESSION_MENU = "redis_session_menu";
    public static final String REDIS_KEY_SESSION_LABEL = "redis_session_label";
    public static final String REDIS_KEY_SESSION_ROLE = "redis_session_role";

    /**
     * 批次订单长度 异步标注
     */
    public static final int BATCH_ASYN_LENTH = 5;

    /**
     * 代付接口用的到
     */
    public static final String SYS_SOURCE = "simple_admin";
}
