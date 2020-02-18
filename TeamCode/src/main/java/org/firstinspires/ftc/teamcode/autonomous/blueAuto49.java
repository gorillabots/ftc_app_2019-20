package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;
import org.firstinspires.ftc.teamcode.components.Alignment;
import org.firstinspires.ftc.teamcode.components.Grabber;
import org.firstinspires.ftc.teamcode.components.Hooks;

@Autonomous(group = "test", name = "blue49 - 3 Stone")

public class blueAuto49 extends GorillabotsCentral {
    public void runOpMode() {
        initializeComponentsAutonomous();

        int pos = getBlue();

        grabber.intake(Grabber.INTAKE_IN);
        alignment.alignment(Alignment.ALIGN_DOWN);
        grabber.rotate(Grabber.ROTATE_ALIGN);

        switch (pos){
            case 1:
                specialPush();
                sleep(1000);
                stopMotors();
                return;
                //break;
            case 2:
                MoveUntilEncoderGYRO(12,0,1,0);
                MoveUntilTouch(0,.2,0);
                break;
            case 3:
                return;
                //break;
        }

        grabber.rotate(Grabber.ROTATE_DOWN);

        sleep(550);

        grabber.rotate(Grabber.ROTATE_45);
        alignment.alignment(Alignment.ALIGN_45);
        grabber.intake(Grabber.INTAKE_IN * .1);

        MoveUntilEncoderGYRO(5,180,.3,0);

        MoveUntilEncoderGYRO(60,180,.5,-88); //Turn
        hooks.hookL(Hooks.HOOKL_MID);
        hooks.hookR(Hooks.HOOKR_MID);
        MoveUntilEncoderGYRO(28,180,.75,-88);
        TurnAbsolute(0,.2,5);
        MoveUntilRangeF(4,0,.5);
        hooks.setDown(true);

        MoveUntilEncoderGYRO(10,180,1,0);
        grabber.intake(Grabber.INTAKE_OUT);
        TurnAbsolute(90,.5,1);
        MoveUntilEncoderGYRO(20,180,1,98);
        hooks.setDown(false);
        TurnAbsolute(90,.2,.5);
        MoveUntilEncoderGYRO(55,180,1,93);
        MoveUntilEncoderGYRO(7,270,1,90);
        alignment.alignment(Alignment.ALIGN_DOWN);
        MoveUntilRangeB(5,180,.2);
        TurnAbsolute(0,.2,.5);
        grabber.rotate(Grabber.ROTATE_ALIGN);
        grabber.intake(Grabber.INTAKE_IN);
        MoveUntilTouch(0,.2,0);
        grabber.rotate(Grabber.ROTATE_DOWN);
        sleep(550);
        grabber.rotate(Grabber.ROTATE_45);
        alignment.alignment(Alignment.ALIGN_45);
        grabber.intake(Grabber.INTAKE_IN * .1);
        MoveUntilEncoderGYRO(3,180,.3,0);
        TurnAbsolute(90,.2,.5);
        MoveUntilEncoderGYRO(55,0,1,92);
        grabber.intake(Grabber.INTAKE_OUT);
        MoveUntilRangeF(4,0,.5);
        MoveUntilEncoderGYRO(20,180,1,92);
        MoveUntilRangeB(17,180,.2);
        alignment.alignment(Alignment.ALIGN_DOWN);
        MoveUntilEncoderGYRO(5,270,.5,90);
        TurnAbsolute(0,.2,.5);
        grabber.rotate(Grabber.ROTATE_ALIGN);
        grabber.intake(Grabber.INTAKE_IN);
        MoveUntilTouchRangeF(0,.2,0);
        grabber.rotate(Grabber.ROTATE_DOWN);
        sleep(550);
        grabber.rotate(Grabber.ROTATE_45);
        alignment.alignment(Alignment.ALIGN_45);
        grabber.intake(Grabber.INTAKE_IN * .1);
        MoveUntilEncoderGYRO(3,180,.3,0);
        TurnAbsolute(90,.2,.5);
        hooks.hookR(Hooks.HOOKR_DOWN);
        hooks.hookL(Hooks.HOOKL_MID);
        MoveUntilEncoderGYRO(40,0,1,92);
        MoveUntilRangeF(4,0,.5);
        hooks.hookL(Hooks.HOOKL_DOWN);
        grabber.intake(Grabber.INTAKE_OUT);
        specialPush();
        setParkerPos(900);
        sleep(500);
        hooks.hookL(Hooks.HOOKL_UP);
        MoveUntilEncoderGYRO(10,180,1,100);
        return;
    }
    public void specialPush()
    {
        setDriveEncoderOn(false);
        setMotorsBackwards();
        drive.mfr.setPower(-.7);
        drive.mbr.setPower(-.7);
        drive.mbl.setPower(-.3);
        drive.mfl.setPower(-.3);

    }
}
