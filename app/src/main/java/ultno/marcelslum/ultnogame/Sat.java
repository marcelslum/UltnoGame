package ultno.marcelslum.ultnogame;


import android.util.Log;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by marcel on 01/08/2016.
 */
public class Sat {

    private static Sat ourInstance = new Sat();

    private int LEFT_VORONOI_REGION = -1;
    private int MIDDLE_VORONOI_REGION = 0;
    private int RIGHT_VORONOI_REGION = 1;

    public static Sat getInstance() {
        return ourInstance;
    }

    // A pool of `Vector` objects that are used in calculations to avoid
    // allocating memory.
    public Stack<Vector> T_VECTORS;

    // A pool of arrays of numbers used in calculations to avoid allocating
    // memory.
    public Stack<float[]> T_ARRAYS;

    // Temporary response used for polygon hit detection.
    public SatResponse T_RESPONSE;

    // Unit square polygon used for polygon hit detection.
    public SatPolygon UNIT_SQUARE;

    String TAG = "TAG";
    private Sat() {
        T_VECTORS = new Stack<Vector>();

        for (int i = 0; i < 10; i++) {
            T_VECTORS.push(new Vector(0, 0));
        }

        T_ARRAYS = new Stack<float[]>();
        for (int i = 0; i < 5; i++) {
            float [] a = new float[2];
            a[0] = 0.0f;
            a[1] = 0.0f;
            T_ARRAYS.push(a);
        }

        T_RESPONSE = new SatResponse();

        SatBox box = new SatBox(new Vector(0, 0), 1, 1);

        UNIT_SQUARE = box.toPolygon();
    }
    // ## Helper Functions

    // Flattens the specified array of points onto a unit vector axis,
    // resulting in a one dimensional range of the minimum and
    // maximum value on that axis.
    /**
     * @param {Array.<Vector>} points The points to flatten.
     * @param {Vector} normal The unit vector axis to flatten on.
     * @param {Array.<number>} result An array.  After calling this function,
     *   result[0] will be the minimum value,
     *   result[1] will be the maximum value.
     */
    public void flattenPointsOn(ArrayList<Vector> points, Vector normal, float[] result) {
        float min = Float.MAX_VALUE;
        float max = -Float.MAX_VALUE;
        int len = points.size();

        for (int i = 0; i < len; i++ ) {
            // The magnitude of the projection of the point onto the normal
            float dot = points.get(i).dot(normal);
            if (dot < min) { min = dot; }
            if (dot > max) { max = dot; }

            //console.log("P"+i+" ("+points[i].x+", "+points[i].y+") - N ("+normal.x+", "+normal.y+") DOT "+dot+" = mín e máx: "+min+" ,"+max);
        }
        result[0] = min;
        result[1] = max;
    }

    // Check whether two convex polygons are separated by the specified
    // axis (must be a unit vector).
    /**
     * @param {Vector} aPos The position of the first polygon.
     * @param {Vector} bPos The position of the second polygon.
     * @param {Array.<Vector>} aPoints The points in the first polygon.
     * @param {Array.<Vector>} bPoints The points in the second polygon.
     * @param {Vector} axis The axis (unit sized) to test against.  The points of both polygons
     *   will be projected onto this axis.
     * @param {SatResponse=} response A SatResponse object (optional) which will be populated
     *   if the axis is not a separating axis.
     * @return {boolean} true if it is a separating axis, false otherwise.  If false,
     *   and a response is passed in, information about how much overlap and
     *   the direction of the overlap will be populated.
     */
    public boolean isSeparatingAxis(Vector aPos, Vector bPos, ArrayList<Vector> aPoints, ArrayList<Vector> bPoints, Vector axis, SatResponse response) {
        float[] rangeA = T_ARRAYS.pop();
        float[] rangeB = T_ARRAYS.pop();
        // The magnitude of the offset between the two polygons
        Vector offsetV = T_VECTORS.pop().copy(bPos).sub(aPos);
        float projectedOffset = offsetV.dot(axis);

        // Project the polygons onto the axis.
        flattenPointsOn(aPoints, axis, rangeA);
        flattenPointsOn(bPoints, axis, rangeB);
        // Move B's range to its position relative to A.
        rangeB[0] += projectedOffset;
        rangeB[1] += projectedOffset;

        // Check if there is a gap. If there is, this is a separating axis and we can stop
        if (rangeA[0] > rangeB[1] || rangeB[0] > rangeA[1]) {
            T_VECTORS.push(offsetV);
            T_ARRAYS.push(rangeA);
            T_ARRAYS.push(rangeB);
            return true;
        }
        // This is not a separating axis. If we're calculating a response, calculate the overlap.
        if (response != null) {
            float overlap = 0.0f;
            // A starts further left than B
            if (rangeA[0] < rangeB[0]) {
                //console.log("A starts further left than B");
                response.aInB = false;
                // A ends before B does. We have to pull A out of B
                if (rangeA[1] < rangeB[1]) {
                    //console.log("A ends before B does. We have to pull A out of B");
                    overlap = rangeA[1] - rangeB[0];
                    response.bInA = false;
                    // B is fully inside A.  Pick the shortest way out.
                } else {
                    //console.log("B is fully inside A.  Pick the shortest way out.");
                    float option1 = rangeA[1] - rangeB[0];
                    float option2 = rangeB[1] - rangeA[0];
                    if (option1 < option2) {
                        overlap = option1;
                    }
                    else {
                        overlap = -option2;
                    }
                }
                // B starts further left than A
            } else {
                //console.log("B starts further left than A");
                response.bInA = false;
                // B ends before A ends. We have to push A out of B
                if (rangeA[1] > rangeB[1]) {
                    //console.log("B ends before A ends. We have to push A out of B");
                    overlap = rangeA[0] - rangeB[1];
                    response.aInB = false;
                    // A is fully inside B.  Pick the shortest way out.
                } else {
                    //console.log("A is fully inside B.  Pick the shortest way out.");
                    float option1 = rangeA[1] - rangeB[0];
                    float option2 = rangeB[1] - rangeA[0];
                    if (option1 < option2) {
                        overlap = option1;
                    }
                    else {
                        overlap = -option2;
                    }
                }
            }
            // If this is the smallest amount of overlap we've seen so far, set it as the minimum overlap.

            float absOverlap = Math.abs(overlap);
            if (absOverlap < response.overlap) {
                response.overlap = absOverlap;
                response.overlapN.copy(axis);
                if (overlap < 0) {
                    response.overlapN.reverse();
                }
            }
        }
        T_VECTORS.push(offsetV);
        T_ARRAYS.push(rangeA);
        T_ARRAYS.push(rangeB);
        return false;
    }

