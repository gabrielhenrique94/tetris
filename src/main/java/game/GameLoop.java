
package game;

import game.base.BodiesAttacher;
import game.base.Game;
import game.base.Window;
import game.models.SimpleCube;
import game.models.Tetromino;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.base.Constants.DOWN_SPEED;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GameLoop implements Game.Loop {

    List<SimpleCube> bodies = new ArrayList<>();
    private BodiesAttacher attachers;
    private float[] initialPos = {400, 450, 0};
    private float[] initialSpd = {0, DOWN_SPEED, 0};
    long currentTime = System.currentTimeMillis();
    int[][] landed = new int[16][10];

    @Override
    public void prepare() {
        System.out.println("PREPARE");
        glLoadIdentity();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, 800, 0, 800, 10000, -1000);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glLightModeli(GL_LIGHT_MODEL_TWO_SIDE, GL_TRUE);
        glEnable(GL_COLOR_MATERIAL);
        glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);
        glLightfv(GL_LIGHT0, GL_POSITION, floatBuffer(20, 0, -20, 1));
        initLanded();
        Tetromino tetromino = new Tetromino(initialPos, initialSpd, 1);
        attachers = tetromino;
    }

    @Override
    public void processInput(long window, int key, int scancode, int action, int mods) {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
            pause = !pause;
        } else if ((key == GLFW_KEY_RIGHT && action == GLFW_RELEASE)) {
            System.out.println("press right");
            if (!attachers.checkCollision(bodies)) {
                attachers.moveRight();
            }
        } else if ((key == GLFW_KEY_LEFT && action == GLFW_RELEASE)) {
            attachers.moveLeft();
        } else if (key == GLFW_KEY_SPACE && action == GLFW_RELEASE)
            attachers.rotate();
    }

    int count = 0;

    @Override
    public void render() {
        count++;
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glPopMatrix();
        glPushMatrix();

        glRotated(-45, 1, 1, 0);
        glColor3d(1, 1, 0);

        Window grid = new Window();
        for (SimpleCube body : bodies)
            body.render();
        for (SimpleCube body : attachers.getCubes())
            body.render();

    }

    @Override
    public void afterRender() {

    }

    boolean pause = false;

    @Override
    public void step() {
        if (!pause) {
            currentTime = System.currentTimeMillis();

            if (attachers.checkCollision(bodies)) {
                attachers.stop();
                bodies.addAll(attachers.getCubes());
                createNewAttacher();
            } else {
                attachers.step();
            }
            busyWait(currentTime + 300);
        }
    }

    void initLanded() {
        for (int row = 0; row < landed.length; row++) {
            for (int col = 0; col < landed[row].length; col++) {
                landed[row][col] = 0;
            }
        }
    }

    private void createNewAttacher() {
        Random generator = new Random();
        switch (generator.nextInt(7)) {
            case 0:
                attachers = new Tetromino(initialPos, initialSpd, 1);
                break;
            case 1:
                attachers = new Tetromino(initialPos, initialSpd, 2);
                break;
            case 2:
                attachers = new Tetromino(initialPos, initialSpd, 3);
                break;
            case 3:
                attachers = new Tetromino(initialPos, initialSpd, 4);
                break;
            case 4:
                attachers = new Tetromino(initialPos, initialSpd, 5);
                break;
            case 5:
                attachers = new Tetromino(initialPos, initialSpd, 6);
                break;
            case 6:
                attachers = new Tetromino(initialPos, initialSpd, 1);
                break;
        }
    }

    public FloatBuffer floatBuffer(float a, float b, float c, float d) {
        float[] data = new float[]{a, b, c, d};
        FloatBuffer fb = BufferUtils.createFloatBuffer(data.length);
        fb.put(data);
        fb.flip();
        return fb;
    }

    public static void busyWait(long time) {
        while (System.currentTimeMillis() < time) Thread.yield();
    }
}