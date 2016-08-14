package com.UET.SyPham.Tank.GUI;

import com.UET.SyPham.Tank.common.CommonVLs;
import com.UET.SyPham.Tank.object.AnimationManager;
import com.UET.SyPham.Tank.object.Bullet.Bullet;
import com.UET.SyPham.Tank.object.Bullet.BulletManager;
import com.UET.SyPham.Tank.object.Tank.EnemyTank;
import com.UET.SyPham.Tank.object.Tank.EnemyTankManager;
import com.UET.SyPham.Tank.object.Tank.PlayerTank;
import com.UET.SyPham.Tank.object.Tank.Tank;
import com.UET.SyPham.Tank.object.maps.MapManager;
import com.UET.SyPham.Tank.object.maps.Observe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class PlayGamePanel extends JPanel implements KeyListener {
    private PlayerTank playerTank;
    private EnemyTank enemyTanks1, enemyTanks2, enemyTanks3;
    private EnemyTankManager enemyTankManager;

    CommonVLs commonVLs;
    private BulletManager bulletManagerEnemyTanks;
    private BulletManager bulletManager;

    private AnimationManager animationMgr;
    private Observe observe;
    private MapManager mapMgr;
    private boolean isPlaying;
    private int orient;

    public PlayGamePanel() {
        isPlaying = true;
        initCompoments();
        thread.start();
    }

    public void initCompoments() {
        setBounds(0, 0, CommonVLs.WIDTH, CommonVLs.HEIGHT);
        setBackground(Color.BLUE);
        bulletManager = new BulletManager();
        playerTank = new PlayerTank(30, 30);
        enemyTankManager = new EnemyTankManager();
        animationMgr = new AnimationManager();
        commonVLs = new CommonVLs();
        bulletManagerEnemyTanks = new BulletManager();
        enemyTanks1 = new EnemyTank(250, 250);
        enemyTanks2 = new EnemyTank(300, 350);
        enemyTanks3 = new EnemyTank(400, 450);
        enemyTankManager.addEnemyTank(enemyTanks1);
        enemyTankManager.addEnemyTank(enemyTanks2);
        enemyTankManager.addEnemyTank(enemyTanks3);
        enemyTankManager.addEnemyTank(new EnemyTank(150,150));
        mapMgr = new MapManager();
        //mapMgr.checkInside(playerTank.getX(), playerTank.getY(), playerTank.getSizeTank());
        addKeyListener(this);
        setFocusable(true);
    }

    /**
     * update lai tro choi sau 1 khoang tgian nao day
     */
    private void gameLoop() {
        update();
        draw();
    }

    /**
     * Check trung dan cua playerTank
     */

    public void checkStickBulletPlayer() {
        for (int i = 0; i < bulletManagerEnemyTanks.returnBullet().size(); i++) {
            Bullet bullet = bulletManagerEnemyTanks.returnBullet().get(i);
            if(playerTank.checkBullet(bullet.getX(), bullet.getY(),bullet.getSize())){
                System.out.println("Dinh Dan");
                animationMgr.addAnim(1,playerTank.getX(), playerTank.getY());
                bulletManagerEnemyTanks.returnBullet().remove(i);
                isPlaying = false;
                JOptionPane.showMessageDialog(null, "Thua rồi cưng ơi");
            }
        }
    }


    /**
     * Xóa đạn khi bị bắn vào tường
     */
    private void DeleBullet(BulletManager bulletMgr) {
        for (int i = 0; i < bulletMgr.returnBullet().size(); i++) {
            //Bullet contentBullet = bulletManager.returnBullet().get(i);
            if (mapMgr.checkInside(bulletMgr.returnBullet().get(i).getX(), bulletMgr.returnBullet().get(i).getY(),
                    bulletMgr.returnBullet().get(i).getSize())) {
                int orientTemp = bulletMgr.returnBullet().get(i).getOrient();
                switch (orientTemp) {
                    case Tank.UP:
                        animationMgr.addAnim(1, bulletMgr.returnBullet().get(i).getX() - (CommonVLs.ANIMATION_SIZE / 2) + 4, bulletMgr.returnBullet().get(i).getY());
                        break;
                    case Tank.DOWN:
                        animationMgr.addAnim(1, bulletMgr.returnBullet().get(i).getX() - (CommonVLs.ANIMATION_SIZE / 2) + 4, bulletMgr.returnBullet().get(i).getY() - CommonVLs.ANIMATION_SIZE);
                        break;
                    case Tank.LEFT:
                        animationMgr.addAnim(1, bulletMgr.returnBullet().get(i).getX(), bulletMgr.returnBullet().get(i).getY() - (CommonVLs.ANIMATION_SIZE / 2) + 4);
                        break;
                    case Tank.RIGHT:
                        animationMgr.addAnim(1, bulletMgr.returnBullet().get(i).getX() - CommonVLs.ANIMATION_SIZE / 2, bulletMgr.returnBullet().get(i).getY() - (CommonVLs.ANIMATION_SIZE / 2) + 4);
                        break;
                }
                bulletMgr.returnBullet().remove(i);
            }
        }
    }

    private void enemyMove(){
        for (int i = 0; i < enemyTankManager.getenemyTanks().size() ; i++) {
            int orientTank = enemyTankManager.getenemyTanks().get(i).getOrient();
            EnemyTank tempEnt = enemyTankManager.getenemyTanks().get(i);
            switch (orientTank){
                case Tank.UP:
                    if(!mapMgr.checkInside(tempEnt.getX(), tempEnt.getY() - tempEnt.getSpeed(),tempEnt.getSizeTank())){
                        enemyTankManager.getenemyTanks().get(i).move();
                    }else {
                        enemyTankManager.getenemyTanks().get(i).setY(tempEnt.getY() + 1 );
                        enemyTankManager.getenemyTanks().get(i).move();
                    }
                    //System.out.println("ko chay nua UP");
                    break;
                case Tank.DOWN:
                    if(!mapMgr.checkInside(tempEnt.getX(), tempEnt.getY() + tempEnt.getSpeed(),tempEnt.getSizeTank())){
                        enemyTankManager.getenemyTanks().get(i).move();
                    }else {
                        enemyTankManager.getenemyTanks().get(i).setY(tempEnt.getY() - 1 );
                        enemyTankManager.getenemyTanks().get(i).move();
                    }
                    //System.out.println("ko chay nua Down");
                    break;
                case Tank.LEFT:
                    if(!mapMgr.checkInside(tempEnt.getX()- tempEnt.getSpeed(), tempEnt.getY() ,tempEnt.getSizeTank())){
                        enemyTankManager.getenemyTanks().get(i).move();
                    }else {
                        enemyTankManager.getenemyTanks().get(i).setX(tempEnt.getX() + 1 );
                        enemyTankManager.getenemyTanks().get(i).move();
                    }
                    //System.out.println("ko chay nua Left");
                    break;
                case Tank.RIGHT:
                    if(!mapMgr.checkInside(tempEnt.getX()+ tempEnt.getSpeed(), tempEnt.getY() - tempEnt.getSpeed(),tempEnt.getSizeTank())){
                        enemyTankManager.getenemyTanks().get(i).move();
                    }else {
                        enemyTankManager.getenemyTanks().get(i).setX(tempEnt.getX() - 1 );
                        enemyTankManager.getenemyTanks().get(i).move();
                    }
                    //System.out.println("ko chay nua Right");
                    break;
            }
        }
    }


    /**
     * Hàm update các hoạt động trong game
     */
    private void update() {
        enemyTankManager.shootAll(bulletManagerEnemyTanks);
        bulletManagerEnemyTanks.moveAll();
        enemyTankManager.moveAll();
        //enemyTankManager.move();
        bulletManager.moveAll();

        DeleBullet(bulletManager);
        DeleBullet(bulletManagerEnemyTanks);

        checkStickBulletPlayer();

        enemyTankManager.checkStickBullet(bulletManager,animationMgr);

        /**
         * Check biến isPlaying, nếu hết tank địch thì trả về true --> Win
         */
        if(enemyTankManager.returnEnemyTank().isEmpty()){
            isPlaying = false;
            JOptionPane.showMessageDialog(null,"Thắng rồi cưng ơi!!!đsss");
        }

        /**
         * Kiểm tra va chạm của tank với Map
         */
        if (orient != 0) {

            if (orient != 0) {
                switch (orient) {
                    case Tank.UP:
                        if (!mapMgr.checkInside(playerTank.getX(), playerTank.getY() - playerTank.getSpeed(),
                                playerTank.getSizeTank())) {
                            playerTank.move(orient);
                        }
                        break;
                    case Tank.DOWN:
                        if (!mapMgr.checkInside(playerTank.getX(), playerTank.getY() + playerTank.getSpeed(),
                                playerTank.getSizeTank())) {
                            //System.out.println("orient: " + orient);
                            playerTank.move(orient);
                        }

                        break;
                    case Tank.LEFT:
                        if (!mapMgr.checkInside(playerTank.getX() - playerTank.getSpeed(), playerTank.getY(),
                                playerTank.getSizeTank())) {
                            //System.out.println("orient: " + orient);
                            playerTank.move(orient);
                        }
                        break;
                    case Tank.RIGHT:
                        if (!mapMgr.checkInside(playerTank.getX() + playerTank.getSpeed(), playerTank.getY(),
                                playerTank.getSizeTank())) {
                            //System.out.println("orient: " + orient);
                            playerTank.move(orient);
                        }
                        break;
                }
            }
        }

    }

    private void draw() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d;
        g2d = (Graphics2D) graphics;
        playerTank.drawTank(g2d);
        bulletManager.drawAllBullet(g2d);
        enemyTankManager.drawAll(g2d);
        bulletManagerEnemyTanks.drawAllBullet(g2d);
        mapMgr.drawAll(g2d);
        if(count%3==0){
            animationMgr.drawAll(g2d);
        }
    }

    /**
     * Biến count để vẽ lại nhiều lần animation tank nổ theo số lần mong muốn
     */
    private int count;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (isPlaying) {
                gameLoop();
                count++;
                if(count>10000){
                    count = 0;
                }
                try {
                    sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    });

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                bulletManager.add(new Bullet(playerTank.getX(), playerTank.getY(), playerTank.getOrient(), playerTank.getSizeTank()));
                try {
                    commonVLs.playSound("shoot_tank.wav");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case KeyEvent.VK_W:
                orient = Tank.UP;
                break;
            case KeyEvent.VK_S:
                orient = Tank.DOWN;
                break;
            case KeyEvent.VK_A:
                orient = Tank.LEFT;
                break;
            case KeyEvent.VK_D:
                orient = Tank.RIGHT;
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        orient = 0;
    }
}
