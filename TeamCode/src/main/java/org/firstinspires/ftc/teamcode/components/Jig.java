package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Jig
{
    Telemetry tele;

    Servo front;

    public static final double FRONT_DOWN = 1;
    public static final double FRONT_UP = 0;

    public Jig(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;
        front = hardwareMap.servo.get("front");
    }

    public void front(double pos)
    {
        front.setPosition(pos);
    }
}
