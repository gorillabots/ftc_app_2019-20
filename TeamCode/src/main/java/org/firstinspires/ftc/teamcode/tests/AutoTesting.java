package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.AutoDrive;

@Autonomous(group="test", name="AutoTesting")
public class AutoTesting extends LinearOpMode
{
    //+x: 5000 / 52 = 96.154
    //+y: 5000 / 56.5 = 88.495
    //+x+y: 5000 / 53 = 94.340

    public void runOpMode()
    {
        AutoDrive drive = new AutoDrive(hardwareMap, telemetry);

        waitForStart();

        drive.driveCartesian(0.2, 0.2, 5000);
    }
}
