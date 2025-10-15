package wmf.helpers;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RobotHelper {

    public static void pressEnter(Robot robot) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void pressTab(Robot robot) {
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
    }

    public static void typeString(Robot robot, String text) throws InterruptedException {
        for (char c : text.toCharArray()) {
            boolean upperCase = Character.isUpperCase(c);
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);

            if (upperCase || isSpecialShiftChar(c)) {
                robot.keyPress(KeyEvent.VK_SHIFT);
            }

            robot.keyPress(keyCode);
            robot.keyRelease(keyCode);

            if (upperCase || isSpecialShiftChar(c)) {
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }

            Thread.sleep(100);
        }
    }

    public static boolean isSpecialShiftChar(char c) {
        return "~!@#$%^&*()_+{}|:\"<>?".indexOf(c) >= 0;

    }
}