    // Calculates which Voronoi region a point is on a line segment.
    // It is assumed that both the line and the point are relative to `(0,0)`
    //
    //            |       (0)      |
    //     (-1)  [S]--------------[E]  (1)
    //            |       (0)      |
    /**
     * @param {Vector} line The line segment.
     * @param {Vector} point The point.
     * @return  {number} LEFT_VORONOI_REGION (-1) if it is the left region,
     *          MIDDLE_VORONOI_REGION (0) if it is the middle region,
     *          RIGHT_VORONOI_REGION (1) if it is the right region.
     */
    public int voronoiRegion(Vector line, Vector point) {
        float len2 = line.len2();
        float dp = point.dot(line);
        // If the point is beyond the start of the line, it is in the
        // left voronoi region.
        if (dp < 0) { return LEFT_VORONOI_REGION; }
        // If the point is beyond the end of the line, it is in the
        // right voronoi region.
        else if (dp > len2) { return RIGHT_VORONOI_REGION; }
        // Otherwise, it's in the middle one.
        else { return MIDDLE_VORONOI_REGION; }
    }


    // ## Collision Tests
    // Check if a point is inside a circle.
    /**
     * @param {Vector} p The point to test.
     * @param {SatCircle} c The circle to test.
     * @return {boolean} true if the point is inside the circle, false if it is not.
     */
    public boolean pointInCircle(Vector p, SatCircle c) {
        Vector differenceV = T_VECTORS.pop().copy(p).sub(c.pos);
        float radiusSq = c.r * c.r;
        float distanceSq = differenceV.len2();
        T_VECTORS.push(differenceV);
        // If the distance between is smaller than the radius then the point is inside the circle.
        if (distanceSq <= radiusSq){
            return true;
        } else {
            return false;
        }
    }

    // Check if a point is inside a convex polygon.
    /**
     * @param {Vector} p The point to test.
     * @param {SatPolygon} poly The polygon to test.
     * @return {boolean} true if the point is inside the polygon, false if it is not.
     */
    public boolean pointInPolygon(Vector p, SatPolygon poly) {
        UNIT_SQUARE.pos.copy(p);
        T_RESPONSE.clear();
        boolean result = testPolygonPolygon(UNIT_SQUARE, poly, T_RESPONSE);
        if (result) {
            result = T_RESPONSE.aInB;
        }
        return result;
    }


