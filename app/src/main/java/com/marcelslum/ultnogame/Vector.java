package com.marcelslum.ultnogame;

/**
 * Created by marcel on 01/08/2016.
 */
//
// ## Vector
//
// Represents a vector in two dimensions with `x` and `y` properties.

public class Vector{

    public float x;
    public float y;

    Vector(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector copy(Vector other) {
        this.x = other.x;
        this.y = other.y;
        return this;
    }

    public Vector clone(){
        return new Vector(this.x, this.y);
    }

    // Change this vector to be perpendicular to what it was before. (Effectively
    // roatates it 90 degrees in a clockwise direction)
    public Vector perp(){
        float x = this.x;
        this.x = this.y;
        this.y = -x;
        return this;
    }

    // Rotate this vector (counter-clockwise) by the specified angle (in radians).
    public Vector rotate(float angle){
        float x = this.x;
        float y = this.y;
        this.x = x * (float)Math.cos((double)angle) - y * (float)Math.sin((double)angle);
        this.y = x * (float)Math.sin((double)angle) + y * (float)Math.cos((double)angle);
        return this;
    }

    // Reverse this vector.
    public Vector reverse(){
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    // Normalize this vector.  (make it have length of `1`)
    public Vector normalize(){
        float d = this.len();
        if (d > 0){
            this.x = this.x / d;
            this.y = this.y / d;
        }
        return this;
    }

    // Add another vector to this one.
    public Vector add(Vector other){
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    // Subtract another vector from this one.
    public Vector sub(Vector other){
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    // Scale this vector. An independant scaling factor can be provided
    // for each axis, or a single scaling factor that will scale both `x` and `y`.
    public Vector scale(float x){
        this.x *= x;
        this.y *= x;
        return this;
    }

    public Vector scale(float x, float y){
        this.x *= x;
        this.y *= y;
        return this;
    }

    // Project this vector on to another vector.
    public Vector project(Vector other){
        float amt = this.dot(other) / other.len2();
        this.x = amt * other.x;
        this.y = amt * other.y;
        return this;
    }

    // Project this vector onto a vector of unit length. This is slightly more efficient
    // than `project` when dealing with unit vectors.
    public Vector projectN(Vector other){
        float amt = this.dot(other);
        this.x = amt * other.x;
        this.y = amt * other.y;
        return this;
    }


    // Reflect this vector on an arbitrary axis.
    public Vector reflect(Vector axis){
        float x = this.x;
        float y = this.y;
        this.project(axis).scale(2);
        this.x -= x;
        this.y -= y;
        return this;
    }

    // Reflect this vector on an arbitrary axis (represented by a unit vector). This is
    // slightly more efficient than `reflect` when dealing with an axis that is a unit vector.
    public Vector reflectN(Vector axis){
        float x = this.x;
        float y = this.y;
        this.projectN(axis).scale(2);
        this.x -= x;
        this.y -= y;
        return this;
    }

    // Get the dot product of this vector and another.
    public float dot(Vector other){
        return this.x * other.x + this.y * other.y;
    }

    // Get the squared length of this vector.
    public float len2(){
        return this.dot(this);
    }

    // Get the length of this vector.
    public float len(){
        return (float)Math.sqrt((double)this.len2());
    }
}