public class Step {
    static final double dT=(Main.T_MAX-Main.T_MIN)/(Main.steps-1);
    public static double getTemperature(int step){
        double T=Main.T_MIN+dT*step;
        return T;
    }
}
