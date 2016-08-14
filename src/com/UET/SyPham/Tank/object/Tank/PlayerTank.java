package com.UET.SyPham.Tank.object.Tank;

import com.UET.SyPham.Tank.object.Bullet.Bullet;
import com.UET.SyPham.Tank.object.Bullet.BulletManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */

/**
 * Tank của người chơi
 */

public class PlayerTank extends Tank {
    private boolean checkAllowMove = false;
    public PlayerTank(int x, int y) {
        super(x, y);
        this.speed = SPEED_LOW;
        //Bullet bulletTank = new Bullet();

    }


    @Override
    public void move() {
        if(checkAllowMove){
            super.move();
        }

    }


    @Override
    public void move(int orient) {
        super.move(orient);
    }

    public boolean checkBullet(BulletManager enemyBullet){
        
        return false;
    }
}
