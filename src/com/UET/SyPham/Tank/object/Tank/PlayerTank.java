package com.UET.SyPham.Tank.object.Tank;

import com.UET.SyPham.Tank.object.Bullet.Bullet;
import com.UET.SyPham.Tank.object.Bullet.BulletManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class PlayerTank extends Tank {
    //public Bullet bulletTank = new Bullet();
    private boolean checkAllowMove = false;

    public Bullet bulletPlayer;
    public PlayerTank(int x, int y) {
        super(x, y);
        this.speed = SPEED_LOW;
        //Bullet bulletTank = new Bullet();

    }

//    @Override
//    public void drawTank(Graphics2D g2d) {
//        super.drawTank(g2d);
//    }

    public void keyPress(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                orient = UP;
                checkAllowMove = true;
                //move();
                break;
            case KeyEvent.VK_S:
                orient = DOWN;
                checkAllowMove = true;
                //move();
                break;
            case KeyEvent.VK_A:
                orient = LEFT;
                checkAllowMove = true;
                //move();
                break;
            case KeyEvent.VK_D:
                orient = RIGHT;
                checkAllowMove = true;
                //move();
                break;
            case KeyEvent.VK_SPACE:
//                bulletPlayer = new Bullet(this.getX(), this.getY(), this.getOrient(), 1);
                break;

        }
    }

    public void keyRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                checkAllowMove = false;
                break;
            case KeyEvent.VK_S:
                checkAllowMove = false;
                break;
            case KeyEvent.VK_A:
                checkAllowMove = false;
                break;
            case KeyEvent.VK_D:
                checkAllowMove = false;
                break;
        }
    }

    @Override
    public void move() {
        if(checkAllowMove){
            super.move();
            System.out.println("Vi tri player: "+this.X+" "+ this.Y);
        }

    }

    @Override
    public void move(int orient) {
//        if(checkAllowMove){
//            super.move(orient);
//            System.out.println("Vi tri player: "+this.X+" "+ this.Y);
//        }

        super.move(orient);
        System.out.println("Vi tri player: "+this.X+" "+ this.Y);


    }

    public boolean checkBullet(BulletManager enemyBullet){
        
        return false;
    }
}
