package A3_2020223;

import java.util.*;

class PortalStart{
    void start(){
        Portal portal=new Portal();
        Scanner s= new Scanner(System.in);
        String typesofmatrix="1. Rectangular Matrix\n"+
        "2. Row Matrix\n"+
        "3. Column Matrix\n"+
        "4. Square Matrix\n"+
        "5. Symmetric Matrix\n"+
        "6. Skew-symmetric Matrix\n"+
        "7. Upper-triangular Matrix\n"+
        "8. Lower-triangular Matrix\n"+
        "9. Singular Matrix\n"+
        "10. Diagonal Matrix\n"+
        "11. Scalar Matrix\n"+
        "12. Identity Matrix\n"+
        "13. Singleton Matrix\n"+
        "14. Ones Matrix\n"+
        "15. Null Matrix";

        String tasks="1. Take matrices as input and label them with appropriate matrix-types.\n"+
        "2. Create matrices of requested matrix-types and label them with appropriate matrix-types.\n"+
        "3. Change the elements of a matrix as long as the fixed matrix-type labels remain valid.\n"+
        "4. Display all the matrix-type labels of a requested matrix.\n"+
        "5. Perform addition.\n"+
        "6. Perform subtraction.\n"+
        "7. Perform multiplication.\n"+
        "8. Perform division.\n"+
        "9. Perform element-wise multiplication\n"+
        "10. Perform element-wise division\n"+
        "11. Transpose matrices.\n"+
        "12. Inverse matrices\n"+
        "13. Compute row-wise mean\n"+
        "14. Comput column-wise mean\n"+
        "15. Compute mean of all the elements.\n"+
        "16. Compute determinants.\n"+
        "17. Use singleton matrices as scalars\n"+
        "18. Compute A+AT for a matrix A.\n"+
        "19. Compute Eigen vectors and values.\n"+
        "20. Solve sets of linear equations using matrices.\n"+
        "21. Retrieve all the existing matrices (entered or created) having requested matrix-type labels.\n"+
        "22. Perform elementwise addition\n"+
        "23. Perform element wise subtraction\n"+
        "24. Exit";
        int option;
        System.out.println("Welcome!");
        while(true){
            System.out.println(tasks);
            System.out.println("enter the task number you want to perform: ");
            option=s.nextInt();
            if (option==1) {
                portal.inputmatrix();
            }else if(option==2){
                System.out.println(typesofmatrix);
                int type=s.nextInt();
                portal.creatematrix(type);
            }
            else if(option==3){
                portal.showallmatrices();
                int id=s.nextInt();
                portal.changelements(id);
            }
            else if(option==4){
                portal.showallmatrices();
                System.out.println("enter id of matrix you want labels of: ");
                int id=s.nextInt();
                portal.displaylabels(id);
            }
            else if(option==5 || option==22){
                portal.showallmatrices();
                System.out.println("enter id of first matrix: ");
                int a=s.nextInt();
                System.out.println("enter id of second matrix: ");
                int b=s.nextInt();
                portal.addition(a, b);
            }
            else if(option==6 || option==23){
                portal.showallmatrices();
                System.out.println("enter id of first matrix: ");
                int a=s.nextInt();
                System.out.println("enter id of second matrix: ");
                int b=s.nextInt();
                portal.subtraction(a, b);
            }
            else if(option==7){
                portal.showallmatrices();
                System.out.println("enter id of first matrix: ");
                int a=s.nextInt();
                System.out.println("enter id of second matrix: ");
                int b=s.nextInt();
                portal.multiplication(a, b);
            }
            else if(option==8){
                portal.showallmatrices();
                System.out.println("enter id of first matrix: ");
                int a=s.nextInt();
                System.out.println("enter id of second matrix: ");
                int b=s.nextInt();
                portal.division(a, b);
            }
            else if(option==9){
                portal.showallmatrices();
                System.out.println("enter id of first matrix: ");
                int a=s.nextInt();
                System.out.println("enter id of second matrix: ");
                int b=s.nextInt();
                portal.multele(a, b);
            }
            else if(option==10){
                portal.showallmatrices();
                System.out.println("enter id of first matrix: ");
                int a=s.nextInt();
                System.out.println("enter id of second matrix: ");
                int b=s.nextInt();
                portal.divele(a, b);
            }
            else if(option==11){
                portal.showallmatrices();
                System.out.println("enter id of matrix: ");
                int a=s.nextInt();
                portal.transpose(a);
            }
            else if(option==12){
                portal.getmatrixoflabel(4);
                System.out.println("enter id of matrix: ");
                int a=s.nextInt();
                portal.inverse(a);
            }
            else if(option==13){
                portal.showallmatrices();
                System.out.println("enter id of matrix: ");
                int id=s.nextInt();
                portal.rowmean(id);
            }
            else if(option==14){
                portal.showallmatrices();
                System.out.println("enter id of matrix: ");
                int id=s.nextInt();
                portal.columnmean(id);
            }
            else if(option==15){
                portal.showallmatrices();
                System.out.println("enter id of matrix: ");
                int id=s.nextInt();
                portal.mean(id);
            }
            else if(option==16){
                portal.getmatrixoflabel(4);
                System.out.println("enter id of matrix: ");
                int id=s.nextInt();
                portal.determinant(id);
            }
            else if(option==17){
                System.out.println("singleton matrices: ");
                portal.getmatrixoflabel(13);
                System.out.println("enter id: ");
                int id=s.nextInt();                
                portal.showallmatrices();
                System.out.println("chose the matrix upon which operation is to be done");
                int id2=s.nextInt();
                portal.singleasscalar(id, id2);
            }
            else if(option==18){
                portal.getmatrixoflabel(4);
                System.out.println("enter id of matrix: ");
                int id=s.nextInt();
                portal.atpa(id);
            }
            else if(option==19){
                portal.showallmatrices();
                int id=s.nextInt();
                System.out.println("enter id");
                portal.eigen(id);
            }
            else if(option==20){
                portal.getmatrixoflabel(4);
                System.out.println("enter id of first matrix: ");
                int a=s.nextInt();
                portal.getmatrixoflabel(3);
                System.out.println("enter id of second matrix: ");
                int b=s.nextInt();
                portal.setoflineqns(a, b);
            }
            else if(option==21){
                System.out.println(typesofmatrix);
                System.out.println("enter id of matrix type");
                int id=s.nextInt();
                portal.getmatrixoflabel(id);
            }
            else if(option==24){
                break;
            }
            else{
                System.out.println("wrong input!");
            }
            System.out.println();
            System.out.println();
        }
    }
}

public class ap_ass3{
    public static void main(String[] args) {
        PortalStart p=new PortalStart();
        p.start();
    }
}

