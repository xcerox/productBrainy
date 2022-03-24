import java.awt.Robot;
import java.awt.event.KeyEvent;

public class Top {
	public static void main(String... args) {
		System.out.println("program started");
		try {	
			Robot robot = new Robot();
			
			while (true) {
				int time = 4000 * 60;
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				System.out.println("ctrl pressed");
				Thread.sleep(time);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			System.out.println("program finished");
		}
	}
}