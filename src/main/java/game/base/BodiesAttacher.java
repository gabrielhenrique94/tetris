package game.base;

import game.models.Body;
import game.models.SimpleCube;

import java.util.ArrayList;
import java.util.List;

import static game.base.Vector.add;

/**
 * Created by Gabriel on 24/05/2017.
 */
public class BodiesAttacher {
    protected SimpleCube[] cubes;
    public float[] speed;
    public float[] position;

    public List<SimpleCube> getCubes() {
        ArrayList<SimpleCube> list = new ArrayList<SimpleCube>();
        for (SimpleCube cube : cubes)
            list.add(cube);
        return list;
    }

    public void step() {
        position = add(position, speed);
    }


    public void rotate() {
    }

    public void moveRight () {
    }

    public void moveLeft() {
    }
}
