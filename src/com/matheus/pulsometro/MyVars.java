package com.matheus.pulsometro;

import java.util.concurrent.atomic.AtomicBoolean;

import android.hardware.Camera;
import android.os.PowerManager.WakeLock;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;


@SuppressWarnings("unused")
public class MyVars {
	static final String APP = "Pulsometro";
	static final AtomicBoolean processing = new AtomicBoolean(false);

    static SurfaceView viewFinder = null;
    static SurfaceHolder viewFinderHolder = null;
    static Camera camera = null;
    static View image = null;
    static TextView text = null;

    static WakeLock wakeLock = null;

    static int indexAVG = 0;
    static final int arraySizeAVG = 4;
    static final int[] arrayAVG = new int[arraySizeAVG];

    public static enum TYPE {
        beatOFF, beatON
    };

    static TYPE statusBatimento = TYPE.beatOFF;

    static TYPE getCurrent() {
        return statusBatimento;
    }

    static int beatsIndex = 0;
    static final int beatsArraySize = 3;
    static final int[] beatsArray = new int[beatsArraySize];
    static double beats = 0;
    static long startTime = 0;
}
