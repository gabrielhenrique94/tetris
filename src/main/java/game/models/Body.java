package game.models;

import game.base.Vector;

import java.util.ArrayList;
import java.util.List;

import static game.base.Vector.add;

/**
 * Created by Gabriel on 23/05/2017.
 */
public abstract class Body {

    protected float[] speed = new float[3];
<<<<<<< HEAD
    protected float[] position = new float[3];
    protected int color = 0;
=======
    public float[] position = new float[3];
>>>>>>> origin/master

    public void applySpeed(float[] speed) {
        this.speed = add(this.speed, speed);
    }

    public void stop() {
        speed = new float[0];
    }

    public abstract void render();

    public void step() {
        position = add(position, speed);
    }

    public float[] getPosition() {
        return position;
    }
}
