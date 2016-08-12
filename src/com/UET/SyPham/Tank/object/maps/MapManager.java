package com.UET.SyPham.Tank.object.maps;

import com.UET.SyPham.Tank.common.CommonVLs;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by sypha_000 on 07-Aug-2016.
 */
public class MapManager {
    private ArrayList<Observe> observes;

    public MapManager(){
        initData();

    }

    private void initData() {
        observes = new ArrayList<>();
        for (int i = 0; i < 25  ; i++) {
            for (int j = 0; j < 25; j++)
                if(i==0||j==0||i ==24 || j==24)
                {
                    observes.add(new Observe(i,j, CommonVLs.BRICK_TYPE));
                }

                observes.add(new Observe(10, 20, CommonVLs.BRICK_TYPE));
                observes.add(new Observe(2, 10, CommonVLs.BRICK_TYPE));
                observes.add(new Observe(4, 15, CommonVLs.BRICK_TYPE));
                observes.add(new Observe(8, 9, CommonVLs.BRICK_TYPE));
        }
    }
    public void drawAll(Graphics2D g2d){
        for (int i = 0; i <observes.size() ; i++) {
            observes.get(i).draw(g2d);
        }
    }

    public boolean checkInside(int x, int y, int size){
        for (int i = 0; i < observes.size() ; i++) {
            if( observes.get(i).isObjInside(x, y, size)) {
                return true;
            }
        }
        return false;

    }



}
