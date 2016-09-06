package ultno.marcelslum.ultnogame;


public class CollisionData{
    public PhysicalObject object;
    public float responseX;
    public float responseY;
    public float normalX;
    public float normalY;
    public float weight;
    public boolean isRepeated;
  
    public CollisionData(PhysicalObject object, float responseX, float responseY, float normalX, float normalY, float weight){
        this.object = object;
        this.responseX = responseX;
        this.responseY = responseY;
        this.normalX = normalX;
        this.normalY = normalY;
        this.weight = weight;
        isRepeated = false;
    }
}