package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Grabber
{
    private Telemetry tele;

    private Servo rotate;
    private CRServo rollerF;
    private CRServo rollerB;
    public DcMotor lift;



    public static final double ROTATE_45 = .63;
    public static final double ROTATE_DOWN = 0.41;
    public static final double ROTATE_ALIGN = 0.52;
    public static final double ROTATE_INIT = .98;

    public static final double INTAKE_IN = 1;
    public static final double INTAKE_OUT = -1;
    public static final double INTAKE_HOLD = 0;

    public static final double LIFT_GOINUP = 1;
    public static final double LIFT_GOINDOWN = -1;
    public static final double LIFT_HOLD = .1;

    public Grabber(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;

        rotate = hardwareMap.servo.get("rotate");

        rollerF = hardwareMap.crservo.get("rollerF");
        rollerB = hardwareMap.crservo.get("rollerB");

        lift = hardwareMap.dcMotor.get("lift");
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

    public void lift(double power)
    {
        lift.setPower(power);
    }

    public void isEncoderModeLift(boolean encoder) {
        if (encoder) {
            lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } else {
            lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }
}
