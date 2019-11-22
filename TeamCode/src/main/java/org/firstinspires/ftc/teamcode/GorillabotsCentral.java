package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.components.AutoDrive;
import org.firstinspires.ftc.teamcode.components.Grabber;
import org.firstinspires.ftc.teamcode.components.Hooks;
import org.firstinspires.ftc.teamcode.components.MecanumDrive;
import org.firstinspires.ftc.teamcode.components.RevGyro;
import org.firstinspires.ftc.teamcode.components.Sensors;
import org.firstinspires.ftc.teamcode.components.Slicer;

import java.util.List;

import static java.lang.Math.abs;

public abstract class GorillabotsCentral extends LinearOpMode {

    public AutoDrive ADrive;
    public Grabber grabber;
    public Hooks hooks;
    public Sensors sensors;
    public MecanumDrive drive;
    public Slicer slicer;
    public RevGyro gyro;

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

        slicer = new Slicer(hardwareMap,telemetry);

        slicer.setIn(true);

        gyro = new RevGyro(hardwareMap,telemetry);

        telemetry.addData("done:","init");
        telemetry.update();
    }

    public static final int degreeCorrection = 180;

    public static final double COUNTS_PER_MOTOR_REV = 160;     //12.5:1
    public static final double DRIVE_GEAR_REDUCTION = 2.0;     // This is < 1.0 if geared UP
    public static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    public static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);

    public void MoveUntilEncoder(double distance, double degree, double power) {

        drive.mfr.setDirection(DcMotor.Direction.REVERSE);
        drive.mfl.setDirection(DcMotor.Direction.FORWARD);
        drive.mbr.setDirection(DcMotor.Direction.REVERSE);
        drive.mbl.setDirection(DcMotor.Direction.FORWARD);

        double degreeRad = Math.toRadians(degree - degreeCorrection);
        double cs = Math.cos(degreeRad);
        double sn = Math.sin(degreeRad);

        setDriveEncoderOn(true);

        int rightFrontStartPos = drive.mfr.getCurrentPosition();
        int rightRearStartPos = drive.mbr.getCurrentPosition();
        int leftFrontStartPos = drive.mfl.getCurrentPosition();
        int leftRearStartPos = drive.mbl.getCurrentPosition();

        int target = (int) (distance * COUNTS_PER_INCH);

        int rightFrontEndPos = rightFrontStartPos + (int) (target * (-sn + cs));
        int leftFrontEndPos = leftFrontStartPos + (int) (target * (sn + cs));
        int rightRearEndPos = rightRearStartPos + (int) (target * (sn + cs));
        int leftRearEndPos = leftRearStartPos + (int) (target * (-sn + cs));

        double pwr = power;

        double rightFrontPower = pwr * (-sn + cs);
        double leftFrontPower = pwr * (sn + cs);
        double rightRearPower = pwr * (sn + cs);
        double leftRearPower = pwr * (-sn + cs);

        drive.mfr.setPower(rightFrontPower);
        drive.mfl.setPower(leftFrontPower);
        drive.mbr.setPower(rightRearPower);
        drive.mbl.setPower(leftRearPower);

        drive.mfr.setTargetPosition(rightFrontEndPos);
        drive.mfl.setTargetPosition(leftFrontEndPos);
        drive.mbr.setTargetPosition(rightRearEndPos);
        drive.mbl.setTargetPosition(leftRearEndPos);

        while (drive.mfl.isBusy() && opModeIsActive()) {
        }
        /*|| mfl.isBusy() || mbr.isBusy() || mbl.isBusy())*/
        stopMotors();
    }

    public void MoveUntilTime(long timeMilli, double direction, double power) {
        setDriveEncoderOn(false);
        MoveTo(direction, power);
        sleep(timeMilli);
        stopMotors();
    }
    public void MoveTo(double degree, double power) {
        double degreeRad = Math.toRadians(degree - degreeCorrection); // Convert to radians
        double cs = Math.cos(degreeRad);
        double sn = Math.sin(degreeRad);

        double fr = power * (-sn + cs);
        double fl = power * (sn + cs);
        double br = power * (sn + cs);
        double bl = power * (-sn + cs);

        drive.mfl.setPower(fl);
        drive.mfr.setPower(fr);
        drive.mbl.setPower(bl);
        drive.mbr.setPower(br);
    }
    public void stopMotors()
    {
        drive.mfr.setPower(0);
        drive.mfl.setPower(0);
        drive.mbr.setPower(0);
        drive.mbl.setPower(0);
    }

    public void setDriveEncoderOn(boolean on) {
        if (on) {
            drive.mfr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            drive.mfr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drive.mfr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            drive.mbr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            drive.mbr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drive.mbr.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            drive.mfl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            drive.mfl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drive.mfl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            drive.mbl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            drive.mbl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            drive.mbl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } else {
            drive.mfr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            drive.mbr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            drive.mfl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            drive.mbl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void TurnAbsolute(double TargetDegree) {
        // clock is negative; anti-clock positive degree
        // rotate range is (-90,90)

        drive.mfr.setDirection(DcMotor.Direction.REVERSE);
        drive.mfl.setDirection(DcMotor.Direction.FORWARD);
        drive.mbr.setDirection(DcMotor.Direction.REVERSE);
        drive.mbl.setDirection(DcMotor.Direction.FORWARD);

        if (TargetDegree > 180) {
            TargetDegree = 180;
        }
        if (TargetDegree < -180) {
            TargetDegree = -180;
        }

        double MaxPower = 0.5;
        double minPower = 0.2;

        double correctionDegree = 0;
        double beginDegree;
        double currentDegree;

        double target;
        double angleDiff;
        double maxTime = 6; //seconds
        ElapsedTime runtime = new ElapsedTime();

        setDriveEncoderOn(false);

        beginDegree = gyro.getAngle();

        runtime.reset();
        runtime.startTime();

        angleDiff = TargetDegree - beginDegree;
        while (abs(angleDiff) > 1 && runtime.seconds() < maxTime && opModeIsActive()) {
            double leftPower;
            double rightPower;
            currentDegree = gyro.getAngle();
            angleDiff = TargetDegree - currentDegree;
            if (angleDiff > 180) {
                angleDiff = angleDiff - 360;
            }
            if (angleDiff < -180) {
                angleDiff = angleDiff + 360;
            }

            if (angleDiff < 0) {
                angleDiff = angleDiff + correctionDegree;
            }
            if (angleDiff > 0) {
                angleDiff = angleDiff - correctionDegree;
            }

            double drivea;
            drivea = (angleDiff) / 100.0;

            if (abs(drivea) > MaxPower) {
                drivea = MaxPower * abs(drivea) / drivea;
            }
            if (abs(drivea) < minPower) {
                if (drivea > 0) {
                    drivea = minPower;
                } else if (drivea < 0) {
                    drivea = -minPower;
                } else {
                    drivea = 0;
                }
            }

            leftPower = Range.clip(-drivea, -1.0, 1.0);
            rightPower = Range.clip(drivea, -1.0, 1.0);

            drive.mfl.setPower(rightPower);
            drive.mbl.setPower(rightPower);
            drive.mfr.setPower(leftPower);
            drive.mbr.setPower(leftPower);

            telemetry.addData("Left Power", leftPower);
            telemetry.addData("right Power", rightPower);
            telemetry.addData("beginDegree", beginDegree);
            telemetry.addData("CurrentDegree", currentDegree);
            telemetry.addData("angleDiff", angleDiff);
            telemetry.update();
        }
        stopMotors();

        telemetry.addData("Current ZDegree", gyro.getAngle());
        telemetry.update();
    }



    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";

    private static final String VUFORIA_KEY =
            "AYIGi3P/////AAABmd1Nj8xVEE6BhnBHFrK7oIOGQxw7Er/V7JSoIDSl3S0XufhegO7yGWO4lksg+LUfRS/4Y5Fr52cpo4/Gnk/GO8NoF65yAkqU5ng+wgwDeKP/ZDHbU1HSGEDfTA0qlMGezmRSBUCupCT8LiIhHUMwd3Ir3CbL2/FXPnncSXkm/1EGha3uJe1/nuFmEZVgHpmcPsvN0r0nsxXjsSsSRyrrdhYNth9Ubfw1OTJR0RdqO84vSHMmJeDBmy2Jbf6r/hGlb1qLSgnyeCStuPOiizlBWRnL4BBd/gggOwG2gM+VpfqEgTT3D1uFiQ6qY1sU1e+hX4cXRlATKeFgv0FgCXWw39nx24rH7PkHVJ6c6OFCNib6";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

    public int getSkystonePos()
    {
        initVuforia();
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }
        if (tfod != null) {
            tfod.activate();
        }

        int position = 2;
        if (!isStarted() && !isStopRequested()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }

            while (!isStarted() && !isStopRequested()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        // step through the list of recognitions and display boundary info.
                        int i = 0;
                        String Lable0 = "";
                        String Lable1 = "";
                        String Lable2 = "";
                        double left0 = 0;
                        double left1 = 0;
                        double left2 = 0;

                        for (Recognition recognition : updatedRecognitions) {
                            telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                            telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                    recognition.getLeft(), recognition.getTop());
                            telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                    recognition.getRight(), recognition.getBottom());
                            if (i == 0){
                                Lable0 = recognition.getLabel();
                                left0 = recognition.getLeft();
                            }
                            if (i == 1){
                                Lable1 = recognition.getLabel();
                                left1 = recognition.getLeft();
                            }
                            if (i == 2){
                                Lable2 = recognition.getLabel();
                                left2 = recognition.getLeft();
                            }
                            i += 1;
                        }
                        if (updatedRecognitions.size() == 2)
                        {
                            if (Lable0 == "Skystone" && (left0 < left1) || (Lable1 == "Skystone" && (left1 < left0))) {
                                position = 1;
                            }
                            else if (Lable0 == "Stone" && Lable1 == "Stone"){
                                position = 3;
                            }
                            else {
                                position = 2;
                            }
                        }
                        else if (updatedRecognitions.size() == 1){
                            if(Lable0 == "Skystone"){
                                position = 2; //GUESS
                            }
                            else{
                                position = 3; //INFERENCE
                            }
                        }
                        telemetry.update();
                    }


                }


                telemetry.addData("position", position);
                telemetry.update();
            }
        }
        if (tfod != null) {
            tfod.shutdown();
        }
        return position;
    }


}