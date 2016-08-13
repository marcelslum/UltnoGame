package ultno.marcelslum.ultnogame;

import java.util.ArrayList;

/**
 * Created by marcel on 01/08/2016.
 */
public class SatBox {

        Vector pos;
        float w;
        float h;

        SatBox(Vector pos, float w, float h) {
        this.pos = pos;
        this.w = w;
        this.h = h;
        }

// Returns a polygon whose edges are the same as this box.
/**
 * @return {SatPolygon} A new SatPolygon that represents this box.
 */
    public SatPolygon toPolygon(){

        Vector pos = this.pos;
        float w = this.w;
        float h = this.h;
        ArrayList<Vector> points = new ArrayList<Vector>();
        points.add(new Vector(0,0));
        points.add(new Vector(w,0));
        points.add(new Vector(w,h));
        points.add(new Vector(0,h));

        return new SatPolygon(new Vector(pos.x, pos.y), points);
    }

}
