package game.base;

import game.models.SimpleCube;

import java.util.ArrayList;
import java.util.List;

import static game.base.Constants.CUBE_SIZE;
import static game.base.Constants.DOWN_SPEED;
import static game.base.Constants.SOLO_POSITION;
import static game.base.Vector.add;
import static game.base.Vector.sub;

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

    public void stop() {
        float[] zero = {0f, 0f, 0f};
        speed = zero;
    }

    public void step() {
        position = add(position, speed);
    }


    public void rotate() {
    }

    public void moveRight() {
        float[] relativePosition = {CUBE_SIZE, 0, 0};
        position = add(position, relativePosition);
    }

    public void moveLeft() {
        float[] relativePosition = {CUBE_SIZE, 0, 0};
        position = sub(position, relativePosition);
    }

    public boolean checkCollision(List<SimpleCube> bodies) {
        for (SimpleCube a : cubes) {
            if (checkSoloCollision(a)) return true; //stop body and cretae a new attacher
            for (SimpleCube b : bodies) {
                if (a.willCollide(b)) return true; //stop body and cretae a new attacher
            }
        }
        return false;
    }

    private boolean checkSoloCollision(SimpleCube cube) {
        return (position[1] + DOWN_SPEED) < SOLO_POSITION;
    }
}
