package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

public class CustomVision
{
    private Telemetry tele;

    VuforiaLocalizer vuforia;
    VuforiaLocalizer.Parameters params;

    public CustomVision(HardwareMap hardwareMap, Telemetry telemetry)
    {
        tele = telemetry;

        params = new VuforiaLocalizer.Parameters();

        params.vuforiaLicenseKey = VuforiaKeyManager.getVuforiaKey(hardwareMap, tele, "vuforiakey.txt");
        //params.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        params.cameraMonitorViewIdParent = cameraMonitorViewId;

        vuforia = ClassFactory.getInstance().createVuforia(params);

        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);
        vuforia.setFrameQueueCapacity(1);
    }

    public Image getImage()
    {
        VuforiaLocalizer.CloseableFrame frame = null;

        try
        {
            frame = vuforia.getFrameQueue().take();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
            return null;
        }

        Image rgb = null;

        long numImages = frame.getNumImages();

        for (int i = 0; i < numImages; i++)
        {
            tele.addData("format" + i, frame.getImage(i).getFormat());

            if (frame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565)
            {

                rgb = frame.getImage(i);
                break;
            }
        }

        frame.close();

        return rgb;
    }
}
