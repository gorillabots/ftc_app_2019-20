package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MecanumDrive
{
    Telemetry tele;

    DcMotor mfl, mfr, mbl, mbr;

    public MecanumDrive(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;

        mfl = hardwareMap.dcMotor.get("mfl");
        mfr = hardwareMap.dcMotor.get("mfr");
        mbl = hardwareMap.dcMotor.get("mbl");
        mbr = hardwareMap.dcMotor.get("mbr");
    }

    double fl, fr, bl, br;
    
    public void go(double x, double y, double r)
    {
        fl = -x + y - r;
        fr = x - y - r;
        bl = x + y - r;
        br = -x - y - r;

        mfl.setPower(fl);
        mfr.setPower(fr);
        mbl.setPower(bl);
        mbr.setPower(br);
    }
}
