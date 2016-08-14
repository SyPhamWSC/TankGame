package com.UET.SyPham.Tank.object.Tank;

import com.UET.SyPham.Tank.common.CommonVLs;
import com.UET.SyPham.Tank.object.AnimationManager;
import com.UET.SyPham.Tank.object.Bullet.Bullet;
import com.UET.SyPham.Tank.object.Bullet.BulletManager;
import com.UET.SyPham.Tank.object.maps.MapManager;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sypha_000 on 02-Aug-2016.
 */
public class EnemyTankManager {

    private CommonVLs commonVLs;
    private ArrayList<EnemyTank> enemyTanks;
    private MapManager mapMgr;

    public EnemyTankManager(){
        enemyTanks = new ArrayList<>();
        commonVLs = new CommonVLs();
        mapMgr = new MapManager();
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
                try {
                    commonVLs.playSound("shoot.wav");
                } catch (IOException e1) {
                }
                enemyTankBullet.add(new Bullet(e.getX(), e.getY(), e.getOrient(), e.getSizeTank()));

            }
        }
    }
    public ArrayList<EnemyTank> getenemyTanks(){
        return this.enemyTanks;
    }

    /**
     * check dinh dan, trung dan thi bi chet, xoa khoi mang
     * @param bulletMgr
     * @param aniMgr
     */
    public void checkStickBullet(BulletManager bulletMgr, AnimationManager aniMgr){
        for (int i = 0; i < bulletMgr.returnBullet().size() ; i++) {
            Bullet bullet = bulletMgr.returnBullet().get(i);
            for (int j = 0; j <enemyTanks.size() ; j++) {
                if (enemyTanks.get(j).checkBullet(bullet.getX(),bullet.getY(),bullet.getSize())){
                    aniMgr.addAnim(1,enemyTanks.get(j).getX(), enemyTanks.get(j).getY());
                    try {
                        commonVLs.playSound("buildBouns.wav");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    enemyTanks.remove(j);
                    if(enemyTanks.isEmpty()){
                        break;
                    }
                }
            }
        }
    }
    int orients;
    public void move(){
        for (int i = 0; i < enemyTanks.size() ; i++) {
            orients = enemyTanks.get(i).getOrient();
            EnemyTank e = enemyTanks.get(i);
            if (orients != 0) {
                switch (orients) {
                    case Tank.UP:
                        if (!mapMgr.checkInside(e.getX(), e.getY() - e.getSpeed(),
                                e.getSizeTank())) {
                            enemyTanks.get(i).move();
                        }
                        else{
                            enemyTanks.get(i).setOrient(Tank.DOWN);
                            enemyTanks.get(i).move();
                        }
                        break;
                    case Tank.DOWN:
                        if (!mapMgr.checkInside(e.getX(), e.getY() + e.getSpeed(),
                                e.getSizeTank())) {
                            //System.out.println("orient: " + orient);
                            enemyTanks.get(i).move();
                        }else{
                            enemyTanks.get(i).setOrient(Tank.UP);
                            enemyTanks.get(i).move();
                        }

                        break;
                    case Tank.LEFT:
                        if (!mapMgr.checkInside(e.getX() - e.getSpeed(), e.getY(),
                                e.getSizeTank())) {
                            //System.out.println("orient: " + orient);
                            enemyTanks.get(i).move();
                        }
                        else{
                            enemyTanks.get(i).setOrient(Tank.RIGHT);
                            enemyTanks.get(i).move();
                        }
                        break;
                    case Tank.RIGHT:
                        if (!mapMgr.checkInside(e.getX() + e.getSpeed(), e.getY(),
                                e.getSizeTank())) {
                            //System.out.println("orient: " + orient);
                            enemyTanks.get(i).move();
                        }
                        else{
                            enemyTanks.get(i).setOrient(Tank.LEFT);
                            enemyTanks.get(i).move();
                        }
                        break;
                }
            }
        }
    }

    public ArrayList<EnemyTank> returnEnemyTank(){
        return enemyTanks;
    }
}
