package com.UET.SyPham.Tank.common;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class CommonVLs {
    public static final int HEIGHT = 500;
    public static final int WIDTH = 500;
    public static final int HEIGHT_GUI = 530;
    public static final int WIDTH_GUI = 700;

    public static final int BRICK_TYPE = 1;

    public static final int TANK_EXPLORE = 1;
    public static final int BULLET_EXPLORE = 2;
    public static final int ANIMATION_SIZE = 30;

    public static final String PATH_AUDIO = "/RESOURCE/sound/";

    public Image getImage(String name){
//        return new ImageIcon(getClass().getResource("/RESOURCE/Image/"+name)).getImage();
        return new ImageIcon(getClass().getResource("/RESOURCE/Image/" + name)).getImage();
    }

    public void playSound(String name) throws IOException {
        String path = getClass().getResource(PATH_AUDIO+name).getPath();
        InputStream inputStream = new FileInputStream(path);
        AudioStream audioStream = new AudioStream(inputStream);
        AudioPlayer.player.start(audioStream);
    }
}
