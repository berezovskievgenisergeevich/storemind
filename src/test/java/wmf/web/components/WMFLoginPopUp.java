package wmf.web.components;

import io.qameta.allure.Step;
import wmf.helpers.RobotHelper;

import java.awt.*;

public class WMFLoginPopUp {

    @Step("entering data in the authentication pop-up window email: {email} pass {pass}")
    public void doLogin(String email, String pass) {

        try {
            Robot robot = new Robot();
            Thread.sleep(1000);

            RobotHelper.typeString(robot, email);

            RobotHelper.pressTab(robot);
            Thread.sleep(500);

            RobotHelper.typeString(robot, pass);

            Thread.sleep(500);

            RobotHelper.pressEnter(robot);
        } catch (Exception e) {
            System.out.println("error entering characters");
        }
    }



}
