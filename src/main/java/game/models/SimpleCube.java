package game.models;

import game.base.models.Cube;

import static game.base.Constants.CUBE_SIZE;

public class SimpleCube extends Body {

    public SimpleCube(float[] initialPos, float[] initialSpeed) {
        position = initialPos;
        applySpeed(initialSpeed);
    }

    @Override
    public void render() {
        Cube.renderCube(position, CUBE_SIZE);
    }

    public boolean willCollide(SimpleCube cube) {
        return ((Math.abs(cube.position[1] - position[1])) <= CUBE_SIZE) && ((Math.abs(cube.position[0] - position[0])) < CUBE_SIZE);
    }

}
