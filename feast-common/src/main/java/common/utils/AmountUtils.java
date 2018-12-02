package common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Description:
 * @Author mengfanzhu
 * @Date 3/21/18 11:10
 * @Version 1.0
 */
@Slf4j
public class AmountUtils {

    public static String bigDecimal2String(BigDecimal d) {
        return d == null?"0.00":fmtMicrometer(d.toPlainString());
    }

    public static String bigDecimalToString(BigDecimal d) {
        return d == null?"0.00":fmtMicrometerToString(d.toPlainString());
    }

    public static String fmtMicrometer(String text) {
        if(StringUtils.isEmpty(text)){return "0.00";}
        String str = checkAmountNumber(text);
        DecimalFormat df = null;
        if(str.indexOf(".") > 0) {
            if(str.length() - str.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0.00");
        }
        double number = 0.0D;
        try {
            number = Double.parseDouble(text);
        } catch (Exception var6) {
            log.info("StringUtil--- fmtMicrometer(),出现异常",var6);
            number = 0.0D;
        }
        return df.format(number);
    }

    public static String fmtNumToString(int text) {
        DecimalFormat df =  new DecimalFormat("###,##0");
        double number = 0.0D;
        try {
            number = Double.parseDouble(text+"");
        } catch (Exception var6) {
            log.info("StringUtil--- fmtMicrometer(),出现异常",var6);
            number = 0D;
        }
        return df.format(number);
    }
    public static String fmtMicrometerToString(String text) {
        if(StringUtils.isEmpty(text)){return "0.00";}
        String str = checkAmountNumber(text);
        DecimalFormat df = null;
        if(str.indexOf(".") > 0) {
            if(str.length() - str.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("#####0.");
            } else {
                df = new DecimalFormat("#####0.00");
            }
        } else {
            df = new DecimalFormat("#####0.00");
        }
        double number = 0.0D;
        try {
            number = Double.parseDouble(text);
        } catch (Exception var6) {
            log.info("StringUtil--- fmtMicrometer(),出现异常",var6);
            number = 0.0D;
        }
        return df.format(number);
    }
    public static String checkAmountNumber(String value) {
        return null != value && !"".equals(value)?value:"0.00";
    }
}
