package org.firstinspires.ftc.teamcode.components;

import android.content.res.AssetManager;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class VuforiaKeyManager
{
    static Telemetry tele;

    public static String getVuforiaKey(HardwareMap hm, Telemetry telemetry, String name)
    {
        tele = telemetry;

        AssetManager am = hm.appContext.getAssets();

        try
        {
            InputStream is = am.open(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String key = br.readLine();
            br.close();
            is.close();
            return key;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
