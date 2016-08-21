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
    //private MapManager mapMgr;

    public EnemyTankManager(){
        enemyTanks = new ArrayList<>();
        commonVLs = new CommonVLs();
        //mapMgr = new MapManager();
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
                if (enemyTanks.get(j).checkInside(bullet.getX(),bullet.getY(),bullet.getSize())){
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


    public void moveAll(MapManager mapMgr){

        for (int i = 0; i < enemyTanks.size() ; i++) {
            EnemyTank e = enemyTanks.get(i);
            boolean allowMove = true;
            switch (e.getOrient()) {
                case Tank.UP:
                    if (mapMgr.checkInside(e.getX(), e.getY() - e.getSpeed(),
                            e.getSizeTank())) {
                        //e.move();
                        allowMove = false;
                    }
                    break;
                case Tank.DOWN:
                    if (mapMgr.checkInside(e.getX(), e.getY() + e.getSpeed(),
                            e.getSizeTank())) {
                        //System.out.println("orient: " + orient);
                        //e.move();
                        allowMove = false;
                    }
                    break;
                case Tank.LEFT:
                    if (mapMgr.checkInside(e.getX() - e.getSpeed(), e.getY(),
                            e.getSizeTank())) {
                        //System.out.println("orient: " + orient);
                        //e.move();
                        allowMove = false;
                    }
                    break;
                case Tank.RIGHT:
                    if (mapMgr.checkInside(e.getX() + e.getSpeed(), e.getY(),
                            e.getSizeTank())) {
                        //System.out.println("orient: " + orient);
                        //e.move();
                        allowMove = false;
                    }
                    break;
            }
            for (int j = 0; j <this.enemyTanks.size() ; j++) {
                EnemyTank other = this.enemyTanks.get(j);
                if(other!=e){

                    switch (e.getOrient()){
                        case Tank.UP:
                            if (other.checkInside(e.getX(), e.getY() - e.getSpeed(),
                                    e.getSizeTank())) {
                                //e.move();
                                allowMove = false;

                            }
                            break;
                        case Tank.DOWN:
                            if (other.checkInside(e.getX(), e.getY() + e.getSpeed(),
                                    e.getSizeTank())) {
                                //System.out.println("orient: " + orient);
                                //e.move();
                                allowMove = false;
                            }
                            break;
                        case Tank.LEFT:
                            if (other.checkInside(e.getX() - e.getSpeed(), e.getY(),
                                    e.getSizeTank())) {
                                //System.out.println("orient: " + orient);
                                //e.move();
                                allowMove = false;
                            }
                            break;
                        case Tank.RIGHT:
                            if (other.checkInside(e.getX() + e.getSpeed(), e.getY(),
                                    e.getSizeTank())) {
                                //System.out.println("orient: " + orient);
                                //e.move();
                                allowMove = false;
                            }
                            break;
                    }
                }
            }
            if(allowMove){
                e.move(e.getOrient());
            }
        }
    }

    public void changeOrientAll(){
        for (int i = 0; i < this.enemyTanks.size() ; i++) {
            this.enemyTanks.get(i).changeOrient();
        }
    }
    public ArrayList<EnemyTank> returnEnemyTank(){
        return enemyTanks;
    }
}
