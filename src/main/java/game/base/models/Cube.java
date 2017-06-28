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

    int colors[] = new int[3];

    @Override
    public void render() {
        glBegin(GL_QUADS);

        glColor3f(colors[0],colors[1],colors[2]);

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


    public void setColor(int color) {
        switch (color) {
            case 1: //red
                colors[0] = 1;
                colors[1] = 0;
                colors[2] = 0;
                break;
            case 2: //green
                colors[0] = 0;
                colors[1] = 1;
                colors[2] = 0;
                break;
            case 3: //blue
                colors[0] = 0;
                colors[1] = 0;
                colors[2] = 1;
                break;
            case 4: //yellow
                colors[0] = 1;
                colors[1] = 1;
                colors[2] = 0;
                break;
            case 5: //cyan
                colors[0] = 0;
                colors[1] = 1;
                colors[2] = 1;
                break;
            case 6: //magenta
                colors[0] = 1;
                colors[1] = 0;
                colors[2] = 1;
                break;
            default: //white
                colors[0] = 1;
                colors[1] = 1;
                colors[2] = 1;
        }
    }

    public static void renderCube(float[] origin, float size, int color) {
        Cube cube = new Cube();
        cube.setSize(size);
        cube.setOrigin(origin);
        cube.setColor(color);
        System.out.println(color);
        cube.render();
    }
}
