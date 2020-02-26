package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;
import org.firstinspires.ftc.teamcode.components.Alignment;
import org.firstinspires.ftc.teamcode.components.Grabber;
import org.firstinspires.ftc.teamcode.components.Hooks;

@Autonomous(group = "test", name = "blue43touch")

public class blueAuto43touch extends GorillabotsCentral {
    public void runOpMode() {
        initializeComponentsAutonomous();

        int pos = getBlue();

        grabber.intake(Grabber.INTAKE_IN);
        alignment.alignment(Alignment.ALIGN_DOWN);
        grabber.rotate(Grabber.ROTATE_ALIGN);

        switch (pos) {
            case 1:
                MoveUntilEncoderGYRO(5, 0, .3, 0);
                MoveUntilEncoderGYRO(11.5, 270, .3, 0);
                MoveUntilEncoderGYRO(8, 0, .7, 0);
                MoveUntilTouchRangeF(0, .2, 0);
                break;
            case 2:
                MoveUntilEncoderGYRO(12, 0, 1, 0);
                MoveUntilTouchRangeF(0, .2, 0);
                break;
            case 3:
                MoveUntilEncoderGYRO(5, 0, .3, 0);
                MoveUntilEncoderGYRO(7, 90, .3, 0);
                MoveUntilEncoderGYRO(8, 0, .7, 0);
                MoveUntilTouchRangeF(0, .2, 0);
                break;

        }
        grabber.rotate(Grabber.ROTATE_DOWN); //first block
        sleep(600);
        grabber.rotate(Grabber.ROTATE_45);
        alignment.alignment(Alignment.ALIGN_45);
        grabber.intake(Grabber.INTAKE_IN * .1);

        MoveUntilEncoder(5, 180, .3);
        TurnAbsolute(-90, .2, .5);
        switch (pos) {

            case 1:
                MoveUntilEncoderGYRO(54, 180, .7, -88);
                break;
            case 2:
                MoveUntilEncoderGYRO(60, 180, .7, -88);
                break;
            case 3:
                MoveUntilEncoderGYRO(68, 180, .7, -88);
                break;
        }
        MoveUntilRangeB(28, 180, .3);

        grabber.rotate(Grabber.ROTATE_INIT);
        alignment.alignment(Alignment.ALGIN_INIT);
        TurnAbsolute(0, .2, .5);
        MoveUntilRangeF(4, 0, .3);
        grabber.rotate(Grabber.ROTATE_DOWN);
        grabber.intake(Grabber.INTAKE_OUT);
        sleep(550);
        grabber.intake(Grabber.INTAKE_OUT);
        grabber.rotate(Grabber.ROTATE_45);
        MoveUntilEncoder(5, 180, .3);
        TurnAbsolute(93, .2, .5);
        // MoveUntilEncoder(100,180,.3);

        switch (pos) {
            case 1:
                MoveUntilEncoderGYRO(86, 180, .7, 90);
                MoveUntilRangeB(22, 180, .3);
                MoveUntilEncoderGYRO(7, 270, .5, 90);
                alignment.alignment(Alignment.ALIGN_DOWN);
                grabber.intake(Grabber.INTAKE_IN);
                grabber.rotate(Grabber.ROTATE_ALIGN);
                TurnAbsolute(0, .2, .5);
                MoveUntilTouchRangeF(0, .2, 0);
                grabber.rotate(Grabber.ROTATE_DOWN);
                sleep(750);
                break;
            case 2:
                MoveUntilEncoderGYRO(100, 180, .7, 90);
                MoveUntilRangeB(5, 180, .3);
                MoveUntilEncoderGYRO(7, 270, .5, 90);
                alignment.alignment(Alignment.ALIGN_DOWN);
                grabber.intake(Grabber.INTAKE_IN);
                grabber.rotate(Grabber.ROTATE_ALIGN);
                TurnAbsolute(0, .2, .5);
                MoveUntilTouchRangeF(8, 0, .2);
                grabber.rotate(Grabber.ROTATE_DOWN);
                sleep(750);
                break;
            case 3: //hard
                MoveUntilEncoderGYRO(100, 180, .7, 90);
                MoveUntilRangeB(5, 180, .3);
                alignment.alignment(Alignment.ALIGN_DOWN);
                grabber.intake(Grabber.INTAKE_IN);
                grabber.rotate(Grabber.ROTATE_ALIGN);
                TurnAbsolute(0, .2, .5);
                MoveUntilTime(500, 90, .5);
                MoveUntilTime(250, 180, .4);
                grabber.rotate(Grabber.ROTATE_ALIGN);
                MoveUntilRangeF(12.5,0, .2);
                TurnAbsolute(-29, .2, .5);
                MoveUntilEncoderGYRO(6, 0, .4, -30);
                grabber.rotate(Grabber.ROTATE_DOWN);
                sleep(500);
                TurnAbsolute(0, .2, .5);
                sleep(400);
                break;
        }
        grabber.rotate(Grabber.ROTATE_45);
        alignment.alignment(Alignment.ALIGN_45);
        grabber.intake(Grabber.INTAKE_IN * .1);
        switch (pos) {
            case 1:
                MoveUntilEncoder(2, 180, .3);
                break;
            case 2:
                MoveUntilEncoder(2, 180, .3);
                break;
            case 3:
                MoveUntilEncoder(3.5, 180, .3);
                break;
        }

        TurnAbsolute(90, .2, .5);
        switch (pos) {
            case 1:
                MoveUntilEncoderGYRO(100, 0, .7, 93);
                break;
            case 2:
                MoveUntilEncoderGYRO(106, 0, .7, 93);
                break;
            case 3:
                MoveUntilEncoderGYRO(106, 0, .7, 93);
                break;
        }
        grabber.rotate(Grabber.ROTATE_INIT);
        alignment.alignment(Alignment.ALGIN_INIT);
        hooks.hookR(Hooks.HOOKR_MID);
        hooks.hookL(Hooks.HOOKL_MID);

        MoveUntilRangeF(20, 0, .5);
        TurnAbsolute(0, .2, .5);
        MoveUntilRangeF(4, 0, .3);
        hooks.setDown(true);
        grabber.rotate(Grabber.ROTATE_45);
        grabber.intake(Grabber.INTAKE_OUT);
        sleep(250);
        grabber.rotate(Grabber.ROTATE_INIT);
        MoveUntilEncoder(4, 180, .2);
        MoveUntilEncoder(14, 180, 1);
        specialTurn();
        grabber.intake(Grabber.INTAKE_HOLD);
        hooks.setDown(false);
        MoveUntilTime(500, 0, .7);
        MoveUntilEncoder(8, 160, .5);
        MoveUntilEncoderGYRO(10,90,1,90);
        TurnAbsolute(102, .2, .5);
        setParkerPos(900);
        parker.parkerPow(0);
        MoveUntilEncoder(10, 180, .5);
        return;
    }

    private void specialTurn() {
        setDriveEncoderOn(false);
        drive.mfr.setPower(-.3);
        drive.mbr.setPower(-.3);
        drive.mbl.setPower(.7);
        drive.mfl.setPower(.7);

        while ((gyro.getAngle() < 90) && opModeIsActive()) {
            //hello
        }
        stopMotors();

    }
}
