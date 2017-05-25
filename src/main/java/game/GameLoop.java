package game;

import game.base.BodiesAttacher;
import game.base.Game;
import game.base.Vector;
import game.models.Body;
import game.models.SimpleCube;
import game.models.Stick;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static game.base.Constants.CUBE_SIZE;
import static game.base.Constants.DOWN_SPEED;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.opengl.GL11.*;

public class GameLoop implements Game.Loop {

    private FloatBuffer matSpecular;
    private FloatBuffer lightPosition;
    private FloatBuffer whiteLight;
    private FloatBuffer lModelAmbient;

    List<SimpleCube> bodies = new ArrayList<>();
    private List<BodiesAttacher> attachers = new ArrayList<>();

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
        float[] initialPos = {500, 500, 0};
        float[] initialSpd = {0, DOWN_SPEED, 0};
        Stick stick = new Stick(initialPos, initialSpd);
        attachers.add(stick);

        bodies.addAll(stick.getCubes());
        for (int i = 0; i < (800 / CUBE_SIZE); i++) {

        }
    }

    @Override
    public void processInput(long window, int key, int scancode, int action, int mods) {
        if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
            pause = !pause;
        else if (action == GLFW_RELEASE)
            attachers.get(0).rotate();
//            glfwSetWindowShouldClose(window, true);
    }

    int count = 0;

    @Override
    public void render() {
        count++;
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glPopMatrix();
        glPushMatrix();
        glRotated(-10, 1, 1, 0);
        glColor3d(1, 1, 0);
        for (SimpleCube body : bodies)
            body.render();
    }

    @Override
    public void afterRender() {

    }

    boolean pause = false;

    @Override
    public void step() {
        if (!pause) {
            for (BodiesAttacher attacher : attachers) {
                attacher.step();
            }
            for (SimpleCube body : bodies) {
                body.step();
            }
        }
    }

    public FloatBuffer floatBuffer(float a, float b, float c, float d) {
        float[] data = new float[]{a, b, c, d};
        FloatBuffer fb = BufferUtils.createFloatBuffer(data.length);
        fb.put(data);
        fb.flip();
        return fb;
    }


}
