package com.UET.SyPham.Tank.object;

import com.UET.SyPham.Tank.common.CommonVLs;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by sypha_000 on 10-Aug-2016.
 */
public class AnimationManager {

    private ArrayList<Animation> animArr ;
    public AnimationManager(){
        this.animArr = new ArrayList<>();
    }

    public void addAnim(int type, int x, int y){
        Animation anim = new Animation(type, CommonVLs.ANIMATION_SIZE,x, y );
        animArr.add(anim);
    }
    public void drawAll(Graphics2D g2d){
        for (int i = 0; i <animArr.size() ; i++) {
            Animation anim = animArr.get(i);
            boolean boolAnim = anim.draw(g2d);
            if(!boolAnim) {
                animArr.remove(i);
                i--;
            }
        }
    }
}
