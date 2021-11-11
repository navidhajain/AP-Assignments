package A3_2020223;
import java.util.*;


class RectangularMatrix{
    private int rows, columns;    
    private double[][] matrix=new double[rows][columns];

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

    int getnumrows(){
        return rows;
    }

    int getnumcolumns(){
        return columns;
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

    double[][] getmatrix(){
        return matrix;
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
        if (!isitSquare()) {
            System.out.println("matrix needs to be square");
            return null;
        }
        double determinant=getdeterminant();
        if (determinant==-1) {
            System.out.println("inverse does not exist since determinant cannot be calculated");
            return null;
        }
        if (isitSingleton() && !isitNull()) {
            double[][] single=new double[1][1];
            single[0][0]= (double)1/getmatrix()[0][0];
            return single;
        }
        if (isitSingular()) {
            System.out.println("matrix is singular, inverse cannot be calculated");
            return null;
        }        
        else if (getnumrows()==2) {
            double[][] inverse={{getmatrix()[1][1]/determinant, (-1)*getmatrix()[0][1]/determinant}, {(-1)*getmatrix()[1][0]/determinant, getmatrix()[0][0]/determinant}};
            return inverse;
        }

        else if(getnumrows()==3){
            double[][] inverse=new double[getnumrows()][getnumcolumns()];
            for (int i = 0; i < inverse.length; i++) {
                for (int j = 0; j < inverse.length; j++) {
                    inverse[i][j]=((getmatrix()[(j+1)%3][(i+1)%3] * getmatrix()[(j+2)%3][(i+2)%3]) - (getmatrix()[(j+1)%3][(i+2)%3] * getmatrix()[(j+2)%3][(i+1)%3]))/ determinant;
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
                for (int j = 0; j < trans[0].length; j++) {
                    if (trans[i][j] != (-1)*getmatrix()[i][j]) {
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
                for (int j = 0; j < trans[0].length; j++) {
                    if (trans[i][j]!=getmatrix()[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean isitSingleton(){
        if (getnumrows()==1 && getnumcolumns()==1) {
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
            det= getmatrix()[0][0];
        }
        else if (getnumrows()==2) {
            det= (getmatrix()[0][0]*getmatrix()[1][1]) - (getmatrix()[0][1]*getmatrix()[1][0]);
        }
        else if (getnumrows()==3) {
            double x = (getmatrix()[1][1] * getmatrix()[2][2]) - (getmatrix()[2][1] * getmatrix()[1][2]);
            double y = (getmatrix()[1][0] * getmatrix()[2][2]) - (getmatrix()[2][0] * getmatrix()[1][2]);
            double z = (getmatrix()[1][0] * getmatrix()[2][1]) - (getmatrix()[2][0] * getmatrix()[1][1]);
            
            det= (getmatrix()[0][0] * x)- (getmatrix()[0][1] * y) + (getmatrix()[0][2] * z);
        }
        else{
            System.out.println("Determinant cannot be calculated");
            det= -1;
        }
        return det;
    }

    double[][] getatplusa(){
        double[][] mat2=gettranspose();
        double[][] matsum=new double[getnumrows()][getnumcolumns()];

        for (int i = 0; i < getmatrix().length; i++) {
            for (int j = 0; j < getmatrix().length; j++) {
                matsum[i][j]= mat2[i][j] + getmatrix()[i][j];
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
            for (int i = 0; i < getmatrix().length; i++) {
                if (getmatrix()[i][i]!=1) {
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
            for (int i = 0; i < getnumrows() ; i++){
                for (int j = 0; j < i; j++){
                    if (getmatrix()[i][j] != 0){
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
            for (int i = 0; i < getnumrows(); i++){
                for (int j = i + 1; j < getnumrows(); j++){
                    if (getmatrix()[i][j] != 0)
                        return false;
                }                    
            }
            return true;
        }
        return false;
    }

    @Override
    double getdeterminant(){
        if (isitLower() || isitUpper()) {
            double sum=1;
        for (int i = 0; i < getmatrix().length; i++) {
            sum=sum*getmatrix()[i][i];
        }
        return sum;
        }
        return super.getdeterminant();
        
    }
}

class DiagnolMatrix extends TriangularMatrix{
    private double[] arr=new double[getnumrows()];

    DiagnolMatrix(){
        super();
        for (int i = 0; i < getmatrix().length; i++) {
            for (int j = 0; j < getmatrix().length; j++) {
                if (i==j) {
                    arr[i]=getmatrix()[i][j];
                }
            }            
        }
    }

    public boolean isitScalar(){
        if (isitDiagnol()) {
            for (int i = 0; i < getmatrix().length-1; i++) {
                if (getmatrix()[i][i]!=getmatrix()[i+1][i+1]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
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
        double[] meanmat=new double[getnumrows()];
        double sum;
        for (int i = 0; i < getnumrows(); i++) {
            sum=0;
            for (int j = 0; j < getnumcolumns(); j++) {
                sum+=getmatrix()[i][j];
            }
            meanmat[i]=sum/getnumcolumns();
        }
        return meanmat;      
    }

    double[] columnmean(){
        double[] meanmat=new double[getnumcolumns()];
        double sum;
        for (int i = 0; i < getnumcolumns(); i++) {
            sum=0;
            for (int j = 0; j < getnumrows(); j++) {
                sum+=getmatrix()[j][i];
            }
            meanmat[i]=sum/getnumrows();
        }
        return meanmat;
    }

    double mean(){
        double sum=0;
        for (int i = 0; i < getnumrows(); i++) {
            for (int j = 0; j < getnumcolumns(); j++) {
                sum+=getmatrix()[i][j];
            }            
        }
        return sum/(getnumrows()*getnumcolumns());
    }

    double[][] getscalaradd(double k){
        double[][] prodmat=new double[getnumrows()][getnumcolumns()];
        for (int i = 0; i < getmatrix().length; i++) {
            for (int j = 0; j < prodmat.length; j++) {
                prodmat[i][j]=getmatrix()[i][j]+k;
            }
        }

        return prodmat;
    }    

    double[][] getscalarsub(double k){
        double[][] prodmat=new double[getnumrows()][getnumcolumns()];
        for (int i = 0; i < getmatrix().length; i++) {
            for (int j = 0; j < prodmat.length; j++) {
                prodmat[i][j]=getmatrix()[i][j]-k;
            }
        }

        return prodmat;
    }

    double[][] getscalardiv(double k){        
        double[][] prodmat=new double[getnumrows()][getnumcolumns()];
        if (k==0) {
            System.out.println("scalar is 0, division is not possible");
            return prodmat;
        }
        for (int i = 0; i < getmatrix().length; i++) {
            for (int j = 0; j < prodmat.length; j++) {
                prodmat[i][j]=getmatrix()[i][j]/k;
            }
        }

        return prodmat;
    }

    
}
