package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Grabber
{
    Telemetry tele;

    CRServo rollerF;
    CRServo rollerB;
    public Servo rotate;

    public static double ROTATE_UP = 1;
    public static double ROTATE_DOWN = .15;
    public static double ROTATE_ALIGN = 0.23;

    public Grabber(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;

        rollerF = hardwareMap.crservo.get("rollerF");
        rollerB = hardwareMap.crservo.get("rollerB");

        rotate = hardwareMap.servo.get("rotate");
    }

    public void setGrabberCollect(boolean down)
    {
        if(!down)
        {
            rotate.setPosition(ROTATE_UP);
        }
        else
        {
            rotate.setPosition(ROTATE_DOWN);
        }
    }
    public void setGrabberAlign()
    {
        rotate.setPosition(ROTATE_ALIGN);
    }
    public void setIntakeOn(boolean intake)
    {
        if (intake)
        {
            rollerF.setPower(-1);
            rollerB.setPower(1);
        }
        else
        {
            rollerF.setPower(0);
            rollerB.setPower(0);
        }
    }
    public void setIntakeRelease()
    {
        rollerF.setPower(1);
        rollerB.setPower(-1);
    }
}