    // Check if two circles collide.
    /**
     * @param {SatCircle} a The first circle.
     * @param {SatCircle} b The second circle.
     * @param {SatResponse=} response SatResponse object (optional) that will be populated if
     *   the circles intersect.
     * @return {boolean} true if the circles intersect, false if they don't.
     */
    public boolean testCircleCircle(SatCircle a, SatCircle b, SatResponse response) {
        // Check if the distance between the centers of the two
        // circles is greater than their combined radius.
        Vector differenceV = T_VECTORS.pop().copy(b.pos).sub(a.pos);
        float totalRadius = a.r + b.r;
        float totalRadiusSq = totalRadius * totalRadius;
        float distanceSq = differenceV.len2();
        // If the distance is bigger than the combined radius, they don't intersect.
        if (distanceSq > totalRadiusSq) {
            T_VECTORS.push(differenceV);
            return false;
        }
        // They intersect.  If we're calculating a response, calculate the overlap.
        if (response != null) {
            float dist = (float) Math.sqrt(distanceSq);
            response.ca = a;
            response.cb = b;
            response.overlap = totalRadius - dist;
            response.overlapN.copy(differenceV.normalize());
            response.overlapV.copy(differenceV).scale(response.overlap);

            boolean aInB = false;
            if ((a.r <= b.r) && (dist <= b.r - a.r)) aInB = true;
            response.aInB = aInB;

            boolean bInA = false;
            if ((b.r <= a.r) && (dist <= a.r - b.r)) aInB = true;
            response.bInA = bInA;

        }
        T_VECTORS.push(differenceV);
        return true;
    }

    // Check if a polygon and a circle collide.
    /**
     * @param {SatPolygon} polygon The polygon.
     * @param {SatCircle} circle The circle.
     * @param {SatResponse=} response SatResponse object (optional) that will be populated if
     *   they interset.
     * @return {boolean} true if they intersect, false if they don't.
     */
    public boolean testPolygonCircle(SatPolygon polygon, SatCircle circle, SatResponse response) {
        // Get the position of the circle relative to the polygon.
        Vector circlePos = T_VECTORS.pop().copy(circle.pos).sub(polygon.pos);
        //console.log("circlePos.x ", circlePos.x);
        //console.log("circlePos.y no sat ", circlePos.y);
        float radius = circle.r;

        //console.log("radius ", radius);
        float radius2 = radius * radius;
        ArrayList<Vector> points = polygon.calcPoints;

        //console.log("points ", points[1].x);
        int len = points.size();
        Vector edge = T_VECTORS.pop();
        Vector point = T_VECTORS.pop();

        // For each edge in the polygon:
        for (int i = 0; i < len; i++) {
            int next = i == len - 1 ? 0 : i + 1;
            int prev = i == 0 ? len - 1 : i - 1;
            float overlap = 0;
            Vector overlapN = null;

            // Get the edge.
            edge.copy(polygon.edges.get(i));
            // Calculate the center of the circle relative to the starting point of the edge.
            point.copy(circlePos).sub(points.get(i));

            // If the distance between the center of the circle and the point
            // is bigger than the radius, the polygon is definitely not fully in
            // the circle.
            if (response != null && point.len2() > radius2) {
                response.aInB = false;
            }

            // Calculate which Voronoi region the center of the circle is in.
            int region = voronoiRegion(edge, point);
            // If it's the left region:
            if (region == LEFT_VORONOI_REGION) {
                // We need to make sure we're in the RIGHT_VORONOI_REGION of the previous edge.
                edge.copy(polygon.edges.get(prev));
                // Calculate the center of the circle relative the starting point of the previous edge
                Vector point2 = T_VECTORS.pop().copy(circlePos).sub(points.get(prev));
                region = voronoiRegion(edge, point2);
                if (region == RIGHT_VORONOI_REGION) {
                    // It's in the region we want.  Check if the circle intersects the point.
                    float dist = point.len();
                    if (dist > radius) {
                        // No intersection
                        T_VECTORS.push(circlePos);
                        T_VECTORS.push(edge);
                        T_VECTORS.push(point);
                        T_VECTORS.push(point2);
                        return false;
                    } else if (response != null) {
                        // It intersects, calculate the overlap.
                        response.bInA = false;
                        overlapN = point.normalize();
                        overlap = radius - dist;
                    }
                }
                T_VECTORS.push(point2);
                // If it's the right region:
            } else if (region == RIGHT_VORONOI_REGION) {
                // We need to make sure we're in the left region on the next edge
                edge.copy(polygon.edges.get(next));
                // Calculate the center of the circle relative to the starting point of the next edge.
                point.copy(circlePos).sub(points.get(next));
                region = voronoiRegion(edge, point);
                if (region == LEFT_VORONOI_REGION) {
                    // It's in the region we want.  Check if the circle intersects the point.
                    float dist = point.len();
                    if (dist > radius) {
                        // No intersection
                        T_VECTORS.push(circlePos);
                        T_VECTORS.push(edge);
                        T_VECTORS.push(point);
                        return false;
                    } else if (response != null) {
                        // It intersects, calculate the overlap.
                        response.bInA = false;
                        overlapN = point.normalize();
                        overlap = radius - dist;
                    }
                }
                // Otherwise, it's the middle region:
            } else {
                // Need to check if the circle is intersecting the edge,
                // Change the edge into its "edge normal".
                Vector normal = edge.perp().normalize();
                // Find the perpendicular distance between the center of the
                // circle and the edge.
                float dist = point.dot(normal);
                float distAbs = Math.abs(dist);
                // If the circle is on the outside of the edge, there is no intersection.
                if (dist > 0 && distAbs > radius) {
                    // No intersection
                    T_VECTORS.push(circlePos);
                    T_VECTORS.push(normal);
                    T_VECTORS.push(point);
                    return false;
                } else if (response != null) {
                    // It intersects, calculate the overlap.
                    overlapN = normal;
                    overlap = radius - dist;
                    // If the center of the circle is on the outside of the edge, or part of the
                    // circle is on the outside, the circle is not fully inside the polygon.
                    if (dist >= 0 || overlap < 2 * radius) {
                        response.bInA = false;
                    }
                }
            }

            // If this is the smallest overlap we've seen, keep it.
            // (overlapN may be null if the circle was in the wrong Voronoi region).
            if (overlapN != null && response != null && Math.abs(overlap) < Math.abs(response.overlap)) {
                response.overlap = overlap;
                response.overlapN.copy(overlapN);
            }
        }

        // Calculate the final overlap vector - based on the smallest overlap.
        if (response != null) {
            response.pa = polygon;
            response.cb = circle;

            response.overlapV.copy(response.overlapN).scale(response.overlap);
        }
        T_VECTORS.push(circlePos);
        T_VECTORS.push(edge);
        T_VECTORS.push(point);
        return true;
    }


