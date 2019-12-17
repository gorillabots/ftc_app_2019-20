package org.firstinspires.ftc.teamcode.tests;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.components.CustomVision;

@Autonomous(name="CustomVisionTest", group="tests")
public class CustomVisionTest extends LinearOpMode
{
    public void runOpMode()
    {
        CustomVision vision = new CustomVision(hardwareMap, telemetry);

        waitForStart();

        double fps = 0;

        int i = 0;
        int refi = 0;

        long time = System.currentTimeMillis();
        long reftime = time;

        Bitmap bmp = vision.getImage();
        int width = bmp.getWidth();
        int height = bmp.getHeight();

        while(opModeIsActive())
        {
            i++;
            bmp = vision.getImage();

            long sumr = 0;
            long sumg = 0;
            long sumb = 0;

            long num = (width * height / 100);

            for(int x = 0; x < width; x += 10)
            {
                for (int y = 0; y < height; y += 10)
                {
                    int color = bmp.getPixel(x, y);

                    sumr += Color.red(color);
                    sumg += Color.green(color);
                    sumb += Color.blue(color);
                }
            }

            int r = Color.red(bmp.getPixel(0, 0));
            int g = Color.green(bmp.getPixel(0, 0));
            int b = Color.blue(bmp.getPixel(0, 0));

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
            telemetry.addData("r", r);
            telemetry.addData("g", g);
            telemetry.addData("b", b);
            telemetry.addData("sumr", sumr / (double) num);
            telemetry.addData("sumg", sumg / (double) num);
            telemetry.addData("sumb", sumb / (double) num);
            telemetry.update();
        }
    }
}
