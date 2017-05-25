package game.models;

import game.base.BodiesAttacher;
import game.base.Vector;
import game.base.models.Cube;

import static game.base.Constants.CUBE_SIZE;

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
        Cube.renderCube(position, CUBE_SIZE);
    }


}
