package com.UET.SyPham.Tank.GUI;

import com.UET.SyPham.Tank.common.CommonVLs;
import com.UET.SyPham.Tank.object.Bullet.Bullet;
import com.UET.SyPham.Tank.object.Bullet.BulletManager;
import com.UET.SyPham.Tank.object.Tank.EnemyTank;
import com.UET.SyPham.Tank.object.Tank.PlayerTank;

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
    private EnemyTank enemyTanks;

    private BulletManager bulletManager;
    public PlayGamePanel() {
        initCompoments();
    }

    public void initCompoments() {
        setBounds(0, 0, CommonVLs.WIDTH, CommonVLs.HEIGHT);
        setBackground(Color.BLUE);
        bulletManager = new BulletManager();
        playerTank = new PlayerTank(30, 30);
        enemyTanks = new EnemyTank(this.randomLocation(), this.randomLocation());

        thread.start();
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d;
        g2d = (Graphics2D) graphics;

        playerTank.drawTank(g2d);
        bulletManager.drawAllBullet(g2d);
        enemyTanks.drawTank(g2d);
        //enemyTanks.move();
        //playerTank.move();
        //playerTank.bulletTank.drawBullet(g2d);


    }

    long count = 0;
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {


            while (true) {
                count++;
                if (count % 10 == 0) {
                    playerTank.move();
                    enemyTanks.move();
                    count = 0;
                }
                if (count % 5 == 0){
                    bulletManager.moveAll();
                }
                repaint();
                try {
                    sleep(1);
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
        playerTank.keyPress(keyEvent);
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_SPACE:
                bulletManager.add(new Bullet(playerTank.getX(), playerTank.getY(), playerTank.getOrient(), playerTank.getSizeTank()));
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        playerTank.keyRelease(keyEvent);


        //System.out.println(keyEvent.getKeyCode());
    }
    public int randomLocation(){
        int index;
        Random rd = new Random();
        index = rd.nextInt(470);
        return index;
    }
}
