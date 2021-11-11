package A3_2020223;

import java.util.*;
// import java.Matrix.*;

public class matrixCalculator {
    void printmatrix(double[][] mat){
        int row=mat.length;
        int column=mat[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    void printmatrix(int[][] mat){
        int row=mat.length;
        int column=mat[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    void addition(Matrix matt1, Matrix matt2){
        double[][] mat1=matt1.getmatrix();
        double[][] mat2=matt2.getmatrix();
        double[][] mat3=new double[matt1.getnumrows()][matt1.getnumcolumns()];

        if (matt1.getnumcolumns()!=matt2.getnumcolumns() || matt1.getnumrows()!=matt2.getnumrows()) {
            System.out.println("matrices have different dimensions");
            return;
        }

        for (int i = 0; i < matt1.getnumrows(); i++) {
            for (int j = 0; j < matt1.getnumcolumns(); j++) {
                mat3[i][j]= mat1[i][j]+ mat2[i][j];
            }            
        }
        printmatrix(mat3);
    }

    void subtraction(Matrix matt1, Matrix matt2){
        double[][] mat1=matt1.getmatrix();
        double[][] mat2=matt2.getmatrix();
        double[][] mat3=new double[matt1.getnumrows()][matt1.getnumcolumns()];

        if (matt1.getnumcolumns()!=matt2.getnumcolumns() || matt1.getnumrows()!=matt2.getnumrows()) {
            System.out.println("matrices have different dimensions");
            return;
        }

        for (int i = 0; i < matt1.getnumrows(); i++) {
            for (int j = 0; j < mat3.length; j++) {
                mat3[i][j]= mat1[i][j] - mat2[i][j];
            }            
        }

        printmatrix(mat3);
    }

    void multiplication(Matrix matt1, Matrix matt2){
        double[][] mat1=matt1.getmatrix();
        double[][] mat2=matt2.getmatrix();
        double[][] mat3=new double[matt1.getnumrows()][matt2.getnumcolumns()];

        if (matt1.isitNull() || matt2.isitNull()) {
            printmatrix(mat3);
            return;
        }

        if (matt1.getnumcolumns()!=matt2.getnumrows()) {
            System.out.println("multiplication is not possible of given matrices");
            return;
        }

        for (int i = 0; i < matt1.getnumrows(); i++) {
            for (int j = 0; j < matt2.getnumcolumns(); j++) {
                mat3[i][j]=0;
                for (int k = 0; k < matt2.getnumrows(); k++)
                    mat3[i][j] += mat1[i][k] * mat2[k][j];
                    // System.out.println(mat3[i][j]);
            }
        }

        printmatrix(mat3);
    }

    void division(Matrix matt1, Matrix matt2){
        int[][] mat3=new int[matt1.getnumrows()][matt2.getnumcolumns()];

        if (matt1.isitNull()) {
            printmatrix(mat3);
            return;
        }
        if (matt2.isitNull()) {
            System.out.println("2nd matrix is null, division is not defined");
            return;
        }
        if (!matt2.isitSquare()) {
            System.out.println("2nd matrix cannot be the divisor since it is not invertible");
            return;
        }
        else{
            if (matt2.isitSingular()) {
                System.out.println("2nd matrix is singular");
                return;
            }
            double[][] invmat= matt2.getinverse();
            double[][] invmat2=new double[invmat.length][invmat[0].length];
            for (int i = 0; i < invmat2.length; i++) {
                for (int j = 0; j < invmat2[0].length; j++) {
                    invmat2[i][j]=(int) invmat[i][j];
                }
            }
            Matrix inversemat2= new Matrix();
            inversemat2.setvalues(invmat2.length, invmat2[0].length, invmat2);
            multiplication(matt1, inversemat2);
            
        }
    }

    void solvelineareqns(Matrix matt1, Matrix matt2){    
        if (matt1.getnumrows()!=matt2.getnumrows()) {
            System.out.println("rows should be the same");
            return;
        }
        if (matt1.isitNull() && !matt2.isitNull()) {
            System.out.println("solution not possible");
            return;
        }
        if (!matt1.isitNull() && matt2.isitNull()) {
            int[][] nullmat=new int[matt2.getnumrows()][1];
            printmatrix(nullmat);
            return;
        }
        double[][] invmat1=matt1.getinverse();
        Matrix inversemat1=new Matrix();
        inversemat1.setvalues(matt1.getnumrows(), matt1.getnumcolumns(), invmat1);
        multiplication(inversemat1, matt2);
    }

    void elementmult(Matrix matt1, Matrix matt2){
        double[][] mat1=matt1.getmatrix();
        double[][] mat2=matt2.getmatrix();
        double[][] mat3=new double[matt1.getnumrows()][matt1.getnumcolumns()];

        if (matt1.isitNull() || matt2.isitNull()) {
            System.out.println("one of these matrices is null");
            printmatrix(mat3);
            return;
        }

        if (matt1.getnumrows()!=matt2.getnumrows() || matt1.getnumcolumns()!=matt2.getnumcolumns()) {
            System.out.println("dimensions need to be same for element wise multiplication");
            return;
        }

        for (int i = 0; i < mat3.length; i++) {
            for (int j = 0; j < mat3[0].length; j++) {
                mat3[i][j]=mat1[i][j]*mat2[i][j];
            }
        }

        printmatrix(mat3);
    }

    void elementdiv(Matrix matt1, Matrix matt2){
        double[][] mat1=matt1.getmatrix();
        double[][] mat2=matt2.getmatrix();
        double[][] mat3=new double[matt1.getnumrows()][matt1.getnumcolumns()];

        if (matt1.isitNull()) {
            printmatrix(mat3);
            return;
        }
        if (matt2.isitNull()) {
            System.out.println("2nd matrix is null, division is not defined");
            return;
        }

        if (matt1.getnumrows()!=matt2.getnumrows() || matt1.getnumcolumns()!=matt2.getnumcolumns()) {
            System.out.println("dimensions need to be same for element wise division");
            return;
        }

        for (int i = 0; i < mat3.length; i++) {
            for (int j = 0; j < mat3[0].length; j++) {
                if(mat2[i][j]==0){
                    System.out.println("zero encountered in the 2nd matrix, division not possible");
                    return;
                }
                mat3[i][j]=mat1[i][j]/mat2[i][j];
            }
        }

        printmatrix(mat3);
    }
}

