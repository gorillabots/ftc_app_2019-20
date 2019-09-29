package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(group="tests", name="SampleOpMode")
public class SampleOpMode extends LinearOpMode
{
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        int n = 1;
        while(opModeIsActive())
        {
            telemetry.addData("Status", "Running");
            telemetry.addData("Count", n++);
            telemetry.update();
        }

        telemetry.addData("Status", "Stopped");
        telemetry.update();
    }
}
