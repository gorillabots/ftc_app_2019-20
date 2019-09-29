package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(group="tests", name="MotorIdentificationTest")
public class MotorIdentificationTest extends LinearOpMode
{
    public void runOpMode()
    {
        DcMotor mfl, mfr, mbl, mbr;
        mfl = hardwareMap.dcMotor.get("mfl");
        mfr = hardwareMap.dcMotor.get("mfr");
        mbl = hardwareMap.dcMotor.get("mbl");
        mbr = hardwareMap.dcMotor.get("mbr");

        waitForStart();

        int sel = 0;
        boolean lastLeft = false;
        boolean lastRight = false;

        while(opModeIsActive())
        {
            if(gamepad1.dpad_left && !lastLeft)
            {
                sel += 3;
                sel %= 4;
            }

            if(gamepad1.dpad_right && !lastRight)
            {
                sel++;
                sel %= 4;
            }

            switch(sel)
            {
                case 0:
                    mfr.setPower(gamepad1.right_stick_y);
                    telemetry.addData("Motor", "FR");
                    break;
                case 1:
                    mbr.setPower(gamepad1.right_stick_y);
                    telemetry.addData("Motor", "BR");
                    break;
                case 2:
                    mfl.setPower(gamepad1.right_stick_y);
                    telemetry.addData("Motor", "FL");
                    break;
                case 3:
                    mbl.setPower(gamepad1.right_stick_y);
                    telemetry.addData("Motor", "BL");
                    break;
            }

            telemetry.addData("Power", gamepad1.right_stick_y);

            telemetry.update();

            lastLeft = gamepad1.dpad_left;
            lastRight = gamepad1.dpad_right;
        }
    }
}
