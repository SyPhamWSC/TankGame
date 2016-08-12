package com.UET.SyPham.Tank.common;

import javax.swing.*;
import java.awt.*;

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

    public Image getImage(String name){
//        return new ImageIcon(getClass().getResource("/RESOURCE/Image/"+name)).getImage();
        return new ImageIcon(getClass().getResource("/RESOURCE/Image/" + name)).getImage();
    }
}
