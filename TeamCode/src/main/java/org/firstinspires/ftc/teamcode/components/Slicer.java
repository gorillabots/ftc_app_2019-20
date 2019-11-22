package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Slicer
{
    Telemetry tele;

    Servo slicer;

    public static final double SLICER_IN = .98; //
    public static final double SLICER_OUT = .49;  //

    public Slicer(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;
        slicer = hardwareMap.servo.get("slicer");
    }

    public void slicer(double pos)
    {
        slicer.setPosition(pos);
    }
    public void setIn(boolean in)
    {
        slicer(in ? SLICER_IN : SLICER_OUT);
    }

}
