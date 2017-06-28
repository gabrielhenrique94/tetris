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
public class Tetromino extends BodiesAttacher {

    public Tetromino (float[] position, float[] speed, int tipo ) {
        super();
        this.position = position;
        this.speed = speed;
        this.cubes = new SimpleCube[4];
        for (int i = 0; i < cubes.length; i++) {
            cubes[i] = new SimpleCube(position, speed);
            cubes[i].color = tipo;
        }
        this.tipo = tipo;
    }
    int tipo; //1=I 2=J 3=L 4=O 5=S 6=T 7=Z

    int rotacao = 0;
    float delta[][][] = {{{0,0,0},{-CUBE_SIZE,0,0},{0,CUBE_SIZE,0},{CUBE_SIZE,0,0}},
            {{0,0,0},{0,-CUBE_SIZE,0},{CUBE_SIZE,0,0},{0,CUBE_SIZE,0}},
            {{0,0,0},{CUBE_SIZE,0,0},{0,-CUBE_SIZE,0},{-CUBE_SIZE,0,0}},
            {{0,0,0},{0,CUBE_SIZE,0},{-CUBE_SIZE,0,0},{0,-CUBE_SIZE,0}}};

    float deltaI[][][] = {  {{0,0,0},{0,CUBE_SIZE,0},{0,-CUBE_SIZE,0},{0,-2*CUBE_SIZE,0}},
                            {{0,0,0},{-CUBE_SIZE,0,0},{CUBE_SIZE,0,0},{2*CUBE_SIZE,0,0}},
                            {{0,0,0},{0,CUBE_SIZE,0},{0,-CUBE_SIZE,0},{0,-2*CUBE_SIZE,0}},
                            {{0,0,0},{-CUBE_SIZE,0,0},{CUBE_SIZE,0,0},{2*CUBE_SIZE,0,0}},
    };
    float deltaJ[][][] = {  {{0,0,0},{-CUBE_SIZE,0,0},{-2*CUBE_SIZE,0,0},{0,-CUBE_SIZE,0}},
                            {{0,0,0},{-CUBE_SIZE,0,0},{0,CUBE_SIZE,0},{0,2*CUBE_SIZE,0}},
                            {{0,0,0},{0,CUBE_SIZE,0},{CUBE_SIZE,0,0},{2*CUBE_SIZE,0,0}},
                            {{0,0,0},{0,-2*CUBE_SIZE,0},{0,-CUBE_SIZE,0},{CUBE_SIZE,0,0}}
    };

    float deltaL[][][] = {  {{0,0,0},{CUBE_SIZE,0,0},{2*CUBE_SIZE,0,0},{0,-CUBE_SIZE,0}},
                            {{0,0,0},{-CUBE_SIZE,0,0},{0,-CUBE_SIZE,0},{0,-2*CUBE_SIZE,0}},
                            {{0,0,0},{0,CUBE_SIZE,0},{-CUBE_SIZE,0,0},{-2*CUBE_SIZE,0,0}},
                            {{0,0,0},{0,2*CUBE_SIZE,0},{0,CUBE_SIZE,0},{CUBE_SIZE,0,0}}
    };
    float deltaO[][][] = {  {{0,0,0},{CUBE_SIZE,0,0},{0,CUBE_SIZE,0},{CUBE_SIZE,CUBE_SIZE,0}},
                            {{0,0,0},{CUBE_SIZE,0,0},{0,CUBE_SIZE,0},{CUBE_SIZE,CUBE_SIZE,0}},
                            {{0,0,0},{CUBE_SIZE,0,0},{0,CUBE_SIZE,0},{CUBE_SIZE,CUBE_SIZE,0}},
                            {{0,0,0},{CUBE_SIZE,0,0},{0,CUBE_SIZE,0},{CUBE_SIZE,CUBE_SIZE,0}}
    };
    float deltaS[][][] = {  {{0,0,0},{-CUBE_SIZE,-CUBE_SIZE,0},{0,-CUBE_SIZE,0},{CUBE_SIZE,0,0}},
                            {{0,0,0},{0,CUBE_SIZE,0},{CUBE_SIZE,0,0},{CUBE_SIZE,-CUBE_SIZE,0}},
                            {{0,0,0},{-CUBE_SIZE,-CUBE_SIZE,0},{0,-CUBE_SIZE,0},{CUBE_SIZE,0,0}},
                            {{0,0,0},{0,CUBE_SIZE,0},{CUBE_SIZE,0,0},{CUBE_SIZE,-CUBE_SIZE,0}}
    };
    float deltaT[][][] = {  {{0,0,0},{-CUBE_SIZE,0,0},{0,CUBE_SIZE,0},{CUBE_SIZE,0,0}},
                            {{0,0,0},{0,-CUBE_SIZE,0},{CUBE_SIZE,0,0},{0,CUBE_SIZE,0}},
                            {{0,0,0},{CUBE_SIZE,0,0},{0,-CUBE_SIZE,0},{-CUBE_SIZE,0,0}},
                            {{0,0,0},{0,CUBE_SIZE,0},{-CUBE_SIZE,0,0},{0,-CUBE_SIZE,0}}
    };
    float deltaZ[][][] = {  {{0,0,0},{-CUBE_SIZE,0,0},{0,-CUBE_SIZE,0},{CUBE_SIZE,-CUBE_SIZE,0}},
                            {{0,0,0},{0,CUBE_SIZE,0},{-CUBE_SIZE,0,0},{-CUBE_SIZE,-CUBE_SIZE,0}},
                            {{0,0,0},{-CUBE_SIZE,0,0},{0,-CUBE_SIZE,0},{CUBE_SIZE,-CUBE_SIZE,0}},
                            {{0,0,0},{0,CUBE_SIZE,0},{-CUBE_SIZE,0,0},{-CUBE_SIZE,-CUBE_SIZE,0}}
    };

