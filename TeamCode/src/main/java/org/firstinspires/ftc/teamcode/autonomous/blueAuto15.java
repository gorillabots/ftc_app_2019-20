package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;
import org.firstinspires.ftc.teamcode.components.Grabber;

@Autonomous(group="test", name="blue15")
public class blueAuto15 extends GorillabotsCentral
{
    public void runOpMode()
    {
        initializeComponents();

        waitForStart();

        MoveUntilEncoder(34,0,.3);
        hooks.setDown(true);
        sleep(1000);
        MoveUntilTime(500,225,.3);
        MoveUntilEncoder(30,180,.5);
        MoveUntilTime(2000,180,.3);
        hooks.setDown(false);
        grabber.rotate(Grabber.ROTATE_45);
        sleep(1000);
        MoveUntilEncoder(40,90,.5);
        TurnAbsolute(90);
        MoveUntilEncoder(30,180,1);
        MoveUntilTime(1000,270,.3);

        sleep(1000);
    }
}
