

public class Main {
    //     static double[] w=new double[5];
//     static int spinsCount =0;
//     static int count=0;
    static double T_MAX = 5.4;
    static double T_MIN = 2.6;
    static int steps =10000;

    //     static double e2Sum=0;
//     static double mSum=0;
//     static double m2Sum=0;
//
//     static void initialization(){
//        Random rnd=new Random();
//        for (int i = 0; i < L; i++) {
//            for (int j = 0; j < L; j++) {
//                for (int k = 0; k < L; k++) {
//                    model[i][j][k]= rnd.nextInt(2);
//                    if (model[i][j][k]==0)model[i][j][k]-=1;
//                    M+=model[i][j][k];
////                    System.out.print(model[i][j][k]+" ");
//                }
//            }
//        }
//        for (int i = 0; i < L; i++) {
//            for (int j = 0; j < L; j++) {
//                for (int k = 0; k < L; k++) {
//                    E+=(i+1!=L)?model[i][j][k]*model[i+1][j][k]:0;
//                    E+=(j+1!=L)?model[i][j][k]*model[i][j+1][k]:0;
//                    E+=(k+1!=L)?model[i][j][k]*model[i][j][k+1]:0;
//                }
//            }
//        }
//        calcW();
//
//
//    }
//     static void show(){
//        System.out.println("Temperature set to "+T);
//        System.out.println("The magnetization is "+M);
//        System.out.println("The energy is "+E);
//        for (int i = 0; i < L; i++) {
//            for (int j = 0; j < L; j++) {
//                for (int k = 0; k < L; k++) {
//                    System.out.print(model[i][j][k]+" ");
//                }
//                System.out.println(" ");
//            }
//        }
//    }
//    static void calcW(){
//         double e4=Math.exp(-4/5.4);
//         double e8=e4*e4;
//         w[0]=e8;
//         w[4]=e8;
//         w[1]=e4;
//         w[3]=e4;
//         w[2]=0;
//
//    }
//    static void calcAll(){
//         count++;
//        eSum += E;
//        e2Sum += E * E;
//        mSum += M;
//        m2Sum += M * M;
//
//    }
//     static void metropolis(){
//         Random rand=new Random();
//         int x=0;int y=0;int z=0;int sum=0;
//         System.out.println(sum);
//         for (int i = 0; i < L*L*L; i++) {
//             x= rand.nextInt(L);
//             y= rand.nextInt(L);
//             z= rand.nextInt(L);
//             sum=model[(x-1+L)%L][y][z]+model[(x+1+L)%L][y][z]+
//                     model[x][(y-1+L)%L][z]+model[x][(y+1+L)%L][z]+
//                     model[x][y][(z-1+L)%L]+model[x][y][(z+1+L)%L];
//         }
//         if(sum*model[x][y][z]<=0||Math.random()<w[sum/2+2]){
//             model[x][y][z]*=-1;
//             spinsCount++;
//             M+=2*model[x][y][z];
//             E-=2*model[x][y][z]*sum;
//         }
//         calcAll();
//
//
//     }
//     static void outputData(){
//         double norm=1/(double)(count*L*L*L);
//         System.out.println("Коэффициент принятия = "+spinsCount*norm);
//         System.out.println("Средняя энергия на спин = "+eSum*norm);
//         System.out.println("Средний квадрат энергии на спин = "+e2Sum*norm);
//         System.out.println("Средняя намагниченность = "+mSum*norm);
//         System.out.println("Средний квадрат намагниченности = "+m2Sum*norm);
//     }
//
//
//
    public static void main(String[] args) {
        float eSum=0;
        float e2Sum=0;

        double norm;
        Model ising = new Model(3);
        for (int step = 0; step < steps; step++) {
            double T=Step.getTemperature(step);
            ising.metropolis();
            eSum+= ising.E;
            e2Sum+= ising.E* ising.E;
        }
        norm=1/(steps *Math.pow(ising.L, 3));
        System.out.println("eSum="+eSum*norm);
        System.out.println("e2Sum="+e2Sum*norm);

    }
}