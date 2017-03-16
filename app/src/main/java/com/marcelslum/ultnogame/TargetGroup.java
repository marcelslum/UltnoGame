public class TargetGroup exteds Entity{


public ArrayList<TargetGroupData> targets;

  TargetGroup(){
      super("targetGroup", 0f, 0f, Entity.TYPE_TARGET_GROUP);
      textureId = Texture.TEXTURE_TARGETS;
      program = Game.targetProgram;
  }
    
  public void setDrawInfo(){
        initializeData(12 * targets.size(), 6 * targets.size(), 8 * targets.size(), 0);
        for (int i = 0; i < targets.size(); i++){
            Utils.insertRectangleVerticesData(verticesData,0, targets.get(i).x, 
                                              targets.get(i).width, targets.get(i).y, targets.get(i).height, targets.get(i).alpha);
            verticesBuffer = Utils.generateFloatBuffer(verticesData);

            Utils.insertRectangleIndicesData(indicesData, 0, 0);
            indicesBuffer = Utils.generateShortBuffer(indicesData);

            if (targets.get(i).type == TARGET_RED){
                  Utils.insertRectangleUvData(uvsData, 0, 0f, 816f/1024f, 1f/1024f, 206f/1024f);
            } else if (targets.get(i).type == TARGET_BLUE){
                  Utils.insertRectangleUvData(uvsData, 0, 0f, 816f/1024f, 624f/1024f, 830f/1024f);
            } else if (targets.get(i).type == TARGET_GREEN){
                  Utils.insertRectangleUvData(uvsData, 0, 0f, 816f/1024f, 208f/1024f, 414f/1024f);
            } else if (targets.get(i).type == TARGET_BLACK){
                  Utils.insertRectangleUvData(uvsData, 0, 0f, 816f/1024f, 416f/1024f, 622f/1024f);
            }
            uvsBuffer = Utils.generateFloatBuffer(uvsData);
        }
  }
  
  private class TargetGroupData{
      int id;
      float x;
      float y;
      float width;
      float height;
      float alpha;
      int type;
    
      TargetGroupData(int id, float x, float y, float width, float height, float alpha, int type){
          this.id = id;
          this.x = x;
          this.y = y;
          this.width = width;
          this.height = height;
          this.alpha = alpha;
          this.type = type;
      }
  }

}
