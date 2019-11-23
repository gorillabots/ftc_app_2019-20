package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;
import org.firstinspires.ftc.teamcode.components.Grabber;

@Autonomous(group="test", name="blue43")

public class blueAuto43 extends GorillabotsCentral
{
    public void runOpMode()
    {
        initializeComponents();

        int pos = getSkystonePos();

        pos = 2;
        MoveUntilEncoder(5,0,.3);

        switch (pos)
        {
            case 1:
                MoveUntilEncoder(10,270,.3);
                break;
            case 2:
                MoveUntilEncoder(4,90,.3);
                break;
            case 3:
                MoveUntilEncoder(18,90,.3);
                break;
        }

        MoveUntilTime(500,180,.3);
        grabber.rotate(Grabber.ROTATE_ALIGN);
        grabber.intake(Grabber.INTAKE_IN);
        //ADrive.driveCartesian(.3,.3,50);
        MoveUntilEncoder(27,0,.3);
        grabber.rotate(Grabber.ROTATE_DOWN);
        sleep(1000);
        grabber.intake(Grabber.INTAKE_HOLD);
        grabber.rotate(Grabber.ROTATE_45);

        //sleep(50000);
        MoveUntilEncoder(10,180,.3);
        TurnAbsolute(-90,.2,.5);
        MoveUntilEncoder(100,177,.3);
        MoveUntilTime(1000,180,.3);
        MoveUntilEncoder(10,0,.3);
        TurnAbsolute(0,.2,.5);
        MoveUntilEncoder(10,0,.3);
        sleep(700);
        hooks.setDown(true);
        sleep(800);
        grabber.rotate(Grabber.ROTATE_DOWN);
        grabber.intake(Grabber.INTAKE_OUT);
        //sleep(500);
        MoveUntilEncoder2(38,180,.3);
        //sleep(500);
        grabber.rotate(Grabber.ROTATE_45);
        grabber.intake(Grabber.INTAKE_HOLD);
        TurnAbsolute(50,.9,1); //and lift
        MoveUntilTime(2000,0,.3);
        hooks.setDown(false);
        MoveUntilEncoder(10, 180,.5);
        TurnAbsolute(0,.2,.5);

        sleep(50000);

        MoveUntilTime(2000,180,.4);
        MoveUntilEncoder(37,0,.35);
        TurnAbsolute(-90,.2,.5);
        MoveUntilEncoder(70,180,.5);
        MoveUntilTime(500,180,.4);

        switch(pos) {
            case 1:
                MoveUntilEncoder(20, 0, .3);
                TurnAbsolute(0,.2,.5);
                break;
            case 2:
                MoveUntilEncoder(14, 0, .3);
                TurnAbsolute(0,.2,.5);
                break;
            case 3:
                TurnAbsolute(29,.2,.5);
                break;
        }

        grabber.intake(Grabber.INTAKE_IN);
        grabber.rotate(Grabber.ROTATE_DOWN);
        sleep(500);
        grabber.intake(Grabber.INTAKE_HOLD);
        grabber.rotate(Grabber.ROTATE_45);
        TurnAbsolute(-90,.2,.5);
        MoveUntilEncoder(70, 0,.5);
        MoveUntilTime(500,0,.5);
        grabber.rotate(Grabber.ROTATE_DOWN);
        grabber.intake(Grabber.INTAKE_OUT);
        sleep(1000);
        grabber.rotate(Grabber.ROTATE_45);
        grabber.intake(Grabber.INTAKE_HOLD);

    }
}
