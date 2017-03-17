package com.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

class TargetGroup extends Entity{

    static final String TAG = "TargetGroup";

public ArrayList<TargetGroupData> targets;

  TargetGroup(){
      super("targetGroup", 0f, 0f, Entity.TYPE_TARGET_GROUP);
      textureId = Texture.TEXTURE_TARGETS;
      program = Game.imageColorizedProgram;
  }
    
    public void setDrawInfo(){
      initializeData(12 * targets.size(), 6 * targets.size(), 8 * targets.size(), 16 * targets.size());

      //Log.e(TAG, " desenhando targets "+targets.size());

        for (int i = 0; i < targets.size(); i++){
            //Log.e(TAG, " inserindo target "+
            //        targets.get(i).x + " " +
            //        (targets.get(i).x + targets.get(i).width) + " " +
            //        targets.get(i).y + " " +
            //        (targets.get(i).y + targets.get(i).height));

            Utils.insertRectangleVerticesData(verticesData, i * 12, targets.get(i).x,
                        targets.get(i).x + targets.get(i).width, targets.get(i).y, targets.get(i).y + targets.get(i).height, targets.get(i).alpha);

            Utils.insertRectangleIndicesData(indicesData, i * 6, i * 4);

            if (targets.get(i).type == Target.TARGET_RED){
                  Utils.insertRectangleUvData(uvsData, i * 8, 0f, 816f/1024f, 1f/1024f, 206f/1024f);
            } else if (targets.get(i).type == Target.TARGET_BLUE){
                  Utils.insertRectangleUvData(uvsData, i * 8, 0f, 816f/1024f, 624f/1024f, 830f/1024f);
            } else if (targets.get(i).type == Target.TARGET_GREEN){
                  Utils.insertRectangleUvData(uvsData, i * 8, 0f, 816f/1024f, 208f/1024f, 414f/1024f);
            } else if (targets.get(i).type == Target.TARGET_BLACK){
                  Utils.insertRectangleUvData(uvsData, i * 8, 0f, 816f/1024f, 416f/1024f, 622f/1024f);
            }

            Utils.insertRectangleColorsData(colorsData, i * 16, targets.get(i).lastDecayPercentage*0.1f, targets.get(i).lastDecayPercentage*0.1f, targets.get(i).lastDecayPercentage*0.1f, targets.get(i).alpha);

        }

        colorsBuffer = Utils.generateFloatBuffer(colorsData);
        verticesBuffer = Utils.generateFloatBuffer(verticesData);
        indicesBuffer = Utils.generateShortBuffer(indicesData);
        uvsBuffer = Utils.generateFloatBuffer(uvsData);
    }
}
