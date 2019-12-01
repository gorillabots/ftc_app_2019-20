package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;
import org.firstinspires.ftc.teamcode.components.Grabber;
import org.firstinspires.ftc.teamcode.components.Hooks;
import org.firstinspires.ftc.teamcode.components.Parker;

@Autonomous(group = "test", name = "red43")

public class redAuto43 extends GorillabotsCentral {
    public void runOpMode() {
        initializeComponentsAutonomous();

        int pos = getSkystonePosRed();

        grabber.rotate(Grabber.ROTATE_INIT);
        grabber.intake(Grabber.INTAKE_IN);
        //ADrive.driveCartesian(.3,.3,50);
        switch (pos) {
            case 1:
                MoveUntilEncoderGYRO(5, 0, .3, 0);
                MoveUntilEncoderGYRO(28.5, 270, .6, 0);
                MoveUntilRangeF(9, 0, .3);
                TurnAbsolute(-15, .2, .5);
                break;
            case 2:
                MoveUntilEncoderGYRO(5, 0, .3, 0);
                MoveUntilEncoderGYRO(21, 270, .3, 0);
                MoveUntilRangeF(9.5, 0, .3);
                TurnAbsolute(-15, .2, .5);
                break;
            case 3:
                MoveUntilEncoderGYRO(5, 0, .3, 0);
                MoveUntilEncoderGYRO(13, 270, .3, 0);
                MoveUntilRangeF(9.5, 0, .3);
                TurnAbsolute(-15, .2, .5);
                break;

        }
        grabber.rotate(Grabber.ROTATE_DOWN);
        sleep(1500);
        grabber.intake(Grabber.INTAKE_HOLD);
        grabber.rotate(Grabber.ROTATE_45);
        MoveUntilEncoder(2, 180, .3);
        TurnAbsolute(90, .2, .5);

        switch (pos) {
            case 1:
                MoveUntilEncoderGYRO(68, 180, .7, 92);
                MoveUntilRangeB(32, 180, .3);
                break;
            case 2:
                MoveUntilEncoderGYRO(60, 180, .6, 92);
                MoveUntilRangeB(28, 180, .3);
                break;
            case 3:
                MoveUntilEncoderGYRO(54, 180, .5, 92);
                MoveUntilRangeB(28, 180, .3);
                break;
        }

        grabber.rotate(Grabber.ROTATE_INIT);
        TurnAbsolute(0, .2, .5);
        MoveUntilRangeF(4, 0, .3);
        grabber.rotate(Grabber.ROTATE_DOWN);
        grabber.intake(Grabber.INTAKE_OUT);
        sleep(750);
        grabber.rotate(Grabber.ROTATE_45);
        grabber.intake(Grabber.INTAKE_HOLD);
        MoveUntilEncoder(5, 180, .3);
        TurnAbsolute(-90, .2, .5);
        // MoveUntilEncoder(100,180,.3);

        switch (pos) {
            case 1:
                MoveUntilEncoderGYRO(90, 180, .7, -83);
                MoveUntilRangeB(5, 180, .6);
                grabber.intake(Grabber.INTAKE_IN);
                grabber.rotate(Grabber.ROTATE_INIT);
                TurnAbsolute(0, .2, .5);
                MoveUntilTime(500, 270, .5);
                MoveUntilRangeF(8, 0, .2);
                TurnAbsolute(29, .2, .5);
                MoveUntilRangeF(8, 0, .2);
                grabber.rotate(Grabber.ROTATE_ALIGN);
                MoveUntilEncoderGYRO(3, -30, .4, 30);
                grabber.rotate(Grabber.ROTATE_DOWN);
                sleep(600);
                TurnAbsolute(0, .2, .5);
                break;
            case 2:
                MoveUntilEncoderGYRO(100, 180, .6, -83);
                MoveUntilRangeB(5, 180, .3);
                grabber.intake(Grabber.INTAKE_IN);
                grabber.rotate(Grabber.ROTATE_INIT);
                //MoveUntilEncoderGYRO(7.5,90,.3,-83);
                //TurnAbsolute(0,.2,.5);
                TurnAbsolute(6, .2, .5);
                MoveUntilRangeF(8.5, 0, .2);
                grabber.rotate(Grabber.ROTATE_DOWN);
                sleep(1000);
                break;
            case 3:
                MoveUntilEncoderGYRO(86, 180, .5, -83);
                MoveUntilRangeB(18, 180, .3);
                grabber.intake(Grabber.INTAKE_IN);
                grabber.rotate(Grabber.ROTATE_INIT);
                TurnAbsolute(-6, .2, .5);
                MoveUntilRangeF(8, 0, .2);
                grabber.rotate(Grabber.ROTATE_DOWN);
                sleep(1000);
                break;
        }
        grabber.intake(Grabber.INTAKE_HOLD);
        grabber.rotate(Grabber.ROTATE_45);
        switch (pos) {
            case 1:
                MoveUntilEncoder(3.5, 180, .3);
                break;
            case 2:
                MoveUntilEncoder(2, 180, .3);
                break;
            case 3:
                MoveUntilEncoder(2, 180, .3);
                break;
        }

        TurnAbsolute(-90, .2, .5);
        switch (pos) {
            case 1:
                MoveUntilEncoderGYRO(106, 0, .7, -85);
                break;
            case 2:
                MoveUntilEncoderGYRO(106, 0, .6, -85);
                break;
            case 3:
                MoveUntilEncoderGYRO(100, 0, .5, -85);
                break;
        }
        grabber.rotate(Grabber.ROTATE_INIT);
        MoveUntilRangeF(20, 0, .5);
        TurnAbsolute(0, .2, .5);
        MoveUntilRangeF(4,0,.3);
        hooks.setDown(true);
        grabber.rotate(Grabber.ROTATE_DOWN);
        grabber.intake(Grabber.INTAKE_OUT);
        sleep(750);
        grabber.rotate(Grabber.ROTATE_45);
        grabber.intake(Grabber.INTAKE_HOLD);

        switch (pos){
            case 1:
                MoveUntilEncoder(12,180,.35);
                specialTurn();
                hooks.hookR(Hooks.HOOKR_MID);
                hooks.hookL(Hooks.HOOKL_MID);
                sleep(250);
                parker.parkerPow(-.8);
                MoveUntilEncoder(4, 180, .5);
                hooks.setDown(true);
                sleep(250);
                MoveUntilTime(1000, 0, .5);
                MoveUntilEncoder(4, 160, .5);
                TurnAbsolute(-110,.2,.5);
                parker.parkerPow(0);
                MoveUntilEncoder(10,180,.5);
                break;
            case 2:
                MoveUntilEncoder(10,180,.3);
                specialTurn();
                hooks.setDown(false);
                sleep(500);
                MoveUntilEncoder(4, 180, .5);
                hooks.setDown(true);
                parker.parkerPow(Parker.PARKER_OUT);
                sleep(750);
                MoveUntilTime(1000, 0, .5);
                MoveUntilEncoder(4, 160, .5);
                TurnAbsolute(-110,.2,.5);
                parker.parkerPow(0);
                MoveUntilEncoder(10,180,.5);
                break;
            case 3:
                MoveUntilEncoder(7.5,180,.3);
                specialTurn();
                hooks.setDown(false);
                sleep(500);
                MoveUntilEncoder(4, 180, .5);
                hooks.setDown(true);
                parker.parkerPow(Parker.PARKER_OUT);
                sleep(750);
                MoveUntilTime(1000, 0, .5);
                MoveUntilEncoder(4, 160, .5);
                TurnAbsolute(-110,.2,.5);
                parker.parkerPow(0);
                MoveUntilEncoder(10,180,.5);
                break;
        }


        //parker.parkerPow(0);
        //MoveUntilEncoder(20,180,.4);
    }

    private void specialTurn() {
        setDriveEncoderOn(false);
        drive.mfr.setPower(.7);
        drive.mbr.setPower(.7);
        drive.mbl.setPower(-.3);
        drive.mfl.setPower(-.3);

        while ((gyro.getAngle() > -90) && opModeIsActive()) {
            //hello
        }
        stopMotors();

        //TurnAbsolute(110,.9,1);
    }
}
