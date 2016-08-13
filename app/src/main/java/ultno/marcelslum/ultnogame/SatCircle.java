package ultno.marcelslum.ultno;

/**
 * Created by marcel on 01/08/2016.
 */
// ## SatCircle
//
// Represents a circle with a position and a radius.

public class SatCircle {
    public Vector pos;
    public float r;

    SatCircle(Vector pos, float r){
        this.pos = pos;
        this.r = r;
    }

    // Compute the axis-aligned bounding box (AABB) of this SatCircle.
    //
    // Note: Returns a _new_ `SatPolygon` each time you call this.
    public SatPolygon getAABB(){
        float r = this.r;
        Vector corner = this.pos.clone().sub (new Vector(r, r));
        SatBox box = new SatBox(new Vector(corner.x, corner.y), r*2, r*2);
        return box.toPolygon();
    }
}