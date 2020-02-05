package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Alignment
{
    Telemetry tele;

    Servo alignment;

    public static final double ALIGN_DOWN = .61;
    public static final double ALIGN_TELEREST = .13;
    public static final double ALIGN_45 = .45;
    public static final double ALGIN_INIT = .07;


    public Alignment(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;
        alignment = hardwareMap.servo.get("alignment");
    }

    public void alignment(double pos)
    {
        alignment.setPosition(pos);
    }

}
