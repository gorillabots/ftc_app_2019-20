package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AutoDrive
{
    Telemetry tele;

    MecanumDrive drive;
    RevGyro gyro;

    public AutoDrive(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;
        drive = new MecanumDrive(hardwareMap, telemetry);
        gyro = new RevGyro(hardwareMap, telemetry);
    }

    double gyroOffset = 0;

    public void driveCartesian(double x, double y, double dist)
    {


        tele.addData("Gyro Angle", gyro.getAngle());
    }

}
