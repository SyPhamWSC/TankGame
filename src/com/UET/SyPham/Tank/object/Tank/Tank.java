package com.UET.SyPham.Tank.object.Tank;

import com.UET.SyPham.Tank.common.CommonVLs;
import com.UET.SyPham.Tank.object.Bullet.Bullet;
import com.UET.SyPham.Tank.object.Bullet.BulletManager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public abstract class Tank {
    protected int X;
    protected int Y;
    protected final int sizeTank = 30;
    protected int speedTank;
    protected boolean checkLocation;
    protected String direction;
    protected Image upTank;
    protected Image downTank;
    protected Image leftTank;
    protected Image rightTank;

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    protected BulletManager bulletManager;
    int orient;

    public Tank(int x, int y) {
        this.X = x;
        this.Y = y;
        bulletManager = new BulletManager();

        CommonVLs common = new CommonVLs();
        this.upTank = common.getImage("bossyellow_up.png");
        this.downTank = common.getImage("bossyellow_down.png");
        this.leftTank = common.getImage("bossyellow_left.png");
        this.rightTank = common.getImage("bossyellow_right.png");
        orient = 0;
        speedTank = 10;


    }

    /**
     * Vẽ tank với các vị trí xoay hình khác nhau
     * @param g2d
     */
    public void drawTank(Graphics2D g2d) {
        //System.out.println(orient);
        switch (orient) {
            case 0:
                g2d.drawImage(rightTank, X, Y, sizeTank, sizeTank, null);
                break;
            case UP:
                g2d.drawImage(upTank, X, Y, 30, 30, null);
                break;
            case DOWN:
                g2d.drawImage(downTank, X, Y, 30, 30, null);
                break;
            case LEFT:
                g2d.drawImage(leftTank, X, Y, 30, 30, null);
                break;
            case RIGHT:
                g2d.drawImage(rightTank, X, Y, 30, 30, null);
                break;
            default:
                //g2d.drawImage(rightTank,X,Y,30,30,null);
                break;
        }

    }

    /**
     * Hàm di chuyển
     */
    public void move() {
        checkCollisionTank();
        switch (orient) {
            case UP:
                if (checkCollisionTank())
                    Y--;
                    break;

            case DOWN:

                if (checkCollisionTank())
                    Y++;
                    break;
            case RIGHT:

                if (checkCollisionTank())
                    X++;
                    break;

            case LEFT:
                if (checkCollisionTank())
                    X--;
                    break;
            default:
                break;
        }
    }

    /**
     *Hàm kiểm tra vị trí của tank và tường, nếu vào tường thì phải di chuyển theo hướng khác
     * @return
     */
    protected boolean checkCollisionTank() {
        System.out.println(X + " " + Y);
        if (orient == UP && Y <= 0) {
            Y = 1;
            return false;
        }
        if (orient == DOWN && Y >= (CommonVLs.HEIGHT -sizeTank)) {
            Y = CommonVLs.HEIGHT-sizeTank;
            return false;
        }
        if (orient == RIGHT &&X >= (CommonVLs.WIDTH-sizeTank)) {
            X = CommonVLs.WIDTH-sizeTank;
            return false;
        }
        if (orient == LEFT && X <= 0) {
            X = 1;
            return false;
        }
        else return true;

    }

    public boolean checkSticklyBullet() {
        boolean check = false;

        return check;
    }

    public int getSizeTank(){return this.sizeTank;}

    public int getX() {
        return this.X;
    }

    public int getY() {
        return this.Y;
    }

    public int getOrient() {
        return this.orient;
    }


}
