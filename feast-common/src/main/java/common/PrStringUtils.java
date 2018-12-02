package common;

import org.apache.commons.lang3.StringUtils;

/**
 * 项目中string使用工具类
 * @author songpingping
 *         Created on 2018/3/17.
 */
public class PrStringUtils {


    /**
     * 手机号脱敏
     * @param mobile
     */
    public static String mobileTuoMin(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(mobile.substring(0,3)).append("****").append(mobile.substring(7));
        return stringBuffer.toString();
    }

    /**
     * 银行脱敏
     * @param accNo 银行卡号
     * @return
     */
    public static String accNoTuoMin(String accNo){
        if(StringUtils.isEmpty(accNo)){
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int tuoMiLength = accNo.length()-4;
        for(int i=0;i<12;i++){
            stringBuffer.append("*");
        }
        stringBuffer.append(accNo.substring(tuoMiLength));
        return stringBuffer.toString();
    }

    public static String idNoTuoMin(String idNo){
        if(StringUtils.isEmpty(idNo)){
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int len = idNo.length();
        stringBuffer.append(idNo.substring(0,6)).append("********").append(idNo.substring(len-4,len));
        return stringBuffer.toString();
    }

    public static String userNameTuoMin(String userName){
        if(StringUtils.isEmpty(userName)){
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(userName.substring(0,1));
        int tuoMiLength = userName.length();
        for(int i=1;i<tuoMiLength;i++){
            stringBuffer.append("*");
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(userNameTuoMin(""));
    }

}
