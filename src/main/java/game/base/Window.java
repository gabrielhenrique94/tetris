package game.base;

import game.base.models.Cube;

import static org.lwjgl.opengl.GL11.*;
import static game.base.Constants.*;

/**
 * Created by Gabriel on 21/05/2017.
 */
public class Window {

    public Window () {
        //drawBackground();
        drawGrid(650);
    }
    void drawGrid(float HALF_GRID_SIZE)
    {
        for(int x = (int)SIDE_LEFT_POSITION; x <= (int)SIDE_RIGHT_POSITION; x=x+25  ){

            glBegin(GL_LINES);
            glColor3d(1, 1, 1);
            glVertex3f(x,550,0);
            glVertex3f(x,25,0);
            glEnd();
        };

        for(int y = 550; y >=25; y=y-25){
            glBegin(GL_LINES);
            glVertex3f(300, y,0);
            glVertex3f(550,y,0);
            glEnd();
        };

        glBegin(GL_LINES);
        glVertex3f(300, 25,CUBE_SIZE);
        glVertex3f(550,25,CUBE_SIZE);
        glEnd();

        for(int z = (int)SIDE_LEFT_POSITION; z <= (int)SIDE_RIGHT_POSITION; z=z+25  ){
            glBegin(GL_LINES);
            glVertex3f(z,25, 0);
            glVertex3f(z,25,CUBE_SIZE);
            glEnd();
        }
        glColor3d(1, 1, 0);
    }

    public void drawBackground() {

        glRotated(-20f, -4f, 4.5f, 0);
        glBegin(GL_QUADS);
        glColor3d(1, 1, 1);
        glVertex3f(0, 0, 10*CUBE_SIZE);
        glColor3d(0, 0, 0);
        glVertex3f(0, 800, 10*CUBE_SIZE);
        glColor3d(1, 1, 1);
        glVertex3f(800, 800, 10*CUBE_SIZE);
        glColor3d(0, 0, 0);
        glVertex3f(800, 0, 10*CUBE_SIZE);
        glEnd();

        glColor3d(1, 1, 0);
        glRotated(-45, 1, 1, 0);
    }
}
