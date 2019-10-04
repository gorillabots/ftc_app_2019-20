package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.Grabber;
import org.firstinspires.ftc.teamcode.components.MecanumDrive;

@TeleOp(group = "teleop", name = "TestingTeleop")
public class Teleop extends LinearOpMode {

    MecanumDrive drive;
    Grabber grabber;

    @Override
    public void runOpMode() {

        drive = new MecanumDrive(hardwareMap, telemetry);
        grabber = new Grabber(hardwareMap, telemetry);

        waitForStart(); // RIGHT TRIGGER PROGRESSES THE PROGRAM, RIGHT BUMPER DOES ACTIONS

        boolean driveSlow = false;
        boolean driveSlowWatch = false;

        boolean doIt = false;
        boolean doItWatch = false;

        int stage = 0;
        boolean switchStageWatch = false;


        while (opModeIsActive()) {

            // SET DRIVING STUFF ↓

            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;

            // TOGGLES ↓

            if (gamepad1.b && !driveSlowWatch) {
                driveSlow = !driveSlow;
            }
            driveSlowWatch = gamepad1.b;

            if (gamepad1.right_bumper && !doItWatch) {
                doIt = !doIt;
            }
            doItWatch = gamepad1.right_bumper;

            if (gamepad1.right_trigger > .5 && !switchStageWatch) {
                stage += 1;
            }
            switchStageWatch = gamepad1.right_trigger > .5;

            //BULK OF PROGRAM: STAGES 1-4 ↓

            switch (stage) {

                case 0: //normal: driving
                    drive.go(x, y, r);
                    grabber.setIntakeOn(false);
                    grabber.setGrabberCollect(false);
                    doIt = false;
                    break;
                case 1: //collection
                    drive.go(x * .25, y * .25, r * .25);

                    grabber.setIntakeOn(doIt);
                    if (doIt) {
                        grabber.setGrabberCollect(true);
                    } else {
                        grabber.setGrabberAlign();
                    }
                    break;
                case 2:  //normal: driving
                    drive.go(x, y, r);
                    grabber.setIntakeOn(false);
                    grabber.setGrabberCollect(false);
                    doIt = false;
                    break;
                case 3:  //releasing
                    drive.go(x * .5, y * .5, r * .5);

                    if (doIt) {
                        grabber.setGrabberCollect(true);
                        grabber.setIntakeOn(false); //backwards
                    } else {
                        grabber.setGrabberAlign();
                    }
                    break;
                case 4: //set back to stage 1
                    stage = 1;
                    break;
            }
        }
    }
}