package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Grabber
{
    private Telemetry tele;

    private CRServo rollerF;
    private CRServo rollerB;
    private Servo rotate;

    private static final double ROTATE_UP = 1;
    private static final double ROTATE_DOWN = .15;
    private static final double ROTATE_ALIGN = 0.3;

    public Grabber(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;

        rollerF = hardwareMap.crservo.get("rollerF");
        rollerB = hardwareMap.crservo.get("rollerB");

        rotate = hardwareMap.servo.get("rotate");
    }

    //TODO: Remove once ServoTests is removed
    public void setRotate(double pos)
    {
        rotate.setPosition(pos);
    }

    public void gotoRotCollect(boolean down)
    {
        if(down)
        {
            rotate.setPosition(ROTATE_DOWN);
        }
        else
        {
            rotate.setPosition(ROTATE_UP);
        }
    }

    public void gotoRotAlign()
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

    public void gotoIntakeRelease()
    {
        rollerF.setPower(1);
        rollerB.setPower(-1);
    }
}
