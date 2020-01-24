package org.firstinspires.ftc.teamcode.v2.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.components.RevGyro;

public class V2Tracker
{
	//Other Components
	private Telemetry tele;
	public RevGyro gyro;

	//Hardware
	private DcMotor encoderL, encoderR, encoderF;

	//Constants
	private final double WHEEL_DIAMETER = 4; //inches
	private final double WHEEL_CIRCUMFERENCE = 2 * Math.PI * WHEEL_DIAMETER; //inches
	private final double ENCODER_TICKS_PER_REVOLUTION = 360;
	private final double TICKS_PER_INCH = ENCODER_TICKS_PER_REVOLUTION / WHEEL_CIRCUMFERENCE;
	private final double INCHES_PER_TICK = 1 / TICKS_PER_INCH;

	private final double ENCODER_L_RATE = -INCHES_PER_TICK;
	private final double ENCODER_R_RATE = INCHES_PER_TICK;
	private final double ENCODER_F_RATE = INCHES_PER_TICK;

	private final double LR_WIDTH = 2; //inches
	private final double ROBOT_CIRCUMFERENCE = LR_WIDTH * Math.PI; //inches

	//Local Variables
	private double lastL = 0;
	private double lastR = 0;
	private double lastF = 0;

	private double x, y, rot;

	public V2Tracker(HardwareMap hardwareMap, Telemetry telemetry)
	{
		tele = telemetry;

		encoderL = hardwareMap.dcMotor.get("encoderL");
		encoderR = hardwareMap.dcMotor.get("encoderR");
		encoderF = hardwareMap.dcMotor.get("encoderF");

		gyro = new RevGyro(hardwareMap, tele);

		x = 0;
		y = 0;
		rot = 0;
	}

	public void set(double x, double y, double rot)
	{
		this.x = x;
		this.y = y;
		this.rot = rot;
	}

	public void update()
	{
		double curL = encoderL.getCurrentPosition();
		double curR = encoderR.getCurrentPosition();
		double curF = encoderF.getCurrentPosition();

		double deltaL = (curL - lastL) * ENCODER_L_RATE;
		double deltaR = (curR - lastR) * ENCODER_R_RATE;
		double deltaF = (curF - lastF) * ENCODER_F_RATE;

		double deltaFB = (deltaL + deltaR) / 2;
		double deltaRL = deltaF;

		x += deltaRL;
		y += deltaFB;
		rot = gyro.getAngle();

		lastL = curL;
		lastR = curR;
		lastF = curF;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getRot()
	{
		return rot;
	}
}
