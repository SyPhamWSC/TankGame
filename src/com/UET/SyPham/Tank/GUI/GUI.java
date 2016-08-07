package com.UET.SyPham.Tank.GUI;

import com.UET.SyPham.Tank.common.CommonVLs;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class GUI extends JFrame{
    private PlayGamePanel playGamePanel;
    private JMenuBar menuBar;


    public GUI(){
        initCompoments();
    }
    public void initCompoments(){
        setBounds(200,200, CommonVLs.WIDTH_GUI,CommonVLs.HEIGHT_GUI);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//        setUndecorated(true);
        playGamePanel = new PlayGamePanel();
        add(playGamePanel);


    }
}
