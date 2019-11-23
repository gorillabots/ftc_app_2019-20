package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;

@Autonomous(group="test", name="turnTest")
public class turnTest extends GorillabotsCentral
{
    public void runOpMode()
    {
        initializeComponents();

        waitForStart();

        TurnAbsolute(90,.2,.5);

        sleep(1000);
    }
}
