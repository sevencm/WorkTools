package seven.tools.time;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaTime {
	private static final DateTimeFormatter FormateDateTime = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter FormateDate = DateTimeFormat.forPattern("yyyy-MM-dd");
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @param validTime - now()的时间
	 * @return 
	 * 
	 */
	public static String calRemainTime(String validTime) {
		DateTime validDateTime = DateTime.parse(validTime, FormateDateTime);
		DateTime nowDateTime = new DateTime();
		int days = Days.daysBetween(nowDateTime, validDateTime).getDays();
		if (days > 0) {
			return days + "天";
		}
		int hour = Hours.hoursBetween(nowDateTime, validDateTime).getHours();
		if (hour > 0) {
			return hour + "小时";
		}
		int minutes = Minutes.minutesBetween(nowDateTime, validDateTime).getMinutes();
		if (minutes > 0) {
			return minutes + "分钟";
		}
		int seconds = Seconds.secondsBetween(nowDateTime, validDateTime).getSeconds();
		if (seconds > 0) {
			return seconds + "秒";
		}
		return null;
	}
	
	/**
	 * 计算年龄
	 * @param birthday
	 * @return
	 */
	public static int calAge(String birthday){
		DateTime birthdate = DateTime.parse(birthday, FormateDate);
		DateTime nowDateTime = new DateTime();
		int days = Days.daysBetween(birthdate, nowDateTime).getDays();
		return days/365;
	}
	
	/**
	 * 数据库时间格式化->yyyy-MM-dd
	 * @param stamp
	 * @return
	 */
	public static String tranDBDate2Str(Timestamp stamp){
		DateTime date = new DateTime(stamp);
		return date.toString(FormateDate);
	}
	
	/**
	 * 数据库时间格式化->yyyy-MM-dd HH:mm:ss
	 * @param stamp
	 * @return
	 */
	public static String tranDBDatetime2Str(Timestamp stamp){
		DateTime date = new DateTime(stamp);
		return date.toString(FormateDateTime);
	}
	
	/**
	 * 计算时间差->分钟
	 * @param previous
	 * @param later
	 * @return
	 */
	public static int calMinutes(String previous, String later){
		if(previous.length()==10){
			DateTime previousDate = DateTime.parse(previous, FormateDate);
			DateTime laterDate = DateTime.parse(later, FormateDate);
			int minutes = Minutes.minutesBetween(previousDate, laterDate).getMinutes();
			return minutes;
		}else{
			DateTime previousDate = DateTime.parse(previous, FormateDateTime);
			DateTime laterDate = DateTime.parse(later, FormateDateTime);
			int minutes = Minutes.minutesBetween(previousDate, laterDate).getMinutes();
			return minutes;
		}
	}
}
