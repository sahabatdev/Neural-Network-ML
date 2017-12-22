package com.sahabatdeveloper.neuralnetwork;

/**
 * Created by Sahabat Developer on 21/12/2017.
 */

public class Model {
    int x1;
    int x2;
    int y;

    public Model(int x1, int x2, int y) {
        this.x1 = x1;
        this.x2 = x2;
        this.y = y;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY() {
        return y;
    }
}
