package test;

import java.sql.Timestamp;

import org.joda.time.DateTime;
import org.junit.Test;

import seven.tools.time.JodaTime;

public class JodaTimeTest {

	@Test
	public void runTess(){
		System.out.println(JodaTime.calRemainTime("2015-05-14 00:00:00"));
		System.out.println(JodaTime.calRemainTime("2015-04-18 00:00:00"));
		System.out.println(JodaTime.calRemainTime("2015-04-17 16:37:00"));
		
		System.out.println(JodaTime.calAge("2012-04-07"));
		DateTime dateTime = new DateTime();
		System.out.println(JodaTime.tranDBDate2Str(new Timestamp(dateTime.getMillis())));
		System.out.println(JodaTime.tranDBDatetime2Str(new Timestamp(dateTime.getMillis())));
		
		System.out.println(JodaTime.calMinutes("2015-04-17 00:20:00", "2015-04-17 00:25:00"));
	}

}
