import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Period;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Top {
	public static void main(String... args) {
		System.out.println("program started");
		try {	
			Robot robot = new Robot();
			TimeZone RDtz = TimeZone.getTimeZone("America/Santo_Domingo");
			
			while (true) {
				Calendar day = Calendar.getInstance();
				day.setTimeZone(RDtz);
				
				if (isWorkDay(day)) {
					if (isOnWorkingHours(day)) {
						robot.keyPress(KeyEvent.VK_CONTROL);
						robot.keyRelease(KeyEvent.VK_CONTROL);
						System.out.println("ctrl pressed");
						Thread.sleep(TimeUnit.MINUTES.toMillis(4));	
					} else {
						System.out.println("scheduling next working hour");
						Thread.sleep(getTimeToNextWorkingHour(day));
					}
				} else {
					System.out.println("on weekend adding one day");
					Thread.sleep(TimeUnit.DAYS.toMillis(1));
				}
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			System.out.println("program finished");
		}
	}
	
	private static boolean isWorkDay(Calendar calendar) {
		int today = calendar.get(Calendar.DAY_OF_WEEK);
		return (today > 0 && today < 7);
	}
	
	private static boolean isOnWorkingHours(Calendar today) {
		int hour = today.get(Calendar.HOUR_OF_DAY);
		return (hour >= 8 && hour < 17);
	}
	
	
	private static long getTimeToNextWorkingHour(Calendar today) {
		int hour = today.get(Calendar.HOUR_OF_DAY);
		long time = 0;
		Calendar next = Calendar.getInstance();
		next.setTimeZone(today.getTimeZone());
		next.set(Calendar.HOUR_OF_DAY, 8);
		next.set(Calendar.MINUTE, 0);
		next.set(Calendar.SECOND, 0);
		next.set(Calendar.MILLISECOND, 0);
		
		if (hour > 17) {
			next.add(Calendar.DATE, 1);
		}
		
		return today.getTimeInMillis() - next.getTimeInMillis();
	}
}