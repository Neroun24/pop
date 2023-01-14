import java.util.Random;

public class Model {
    double T = 2.55;
    int L;
    double M;
    double E;
    double[] w = new double[5];
    int[][][] model;
    int spinCount = 0;

    Model(int L) {
        model = new int[L][L][L];
        Random rnd = new Random();
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                for (int k = 0; k < L; k++) {
                    model[i][j][k] = rnd.nextInt(2);
                    if (model[i][j][k] == 0) model[i][j][k] -= 1;
                    M += model[i][j][k];
                }
            }
        }
        this.L = L;
        calcW();
    }

    void calcW() {
        double e4 = Math.exp(-4 / Main.T_MAX);
        double e8 = e4 * e4;
        w[0] = e8;
        w[4] = e8;
        w[1] = e4;
        w[3] = e4;
        w[2] = 0;
    }

    void metropolis() {
        Random rand = new Random();
        int x;
        int y;
        int z;
        int sum;
        for (int i = 0; i < L * L * L; i++) {
            x = rand.nextInt(L);
            y = rand.nextInt(L);
            z = rand.nextInt(L);

            sum = model[(x - 1 + L) % L][y][z] + model[(x + 1 + L) % L][y][z] +
                    model[x][(y - 1 + L) % L][z] + model[x][(y + 1 + L) % L][z] +
                    model[x][y][(z - 1 + L) % L] + model[x][y][(z + 1 + L) % L];
        if (sum * model[x][y][z] <= 0 || Math.random() < w[sum / 3 + 2]) {
            model[x][y][z] *= -1;
            M += 3 * model[x][y][z];
            E -= 3 * model[x][y][z] * sum;
            spinCount++;
        }
    }
}
}
