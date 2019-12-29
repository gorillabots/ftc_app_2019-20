package org.firstinspires.ftc.teamcode.tests;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.provider.MediaStore;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.CustomVision;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Autonomous(name="CustomVisionTest", group="tests")
public class CustomVisionTest extends LinearOpMode
{
    public void runOpMode()
    {
        final CustomVision vision = new CustomVision(hardwareMap, telemetry);

        waitForStart();

        double fps = 0;

        int i = 0;
        int refi = 0;

        long time = System.currentTimeMillis();
        long reftime = time;

        Bitmap bmp = vision.getImage();
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        boolean lastA = false;

        final int L_LEFT = 0;
        final int L_RIGHT = 180;
        final int L_TOP = 135;
        final int L_BOTTOM = 205;
        
        final int R_LEFT = 210;
        final int R_RIGHT = 390;
        final int R_TOP = 140;
        final int R_BOTTOM = 220;

        final int L_XINC = 5;
        final int L_YINC = 5;
        final int L_XYINC = L_XINC * L_YINC;

        final int L_WIDTH = L_RIGHT - L_LEFT;
        final int L_HEIGHT = L_BOTTOM - L_TOP;
        final int L_SIZE = L_WIDTH * L_HEIGHT;
        final int L_SCANSIZE = L_SIZE / L_XYINC;

        final int R_XINC = 5;
        final int R_YINC = 5;
        final int R_XYINC = R_XINC * R_YINC;

        final int R_WIDTH = R_RIGHT - R_LEFT;
        final int R_HEIGHT = R_BOTTOM - R_TOP;
        final int R_SIZE = R_WIDTH * R_HEIGHT;
        final int R_SCANSIZE = R_SIZE / R_XYINC;

        while(opModeIsActive())
        {
            i++;
            bmp = vision.getImage();

            if(gamepad1.a && !lastA)
            {
                File path = Environment.getExternalStorageDirectory();
                String file = "pics/" + i + ".png";
                File dest = new File(path, file);

                try (FileOutputStream out = new FileOutputStream(dest))
                {
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
                    // PNG is a lossless format, the compression factor (100) is ignored
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            lastA = gamepad1.a;

            long suml = 0;
            long sumr = 0;

            for(int x = L_LEFT; x < L_RIGHT; x += L_XINC)
            {
                for (int y = L_TOP; y < L_BOTTOM; y += L_YINC)
                {
                    int color = bmp.getPixel(x, y);

                    suml += Color.red(color) + Color.green(color) + Color.blue(color);
                }
            }

            for(int x = R_LEFT; x < R_RIGHT; x += R_XINC)
            {
                for (int y = R_TOP; y < R_BOTTOM; y += R_YINC)
                {
                    int color = bmp.getPixel(x, y);

                    sumr += Color.red(color) + Color.green(color) + Color.blue(color);
                }
            }

            double avgl = suml / (double) L_SCANSIZE;
            double avgr = sumr / (double) R_SCANSIZE;

            time = System.currentTimeMillis();

            if(time > reftime + 1000)
            {
                fps = (i - refi) / ((time - reftime) / 1000d);
                refi = i;
                reftime = time;
            }

            telemetry.addData("i", i);
            telemetry.addData("fps", fps);
            telemetry.addData("width", width);
            telemetry.addData("height", height);
            telemetry.addData("suml", suml);
            telemetry.addData("avgl", avgl);
            telemetry.addData("sumr", sumr);
            telemetry.addData("avgr", avgr);

            if(avgl > 150 && avgr > 150)
            {
                telemetry.addData("Position", "Left");
            }
            else if(avgl < 150 && avgr > 150)
            {
                telemetry.addData("Position", "Middle");
            }
            else if(avgl > 150 && avgr < 150)
            {
                telemetry.addData("Position", "Right");
            }
            else
            {
                telemetry.addData("Position", "Confused");
            }

            telemetry.update();
        }
    }
}