    // Check if a circle and a polygon collide.
    //
    // **NOTE:** This is slightly less efficient than polygonCircle as it just
    // runs polygonCircle and reverses everything at the end.
    /**
     * @param {SatCircle} circle The circle.
     * @param {SatPolygon} polygon The polygon.
     * @param {SatResponse=} response SatResponse object (optional) that will be populated if
     *   they interset.
     * @return {boolean} true if they intersect, false if they don't.
     */
    public boolean testCirclePolygon(SatCircle circle, SatPolygon polygon, SatResponse response) {
        // Test the polygon against the circle.

        //Log.e("teste CP", "circle y"+ circle.pos.y);
        //Log.e("teste CP", "circle r"+ circle.r);
        //Log.e("teste CP", "polygon y"+ polygon.pos.y);
        //Log.e("teste CP", "polygon p y0"+ polygon.points.get(0).y + " y1"+ polygon.points.get(1).y + " y1"+ polygon.points.get(2).y + " y1"+ polygon.points.get(3).y);



        boolean result = testPolygonCircle(polygon, circle, response);
        if (result == true && response != null) {
            // Swap A and B in the response.
            SatPolygon a = response.pa;
            boolean aInB = response.aInB;
            response.overlapN.reverse();
            response.overlapV.reverse();
            response.ca = response.cb;
            response.cb = null;
            response.pb = a;
            response.pa = null;
            response.aInB = response.bInA;
            response.bInA = aInB;
        }
        return result;
    }

    // Checks whether polygons collide.
    /**
     * @param {SatPolygon} a The first polygon.
     * @param {SatPolygon} b The second polygon.
     * @param {SatResponse=} response SatResponse object (optional) that will be populated if
     *   they interset.
     * @return {boolean} true if they intersect, false if they don't.
     */
    public boolean testPolygonPolygon(SatPolygon a, SatPolygon b, SatResponse response){
        //Log.e("tag size", " "+a.calcPoints.size());
        ArrayList<Vector> aPoints = a.calcPoints;
        int aLen = aPoints.size();
        //Log.e("tag size b", " "+b.calcPoints.size());
        ArrayList<Vector> bPoints = b.calcPoints;
        int bLen = aPoints.size();

        // If any of the edge normals of A is a separating axis, no intersection.
        for (int i = 0; i < aLen; i++) {
            //console.log(isSeparatingAxis(a.pos, b.pos, aPoints, bPoints, a.normals[i], response));
            if (isSeparatingAxis(a.pos, b.pos, aPoints, bPoints, a.normals.get(i), response)) {
                return false;
            }
        }
        // If any of the edge normals of B is a separating axis, no intersection.
        for (int i = 0;i < bLen; i++) {
            if (isSeparatingAxis(a.pos, b.pos, aPoints, bPoints, b.normals.get(i), response)) {
                return false;
            }
        }
        // Since none of the edge normals of A or B are a separating axis, there is an intersection
        // and we've already calculated the smallest overlap (in isSeparatingAxis).  Calculate the
        // final overlap vector.
        if (response != null) {
            response.pa = a;
            response.pb = b;
            response.overlapV.copy(response.overlapN).scale(response.overlap);
        }
        return true;
    }
}