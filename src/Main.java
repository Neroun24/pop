import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
     static final int L=3;
     static  double T=0;
     static  double M=0;
     static  double E=0;
     static int[][][] model=new int[L][L][L];
     static double[] w=new double[5];
     static int spinsCount =0;
     static int count=0;
     static double eSum=0;
     static double e2Sum=0;
     static double mSum=0;
     static double m2Sum=0;

     static void initialization(){
        Random rnd=new Random();
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                for (int k = 0; k < L; k++) {
                    model[i][j][k]= rnd.nextInt(2);
                    M+=model[i][j][k];
//                    System.out.print(model[i][j][k]+" ");
                }
            }
        }
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                for (int k = 0; k < L; k++) {
                    E+=(i+1!=L)?model[i][j][k]*model[i+1][j][k]:0;
                    E+=(j+1!=L)?model[i][j][k]*model[i][j+1][k]:0;
                    E+=(k+1!=L)?model[i][j][k]*model[i][j][k+1]:0;
                }
            }
        }
        calcW();


    }
//     static void show(){
//        System.out.println("Temperature set to "+T);
//        System.out.println("The magnetization is "+M);
//        System.out.println("The energy is "+E);
//        for (int i = 0; i < L; i++) {
//            for (int j = 0; j < L; j++) {
//                for (int k = 0; k < L; k++) {
////                    System.out.print(model[i][j][k]+" ");
//                }
//            }
//        }
//    }
    static void calcW(){
         double e4=Math.exp(-4/5.4);
         double e8=e4*e4;
         w[0]=e8;
         w[4]=e8;
         w[1]=e4;
         w[3]=e4;
         w[2]=0;

    }
    static void calcAll(){
         count++;
        eSum += E;
        e2Sum += E * E;
        mSum += M;
        m2Sum += M * M;

    }
     static void metropolis(){
         Random rand=new Random();
         int x=0;int y=0;int z=0;int sum=0;
         System.out.println(sum);
         for (int i = 0; i < L*L*L; i++) {
             x= rand.nextInt(L);
             y= rand.nextInt(L);
             z= rand.nextInt(L);
             sum=model[(x-1+L)%L][y][z]+model[(x+1+L)%L][y][z]+
                     model[x][(y-1+L)%L][z]+model[x][(y+1+L)%L][z]+
                     model[x][y][(z-1+L)%L]+model[x][y][(z+1+L)%L];
         }
         if(sum*model[x][y][z]<=0||Math.random()<w[sum/2+2]){
             model[x][y][z]*=-1;
             spinsCount++;
             M+=2*model[x][y][z];
             E-=2*model[x][y][z]*sum;
         }
         calcAll();


     }
     static void outputData(){
         double norm=1/(double)(count*L*L*L);
         System.out.println("Коэффициент принятия = "+spinsCount*norm);
         System.out.println("Средняя энергия на спин = "+eSum*norm);
         System.out.println("Средний квадрат энергии на спин = "+e2Sum*norm);
         System.out.println("Средняя намагниченность = "+mSum*norm);
         System.out.println("Средний квадрат намагниченности = "+m2Sum*norm);
     }



    public static void main(String[] args) {
        Scanner console=new Scanner(System.in);
        initialization();
        while (T!=9999) {
            System.out.println("Enter T");
            try {
                T= console.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input! Try again.   Tip:Use a comma, not a dot");
                throw new InputMismatchException();
            }
            if(T<2.5||T>5.5){
                System.out.println("T must be greater than 2.5 or less than 5.5");
            }
            else {
                metropolis();
                outputData();
            }
        }
    }
}