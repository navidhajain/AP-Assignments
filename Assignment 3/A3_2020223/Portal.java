package A3_2020223;
import java.util.*;
import java.lang.*;
// import matrixCalculator.*;
// import matrix.*;

public class Portal {
    private matrixCalculator calculator=new matrixCalculator();
    private ArrayList<Matrix> matrixarray=new ArrayList<>();
    private ArrayList<Matrix> rectmatarray=new ArrayList<>();
    private ArrayList<Matrix> squarematarray=new ArrayList<>();
    private ArrayList<Matrix> diagmatarray=new ArrayList<>();
    private ArrayList<Matrix> trimatarray=new ArrayList<>();
    private ArrayList<Matrix> allsamematarray=new ArrayList<>();

    private double[][] matrixa={{1, 2}, {3, 4}};
    private double[][] matrixb={{5, 6, 7}, {8, 9, 10}};
    private double[][] matrixc= {{2, 4, 3}};
    private double[][] matrixd={{5}};
    private double[][] matrixe={{5, 6}, {7, 8}};
    private double[][] matrixf={{1}, {5}};
    private double[][] matrixg={{7}};

    private Matrix mata=identify(matrixa);
    private Matrix matb=identify(matrixb);
    private Matrix matc=identify(matrixc);
    private Matrix matd=identify(matrixd);
    private Matrix mate=identify(matrixe);
    private Matrix matf=identify(matrixf);
    private Matrix matg=identify(matrixg);

    ArrayList<Matrix> getmatrixarray(){
        return matrixarray;
    }

    ArrayList<Matrix> getrectarray(){
        return rectmatarray;
    }

    ArrayList<Matrix> getsquarematrixarray(){
        return squarematarray;
    }

    ArrayList<Matrix> getdiagmatrixarray(){
        return diagmatarray;
    }

    ArrayList<Matrix> gettrimatarray(){
        return trimatarray;
    }

    ArrayList<Matrix> getallsamearray(){
        return allsamematarray;
    }

    void showallmatrices(){
        for (Matrix mat : matrixarray) {
            System.out.println("ID: " +mat.getid());
            calculator.printmatrix(mat.getmatrix());
            System.out.println();
        }
    }

    void inputmatrix(){
        Scanner s=new Scanner(System.in);
        System.out.println("Enter number of rows: ");
        int numrows=s.nextInt();
        System.out.println("Enter number of columns: ");
        int numcol=s.nextInt();
        makematrix(numrows, numcol);
    }
    
    void makematrix(int numrows, int numcol){
        Scanner s=new Scanner(System.in);
        double[][] newmat=new double[numrows][numcol];
        for (int i = 0; i < numrows; i++) {
            System.out.println("In the "+ i +"th row ");
            for (int j = 0; j < numcol; j++) {
                System.out.println("Enter the "+ j+"th element: ");
                int ele=s.nextInt();
                newmat[i][j]=ele;
            }
        }
        Matrix mat=identify(newmat);  
        displaylabels(mat);

    }

    void displaylabels(Matrix mat){
        System.out.println("Labels are: ");
        for (String type : mat.gettypearray()) {
            System.out.print(type +"; ");
        } 
    }

