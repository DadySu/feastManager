package common;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 描述:
 *
 */
public class AmountUtil {
    private static final long ZERO_AMOUNT = 0L;
    private static final BigDecimal HUNDRED = new BigDecimal("100");

    public static Long amountSubAmount(Long amount1,Long amount2){
        if(amount1 == null)
            amount1 = ZERO_AMOUNT;
        if (amount2 == null)
            amount2 = ZERO_AMOUNT;
        return amount1 - amount2;
    }

    public static Long addAmount(Long amount1,Long amount2){
        if(amount1 == null)
            amount1 = ZERO_AMOUNT;
        if (amount2 == null)
            amount2 = ZERO_AMOUNT;
        return amount1 + amount2;
    }

    public static Long calcInterestAmount(Long amount,Long rate,Integer period){
        if(rate == null || amount == null || period == null)
            return 0L;
        return ( amount * rate * period) / (10000 * 365 );
    }

    public static Long calcRateAmount(Long amount,Long rate,Integer days){
        if(rate == null || amount == null || days == null)
            return 0L;
        return ( amount * rate * days) /10000;
    }

    public static Long calcRateAmountDecimal(Long amount,Long rate,Integer days){
        if (rate == null || amount == null || days == null)
            return 0L;
        BigDecimal amountDec = new BigDecimal(amount);
        BigDecimal rateDec = new BigDecimal(rate);
        BigDecimal daysDec = new BigDecimal(days);
        String resultString = amountDec.multiply(rateDec).multiply(daysDec).divide(new BigDecimal("10000"),0,RoundingMode.HALF_UP).toString();
        return Long.parseLong(resultString);
    }

    public static Long getRateByDivideDeciaml(Long dayAmount,Long totalAmount){
        if (dayAmount == null || totalAmount == null )
            return 0L;
        BigDecimal dayAmountDec = new BigDecimal(dayAmount);
        BigDecimal totalAmountDec = new BigDecimal(totalAmount);
        String rateString = dayAmountDec.multiply(new BigDecimal("10000")).divide(totalAmountDec,0,RoundingMode.HALF_UP).toString();
        return Long.parseLong(rateString);
    }

    /**
	 * 分转元
	 * 
	 * @param fen
	 * @return
	 */
	public static String fenToYuan(Long fen) {
		if (fen != null) {
			return fenToYuan(fen.toString());
		}
		return null;
	}
	
	/**
	 * 分转元
	 * 
	 * @param fen
	 * @return
	 */
	public static String fenToYuan(String fen) {
		if (StringUtils.isNotBlank(fen)) {
			return new BigDecimal(fen).divide(HUNDRED, 2, 0).toString();
		}
		return null;
	}
	
	public static String accuracy(Long num, Long total) {
		return accuracyWithDigits(num, total, 0);
	}
	
	/**
	 * 计算百分比,保留小数位
	 * 
	 * @param num
	 * @param total
	 * @param digits
	 * @return
	 */
	public static String accuracyWithDigits(double num, double total, int digits) {
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		// 可以设置精确几位小数
		df.setMinimumFractionDigits(digits);
		df.setMaximumFractionDigits(digits);
		// 模式 去尾
		df.setRoundingMode(RoundingMode.DOWN);
		if (num > total) {
			return df.format(100);
		} else if (total <= 0) {
			return df.format(0);
		}
		double accuracy_num = num / total * 100;
		return df.format(accuracy_num);
	}

    /**
     * 计算复合年化利率
     * @param realRate
     * @return
     */
    public static Long calcCompoundAnnualInterest(Long realRate){
        if(realRate <= 0) return 0L;
        Double annualRate = (Math.pow(realRate / (365 * 10000.0) + 1.0, 365) - 1.0) * 10000;
        return annualRate.longValue();
    }

    //格式化百分比(利率)
    public static String formatPercent(Long rate){
        StringBuffer sbf = new StringBuffer();
        sbf.append(rate / 100);
        Long remainder = rate % 100;
        if(remainder > 10 && remainder %10 == 0){
            sbf.append(".").append(remainder/10);
        } else if (remainder > 10){
            sbf.append(".").append(remainder);
        } else if (remainder > 0){
            sbf.append(".").append(0).append(remainder);
        }
        sbf.append("%");
        return sbf.toString();
    }


    /*public static void main(String[] args) {
        System.err.println(AmountUtil.formatPercent(203001L));
    }*/

    /**
     * 两个数相乘向上保留m小数
     * @param a
     * @param b
     * @return
     */
    public static  int roundOne(int a,double b,int m){
        BigDecimal d = new BigDecimal(b);
        BigDecimal e = d.multiply(new BigDecimal(a));
        int f = e.setScale(m, BigDecimal.ROUND_HALF_UP).intValue();
        return f;
    }

}
