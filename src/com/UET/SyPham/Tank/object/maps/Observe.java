package com.UET.SyPham.Tank.object.maps;

import com.UET.SyPham.Tank.common.CommonVLs;

import java.awt.*;

/**
 * Created by sypha_000 on 07-Aug-2016.
 */
public class Observe {
    private int x;
    private int y;
    private int size = 20;
    private int type;
    private Image img;


    public Observe(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.size = 20;
        this.type = type;
        if(this.type == CommonVLs.BRICK_TYPE){
            CommonVLs common = new CommonVLs();
            this.img = common.getImage("brick1.png");
        }
    }

    public void draw(Graphics2D g2d){
        g2d.drawImage(this.img, x*this.size, y*this.size, this.size, this.size, null);
    }

    public boolean isPointInside(int xObj, int yObj, int x, int y, int size){
        int bottomPoint = x + size;
        int rightPoint = y + size;
        if(xObj > x
                && yObj >y
                && xObj < bottomPoint
                && yObj < rightPoint
                ){
            return true;
        }

        return false;
    }

    public boolean isObjInside(int xObj, int yObj, int sizeObj){
        int x = this.x*this.size;
        int y = this.y*this.size;
        if(isPointInside(xObj,yObj,x,y, this.size)
                ||isPointInside(xObj + sizeObj, yObj, x, y, this.size)
                || isPointInside(xObj, yObj + sizeObj, x, y, this.size)
                || isPointInside(xObj + sizeObj, yObj + sizeObj, x, y, this.size)){
            return true;
        }
        if(isPointInside(x,y,xObj,yObj, sizeObj)
                ||isPointInside(x + this.size, y, xObj, yObj, sizeObj)
                || isPointInside(x, y + this.size, xObj, yObj, sizeObj)
                || isPointInside(x + this.size, y + this.size, xObj, yObj, sizeObj)){
            return true;
        }

        return false;
    }
}
