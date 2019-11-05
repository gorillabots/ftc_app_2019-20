package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;
import org.firstinspires.ftc.teamcode.components.AutoDrive;

@Autonomous(group="test", name="driveEncTest")
public class driveEncTest extends GorillabotsCentral
{
    public void runOpMode()
    {
        initializeComponents();

        waitForStart();

        MoveUntilEncoder(10,0,.5);

        sleep(1000);
    }
}
