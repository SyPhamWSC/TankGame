package com.UET.SyPham.Tank;

import com.UET.SyPham.Tank.GUI.GUI;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class Main {
    public static void main(String[] Args){
        GUI gui = null;
        try {
            gui = new GUI();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gui.setVisible(true);
    }
}
