package org.firstinspires.ftc.teamcode;

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
        int eee = 666;
        while(opModeIsActive() && n > 0)
        {
            telemetry.addData("Status", "Running");
            telemetry.addData("Count", n++);
            telemetry.update();
        }

        telemetry.addData("Status", "Stopped");
        telemetry.update();
    }
}
