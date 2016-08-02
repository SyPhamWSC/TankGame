package com.UET.SyPham.Tank.object.Bullet;

import com.UET.SyPham.Tank.common.CommonVLs;
import com.UET.SyPham.Tank.object.Tank.Tank;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class Bullet {
    protected int X;
    protected int Y;
    protected int tpye;
    protected int orient;
    protected final int size = 8;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    protected int speedBullet;
    protected Image imgBullet;


    public Bullet(int x, int y, int orients, int size) {

        this.orient = orients;
        switch (orients) {
            case UP:
                this.X = x + size / 2 - this.size / 2;
                this.Y = y;
                break;
            case DOWN:
                this.X = x + size / 2 - this.size / 2;
                this.Y = y + size;
                break;
            case LEFT:
                this.X = x;
                this.Y = y + size / 2 - this.size / 2;
                break;
            case RIGHT:
                this.X = x + size;
                this.Y = y + size / 2 - this.size / 2;
                break;
        }
        this.speedBullet = 5;


        CommonVLs common = new CommonVLs();
        imgBullet = common.getImage("bullet.png");
    }

    public void move() {

        switch (orient) {
            case UP:
                Y--;
                break;
            case DOWN:
                //System.out.println("move");
                Y++;
                break;
            case RIGHT:
                X++;
                break;
            case LEFT:
                X--;
        }
    }

    public void drawBullet(Graphics2D g2d) {
        g2d.drawImage(imgBullet, X, Y, size, size, null);
    }

    public void setXYOrient(int x, int y, int orient) {
        this.X = x;
        this.Y = y;
        this.orient = orient;
    }


}
