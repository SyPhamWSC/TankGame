package com.UET.SyPham.Tank.GUI;

import com.UET.SyPham.Tank.common.CommonVLs;
import com.UET.SyPham.Tank.object.Bullet.Bullet;
import com.UET.SyPham.Tank.object.Bullet.BulletManager;
import com.UET.SyPham.Tank.object.Tank.EnemyTank;
import com.UET.SyPham.Tank.object.Tank.EnemyTankManager;
import com.UET.SyPham.Tank.object.Tank.PlayerTank;
import com.UET.SyPham.Tank.object.Tank.Tank;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class PlayGamePanel extends JPanel implements KeyListener {
    private PlayerTank playerTank;
    private EnemyTank enemyTanks1, enemyTanks2, enemyTanks3;
    private EnemyTankManager enemyTankManager;

    private BulletManager bulletManagerEnemyTanks;
    private BulletManager bulletManager;

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

        bulletManagerEnemyTanks = new BulletManager();
        enemyTanks1 = new EnemyTank(250, 250);
        enemyTanks2 = new EnemyTank(300,350);
        enemyTanks3 = new EnemyTank(400, 450);
        enemyTankManager.addEnemyTank(enemyTanks1);
        enemyTankManager.addEnemyTank(enemyTanks2);
        enemyTankManager.addEnemyTank(enemyTanks3);

        addKeyListener(this);
        setFocusable(true);
    }

    /**
     * update lai tro choi sau 1 khoang tgian nao day
     */
    private void gameLoop(){
        update();
        draw();
    }

    private void update() {
        enemyTankManager.shootAll(bulletManagerEnemyTanks);

        if (orient != 0){
            System.out.println("orient: " + orient);
            playerTank.move(orient);
        }


//        count++;
//        if (count % 10 == 0) {
//
//            enemyTankManager.moveAll();
//            count = 0;
//        }
//        if (count % 5 == 0){
//            bulletManager.moveAll();
//            bulletManagerEnemyTanks.moveAll();
//        }


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
    }

    long count = 0;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (isPlaying) {
                gameLoop();
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
//        playerTank.keyPress(keyEvent);
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                bulletManager.add(new Bullet(playerTank.getX(), playerTank.getY(), playerTank.getOrient(), playerTank.getSizeTank()));

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
//        playerTank.keyRelease(keyEvent);
        orient = 0;
        //System.out.println(keyEvent.getKeyCode());
    }


}