    Matrix identify(double[][] newmat){
        Matrix matt=new Matrix();
        matt.setvalues(newmat.length, newmat[0].length, newmat);
        if (newmat[0].length==newmat.length) {            
            matt.addtotype("Square Matrix");
            squarematarray.add(matt);
            if (matt.isitSymm()) {
                matt.addtotype("Symmetric Matrix");
            }
            if (matt.isitskewSymm()) {
                matt.addtotype("Skew-Symmetric Matrix");
            }
            if (matt.isitUpper() || matt.isitLower()) {
                trimatarray.add(matt);
                if (matt.isitLower()) {
                    matt.addtotype("Lower Triangular Matrix");
                }else{
                    matt.addtotype("Upper Triangular Matrix");
                }      
            }
            if (matt.isitSingular()) {
                matt.addtotype("Singular Matrix");
            }
            if (matt.isitDiagnol()) {
                matt.addtotype("Diagnol Matrix");
                diagmatarray.add(matt);
                if (matt.isitIdentity()) {
                    matt.addtotype("Identity Matrix");
                }
                if (matt.isitScalar()) {
                    matt.addtotype("Scalar Matrix");
                }
            }
            if (matt.isitSingleton()) {
                matt.addtotype("Singleton Matrix");
            }
            if (matt.isitNull() || matt.isitOnes()) {
                allsamematarray.add(matt);
                if (matt.isitNull()) {
                    matt.addtotype("Null Matrix");
                }
                if (matt.isitOnes()) {
                    matt.addtotype("Ones Matrix");
                }                
            } 
            matrixarray.add(matt);
        }else{           
            // calculator.printmatrix(matt.getmatrix());
            matt.addtotype("Rectangular Matrix");
            rectmatarray.add(matt);
            if (matt.isitCol()) {
                matt.addtotype("Column Matrix");
            }
            if (matt.isitRow()) {
                matt.addtotype("Row Matrix");
            }
            if (matt.isitNull() || matt.isitOnes()) {
                allsamematarray.add(matt);
                if (matt.isitNull()) {
                    matt.addtotype("Null Matrix");
                }
                if (matt.isitOnes()) {
                    matt.addtotype("Ones Matrix");
                }                
            }
            matrixarray.add(matt);
        }

        return matt;
    }

    Matrix create(int r, int c){
        Scanner s=new Scanner(System.in);
        Matrix mat=new Matrix();
        double[][] newmat=new double[r][c];
        for (int i = 0; i < r; i++) {
            System.out.println("for the "+ i+ "row ");
            for (int j = 0; j < c; j++) {
                System.out.println("enter "+ j+ " element");
                newmat[i][j]=s.nextDouble();
            }
        }

        mat.setvalues(r, c, newmat);
        return mat;

    }

