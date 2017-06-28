package game.models;

import game.base.BodiesAttacher;
import game.base.Vector;
import game.base.models.Cube;

import static game.base.Constants.CUBE_SIZE;
import static game.base.Constants.DOWN_SPEED;

/**
 * Created by Gabriel on 23/05/2017.
 */
public class SimpleCube extends Body {
    public SimpleCube(float[] initialPos, float[] initialSpeed) {
        position = initialPos;
        applySpeed(initialSpeed);
    }

    @Override
    public void render() {
        Cube.renderCube(position, CUBE_SIZE, color);
    }

    public boolean willCollide(SimpleCube cube) {
        return ((Math.abs(cube.position[1] - position[1]) + DOWN_SPEED) < CUBE_SIZE)&& ((Math.abs(cube.position[0] - position[0])) < CUBE_SIZE);
    }

}
