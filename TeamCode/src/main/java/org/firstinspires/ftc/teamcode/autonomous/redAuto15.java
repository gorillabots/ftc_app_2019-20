package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;
import org.firstinspires.ftc.teamcode.components.Grabber;

@Autonomous(group="test", name="red15")
public class redAuto15 extends GorillabotsCentral
{
    public void runOpMode()
    {
        initializeComponents();

        waitForStart();

        MoveUntilEncoder(34,0,.3);
        hooks.setDown(true);
        sleep(1000);
        MoveUntilTime(500,135,.3);
        MoveUntilEncoder(30,180,.5);
        MoveUntilTime(2000,180,.3);
        MoveUntilTime(1000,90,.5);
        hooks.setDown(false);
        grabber.rotate(Grabber.ROTATE_45);
        sleep(1000);
        MoveUntilEncoder(50,270,.5);
        TurnAbsolute(-90);
        MoveUntilEncoder(25,180,1);
        MoveUntilTime(1500,90,.3);

        sleep(1000);
    }
}
