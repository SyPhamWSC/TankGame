package com.UET.SyPham.Tank.object.Tank;

import com.UET.SyPham.Tank.common.CommonVLs;

import java.awt.*;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class EnemyTank extends Tank {

    Random random = new Random();
    int delay = random.nextInt(100);
    int count = 0;
    int shoot = random.nextInt(100);


    public EnemyTank(int x, int y) {
        super(x, y);
        CommonVLs common = new CommonVLs();
        upTank = common.getImage("player_green_1.png");
        downTank = common.getImage("player_green_2.png");
        leftTank = common.getImage("player_green_3.png");
        rightTank = common.getImage("player_green_4.png");
        orient = UP;
        speed = SPEED_LOW;

    }


    @Override
    public void move() {

        super.move();
        if(delay > 30 ) {
            --delay;
        }
        else if(delay <30){
            delay = random.nextInt(100);
        }
        else {
            if(orient != 0){
                count++;
                if(count ==20){
                    super.move();
                    count =0;
                    orient = random.nextInt(5);
                }
            }
            else orient = random.nextInt(5);

            //System.out.println("Vi tri tank dich: "+ this.X +" "+this.Y +" " + orient);
        }

    }
    @Override
    public void drawTank(Graphics2D g2d){
        super.drawTank(g2d);
    }

    public boolean autoShoot(){

        if(shoot>0){
            --shoot;
            return false;
        }
        else {
            shoot = random.nextInt(50);
            return true;
        }
    }


    public void changeOrient() {
        this.orient = random.nextInt(5);
    }
}
