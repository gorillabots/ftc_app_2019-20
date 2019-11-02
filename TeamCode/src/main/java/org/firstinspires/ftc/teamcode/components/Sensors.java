package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Sensors
{
    public DigitalChannel liftBot;
    public Rev2mDistanceSensor rangeF;

    public Sensors(HardwareMap hardwareMap, Telemetry telemetry)
    {
        liftBot = hardwareMap.get(DigitalChannel.class,"liftBot");
        liftBot.setMode(DigitalChannel.Mode.INPUT);
        rangeF = hardwareMap.get(Rev2mDistanceSensor.class, "rangeF");
    }

}
