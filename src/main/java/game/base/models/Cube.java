package game.base.models;


import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Gabriel on 21/05/2017.
 */
public class Cube implements BaseModel {

    float[] origin;

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    float size;

    @Override
    public void render() {
        glBegin(GL_QUADS);

        glVertex3f(origin[0], origin[1], origin[2]);
        glVertex3f(origin[0], origin[1] + size, origin[2]);
        glVertex3f(origin[0] + size, origin[1] + size, origin[2]);
        glVertex3f(origin[0] + size, origin[1], origin[2]);


        glVertex3f(origin[0], origin[1], origin[2]);
        glVertex3f(origin[0] + size, origin[1], origin[2]);
        glVertex3f(origin[0] + size, origin[1], origin[2] + size);
        glVertex3f(origin[0], origin[1], origin[2] + size);

        glVertex3f(origin[0], origin[1], origin[2]);
        glVertex3f(origin[0], origin[1] + size, origin[2]);
        glVertex3f(origin[0], origin[1] + size, origin[2] + size);
        glVertex3f(origin[0], origin[1], origin[2] + size);

        glVertex3f(origin[0], origin[1] + size, origin[2]);
        glVertex3f(origin[0], origin[1] + size, origin[2] + size);
        glVertex3f(origin[0] + size, origin[1] + size, origin[2] + size);
        glVertex3f(origin[0] + size, origin[1] + size, origin[2]);


        glVertex3f(origin[0] + size, origin[1], origin[2]);
        glVertex3f(origin[0] + size, origin[1] + size, origin[2]);
        glVertex3f(origin[0] + size, origin[1] + size, origin[2] + size);
        glVertex3f(origin[0] + size, origin[1], origin[2] + size);


        glVertex3f(origin[0], origin[1], origin[2] + size);
        glVertex3f(origin[0], origin[1] + size, origin[2] + size);
        glVertex3f(origin[0] + size, origin[1] + size, origin[2] + size);
        glVertex3f(origin[0] + size, origin[1], origin[2] + size);


        glEnd();

    }

    public float[] getOrigin() {
        return origin;
    }

    public void setOrigin(float[] origin) {
        this.origin = origin;
    }

    public static void renderCube(float[] origin, float size) {
        Cube cube = new Cube();
        cube.setSize(size);
        cube.setOrigin(origin);
        cube.render();
    }
}
