package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.GorillabotsCentral;
import org.firstinspires.ftc.teamcode.components.Grabber;

@Autonomous(group="test", name="blue29")
public class blueAuto29 extends GorillabotsCentral
{
    public void runOpMode()
    {
        initializeComponents();

        int pos = getSkystonePos();

        switch (pos)
        {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }


    }
}
