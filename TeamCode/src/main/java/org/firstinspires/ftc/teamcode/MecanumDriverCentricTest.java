package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.components.MecanumDrive;

@TeleOp(group="tests", name="MecanumDriverCentricTest")
public class MecanumDriverCentricTest extends LinearOpMode
{
    MecanumDrive drive;



    @Override
    public void runOpMode()
    {
        drive = new MecanumDrive();
        drive.init(hardwareMap);

        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "imu");

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        waitForStart();

        while(opModeIsActive())
        {
            double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double r = gamepad1.right_stick_x;

            drive.go(x, y, r);
        }
    }
}
