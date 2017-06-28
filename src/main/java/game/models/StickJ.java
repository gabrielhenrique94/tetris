package game.models;

import game.base.BodiesAttacher;
import game.base.Vector;
import game.base.models.Cube;

import static game.base.Constants.*;
import static game.base.Vector.add;
import static game.base.Vector.sub;

/**
 * Created by Andre on 25/06/2017.
 */
public class StickJ extends BodiesAttacher {
    public StickJ(float[] position, float[] speed) {
        super();
        this.position = position;
        this.speed = speed;
        this.cubes = new SimpleCube[4];
        for (int i = 0; i < cubes.length; i++) {
            cubes[i] = new SimpleCube(position, speed);
        }
    }

    private int rotacao = 0;
    //incompleto
    float delta[][][] = {   {{0,0,0},{-CUBE_SIZE,0,0},{-2*CUBE_SIZE,0,0},{0,-CUBE_SIZE,0}},
            {{0,0,0},{-CUBE_SIZE,0,0},{0,CUBE_SIZE,0},{0,2*CUBE_SIZE,0}},
            {{0,0,0},{0,CUBE_SIZE,0},{CUBE_SIZE,0,0},{2*CUBE_SIZE,0,0}},
            {{0,0,0},{0,-2*CUBE_SIZE,0},{0,-CUBE_SIZE,0},{CUBE_SIZE,0,0}}
    };
    @Override
    public void step() {
        super.step();

        float[][] delta1 = delta[rotacao];

        cubes[0].position = add(position, delta1[0]);
        cubes[1].position = add(position, delta1[1]);
        cubes[2].position = add(position, delta1[2]);
        cubes[3].position = add(position, delta1[3]);
    }

    @Override
    public void rotate() {

        rotacao = (rotacao + 1)%4;
    }

    public void moveRight() {

        if((cubes[0].position[0] < SIDE_RIGHT_POSITION)&& (cubes[1].position[0] < SIDE_RIGHT_POSITION)&&(cubes[2].position[0] < SIDE_RIGHT_POSITION)&&(cubes[3].position[0] < SIDE_RIGHT_POSITION)) {
            super.moveRight();
        }
    }

    public void moveLeft() {
        super.moveLeft();
    }


}

