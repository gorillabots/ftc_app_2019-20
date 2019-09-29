package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.MecanumDrive;

@TeleOp(group="tests", name="MecanumTest")
public class MecanumTest extends LinearOpMode
{
    MecanumDrive drive;

    @Override
    public void runOpMode()
    {
        drive = new MecanumDrive(hardwareMap, telemetry);

        waitForStart();

        while(opModeIsActive())
        {
            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;

            drive.go(x, y, r);
        }
    }
}
