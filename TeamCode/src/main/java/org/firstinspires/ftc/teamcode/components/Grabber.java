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
    Servo rotate;

    public Grabber(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;

        rollerF = hardwareMap.crservo.get("rollerF");
        rollerB = hardwareMap.crservo.get("rollerB");

        rotate = hardwareMap.servo.get("rotate");
    }
}
