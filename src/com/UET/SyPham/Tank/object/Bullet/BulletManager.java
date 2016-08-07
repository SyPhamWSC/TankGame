package com.UET.SyPham.Tank.object.Bullet;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by sypha_000 on 30-Jul-2016.
 */
public class BulletManager {
//    Graphics2D g2d;
    private ArrayList<Bullet> bullets;

    public BulletManager(){
        bullets = new ArrayList<>();
    }

    public void drawAllBullet(Graphics2D g2d){
        for (Bullet bullet : bullets) {
            bullet.drawBullet(g2d);
        }
    }

    public void moveAll(){
        for (int i = 0; i < bullets.size() ; i++) {
            bullets.get(i).move();
        }
    }
    public void add(Bullet bullet){
        bullets.add(bullet);
    }

    public ArrayList<Bullet> returnBullet(){
        return bullets;
    }

}
