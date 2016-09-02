package ultno.marcelslum.ultnogame;


public class CollisionData{
  public PhysicalObject object;
  public float responseX;
  public float responseY;
  public float weight;
  
  public CollisionData(PhysicalObject object, float responseX, float responseY, float weight){
      this.object = object;
      this.responseX = responseX;
      this.responseY = responseY;
      this.weight = weight;
  }
  
}
