package com.UET.SyPham.Tank.GUI;

import com.UET.SyPham.Tank.common.CommonVLs;
import com.sun.media.jfxmedia.Media;
import com.sun.media.jfxmedia.MediaPlayer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class GUI extends JFrame{
    private PlayGamePanel playGamePanel;
    CommonVLs commonVLs;


    public GUI() throws IOException {
        initCompoments();
    }
    public void initCompoments() throws IOException {
        setBounds(200,200, CommonVLs.WIDTH_GUI,CommonVLs.HEIGHT_GUI);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playGamePanel = new PlayGamePanel();
        add(playGamePanel);
        commonVLs = new CommonVLs();
        MenuPanel menuPanel= new MenuPanel();
        add(menuPanel);
        menuPanel.setListener(playGamePanel);

    }


}
