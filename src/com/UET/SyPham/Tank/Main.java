package com.UET.SyPham.Tank;

import com.UET.SyPham.Tank.GUI.GUI;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class Main {
    public static void main(String[] Args){
        new Thread(new Runnable() {
            @Override
            public void run() {
                GUI gui = new GUI();
                gui.setVisible(true);
            }
        }).start();
    }
}
