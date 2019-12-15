package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.Image;

import org.firstinspires.ftc.teamcode.components.CustomVision;

import java.math.BigInteger;
import java.nio.ByteBuffer;

@Autonomous(name="CustomVisionTest", group="tests")
public class CustomVisionTest extends LinearOpMode
{
    public void runOpMode()
    {
        CustomVision vision = new CustomVision(hardwareMap, telemetry);

        waitForStart();

        int i = 0;
        double fps = 0;

        long reftime = System.currentTimeMillis();
        int refi = 0;

        int[][][] arr = null;

        while(opModeIsActive())
        {
            i++;
            Image img = vision.getImage();
            int width = img.getWidth();
            int height = img.getHeight();
            int stride = img.getStride();
            int bytesperpixel = stride / width;

            int wstride = 10;
            int hstride = 10;

            int size = height * stride;

            ByteBuffer imgb = img.getPixels();

            if(arr == null)
            {
                arr = new int[height / hstride][width / wstride][3];
            }

            long sumr = 0;
            long sumg = 0;
            long sumb = 0;

            for(int y = 0; y < height / hstride; y++)
            {
                for(int x = 0; x < width / wstride; x++)
                {
                    int addr = (y*hstride) * stride + (width*wstride);

                    byte byte1 = imgb.get(addr);
                    byte byte2 = imgb.get(addr + 1);

                    int r = byte1 & 0xF8 >> 3;
                    int g = byte1 & 0x07 << 5 + byte2 & 0xE0 >> 5;
                    int b = byte2 & 0x1F;

                    sumr += r;
                    sumb += b;
                    sumg += g;
                }
            }

            if(System.currentTimeMillis() > reftime + 1000)
            {
                fps = (i - refi);
                refi = i;
                reftime = System.currentTimeMillis();
            }

            telemetry.addData("i", i);
            telemetry.addData("fps", fps);
            telemetry.addData("width", width);
            telemetry.addData("height", height);
            telemetry.addData("bytes per pixel", bytesperpixel);
            telemetry.addData("sumr", sumr);
            telemetry.addData("sumg", sumg);
            telemetry.addData("sumb", sumb);
            telemetry.update();
        }
    }
}
