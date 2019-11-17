package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;
import org.firstinspires.ftc.teamcode.components.Grabber;

@Autonomous(group="Autonomous", name="red10")
public class redAuto10 extends GorillabotsCentral
{
    public void runOpMode()
    {
        initializeComponents();

        waitForStart();

        MoveUntilEncoder(34,0,.3);//approach the platform
        hooks.setDown(true);
        sleep(1000);
        MoveUntilTime(500,135,.3);//ensure traction

        MoveUntilEncoder(30,180,.5);//go back
        MoveUntilTime(2000,180,.3); //ram wall for stability
        MoveUntilTime(1000,90,.5); //ram corner for accuracy
        hooks.setDown(false);
        grabber.rotate(Grabber.ROTATE_45);
        sleep(25000);
    }
}
