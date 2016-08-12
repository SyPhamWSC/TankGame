package com.UET.SyPham.Tank.object;

import com.UET.SyPham.Tank.common.CommonVLs;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by sypha_000 on 10-Aug-2016.
 */
public class Animation {

    private int type;
    private ArrayList<Image> imgArr;
    private int size;
    private int state;
    private int x;
    private int y;

    public Animation(int type, int size, int x, int y){
        this.type =type;
        this.size= size;
        this.x = x;
        this.y = y;
        imgArr =new ArrayList<>();

        initImageFromType(type);
    }

    private void initImageFromType(int type) {
        CommonVLs common = new CommonVLs();
        if(type == CommonVLs.TANK_EXPLORE){
            //lay anh tank no cho vao arraylist
            String tankName = "tank_exp";

            for (int i = 1; i < 6 ; i++) {
                Image imgTank = common.getImage(tankName + i +".png");
                imgArr.add(imgTank);
            }
        }else if(type == CommonVLs.BULLET_EXPLORE){
            Image imgBullet = common.getImage("explosion.png");
            imgArr.add(imgBullet);
        }
    }

    public boolean draw(Graphics2D g2d){
        Image img = imgArr.get(0);
        imgArr.remove(0);
        g2d.drawImage(img, this.x, this.y,size,size, null);
        if(imgArr.isEmpty()){
            return false;
        }else return true;
    }

}
