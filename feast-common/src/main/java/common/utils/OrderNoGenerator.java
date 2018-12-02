package common.utils;


import common.DatetimeUtil;

import java.util.Date;
import java.util.UUID;

public class OrderNoGenerator {


    public static String generateContractPdfName(){
        StringBuffer sbf = new StringBuffer("cc");
        sbf.append(getUUIDMostSignificantBits().substring(0, 4));
        sbf.append(getOrderNoDateStr(new Date()));
        sbf.append(".pdf");
        return sbf.toString();
    }

    public static String generateUserId(){
        StringBuilder userId = new StringBuilder("");
        userId.append(getOrderNoDateStr(new Date()));
        userId.append(getUUIDMostSignificantBits().substring(0, 6));
        return userId.toString();
    }

    public static String genOrderNo(String preStr){
        StringBuilder orderNo = new StringBuilder(preStr);
        orderNo.append(getOrderNoDateStr(new Date()));
        orderNo.append(getUUIDMostSignificantBits().substring(0, 10));
        return orderNo.toString();
    }

	
	private static String getUUIDMostSignificantBits() {
		String significant = String.valueOf(UUID.randomUUID().getMostSignificantBits());
		return significant.replace("-", "1");
	}
	
	/** uuid */
	public static String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/** 15 位 */
	private static String getOrderNoDateStr(Date date) {
		return DatetimeUtil.getCurrentDateTime("yyMMddHHmmssSSS");
	}
	
}
