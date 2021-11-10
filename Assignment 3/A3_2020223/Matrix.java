package A3_2020223;
import java.util.*;


class RectangularMatrix{
    protected int rows, columns;    
    protected double[][] matrix=new double[rows][columns];

    RectangularMatrix(){
        rows=0;
        columns=0;
    }

    void setvalues(int row, int column, double[][] mat){
        rows=row;
        columns=column;
        matrix=mat;
    }

    public boolean isitNull(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j]!=0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isitSquare(){
        if (rows==columns) {
            return true;
        }
        else{
            return false;
        }
    }

    double[][] getscalarproduct(double k){
        double[][] prodmat=new double[rows][columns];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                prodmat[i][j]=matrix[i][j]*k;
            }
        }

        return prodmat;
    }

    public boolean isitOnes(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j]!=1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isitDiagnol(){
        if (isitNull()) {
            return false;
        }
        if (isitSquare()) {
            for (int i =  0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (i!=j && matrix[i][j]!=0) {
                        return false;
                    }
                }
                
            }
            return true;
        }
        return false;
    }


    public boolean isitRow(){
        if (rows==1) {
            return true;
        }
        return false;
    }

    double[][] gettranspose(){
        double[][] transmat=new double[columns][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <columns; j++) {
                transmat[j][i]=matrix[i][j];
            }            
        }
        return transmat;   
    }

    public boolean isitCol(){
        if (columns==1) {
            return true;
        }
        return false;
    }

}

class SquareMatrix extends RectangularMatrix{

    SquareMatrix(){
        super();   
    }

    double[][] getinverse(){
        double determinant=getdeterminant();
        if (determinant==-1) {
            System.out.println("inverse does not exist since determinant cannot be calculated");
            return null;
        }
        if (isitSingleton() && !isitNull()) {
            double[][] single=new double[1][1];
            single[0][0]= (double)1/matrix[0][0];
            return single;
        }
        if (isitSingular()) {
            System.out.println("matrix is singular, inverse cannot be calculated");
            return null;
        }        
        else if (rows==2) {
            double[][] inverse={{matrix[1][1]/determinant, (-1)*matrix[0][1]/determinant}, {(-1)*matrix[1][0]/determinant, matrix[0][0]/determinant}};
            return inverse;
        }

        else if(rows==3){
            double[][] inverse=new double[rows][columns];
            for (int i = 0; i < inverse.length; i++) {
                for (int j = 0; j < inverse.length; j++) {
                    inverse[i][j]=((matrix[(j+1)%3][(i+1)%3] * matrix[(j+2)%3][(i+2)%3]) - (matrix[(j+1)%3][(i+2)%3] * matrix[(j+2)%3][(i+1)%3]))/ determinant;
                }
            }
            return inverse;            
        }else{
            System.out.println("enter valid matrix");
            return null;
        }
    }

