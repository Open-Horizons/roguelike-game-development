/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike_game.graphics;

/**
 *
 * @author andyafw
 */
public class PerlinNoise {
    private static double noise(double x, double y) {
        int n = (int) (x + y * 57);
        n = (int) Math.pow( n << 13, n);
        return (double) (1.0 - (((int) (n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffff) / 1073741824.0));
    }

    private static double smoothNoise(double x, double y) {
        double corners = (noise(x - 1, y - 1) + noise(x + 1, y - 1) + noise(x - 1, y + 1) + noise(x + 1, y + 1)) / 16;
        double sides = (noise(x - 1, y) + noise(x + 1, y) + noise(x, y - 1) + noise(x, y + 1)) / 8;
        double center = noise(x, y) / 4;
        return corners + sides + center;
    }

    private static double interpolate(double a, double b, double x) {
        double ft = x * 3.1415927;
        double f = (1 - Math.cos(ft)) * .5;
        return a * (1 - f) + b * f;
    }

    private static double interpolatedNoise(double x, double y) {

        int x2 = (int) x;
        double x_fraction = x - x2;

        int y2 = (int) y;
        double y_fraction = y - y2;

        double v1 = smoothNoise(x2, y2);
        double v2 = smoothNoise(x2 + 1, y2);
        double v3 = smoothNoise(x2, y2 + 1);
        double v4 = smoothNoise(x2 + 1, y2 + 1);

        double i1 = interpolate(v1, v2, x_fraction);
        double i2 = interpolate(v3, v4, x_fraction);

        return interpolate(i1, i2, y_fraction);
    }

    public static double PerlinNoise2D(int n, double x, double y) {
        double total = 0;
        double p = 1 / 4;
        for (int i = 0; i <= n - 1; i++) {
            double frequency = Math.pow(2, i);
            double amplitude = Math.pow(p, i);
            //System.out.println("total = " + total);
            total = total + interpolatedNoise(x * frequency, y * frequency) * amplitude;
        }
        return total;
    }
}
