package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Grabber
{
    private Telemetry tele;

    private Servo rotate;
    private CRServo rollerF;
    private CRServo rollerB;

    public static final double ROTATE_UP = 1;
    public static final double ROTATE_DOWN = 0.15;
    public static final double ROTATE_ALIGN = 0.3;

    public static final double INTAKE_IN = 1;
    public static final double INTAKE_OUT = -1;
    public static final double INTAKE_HOLD = 0;

    public Grabber(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;

        rotate = hardwareMap.servo.get("rotate");

        rollerF = hardwareMap.crservo.get("rollerF");
        rollerB = hardwareMap.crservo.get("rollerB");
    }

    public void rotate(double pos)
    {
        rotate.setPosition(pos);
    }

    public void intake(double intake)
    {
        rollerF.setPower(-intake);
        rollerB.setPower(intake);
    }
}
