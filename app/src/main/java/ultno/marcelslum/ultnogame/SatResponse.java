package ultno.marcelslum.ultnogame;


public class SatResponse {
    // An object representing the result of an intersection. Contains:
    //  - The two objects participating in the intersection
    //  - The vector representing the minimum change necessary to extract the first object
    //    from the second one (as well as a unit vector in that direction and the magnitude
    //    of the overlap)
    //  - Whether the first object is entirely inside the second, and vice versa.
    SatPolygon pa;
    SatPolygon pb;
    SatCircle ca;
    SatCircle cb;
    Vector overlapN;
    Vector overlapV;
    boolean aInB;
    boolean bInA;
    float overlap;

    SatResponse(){
        this.ca = null;
        this.cb = null;
        this.pa = null;
        this.pb = null;
        this.overlapN = new Vector(0,0);
        this.overlapV = new Vector(0,0);
        this.clear();
    }

    // Set some values of the response back to their defaults.  Call this between tests if
    // you are going to reuse a single SatResponse object for multiple intersection tests (recommented
    // as it will avoid allcating extra memory)
    public SatResponse clear(){
        this.aInB = true;
        this.bInA = true;
        this.overlap = Float.MAX_VALUE;
        return this;
    }
}