    @Override
    public void step() {
        super.step();
        float[][] delta1 = delta[rotacao]; //inicializacao padrao
        switch (this.tipo) {
            //1=I 2=J 3=L 4=O 5=S 6=T 7=Z
            case 1:
                delta1 = deltaI[rotacao];
                break;
            case 2:
                delta1 = deltaJ[rotacao];
                break;
            case 3:
                delta1 = deltaL[rotacao];
                break;
            case 4:
                delta1 = deltaO[rotacao];
                break;
            case 5:
                delta1 = deltaS[rotacao];
                break;
            case 6:
                delta1 = deltaT[rotacao];
                break;
            case 7:
                delta1 = deltaZ[rotacao];
                break;
        }


        cubes[0].position = add(position, delta1[0]);
        cubes[1].position = add(position, delta1[1]);
        cubes[2].position = add(position, delta1[2]);
        cubes[3].position = add(position, delta1[3]);


        if(!checkCollisionLeft()) {
            position[0] = position[0] + CUBE_SIZE;
            cubes[0].position = add(position, delta1[0]);
            cubes[1].position = add(position, delta1[1]);
            cubes[2].position = add(position, delta1[2]);
            cubes[3].position = add(position, delta1[3]);
        }
        if(!checkCollisionRight()) {
            position[0] = position[0] - CUBE_SIZE;
            cubes[0].position = add(position, delta1[0]);
            cubes[1].position = add(position, delta1[1]);
            cubes[2].position = add(position, delta1[2]);
            cubes[3].position = add(position, delta1[3]);
        }

    }

    @Override
    public void rotate() {
        rotacao = (rotacao + 1)%4;
    }

    public void moveRight() {
        if(checkSideCollisionRight()) {
            super.moveRight();
        }
    }

    public void moveLeft() {
        if(checkSideCollisionLeft()) {
            super.moveLeft();
        }
    }

    public boolean checkSideCollisionRight() {
        return (((cubes[0].position[0]+CUBE_SIZE) < SIDE_RIGHT_POSITION)&&
                ((cubes[1].position[0]+CUBE_SIZE) < SIDE_RIGHT_POSITION)&&
                ((cubes[2].position[0]+CUBE_SIZE) < SIDE_RIGHT_POSITION)&&
                ((cubes[3].position[0]+CUBE_SIZE) < SIDE_RIGHT_POSITION));
    }

    public boolean checkSideCollisionLeft() {
        return (((cubes[0].position[0] - CUBE_SIZE) >= SIDE_LEFT_POSITION) &&
                ((cubes[1].position[0] - CUBE_SIZE) >= SIDE_LEFT_POSITION) &&
                ((cubes[2].position[0] - CUBE_SIZE) >= SIDE_LEFT_POSITION) &&
                ((cubes[3].position[0] - CUBE_SIZE) >= SIDE_LEFT_POSITION));
    }

    public boolean checkCollisionRight() {
        return (((cubes[0].position[0]) < SIDE_RIGHT_POSITION)&&
                ((cubes[1].position[0]) < SIDE_RIGHT_POSITION)&&
                ((cubes[2].position[0]) < SIDE_RIGHT_POSITION)&&
                ((cubes[3].position[0]) < SIDE_RIGHT_POSITION));
    }

    public boolean checkCollisionLeft() {
        return (((cubes[0].position[0] ) >= SIDE_LEFT_POSITION) &&
                ((cubes[1].position[0] ) >= SIDE_LEFT_POSITION) &&
                ((cubes[2].position[0] ) >= SIDE_LEFT_POSITION) &&
                ((cubes[3].position[0] ) >= SIDE_LEFT_POSITION));
    }


}