    public boolean isitskewSymm(){
        if (isitSquare()) {
            double[][] trans=gettranspose();
            for (int i = 0; i < trans.length; i++) {
                for (int j = 0; j < trans.length; j++) {
                    if (trans[i][j] != (-1)*matrix[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean isitSymm(){
        if (isitSquare()) {
            double[][] trans=gettranspose();
            for (int i = 0; i < trans.length; i++) {
                for (int j = 0; j < trans.length; j++) {
                    if (trans[i][j]!=matrix[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean isitSingleton(){
        if (rows==1 && columns==1) {
            return true;
        }else{
            return false;
        }
    }

    public boolean isitSingular(){
        if (isitSquare()) {
            if (getdeterminant()==0) {
                return true;
            }else{
                return false;
            }
        }
        return false;
        
    }

    double getdeterminant(){
        // System.out.println("rows: " + rows);
        double det=0;
        if (isitSingleton()) {
            det= matrix[0][0];
        }
        else if (rows==2) {
            det= (matrix[0][0]*matrix[1][1]) - (matrix[0][1]*matrix[1][0]);
        }
        else if (rows==3) {
            double x = (matrix[1][1] * matrix[2][2]) - (matrix[2][1] * matrix[1][2]);
            double y = (matrix[1][0] * matrix[2][2]) - (matrix[2][0] * matrix[1][2]);
            double z = (matrix[1][0] * matrix[2][1]) - (matrix[2][0] * matrix[1][1]);
            
            det= (matrix[0][0] * x)- (matrix[0][1] * y) + (matrix[0][2] * z);
        }
        else{
            System.out.println("Determinant cannot be calculated");
            det= -1;
        }
        return det;
    }

    double[][] getatplusa(){
        double[][] mat2=gettranspose();
        double[][] matsum=new double[rows][columns];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matsum[i][j]= mat2[i][j] + matrix[i][j];
            }
        }

        return matsum;
    }
}

class TriangularMatrix extends SquareMatrix{

    TriangularMatrix(){
        super();      
    }  

    public boolean isitIdentity(){
        if (isitDiagnol()) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][i]!=1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isitUpper(){
        if (isitOnes()) {
            return false;
        }
        if (isitSingleton() || isitIdentity()) {
            return true;
        }
        if (isitSquare()) {
            for (int i = 0; i < rows ; i++){
                for (int j = 0; j < i; j++){
                    if (matrix[i][j] != 0){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean isitLower(){
        if (isitSingleton()) {
            return true;
        }
        if (isitNull()) {
            return false;
        }
        if (isitSquare()) {
            for (int i = 0; i < rows; i++){
                for (int j = i + 1; j < rows; j++){
                    if (matrix[i][j] != 0)
                        return false;
                }                    
            }
            return true;
        }
        return false;
    }

    double getdeterminant(){
        if (isitLower() || isitUpper()) {
            double sum=1;
        for (int i = 0; i < matrix.length; i++) {
            sum=sum*matrix[i][i];
        }
        return sum;
        }
        return super.getdeterminant();
        
    }
}

class DiagnolMatrix extends TriangularMatrix{
    private double[] arr=new double[rows];

    DiagnolMatrix(){
        super();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i==j) {
                    arr[i]=matrix[i][j];
                }
            }            
        }
    }

    public boolean isitScalar(){
        if (isitDiagnol()) {
            for (int i = 0; i < matrix.length-1; i++) {
                if (matrix[i][i]!=matrix[i+1][i+1]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    double getdeterminant(){
        if (isitIdentity()) {
            return 1;
        }
        if (isitDiagnol()) {
            double sum=1;
            for (int i = 0; i < arr.length; i++) {
                sum=sum*arr[i];
            }
        return sum;
        }else{
            return super.getdeterminant();
        }
        
    }
}

class AllSame extends DiagnolMatrix{    
    private int ele;

    AllSame(){
        super(); 
        if (isitNull()) {
            ele=0;
        }
        if (isitOnes()) {
            ele=1;
        }
    }

    int getele(){
        return ele;
    }
    
}

public class Matrix extends AllSame{
    private static int id=0;
    private int matid;
    private ArrayList<String> typearray=new ArrayList<>();

    Matrix(){
        super();     
        matid=id;
        id++;  
    }

    int getnumrows(){
        return rows;
    }

    int getnumcolumns(){
        return columns;
    }

    void addtotype(String s){
        typearray.add(s);
    }
    
    ArrayList<String> gettypearray(){
        return typearray;
    }

    int getid(){
        return matid;
    }

    double[] rowmean(){
        double[] meanmat=new double[rows];
        double sum;
        for (int i = 0; i < rows; i++) {
            sum=0;
            for (int j = 0; j < columns; j++) {
                sum+=matrix[i][j];
            }
            meanmat[i]=sum/columns;
        }
        return meanmat;      
    }

    double[] columnmean(){
        double[] meanmat=new double[columns];
        double sum;
        for (int i = 0; i < columns; i++) {
            sum=0;
            for (int j = 0; j < rows; j++) {
                sum+=matrix[j][i];
            }
            meanmat[i]=sum/rows;
        }
        return meanmat;
    }

    double mean(){
        double sum=0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sum+=matrix[i][j];
            }            
        }
        return sum/(rows*columns);
    }

    double[][] getscalaradd(double k){
        double[][] prodmat=new double[rows][columns];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < prodmat.length; j++) {
                prodmat[i][j]=matrix[i][j]+k;
            }
        }

        return prodmat;
    }    

    double[][] getscalarsub(double k){
        double[][] prodmat=new double[rows][columns];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < prodmat.length; j++) {
                prodmat[i][j]=matrix[i][j]-k;
            }
        }

        return prodmat;
    }

    double[][] getscalardiv(double k){        
        double[][] prodmat=new double[rows][columns];
        if (k==0) {
            System.out.println("scalar is 0, division is not possible");
            return prodmat;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < prodmat.length; j++) {
                prodmat[i][j]=matrix[i][j]/k;
            }
        }

        return prodmat;
    }

    double[][] getmatrix(){
        return matrix;
    }
}
