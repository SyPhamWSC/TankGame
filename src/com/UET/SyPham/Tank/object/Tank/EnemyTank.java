package com.UET.SyPham.Tank.object.Tank;

import com.UET.SyPham.Tank.common.CommonVLs;

import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by sypha_000 on 27-Jul-2016.
 */
public class EnemyTank extends Tank {

    Random random = new Random();
    int delay = random.nextInt(100);
    int count = 0;



    public EnemyTank(int x, int y) {
        super(x, y);
        CommonVLs common = new CommonVLs();
        upTank = common.getImage("player_green_1.png");
        downTank = common.getImage("player_green_2.png");
        leftTank = common.getImage("player_green_3.png");
        rightTank = common.getImage("player_green_4.png");
        orient = UP;

    }

    @Override
    public void move() {
        //int i = 100000;
        super.move();
        System.out.println(orient);
        if(delay > 40 ) {
            //super.move();
            --delay;
            System.out.println("delay: "+delay);
        }
        else if(delay <40){
            delay = random.nextInt(1000);

        }
        else {

            if(orient != 0){
                count++;
                System.out.println("count: "+ count);
                if(count ==50){
                    super.move();
                    count =0;
                    orient = random.nextInt(5);
                }
            }
            else orient = random.nextInt(5);

            System.out.println("Vi tri tank dich: "+ this.X +" "+this.Y);
        }

    }

}
