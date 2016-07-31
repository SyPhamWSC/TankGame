package com.UET.SyPham.Tank.common;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class CommonVLs {
    public static final int HEIGHT = 500;
    public static final int WIDTH = 500;
    public static final int HEIGHT_GUI = 500;
    public static final int WIDTH_GUI = 700;

    public Image getImage(String name){
//        return new ImageIcon(getClass().getResource("/RESOURCE/Image/"+name)).getImage();
        return new ImageIcon(getClass().getResource("/RESOURCE/Image/" + name)).getImage();
    }
}
