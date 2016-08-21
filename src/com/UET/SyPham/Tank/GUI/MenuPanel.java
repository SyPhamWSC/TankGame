package com.UET.SyPham.Tank.GUI;

import com.UET.SyPham.Tank.common.CommonVLs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class MenuPanel extends JPanel{
    private JButton btnPlay;
    private JButton btnExit;
    private JButton btnNewGame;
    private JButton btnCheckSound;
    private CommonVLs commonVLs;
    private boolean checkPlay;
    private Image imgTankGame;

    public MenuPanel(){
        initCompoments();
    }
    public void initCompoments(){
        setBackground(Color.GRAY);
        setLayout(null);
        setBounds(500,0,200,500);
        btnPlay = new JButton("Pause");
        btnPlay.setBounds(25,260,150,40);
        add(btnPlay);
        checkPlay = false;
        commonVLs = new CommonVLs();
        imgTankGame = commonVLs.getImage("icon.jpg");
        btnPlay.setIcon(commonVLs.setIconBtn("pauseGame.png", 30,30));

        btnExit = new JButton("Quit Game");
        btnExit.setBounds(25,380,150,40);
        btnExit.setIcon(commonVLs.setIconBtn("quitGame.png",30,30));
        add(btnExit);

        btnNewGame = new JButton("New Game");
        btnNewGame.setBounds(25, 200,150,40);
        btnNewGame.setIcon(commonVLs.setIconBtn("newGame.png",30,30));
        add(btnNewGame);

        btnCheckSound = new JButton("Sound: Enable");
        btnCheckSound.setBounds(25,320,150,40);
        btnCheckSound.setIcon(commonVLs.setIconBtn("enableSound.png",30,30));
        add(btnCheckSound);


        /**
         * Check Pause game
         */
        btnPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!checkPlay) {
                    myListener.returnIsPause(true);
                    btnPlay.setIcon(commonVLs.setIconBtn("playGame.png", 30,30));
                    btnPlay.setText("Resume");
                    checkPlay = true;
                    //System.out.println("check Play 2: "+ checkPlay);
                }
                else if(checkPlay){
                    btnPlay.setIcon(commonVLs.setIconBtn("pauseGame.png", 30,30));
                    btnPlay.setText("Pause");
                    myListener.returnIsPause(false);
                    checkPlay = false;
                    //System.out.println("check Play: "+ checkPlay);
                }
            }
        });

        /**
         * Check Quit Game
         */

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

    }


    /**
     * Tran data
     */
    private void actionListenner(){

    }
    interface Listener{
        void returnIsPause(boolean isPause);
    }
    private Listener myListener;
    public void setListener(Listener listener){
        this.myListener = listener;
    }


    @Override
    protected void paintComponent(Graphics graphics){
       super.paintComponent(graphics);
        Graphics2D g2d;
        g2d = (Graphics2D) graphics;
        g2d.drawImage(imgTankGame,10,20,175,100,null);
    }
}
