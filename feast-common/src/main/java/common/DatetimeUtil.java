package common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatetimeUtil {
    private static final int MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;  // 时间计算格式

    /**
     * not called
     */
    private DatetimeUtil() {

    }
    
    /****************Date for String --start********************/
    public static String sqlDate2StrFormat(Date date,String format) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
    
    public static String utilDate2StrFormat(Date date,String format) {
    	if (date == null) {
    		return "";
    	}
    	DateFormat df = new SimpleDateFormat(format);
    	return df.format(date);
    }

    /****************String for Date  ********************/
    public static Date str2FormatDate(String str,String format) throws ParseException {
    	if(str == null){
    		return null;
    	}
    	if(str.contains(".")){
    		str= str.replace(".", "-");
    	}
        DateFormat df = new SimpleDateFormat(format);
        return new Date(df.parse(str).getTime());
    }
    /**
     * yyyy-MM-dd格式转yyyy.MM.dd
     * @param date
     * @return
     */
    public static String date2date(String date) {
    	if("".equals(date)){
    		return "";
    	}
        SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        try {
        	date = sdf.format(df.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
    }


    /****************Timestamp for String  ********************/
    public static String timestamp2StrFormat(Timestamp t,String format) {
    	if (t == null) {
    		return "";
    	}

    	DateFormat df = new SimpleDateFormat(format);
    	return df.format(t);
    }


    public static Timestamp str2Timestamp(String str) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = df.parse(str);
        String time = df.format(date);
        return Timestamp.valueOf(time);
    }

    // T+1 非工作日
    public static Date periodStartTime(Integer period) {
        Calendar time = Calendar.getInstance();
        int day = time.get(Calendar.DAY_OF_YEAR);
        time.set(Calendar.DAY_OF_YEAR, day + 1);
        time.set(Calendar.MONTH, time.get(Calendar.MONTH) + period - 1);
        return new Date(time.getTime().getTime());
    }

    // 给指定添加天数
    public static Timestamp addDay(Timestamp timestamp, Integer iNbDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        cal.add(Calendar.DAY_OF_MONTH, iNbDay);
        return new Timestamp(cal.getTime().getTime());
    }
    //给当前时间添加天数 yyyy-MM-dd HH:mm:ss
    public static Timestamp addDayToCurrentDateTime(Integer iNbDay){
        Timestamp timestamp = getCurrentTimestamp();
        return addDay(timestamp,iNbDay);
    }

    public static Date addDay(Date date, Integer iNbDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, iNbDay);
        return new Date(cal.getTime().getTime());
    }

    public static Date adMonth(Date date, Integer num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, num);
        return new Date(cal.getTime().getTime());
    }

    public static Date periodEndTime(Integer period) {
        Calendar time = Calendar.getInstance();
        int day = time.get(Calendar.DAY_OF_YEAR);
        time.set(Calendar.DAY_OF_YEAR, day + 1);
        time.set(Calendar.MONTH, time.get(Calendar.MONTH) + period);
        return new Date(time.getTime().getTime());
    }

    /**
     * 获取系统当前时间
     *
     * @return
     */
    public static Date getCurrentDate(){
    	return new Date();
    }

    public static String getCurrentDateTime(String formatType) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(date);
    }
    public static Timestamp getCurrentTimestamp(){
        String tsStr = DatetimeUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss");
        return Timestamp.valueOf(tsStr);
    }

    /**
     * 返回两个日期之间的天数
     *
     * @param start
     *            开始日期
     * @param end
     *            结束日期
     * @return 间隔的天数
     */
    public static int getDateInterval(Date start, Date end) {
        GregorianCalendar startCal = new GregorianCalendar();
        GregorianCalendar endCal = new GregorianCalendar();
        startCal.setTime(start);
        endCal.setTime(end);
        // 得到两个日期相差的天数
        int days = ((int) (endCal.getTime().getTime() / 1000) - (int) (startCal.getTime().getTime() / 1000)) / 3600
                / 24;
        return days;
    }

    public static int dateDiff(String start, String end) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("zh", "cn"));
        try {
            long t1 = simpleDateFormat.parse(start).getTime();
            long t2 = simpleDateFormat.parse(end).getTime();
            int diff = (int) ((t2 - t1) / 1000);
            return diff;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 取得两个时间之间相差的天数,参数类型为Date
     * @param startDate 开始的时间
     * @param endDate 结束的时间
     * @return ( endDate - startDate +1 )
     */
    public static int getDaysBetween(Date startDate, Date endDate) {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(startDate);
        calendarStart.set(calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DATE), 0, 0, 0);
        calendarStart.set(Calendar.MILLISECOND, 0);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(endDate);
        calendarEnd.set(calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH), calendarEnd.get(Calendar.DATE), 0, 0, 0);
        calendarEnd.set(Calendar.MILLISECOND, 0);

        long startTime = calendarStart.getTimeInMillis();
        long endTime = calendarEnd.getTimeInMillis();
        long totalDate = Math.abs((endTime - startTime)) / (MILLIS_IN_A_DAY);
        return (int) totalDate + 1;
    }


    /**
     * 两个时间相差距离多少分
     *
     * @param str1
     *            时间参数 1 格式：1990-01-01 12:00:00
     * @param str2
     *            时间参数 2 格式：2009-01-01 12:00:00
     * @return long 返回值为：分
     */
    public static long getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long min = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            min = diff / (60 * 1000);
        } catch (ParseException e) {
        }
        return min;
    }

    public static boolean isValidDate(String str) {
        String patternStr = "^((((19|20)(([02468][048])|([13579][26]))\\-02\\-29))|((20[0-9][0-9])|(19[0-9][0-9]))\\-((((0[1-9])|(1[0-2]))\\-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))\\-31)|(((01,3-9])|(1[0-2]))\\-(29|30)))))$";
        Pattern p = Pattern.compile(patternStr);
        Matcher m = p.matcher(str);
        if (m.matches())
            return true;
        else
            return false;
    }

    public static String dateDiffForTime(Long endTimeSec, Long startTimeSec) {

        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0L;// 计算差多少天
        long hour = 0L;// 计算差多少小时
        long min = 0L;// 计算差多少分钟
        long sec = 0L;// 计算差多少秒
        StringBuffer result = new StringBuffer();

        // 获得两个时间的毫秒时间差异
        diff = endTimeSec - startTimeSec;
        if (diff >= 0) {
            day = diff / nd;// 计算差多少天
            hour = diff % nd / nh;// 计算差多少小时
            min = diff % nd % nh / nm;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒

            result.append(day + "天");
            if (hour < 10) {
                result.append("0" + hour + "小时");
            } else {
                result.append(hour + "小时");
            }
            if (min < 10) {
                result.append("0" + min + "分");
            } else {
                result.append(min + "分");
            }
            if (sec < 10) {
                result.append("0" + sec + "秒");
            } else {
                result.append(sec + "秒");
            }
        } else {
            result.append("0天00小时00分00秒");
        }
        // 输出结果
        return result.toString();
    }

    public static String dateDiffForTimeValue(Long endTimeSec, Long startTimeSec) {

        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0L;// 计算差多少天
        long hour = 0L;// 计算差多少小时
        long min = 0L;// 计算差多少分钟
        long sec = 0L;// 计算差多少秒
        StringBuffer result = new StringBuffer();

        // 获得两个时间的毫秒时间差异
        diff = endTimeSec - startTimeSec;
        if (diff >= 0) {
            day = diff / nd;// 计算差多少天
            hour = diff % nd / nh;// 计算差多少小时
            min = diff % nd % nh / nm;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒

            result.append(day + ":");
            result.append(hour + ":");
            result.append(min + ":");
            result.append(sec);

        } else {
            result.append("0:0:0:0");
        }
        // 输出结果
        return result.toString();
    }

    public static String dateDiffForUsedTime(Long endTimeSec, Long startTimeSec) {

        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0L;// 计算差多少天
        long hour = 0L;// 计算差多少小时
        long min = 0L;// 计算差多少分钟
        long sec = 0L;// 计算差多少秒
        StringBuffer result = new StringBuffer();

        // 获得两个时间的毫秒时间差异
        diff = endTimeSec - startTimeSec;
        if (diff >= 0) {
            day = diff / nd;// 计算差多少天
            hour = diff % nd / nh;// 计算差多少小时
            min = diff % nd % nh / nm;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒
            if (day > 0) {
                result.append(day + "天");
            }
            if (hour > 0) {
                result.append(hour + "小时");
            }
            if (min > 0) {
                result.append(min + "分");
            }
            if (sec > 0) {
                result.append(sec + "秒");
            }
        }
        // 输出结果
        return result.toString();
    }

    public static String millisecondsToTime(long milliseconds) {

        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数

        long day = 0L;// 计算差多少天
        long hour = 0L;// 计算差多少小时
        long min = 0L;// 计算差多少分钟
        long sec = 0L;// 计算差多少秒

        day = milliseconds / nd;// 计算差多少天
        hour = milliseconds % nd / nh;// 计算差多少小时
        min = milliseconds % nd % nh / nm;// 计算差多少分钟
        sec = milliseconds % nd % nh % nm / ns;// 计算差多少秒

        return String.format("%02d天%02d时%02d分%02d秒", day, hour, min, sec);
    }

    public static int compareDate(String startDate, String endDate, String formatter) {
        int compareResult = 0;
        DateFormat dateFormat = new SimpleDateFormat(formatter);

        try {
            Date dateTime1 = dateFormat.parse(startDate);
            Date dateTime2 = dateFormat.parse(endDate);
            compareResult = dateTime1.compareTo(dateTime2);
        } catch (Exception e) {
        }

        return compareResult;
    }

    /**
     * 当前时间添加小时
     *
     * @param iNbTime
     * @return
     */
    public static Timestamp addHour(Timestamp timestamp, Integer iNbTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        cal.add(Calendar.HOUR_OF_DAY, iNbTime);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 当前时间添加分钟
     *
     * @param iNbTime
     * @return
     */
    public static Timestamp addMinute(Timestamp timestamp, Integer iNbTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp);
        cal.add(Calendar.MINUTE, iNbTime);
        return new Timestamp(cal.getTime().getTime());
    }

    /**
     * 获得当前系统日期
     */
    public static String getDate(Timestamp timestamp) {
        return new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
    }

    /**
     *
     * @param nowDate
     * @param endDate
     * @return
     */
    public static int getSeconds(Timestamp nowDate, Timestamp endDate) {
        int n = 0;
        try {
            long now = nowDate.getTime();
            long end = endDate.getTime();
            n = (int) ((end - now) / 1000);
        } catch (Exception e) {
        }
        return n;
    }

    public static String getCurrentWeekFirstDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        // 获取本周一日期
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return df.format(cal.getTime());
    }

    /**
     * 返回开始日期
     *
     * @param periodType
     *            周期类型：本周、近一月、近3月、近半年
     * @return
     */
    public static String getStateDatetime(String periodType) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        if ("tswk".equals(periodType)) {
            c.add(Calendar.DATE, -7);
        } else if ("lastMonth".equals(periodType)) {
            c.add(Calendar.MONTH, -1);
        } else if ("threeMonths".equals(periodType)) {
            c.add(Calendar.MONTH, -3);
        } else if ("sixMonths".equals(periodType)) {
            c.add(Calendar.MONTH, -6);
        } else {
            return null;
        }
        Date monday = c.getTime();
        String preMonday = sdf.format(monday) + " 00:00:00";
        return preMonday;

    }

    /**
     * 时间比较大小
     */
    public static boolean compareDate(Date nowTime, Date endTime) {
        try {
            long nowSeconds = nowTime.getTime();
            long endSeconds = endTime.getTime();

            if (endSeconds < nowSeconds)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 时间比较大小
     */
    public static boolean compareTimestamp(Timestamp nowTime, Timestamp endTime) {
        try {
            long nowSeconds = nowTime.getTime();
            long endSeconds = endTime.getTime();

            if (endSeconds < nowSeconds)
                return true;
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 得到当前日期和num个月之前的日期
     *
     * @param num
     * @return
     */
    public static String[] getDateAndLastDateByNum(Date datenow, int num) {
        String[] str_1 = new String[2];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(datenow);
        String dateend = format.format(datenow);// 当前时间
        cal.add(Calendar.MONTH, -num);
        Date datelastM = cal.getTime();
        String datebegin = format.format(datelastM);
        str_1[0] = datebegin;
        str_1[1] = dateend;
        return str_1;
    }


    /**
     * 获取两个日期相差的年数，保留一位小数
     * @param firstdate
     * @param seconddate
     * @return
     */
    public static String getYearByTwoDate(long firstdate,long seconddate){
    	double operratedate = ((firstdate - seconddate)/1000.0/60/60/24/365);
    	DecimalFormat df = new DecimalFormat("0.0");
    	String result = df.format(operratedate);
    	return result;
    }

    /**
	 * 无时间格式的时间字符串
	 * @param date
	 * @return
	 */
	public static String date2StringDatetimeNoFormat(Date date) {
		if (date == null) {
			return "";
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String dataStr = df.format(date).trim().replaceAll("-","").replaceAll(" ","").replaceAll(":","");
		dataStr = dataStr.replace(".","");
		return dataStr;
	}

    public static String timeFormat(Timestamp t,String format) {
        if (t == null) {
            return "";
        }

        DateFormat df = new SimpleDateFormat(format);
        return df.format(t);
    }

    public static String dateFormat(Date date,String format) {
        if (date == null) {
            return "";
        }

        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }
    public static Long getDaysDelta(Date startDate,Date endDate){
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(startDate);
        calendarStart.set(calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DATE), 0, 0, 0);
        calendarStart.set(Calendar.MILLISECOND, 0);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(endDate);
        calendarEnd.set(calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH), calendarEnd.get(Calendar.DATE), 0, 0, 0);
        calendarEnd.set(Calendar.MILLISECOND, 0);

        long startTime = calendarStart.getTimeInMillis();
        long endTime = calendarEnd.getTimeInMillis();
        long totalDate = (endTime - startTime) / (MILLIS_IN_A_DAY);
        return totalDate;
    }

    /**
     * 返回系统当前的完整日期时间 <br>
     * 格式 1：2008-05-02 13:12:44 <br>
     * 格式 2：2008/05/02 13:12:44 <br>
     * 格式 3：2008年5月2日 13:12:44 <br>
     * 格式 4：2008年5月2日 13时12分44秒 <br>
     * 格式 5：2008年5月2日 星期五 13:12:44 <br>
     * 格式 6：2008年5月2日 星期五 13时12分44秒 <br>
     * 格式 7：20080502 <br>
     * 格式 8：20080502131244 <br>
     * 格式 9：2008-05-02 <br>
     * 格式 10：2008_05 <br>
     * 格式 11：2008 <br>
     * 格式 12：200805 <br>
     * 格式 13：2008-05 <br>
     * 格式 13：13 <br>
     * 格式 default：yyyyMMddHHmmss:20080502131244 <br>
     *
     * @param formatType (formatType) :格式代码号
     * @return 字符串
     */
    public static String get(int formatType, Date date) {
        SimpleDateFormat sdf = null;
        switch (formatType) {
            case 1:
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case 2:
                sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                break;
            case 3:
                sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                break;
            case 4:
                sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                break;
            case 5:
                sdf = new SimpleDateFormat("yyyy年MM月dd日 E HH:mm:ss");
                break;
            case 6:
                sdf = new SimpleDateFormat("yyyy年MM月dd日 E HH时mm分ss秒");
                break;
            case 7:
                sdf = new SimpleDateFormat("yyyyMMdd");
                break;
            case 8:
                sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                break;
            case 9:
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case 10:
                sdf = new SimpleDateFormat("yyyy_MM");
                break;
            case 11:
                sdf = new SimpleDateFormat("yyyy");
                break;
            case 12:
                sdf = new SimpleDateFormat("yyyyMM");
                break;
            case 13:
                sdf = new SimpleDateFormat("yyyy-MM");
                break;
            case 14:
                sdf = new SimpleDateFormat("yyyy年MM月dd日");
                break;
            case 15:
                sdf = new SimpleDateFormat("MM月dd日");
                break;
            case 16:
                sdf = new SimpleDateFormat("HH");
                break;
            default:
                sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                break;
        }
        sdf.setLenient(false);
        return sdf.format(date);
    }

    /**
     * 加 days 天
     * @param date
     * @param days
     * @return
     */
    public static Date addDay(Date date, int days) {
        if(date == null) return null;
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date addMinute(Date date, int minute) {
        if(date == null) return null;
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    public static String timeFormatOne(Timestamp t) {
        return timeFormat(t,"yyyy-MM-dd HH:mm:ss");
    }

}
