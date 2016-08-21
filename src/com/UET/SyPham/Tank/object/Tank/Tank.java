package com.UET.SyPham.Tank.object.Tank;

import com.UET.SyPham.Tank.common.CommonVLs;
import com.UET.SyPham.Tank.object.Bullet.Bullet;
import com.UET.SyPham.Tank.object.Bullet.BulletManager;

import java.awt.*;
import java.util.Objects;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public abstract class Tank {
    protected int X;
    protected int Y;
    protected final int sizeTank = 30;
    //protected int speedTank;
    protected boolean checkLocation;
    protected String direction;
    protected Image upTank;
    protected Image downTank;
    protected Image leftTank;
    protected Image rightTank;
    protected int speed;

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    public static final int SPEED_HIGH = 5;
    public static final int SPEED_LOW = 3;

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
        //speedTank = 10;
        this.speed = 0;
    }

    /**
     * Vẽ tank với các vị trí xoay hình khác nhau
     *
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
                    Y -= speed;
                break;

            case DOWN:

                if (checkCollisionTank())
                    Y += speed;
                break;
            case RIGHT:

                if (checkCollisionTank())
                    X += speed;
                break;

            case LEFT:
                if (checkCollisionTank())
                    X -= speed;
                break;
            default:
                break;
        }
    }

    /**
     * Hàm di chuyển
     * update
     */
    public void move(int orient) {
        this.orient = orient;
        //System.out.println("move: " + orient);
        //checkCollisionTank();
        switch (orient) {
            case UP:
                if (checkCollisionTank())
                    Y -= speed;
                break;

            case DOWN:

                if (checkCollisionTank())
                    Y += speed;
                break;
            case RIGHT:

                if (checkCollisionTank())
                    X += speed;
                break;

            case LEFT:
                if (checkCollisionTank())
                    X -= speed;
                break;
            default:
                break;
        }
    }

    /**
     * Hàm kiểm tra vị trí của tank và tường, nếu vào tường thì phải di chuyển theo hướng khác
     *
     * @return
     */
    protected boolean checkCollisionTank() {
        //System.out.println(X + " " + Y);
        if (orient == UP && Y <= 0) {
            Y = 1;
            return false;
        }
        if (orient == DOWN && Y >= (CommonVLs.HEIGHT - sizeTank)) {
            Y = CommonVLs.HEIGHT - sizeTank;
            return false;
        }
        if (orient == RIGHT && X >= (CommonVLs.WIDTH - sizeTank)) {
            X = CommonVLs.WIDTH - sizeTank;
            return false;
        }
        if (orient == LEFT && X <= 0) {
            X = 1;
            return false;
        } else return true;

    }


    public int getSizeTank() {
        return this.sizeTank;
    }

    public int getX() {
        return this.X;
    }

    public void setX(int x) {
        this.X = x;
    }

    public void setY(int y) {
        this.Y = y;
    }

    public void setOrient(int orient) {
        this.orient = orient;
    }

    public int getY() {
        return this.Y;
    }

    public int getOrient() {
        return this.orient;
    }

    public int getSpeed() {
        return this.speed;
    }


    /**
     * Kiểm tra một điểm có năm trong khối không
     */
    public boolean isPointInside(int xObj, int yObj, int x, int y, int size) {
        int bottomPoint = x + size;
        int rightPoint = y + size;
        if (xObj > x
                && yObj > y
                && xObj < bottomPoint
                && yObj < rightPoint
                ) {
            return true;
        }
        return false;
    }

    /**
     * Kiểm tra 4 điểm là 4 đỉnh của 1 vật thể xem nó có nằm trong khối còn lại không, nếu có 1 điểm nằm trong là 2 vật đang
     * đang giao nhau
     */
    public boolean checkInside(int xObj, int yObj, int sizeObj) {
        int x = this.X;
        int y = this.Y;
        if (isPointInside(xObj, yObj, x, y, this.sizeTank)
                || isPointInside(xObj + sizeObj, yObj, x, y, this.sizeTank)
                || isPointInside(xObj, yObj + sizeObj, x, y, this.sizeTank)
                || isPointInside(xObj + sizeObj, yObj + sizeObj, x, y, this.sizeTank)) {
            return true;
        }
        if (isPointInside(x, y, xObj, yObj, sizeObj)
                || isPointInside(x + this.sizeTank, y, xObj, yObj, sizeObj)
                || isPointInside(x, y + this.sizeTank, xObj, yObj, sizeObj)
                || isPointInside(x + this.sizeTank, y + this.sizeTank, xObj, yObj, sizeObj)) {
            return true;
        }

        return false;
    }




}
