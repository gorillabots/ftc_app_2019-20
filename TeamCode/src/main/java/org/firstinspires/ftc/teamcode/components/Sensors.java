package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Sensors
{
    public DigitalChannel liftBot;

    public Sensors(HardwareMap hardwareMap, Telemetry telemetry)
    {
        liftBot = hardwareMap.get(DigitalChannel.class,"liftBot");
    }

}
