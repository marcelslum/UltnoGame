package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;

// ## SatPolygon
//
// Represents a *convex* polygon with any number of points (specified in counter-clockwise order)
//
// Note: Do _not_ manually change the `points`, `angle`, or `offset` properties. Use the
// provided setters. Otherwise the calculated properties will not be updated correctly.
//
// `pos` can be changed directly.

// Create a new polygon, passing in a position vector, and an array of points (represented
// by vectors relative to the position vector)

public class SatPolygon {
    Vector pos;
    float angle;
    Vector offset;
    ArrayList<Vector> points;
    ArrayList<Vector> calcPoints;
    ArrayList<Vector> edges;
    ArrayList<Vector> normals;

    SatPolygon(Vector pos, ArrayList<Vector> points){
        this.pos = pos;
        this.angle = 0;
        this.offset = new Vector(0,0);
        this.setPoints(points);

    }

    // Set the points of the polygon.
    //
    // Note: The points are counter-clockwise *with respect to the coordinate system*.
    // If you directly draw the points on a screen that has the origin at the top-left corner
    // it will _appear_ visually that the points are being specified clockwise. This is just
    // because of the inversion of the Y-axis when being displayed.

    public SatPolygon setPoints(ArrayList<Vector> points){
        // Only re-allocate if this is a new polygon or the number of points has changed.
        boolean lengthChanged = false;
        if ((this.points == null)||(this.points.size() != points.size())){
            lengthChanged = true;
        }
        //Log.e("left changed ", " "+lengthChanged);
        if (lengthChanged) {
            int i;
            this.calcPoints = new ArrayList<>();
            this.edges = new ArrayList<>();
            this.normals = new ArrayList<>();
            // Allocate the vector arrays for the calculated properties
            for (i = 0; i < points.size(); i++) {
                calcPoints.add(new Vector(0,0));
                edges.add(new Vector(0,0));
                normals.add(new Vector(0,0));
            }

            //Log.e("calc points ", " "+calcPoints.size());

        }
        this.points = points;

        this._recalc();
        return this;
    }

    // Set the current rotation angle of the polygon.
    /**
     * @param {number} angle The current rotation angle (in radians).
     * @return {SatPolygon} This for chaining.
     */

    public SatPolygon setAngle(float angle){
        this.angle = angle;
        this._recalc();
        return this;

    }

    // Set the current offset to apply to the `points` before applying the `angle` rotation.
    /**
     * @param {Vector} offset The new offset vector.
     * @return {SatPolygon} This for chaining.
     */
    public SatPolygon setOffset(Vector offset){
        this.offset = offset;
        this._recalc();
        return this;
    }

    // Rotates this polygon counter-clockwise around the origin of *its local coordinate system* (i.e. `pos`).
    //
    // Note: This changes the **original** points (so any `angle` will be applied on top of this rotation).
    /**
     * @param {number} angle The angle to rotate (in radians)
     * @return {SatPolygon} This for chaining.
     */
    public SatPolygon rotate(float angle){
        int len = this.points.size();
        for (int i = 0; i < len; i++){
            this.points.get(i).rotate(angle);
        }
        this._recalc();
        return this;
    }


    // Translates the points of this polygon by a specified amount relative to the origin of *its own coordinate
    // system* (i.e. `pos`).
    //
    // This is most useful to change the "center point" of a polygon. If you just want to move the whole polygon, change
    // the coordinates of `pos`.
    //
    // Note: This changes the **original** points (so any `offset` will be applied on top of this translation)

    public SatPolygon translate(float x, float y){
        int len = this.points.size();
        for (int i = 0; i < len; i++) {
            this.points.get(i).x += x;
            this.points.get(i).y += y;
        }
        this._recalc();
        return this;
    }

    // Computes the calculated collision polygon. Applies the `angle` and `offset` to the original points then recalculates the
    // edges and normals of the collision polygon.

    public SatPolygon _recalc(){
        // Calculated points - this is what is used for underlying collisions and takes into account
        // the angle/offset set on the polygon.
        ArrayList<Vector> calcPoints = this.calcPoints;
        // The edges here are the direction of the `n`th edge of the polygon, relative to
        // the `n`th point. If you want to draw a given edge from the edge value, you must
        // first translate to the position of the starting point.
        ArrayList<Vector> edges = this.edges;
        // The normals here are the direction of the normal for the `n`th edge of the polygon, relative
        // to the position of the `n`th point. If you want to draw an edge normal, you must first
        // translate to the position of the starting point.
        ArrayList<Vector> normals = this.normals;
        // Copy the original points array and apply the offset/angle
        ArrayList<Vector> points = this.points;
        Vector offset = this.offset;
        float angle = this.angle;
        int len = this.points.size();
        //Log.e("tag size", " "+len);
        //Log.e("tag size11", " "+this.calcPoints.size());
        //Log.e("tag size111", " "+points.size());
        int i;
        for (i = 0; i < len; i++) {
            //Log.e("tag size1111111", " "+i);
            Vector calcPoint = calcPoints.get(i).copy(points.get(i));
            calcPoint.x += offset.x;
            calcPoint.y += offset.y;
            if (angle != 0) {
                calcPoint.rotate(angle);
            }
        }
        // Calculate the edges/normals
        //Log.e("tag size2", " "+len);
        for (i = 0; i < len; i++) {
            Vector p1 = calcPoints.get(i);

            Vector p2;
            if(i < (len - 1)){
                p2 = calcPoints.get(i+1);
            } else {
                p2 = calcPoints.get(0);
            }

            Vector e = edges.get(i).copy(p2).sub(p1);
            normals.get(i).copy(e).perp().normalize();
        }
        return this;
    }

    // Compute the axis-aligned bounding box. Any current state
    // (translations/rotations) will be applied before constructing the AABB.
    //
    // Note: Returns a _new_ `SatPolygon` each time you call this.

    public SatPolygon getAABB(){
        ArrayList<Vector> points = this.calcPoints;
        int len = points.size();
        float xMin = points.get(0).x;
        float yMin = points.get(0).y;
        float xMax = points.get(0).x;
        float yMax = points.get(0).y;

        for (int i = 1; i < len; i++) {
            Vector point = points.get(i);
            if (point.x < xMin) {
                xMin = point.x;
            }
            else if (point.x > xMax) {
                xMax = point.x;
            }
            if (point.y < yMin) {
                yMin = point.y;
            }
            else if (point.y > yMax) {
                yMax = point.y;
            }
        }
        return new SatBox(this.pos.clone().add(new Vector(xMin, yMin)), xMax - xMin, yMax - yMin).toPolygon();
    }
}
