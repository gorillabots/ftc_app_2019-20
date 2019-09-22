package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDrive
{
    DcMotor mfl, mfr, mbl, mbr;

    public void init(HardwareMap hardwareMap)
    {
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
