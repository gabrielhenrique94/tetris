package game.base;

/**
 * Created by Gabriel on 23/05/2017.
 */
public class Vector {
    public static float[] add(float[] a, float[] b) {
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = a[i] + b[i];
        }
        return res;
    }

    public static float[] sub(float[] a, float[] b) {
        float[] res = new float[a.length];
        for (int i = 0; i < a.length; i++) {
            res[i] = a[i] - b[i];
        }
        return res;
    }

    public static float[] multiply(float v[], float scalar) {
        float[] res = new float[v.length];
        for (int i = 0; i < v.length; i++) {
            res[i] = v[i] * scalar;
        }
        return res;
    }
}
