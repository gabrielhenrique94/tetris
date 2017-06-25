package game.models;

import game.base.BodiesAttacher;
import game.base.Vector;
import game.base.models.Cube;

import static game.base.Constants.CUBE_SIZE;
import static game.base.Vector.add;
import static game.base.Vector.sub;

/**
 * Created by Andre on 25/06/2017.
 */
public class StickT extends BodiesAttacher {
    public StickT(float[] position, float[] speed) {
        super();
        this.position = position;
        this.speed = speed;
        this.cubes = new SimpleCube[4];
        for (int i = 0; i < cubes.length; i++) {
            cubes[i] = new SimpleCube(position, speed);
        }
    }

    enum Position {
        vertical,
        horizontal
    }

    Position state = Position.vertical;


    @Override
    public void step() {
        super.step();
        float[] delta = {state == Position.horizontal ? CUBE_SIZE : 0,
                state == Position.vertical ? CUBE_SIZE : 0, 0};
        float[] relativePosition = position;

        cubes[0].position = relativePosition;
        relativePosition = sub(relativePosition, delta);
        cubes[1].position = relativePosition;
        relativePosition = position;
        relativePosition = add(relativePosition, delta);
        cubes[2].position = relativePosition;
        relativePosition = add(relativePosition, delta);
        cubes[3].position = relativePosition;
    }

    @Override
    public void rotate() {
        if (state == Position.vertical)
            state = Position.horizontal;
        else
            state = Position.vertical;
    }

    public void moveRight() {

    }
}

