package com.UET.SyPham.Tank.object.Tank;

import com.UET.SyPham.Tank.object.Bullet.Bullet;
import com.UET.SyPham.Tank.object.Bullet.BulletManager;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by sypha_000 on 02-Aug-2016.
 */
public class EnemyTankManager {

    private ArrayList<EnemyTank> enemyTanks;

    public EnemyTankManager(){
        enemyTanks = new ArrayList<>();
    }

    public void moveAll(){
        for (int i = 0; i <enemyTanks.size() ; i++) {
            enemyTanks.get(i).move();
        }
    }
    public  void addEnemyTank(EnemyTank e){
        enemyTanks.add(e);
    }

    public void drawAll(Graphics2D g2d){
        for(EnemyTank enemy : enemyTanks ){
            enemy.drawTank(g2d);
        }
    }
    public void shootAll(BulletManager enemyTankBullet){
        for (int i = 0; i < enemyTanks.size() ; i++) {
            EnemyTank e = enemyTanks.get(i);
            boolean check = e.autoShoot();
            if (check){
                enemyTankBullet.add(new Bullet(e.getX(), e.getY(), e.getOrient(), e.getSizeTank()));
            }
        }
    }
}
