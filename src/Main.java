import java.io.*;
import java.util.Scanner;

public class Main {

    static int n = 4; //верт
    static int m = 5; //гор
    static double[][] matrix = new double[n][m];

    public static void main(String[] args) {
        File file = new File(".");
        try (Scanner sc = new Scanner(new FileInputStream("src/Gauss/Gauss_Test_4.txt")))
        {
                while (sc.hasNextInt()) {
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < m; j++) {
                            matrix[i][j] = sc.nextInt();
                        }
                    }
                }
        } catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        output();

        int j = 1;
        int k = 0;
        while (k < n) {
            if (Math.abs(matrix[k][k]) < 0.00001) {
                int i;
                for (i = k; i < n; i++) {
                    if (Math.abs(matrix[i][k]) >= 0.00001) {
                        double[] c;
                        c = matrix[k];
                        matrix[k] = matrix[i];
                        matrix[i] = c;
                        break;
                    }
                }
                double koef = matrix[k][k];
                for (i = 0; i < m; i++) {
                    matrix[k][i] /= koef;
                }
            }
            if (matrix[k][k] != 1) {
                double koef = matrix[k][k];
                for (int i = 0; i < m; i++) {
                    matrix[k][i] /= koef;
                }
            }
            while (j < n) {
                double koef = matrix[j][k] / matrix[k][k];
                for (int i = 0; i < m; i++) {
                    matrix[j][i] -= koef * matrix[k][i];
                }
                j++;
            }
            k++;
            j = k + 1;
        }
        output();

        double[] x = new double[n];
        for (int i = 1; i <= n; i++) {
            x[n - i] = matrix[n - i][m - 1];
            for (int g = 1; g < i; g++) {
                x[n - i] -= matrix[n - i][n - g] * x[n - g];
            }
        }

        output();
        for (double i : x) {
            System.out.printf("%.2f\n", i);
        }

    }

    public static void output() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}