    void creatematrix(int typeid){
        Random random = new Random();
        Matrix mat=new Matrix();
        Scanner s=new Scanner(System.in);
        if (typeid==1) {
            System.out.println("enter number of rows: ");
            int numrows=s.nextInt();
            System.out.println("enter number of columns: ");
            int numcol=s.nextInt();
            double[][] tempmat=new double[numrows][numcol];
            for (int i = 0; i < numrows; i++) {
                for (int j = 0; j < numcol; j++) {
                    tempmat[i][j]=random.nextInt(100);
                }
            }
            mat.setvalues(numrows, numcol, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
            
        }else if(typeid==2){
            System.out.println("enter number of columns: ");
            int numcol=s.nextInt();
            double[][] tempmat=new double[1][numcol];
            for (int i = 0; i < numcol; i++) {
                tempmat[0][i]=random.nextInt(100);
            }
            mat.setvalues(1, numcol, tempmat);
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
        }else if(typeid==3){
            System.out.println("enter number of rows: ");
            int numrows=s.nextInt();
            double[][] tempmat=new double[numrows][1];
            for (int i = 0; i < numrows; i++) {
                tempmat[i][0]=random.nextInt(100);
            }
            mat.setvalues(numrows, 1, tempmat);
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
        }else if(typeid==4 ){
            System.out.println("enter number of rows and columns, since it is a square matrix input only one number: ");
            int num=s.nextInt();
            double[][] tempmat=new double[num][num];
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    tempmat[i][j]=random.nextInt(100);
                }
            }
            mat.setvalues(num, num, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
        }else if(typeid==5){
            System.out.println("enter number of rows and columns, since it is a square matrix input only one number: ");
            int num=s.nextInt();
            double[][] tempmat=new double[num][num];
            double ele=random.nextInt(100);
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    tempmat[i][j]=ele;
                }
            }
            mat.setvalues(num, num, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
            
        }else if(typeid==6){
            System.out.println("enter number of rows and columns, since it is a square matrix input only one number: ");
            int num=s.nextInt();
            double[][] tempmat=new double[num][num];
            mat.setvalues(num, num, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
            
        }else if(typeid==7){
            System.out.println("enter number of rows and columns, since it is a square matrix input only one number: ");
            int num=s.nextInt();
            double[][] tempmat=new double[num][num];
            for (int i = 0; i < num; i++) {
                for (int j = i; j < num; j++) {
                    tempmat[i][j]=random.nextInt(100);
                }
            }
            mat.setvalues(num, num, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
        }else if(typeid==8){
            System.out.println("enter number of rows and columns, since it is a square matrix input only one number: ");
            int num=s.nextInt();
            double[][] tempmat=new double[num][num];
            for (int i = 0; i < num; i++) {
                for (int j = 0; j <= i; j++) {
                    tempmat[i][j]=random.nextInt(100);
                }
            }
            mat.setvalues(num, num, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
        }else if(typeid==9){
            System.out.println("enter number of rows and columns, since it is a square matrix input only one number: ");
            int num=s.nextInt();
            double[][] tempmat=new double[num][num];
            double ele=random.nextInt(100);
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    tempmat[i][j]=ele;
                }
            }
            mat.setvalues(num, num, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
        }else if(typeid==10){
            System.out.println("enter number of rows and columns, since it is a square matrix input only one number: ");
            int num=s.nextInt();
            double[][] tempmat=new double[num][num];
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if(i==j){
                        tempmat[i][j]=random.nextInt(100);
                    }
                }
            }
            mat.setvalues(num, num, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
        }else if(typeid==11){
            System.out.println("enter number of rows and columns, since it is a square matrix input only one number: ");
            int num=s.nextInt();
            double[][] tempmat=new double[num][num];
            double ele=random.nextInt(100);
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if(i==j){
                        tempmat[i][j]=ele;
                    }                    
                }
            }
            mat.setvalues(num, num, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
        }else if(typeid==12){
            System.out.println("enter number of rows and columns, since it is a square matrix input only one number: ");
            int num=s.nextInt();
            double[][] tempmat={{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
            mat.setvalues(num, num, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
        }else if(typeid==13){
            double[][] tempmat={{random.nextInt(100)}};
            mat.setvalues(1, 1, tempmat);            
            mat=identify(mat.getmatrix());
            calculator.printmatrix(tempmat);
            displaylabels(mat);
        }else if(typeid==14){
            System.out.println("Enter number of rows: ");
            int numrows=s.nextInt();
            System.out.println("Enter number of columns: ");
            int numcol=s.nextInt();
            double[][] tempmat=new double[numrows][numcol];
            for (int i = 0; i < tempmat.length; i++) {
                for (int j = 0; j < tempmat[0].length; j++) {
                    tempmat[i][j]=1;
                }                
            }
            Matrix mat1=identify(tempmat);
            calculator.printmatrix(tempmat);
            displaylabels(mat1);
        }else if(typeid==15){
            System.out.println("Enter number of rows: ");
            int numrows=s.nextInt();
            System.out.println("Enter number of columns: ");
            int numcol=s.nextInt();
            double[][] tempmat=new double[numrows][numcol];
            Matrix mat2=identify(tempmat);
            calculator.printmatrix(tempmat);
            displaylabels(mat2);
        }else{
            System.out.println("wrong option!");
            return;
        }
    }

    void changelements(int id){
        Scanner s=new Scanner(System.in);
        boolean f=true;
        Matrix chosenmat=new Matrix();
        for (Matrix mat : matrixarray) {
            if (mat.getid()==id) {
                f=false;
                chosenmat=mat;
                break;
            }
        }

        if (f) {
            System.out.println("wrong input");
            return;
        }else{
            double[][] newmat= new double[chosenmat.getmatrix().length][chosenmat.getmatrix()[0].length];
        for (int i = 0; i < chosenmat.getmatrix().length; i++) {
            System.out.println("In the "+ i +"th row ");
            for (int j = 0; j < chosenmat.getmatrix()[0].length; j++) {
                System.out.println("Enter the "+ j+"th element: ");
                int ele=s.nextInt();
                newmat[i][j]=ele;
            }
        }

        Matrix tempmat=new Matrix();
        tempmat.setvalues(chosenmat.getmatrix().length, chosenmat.getmatrix()[0].length, newmat);

        for (String type : chosenmat.gettypearray()) {
            if (type.equals("Symmetric Matrix") && !tempmat.isitSymm()) {
                System.out.println("matrix isnt symmetric anymore");
                return;
            }
            if (type.equals("Skew-Symmetric Matrix") && !tempmat.isitskewSymm()) {
                System.out.println("matrix isnt skew symmetric anymore");
                return;
            }
            if (type.equals("Lower Triangular Matrix") && !tempmat.isitLower()) {
                System.out.println("matrix isnt lower triangular anymore anymore");
                return;
            }
            if (type.equals("Upper Triangular Matrix") && !tempmat.isitUpper()) {
                System.out.println("matrix isnt upper triangular anymore anymore");
                return;
            }
            if (type.equals("Singular Matrix") && !tempmat.isitSingular()) {
                System.out.println("matrix isnt singular anymore anymore");
                return;
            }
            if (type.equals("Diagnol Matrix") && !tempmat.isitDiagnol()) {
                System.out.println("matrix isnt diagnol anymore anymore");
                return;
            }
            if (type.equals("Identity Matrix") && !tempmat.isitIdentity()) {
                System.out.println("matrix isnt identity anymore anymore");
                return;
            }
            if (type.equals("Scalar Matrix") && !tempmat.isitScalar()) {
                System.out.println("matrix isnt scalar anymore anymore");
                return;
            }
            if (type.equals("Singleton Matrix") && !tempmat.isitSingleton()) {
                System.out.println("matrix isnt singleton anymore anymore");
                return;
            }
            if (type.equals("Null Matrix") && !tempmat.isitNull()) {
                System.out.println("matrix isnt null anymore anymore");
                return;
            }
            if (type.equals("Ones Matrix") && !tempmat.isitOnes()) {
                System.out.println("matrix isnt ones anymore anymore");
                return;
            }
            if (type.equals("Row Matrix") && !tempmat.isitRow()) {
                System.out.println("matrix isnt row anymore anymore");
                return;
            }
            if (type.equals("Column Matrix") && !tempmat.isitCol()) {
                System.out.println("matrix isnt column anymore anymore");
                return;
            }
        }

        chosenmat.setvalues(tempmat.getnumrows(), tempmat.getnumcolumns(), tempmat.getmatrix());

    }

    }

    void displaylabels(int id){
        Matrix chosenmat=new Matrix();
        boolean f=true;
        for (int i = 0; i < matrixarray.size(); i++) {
            if (id==matrixarray.get(i).getid()) {
                chosenmat=matrixarray.get(i);
                f=false;
                break;
            }
        }
        if (f) {
            System.out.println("Wrong ID");
        }

        System.out.println("Labels are: ");
        for (String type : chosenmat.gettypearray()) {
            System.out.print(type +", ");
        }        
    }

    void addition(int a, int b){
        boolean f1=true, f2=true;
        Matrix amat=new Matrix(), bmat=new Matrix();
        for (int i = 0; i < matrixarray.size(); i++) {
            if (a==matrixarray.get(i).getid()) {
                amat=matrixarray.get(i);
                f1=false;
            }
            if (b==matrixarray.get(i).getid()) {
                bmat=matrixarray.get(i);
                f2=false;
            }
        }
        if (f2 || f1) {
            System.out.println("Invalid IDs");
            return;
        }

        calculator.addition(amat, bmat); 

    }

    void subtraction(int a, int b){
        boolean f1=true, f2=true;
        Matrix amat=new Matrix(), bmat=new Matrix();
        for (int i = 0; i < matrixarray.size(); i++) {
            if (a==matrixarray.get(i).getid()) {
                amat=matrixarray.get(i);
                f1=false;
            }
            if (b==matrixarray.get(i).getid()) {
                bmat=matrixarray.get(i);
                f2=false;
            }
        }
        if (f2 || f1) {
            System.out.println("Invalid IDs");
            return;
        }

        calculator.subtraction(amat, bmat); 

    }

    void multiplication(int a, int b){
        boolean f1=true, f2=true;
        Matrix amat=new Matrix(), bmat=new Matrix();
        for (int i = 0; i < matrixarray.size(); i++) {
            if (a==matrixarray.get(i).getid()) {
                amat=matrixarray.get(i);
                f1=false;
            }
            if (b==matrixarray.get(i).getid()) {
                bmat=matrixarray.get(i);
                f2=false;
            }
        }
        if (f2 || f1) {
            System.out.println("Invalid IDs");
            return;
        }

        calculator.multiplication(amat, bmat); 

    }

    void division(int a, int b){
        boolean f1=true, f2=true;
        Matrix amat=new Matrix(), bmat=new Matrix();
        for (int i = 0; i < matrixarray.size(); i++) {
            if (a==matrixarray.get(i).getid()) {
                amat=matrixarray.get(i);
                f1=false;
            }
            if (b==matrixarray.get(i).getid()) {
                bmat=matrixarray.get(i);
                f2=false;
            }
        }
        if (f2 || f1) {
            System.out.println("Invalid IDs");
            return;
        }

        calculator.division(amat, bmat); 
    }

    void multele(int a, int b){
        boolean f1=true, f2=true;
        Matrix amat=new Matrix(), bmat=new Matrix();
        for (int i = 0; i < matrixarray.size(); i++) {
            if (a==matrixarray.get(i).getid()) {
                amat=matrixarray.get(i);
                f1=false;
            }
            if (b==matrixarray.get(i).getid()) {
                bmat=matrixarray.get(i);
                f2=false;
            }
        }
        if (f2 || f1) {
            System.out.println("Invalid IDs");
            return;
        }

        calculator.elementmult(amat, bmat); 
    }

    void divele(int a, int b){
        boolean f1=true, f2=true;
        Matrix amat=new Matrix(), bmat=new Matrix();
        for (int i = 0; i < matrixarray.size(); i++) {
            if (a==matrixarray.get(i).getid()) {
                amat=matrixarray.get(i);
                f1=false;
            }
            if (b==matrixarray.get(i).getid()) {
                bmat=matrixarray.get(i);
                f2=false;
            }
        }
        if (f2 || f1) {
            System.out.println("Invalid IDs");
            return;
        }

        calculator.elementdiv(amat, bmat); 
    }

    void transpose(int id){
        for (int i = 0; i < matrixarray.size(); i++) {
            if (id==matrixarray.get(i).getid()) {
                calculator.printmatrix(matrixarray.get(i).gettranspose());
                return;
            }
        }
        System.out.println("Invalid ID");
    }

    void inverse(int id){
        for (int i = 0; i < squarematarray.size(); i++) {
            if (id==squarematarray.get(i).getid()) {
                if (squarematarray.get(i).getinverse()==null) {
                   return;
                }
                calculator.printmatrix(squarematarray.get(i).getinverse());
                return;
            }
        }
        System.out.println("wrong id");
    }

    void rowmean(int id){
        System.out.println("calculating row wise mean, dim=2 in matlab");
        double[] mean=new double[0];
        int rows=0;
        boolean f=true;
        for (int i = 0; i < matrixarray.size(); i++) {
            if (id==matrixarray.get(i).getid()) {
                mean=matrixarray.get(i).rowmean();
                rows=matrixarray.get(i).getnumrows();
                f=false;
                break;
            }
        }

        if (f) {
            System.out.println("invalid id");
            return;
        }

        for (int i = 0; i < rows; i++) {
            System.out.println("mean of "+i+"th row is "+ mean[i]);
        }
    }

    void columnmean(int id){
        System.out.println("calculating column wise mean, dim=1 in matlab");
        double[] mean={0};
        boolean f=true;
        for (int i = 0; i < matrixarray.size(); i++) {
            if (id==matrixarray.get(i).getid()) {
                mean=matrixarray.get(i).columnmean();
                f=false;
                break;
            }
        }

        if (f) {
            System.out.println("invalid id");
            return;
        }

        for (int i = 0; i < mean.length; i++) {
            System.out.println("mean of "+i+"th column is "+ mean[i]);
        }
    }

    void mean(int id){
        double mean=0;
        boolean f=true;
        for (int i = 0; i < matrixarray.size(); i++) {
            if (id==matrixarray.get(i).getid()) {
                mean=matrixarray.get(i).mean();
                f=false;
                break;
            }
        }

        if (f) {
            System.out.println("invalid id");
            return;
        }        
        System.out.println("mean is "+ mean);
        
    }

    void determinant(int id){
        boolean f=true;
        Matrix chosenmat=new Matrix();
        for (int i = 0; i < squarematarray.size(); i++) {
            if (id==squarematarray.get(i).getid()) {
                chosenmat=squarematarray.get(i);
                f=false;
                break;                
            }
        }

        if (f) {
            System.out.println("wrong id");
            return;
        }

        System.out.println("Determinant is ");
        if (chosenmat.isitDiagnol()) {
            System.out.println(chosenmat.getdeterminant());
        }else if (chosenmat.isitLower() || chosenmat.isitUpper()) {
            System.out.println(chosenmat.getdeterminant());
        }else{
            System.out.println(chosenmat.getdeterminant());
        }        
    }

    void singleasscalar(int scalarid, int id){
        Scanner s= new Scanner(System.in);
        boolean f1=true, f2=true;
        Matrix scalarmat=new Matrix();
        Matrix chosenmat=new Matrix();
        for (int i = 0; i < matrixarray.size(); i++) {
            if (scalarid==matrixarray.get(i).getid()) {
                scalarmat=matrixarray.get(i);
                f1=false;
            }
            if (id==matrixarray.get(i).getid()) {
                chosenmat=matrixarray.get(i);
                f2=false;
            }
        }
        if (f2 || f1) {
            System.out.println("Invalid IDs");
            return;
        }

        if (!scalarmat.isitSingleton()) {
            System.out.println("1st matrix needs to be singleton!");
            return;
        }

        String menu="press:\n1. addition\n2.subtraction\n3.multiplication\n4.division";
        System.out.println(menu);
        int input=s.nextInt();
        if (input==1) {
            calculator.printmatrix(chosenmat.getscalaradd(scalarmat.getmatrix()[0][0]));
        }else if(input==2){
            calculator.printmatrix(chosenmat.getscalarsub(scalarmat.getmatrix()[0][0]));
        }else if(input==3){
            calculator.printmatrix(chosenmat.getscalarproduct(scalarmat.getmatrix()[0][0]));
        }else if(input==4){
            calculator.printmatrix(chosenmat.getscalardiv(scalarmat.getmatrix()[0][0]));
        }else{
            System.out.println("invalid option");
        }
    }

    void atpa(int id){
        boolean f=true;
        for (int i = 0; i < squarematarray.size(); i++) {
            if (id==squarematarray.get(i).getid()) {
                SquareMatrix chosenmat=squarematarray.get(i);
                f=false;
                calculator.printmatrix(chosenmat.getatplusa());
                break;                
            }
        }

        if (f) {
            System.out.println("wrong id");
            return;
        }
    }

    void eigen(int id){
        boolean f=true;
        Matrix chosenmat=new Matrix();
        for (int i = 0; i < squarematarray.size(); i++) {
            if (id==squarematarray.get(i).getid()) {
                f=false;
                chosenmat=squarematarray.get(i);
            }
        }

        if (f) {
            System.out.println("invalid id");
            return;
        }
        if (chosenmat.getnumcolumns()!=2 && chosenmat.getnumrows()!=2) {
            System.out.println("enter a 2x2 matrix id");
            return;
        }

        double p=chosenmat.getdeterminant();
        double m=(double)(chosenmat.getmatrix()[0][0] + chosenmat.getmatrix()[1][1])/2;        
        double sqroot= Math.sqrt(Math.pow(m, 2)-p);        
        double eigenvalue1=m-sqroot;
        double eigenvalue2=m+sqroot;
        System.out.println("eigen value 1 is " + eigenvalue1);
        System.out.println("eigen value 2 is "+ eigenvalue2);                        

        System.out.println("eigenvectors are: ");
        double[][] vect1= {{(-1)*chosenmat.getmatrix()[0][1]}, {chosenmat.getmatrix()[0][0]-eigenvalue1}};
        double[][] vect2= {{(-1)*chosenmat.getmatrix()[0][1]}, {chosenmat.getmatrix()[0][0]-eigenvalue2}};

        calculator.printmatrix(vect1);
        calculator.printmatrix(vect2);
    }

    void setoflineqns(int a, int b){
        boolean f1=true, f2=true;
        Matrix amat=new Matrix(), bmat=new Matrix();
        for (int i = 0; i < matrixarray.size(); i++) {
            if (a==matrixarray.get(i).getid()) {
                amat=matrixarray.get(i);
                f1=false;
            }
            if (b==matrixarray.get(i).getid()) {
                bmat=matrixarray.get(i);
                f2=false;
            }
        }

        if (!amat.isitSquare()) {
            System.out.println("enter a square matrix as the first matrix");
            return;
        }

        if (!bmat.isitCol()) {
            System.out.println("enter a column matrix as the second matrix");
            return;
        };

        if (f2 || f1) {
            System.out.println("Invalid IDs");
            return;
        }

        calculator.solvelineareqns(amat, bmat);
    }

    void getmatrixoflabel(int label){
        if (label==1) {
            for (Matrix rectmat : rectmatarray) {
                System.out.println("ID: " +rectmat.getid());
                calculator.printmatrix(rectmat.getmatrix());
                System.out.println();
            }
        }else if (label==2) {
            for (Matrix rectmat : rectmatarray) {
                if (rectmat.isitRow()) {
                    System.out.println("ID: " +rectmat.getid());
                    calculator.printmatrix(rectmat.getmatrix());
                    System.out.println();
                }           
            }
        }else if (label==3) {
            for (Matrix rectmat : rectmatarray) {
                if (rectmat.isitCol()) {
                    System.out.println("ID: " +rectmat.getid());
                    calculator.printmatrix(rectmat.getmatrix());
                    System.out.println();
                } 
            }
        }else if (label==4) {
            for (Matrix mat : matrixarray) {
                if (mat.isitSquare()) {
                    System.out.println("ID: " +mat.getid());
                    calculator.printmatrix(mat.getmatrix());
                    System.out.println();
                }               
            }
        }else if (label==5) {
            for (Matrix sqmat : squarematarray) {
                if (sqmat.isitSymm()) {
                    System.out.println("ID: " +sqmat.getid());
                    calculator.printmatrix(sqmat.getmatrix());
                    System.out.println();
                }
            }
        }else if (label==6) {
            for (Matrix sqmat : squarematarray) {
                if (sqmat.isitskewSymm()) {
                    System.out.println("ID: " +sqmat.getid());
                    calculator.printmatrix(sqmat.getmatrix());
                    System.out.println();
                }
            }
        }else if (label==7) {
            for (Matrix trimat : trimatarray) {
                if (trimat.isitUpper()) {
                    System.out.println("ID: " +trimat.getid());
                    calculator.printmatrix(trimat.getmatrix());
                    System.out.println();
                }
            }
        }else if (label==8) {
            for (Matrix trimat : trimatarray) {
                if (trimat.isitLower()) {
                    System.out.println("ID: " +trimat.getid());
                    calculator.printmatrix(trimat.getmatrix());
                    System.out.println();
                }
            }
        }else if (label==9) {
            for (Matrix sqmat : squarematarray) {
                if (sqmat.isitSingular()) {
                    System.out.println("ID: " +sqmat.getid());
                    calculator.printmatrix(sqmat.getmatrix());
                    System.out.println();
                }
            }
        }else if (label==10) {
            for (Matrix sqmat : squarematarray) {
                if (sqmat.isitDiagnol()) {
                    System.out.println("ID: " +sqmat.getid());
                    calculator.printmatrix(sqmat.getmatrix());
                    System.out.println();
                }
            }
        }else if (label==11) {
            for (Matrix diagmat: diagmatarray) {
                if (diagmat.isitScalar()) {
                    System.out.println("ID: " +diagmat.getid());
                    calculator.printmatrix(diagmat.getmatrix());
                    System.out.println();
                }
            }
        }else if (label==12) {
            for (Matrix diagmat: diagmatarray) {
                if (diagmat.isitIdentity()) {
                    System.out.println("ID: " +diagmat.getid());
                    calculator.printmatrix(diagmat.getmatrix());
                    System.out.println();
                }
            }
        }else if (label==13) {
            for (Matrix sqmat : squarematarray) {
                if (sqmat.isitSingleton()) {
                    System.out.println("ID: " +sqmat.getid());
                    calculator.printmatrix(sqmat.getmatrix());
                    System.out.println();
                }
            }
        }else if (label==14) {
            for (Matrix samemat: allsamematarray) {
                if (samemat.isitOnes()) {
                    System.out.println("ID: " +samemat.getid());
                    calculator.printmatrix(samemat.getmatrix());
                    System.out.println();
                }               
            }
        }else if (label==15) {
            for (Matrix samemat: allsamematarray) {
                if (samemat.isitNull()) {
                    System.out.println("ID: " +samemat.getid());
                    calculator.printmatrix(samemat.getmatrix());
                    System.out.println();
                }               
            }
        }else {
            System.out.println("wrong input!");
        }        
    }
}
