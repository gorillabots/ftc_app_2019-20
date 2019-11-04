package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.AutoDrive;
import org.firstinspires.ftc.teamcode.components.Grabber;
import org.firstinspires.ftc.teamcode.components.Hooks;
import org.firstinspires.ftc.teamcode.components.MecanumDrive;
import org.firstinspires.ftc.teamcode.components.RevGyro;
import org.firstinspires.ftc.teamcode.components.Sensors;

public abstract class GorillabotsCentral extends LinearOpMode {

    public AutoDrive ADrive;
    public Grabber grabber;
    public Hooks hooks;
    public Sensors sensors;
    public MecanumDrive drive;

    /*
    HUB # 1

    Motors:
    0: mfr
    1: mbr

    Servos:
    0: rotate
    1: rollerF
    3: hookR

    I2C:

    0: imu
    1: rangeF

    HUB # 2

    Motors:
    1: lift
    2: mfl
    3: mbl

    Servos:
    5: rollerB
    4: hookL

    Digital Channels:

    3: liftBot
     */

    public void initializeComponents()
    {
        ADrive = new AutoDrive(hardwareMap,telemetry);

        grabber = new Grabber(hardwareMap,telemetry);

        grabber.rotate(Grabber.ROTATE_INIT);
        grabber.intake(Grabber.INTAKE_HOLD);

        hooks = new Hooks(hardwareMap,telemetry);

        hooks.setDown(false);

        sensors = new Sensors(hardwareMap,telemetry);

        drive = new MecanumDrive(hardwareMap,telemetry);

    }

}
