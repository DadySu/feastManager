package common.utils;

/**
 * @description:
 * @author liub
 * @date 2018/12/2 12:33
 * @version 1.0
 */
public class CommonStringUtils {

    /**
     * 处理手机号码敏感信息 133****1111形式
     * @param mobile
     * @return
     */
    public static String formatMobile(String mobile){
        StringBuffer phoneStr = new StringBuffer();
        if(mobile.length()<11){
            for(int i=mobile.length();i<11;i++){
                phoneStr.append(mobile).append("*");
            }
            return phoneStr.toString();
        }
        String head = mobile.substring(0,3);
        String end = mobile.substring(7,11);
        phoneStr.append(head).append("****").append(end);
        return phoneStr.toString();
    }

}
