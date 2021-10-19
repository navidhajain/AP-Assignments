import java.util.*;

interface Classmaterial{
    void viewclassmat();
}

class LecSlides implements Classmaterial{
    private final String topic;
    private final int numberofslides;
    private Date date;
    private ArrayList<String> contentslide= new ArrayList<>();
    private Instructor instruct;

    LecSlides(String top, int num, Instructor inst){
        topic=top;
        numberofslides=num;
        date=new Date();
        instruct=inst;
    }

    String gettopic(){
        return topic;
    }

    int getnumofslides(){
        return numberofslides;
    }

    public void viewclassmat(){
        System.out.println("Title: " + topic);
        for (int i = 0; i < contentslide.size() ; i++) {
            System.out.println("Slide "+ (i+1) + ": " + contentslide.get(i));
        }
        System.out.println("Number of slides: " + numberofslides);
        System.out.println("Date of upload: " + date.toString());
        System.out.println("uploaded by: " + instruct.getname());
    }

    public void addcontent(String con){
        contentslide.add(con);
    }
}

class LecVideos implements Classmaterial{
    private final String name;
    private final String vidfilename;
    private Date date;
    private Instructor instruct;

    LecVideos(String name1, String filename, Instructor inst){
        name=name1;
        vidfilename=filename;
        date=new Date();
        instruct=inst;
    }

    String getname(){
        return name;
    }

    String getvidfilename(){
        return vidfilename;
    }

    public void viewclassmat(){
        System.out.println("Title of video: "+ name);
        System.out.println("Video file: "+ vidfilename);
        System.out.println("Date of upload: " + date.toString());
        System.out.println("uploaded by: " + instruct.getname());
    }
}

interface Assessment {
    void viewassess();
    void gradeassess(Instructor instruct);
    void gradeit();
    String getgradedby();
    void closeassess();
    boolean isitaccepting();
    boolean ispending();
    boolean isitgraded();
    int getmaxmarks();
    int getmarks();
    void markit(int m);
    void submitit(String sub);
    void submit();
    String getsubmission();
    String getassess();
    String gettype();
}

class Quiz implements Assessment{
    private boolean isAccepting;
    private final String ques;
    private boolean hassubmitted;
    private boolean isgraded;
    private String answer;
    private Instructor instructor;
    private int marks;
    private final int maxmarks;

    Quiz(String ques){
        this.ques=ques;
        isAccepting=true;
        hassubmitted=false;
        isgraded=false;
        maxmarks=1;
    }

    public void markit(int m){
        this.marks=m;
    }

    public int getmaxmarks(){
        return maxmarks;
    }

    public void viewassess(){
        System.out.println("question: "+ ques);
        System.out.println("...............");
    }

    public String getgradedby(){
        return instructor.getname();
    }

    public String getassess(){
        return ques;
    }

    public int getmarks(){
        return marks;
    }

    public void submit(){
        hassubmitted=true;
    }

    public String gettype(){
        return "Quiz";
    }

    public void submitit(String sub){
        hassubmitted=true;
        answer=sub;
    }

    public String getsubmission(){
        return answer;
    }

    public void gradeassess(Instructor instruct){
        isgraded=true;
        instructor=instruct;
    }

    public void gradeit(){
        isgraded=true;
    }

    public void closeassess(){
        isAccepting=false;
    }

    public boolean isitgraded(){
        return isgraded;
    }

    public boolean ispending(){
        return !hassubmitted;
    }

    public boolean isitaccepting(){
        return isAccepting;
    }

}

class Assignment implements Assessment{
    private boolean hassubmitted;
    private boolean isAccepting;
    private final int assmaxmarks;
    private final String statement;
    private boolean isgraded;
    private String filesub;
    private int marks;
    private Instructor inst;

    Assignment(String stat, int marks){
        statement=stat;
        assmaxmarks=marks;
        isgraded=false;
        hassubmitted=false;
        isAccepting=true;
    }

    public void markit(int m){
        marks=m;
    }

    public int getmaxmarks(){
        return assmaxmarks;
    }

    public void viewassess(){
        System.out.println("assignment: "+ statement + ", Max marks: " + (assmaxmarks));
        System.out.println("...............");
    }

    public String getgradedby(){
        return inst.getname();
    }

    public void submit(){
        hassubmitted=true;
    }

    public String getassess(){
        return statement;
    }

    public String gettype(){
        return "Assignment";
    }

    public boolean isitgraded(){
        return isgraded;
    }

    public String getsubmission(){
        return filesub;
    }

    public void gradeassess(Instructor instruct){
        isgraded=true;
        inst=instruct;
    }

    public void gradeit(){
        isgraded=true;
    }

    public int getmarks(){
        return marks;
    }

    public void closeassess(){
        isAccepting=false;
    }

    public boolean isitaccepting(){
        return isAccepting;
    }

    public boolean ispending(){
        return !hassubmitted;
    }

    public void submitit(String sub){
        if (sub.length()<=4) {
            System.out.println("Not valid submission");
        }
        if (sub.substring(sub.length()-4).equals(".zip")) {
            hassubmitted=true;
            filesub=sub;
        }else{
            System.out.println("invalid file type");
        }
    }
}

interface Person{
    String getname();
}

class Comments{
    private final String comments;
    private Date date;
    private final Person comby;    
    
    Comments(String comm, Person comby){
        comments=comm;
        date=new Date();
        this.comby=comby;
    }

    void viewcomments(){
        System.out.println(comments + " - " + comby.getname());    
        System.out.println(date);
    }
}

class Student implements Person{
    private final String studname;
    private ArrayList<Assessment> assessarraystud= new ArrayList<>();
    
    Student(String s){
        studname=s;
    }

    public String getname(){
        return studname;
    }

    public ArrayList<Assessment> getassessarray(){
        return assessarraystud;
    }
}

class Instructor implements Person{
    private final String insname;
    Instructor(String s){
        insname=s;
    }

    public String getname(){
        return insname;
    }
}

class Course{
    private ArrayList<Student> studarray=new ArrayList<>();
    private ArrayList<Instructor> instarray= new ArrayList<>();
    private ArrayList<Classmaterial> classmatarray= new ArrayList<>();
    private ArrayList<Assessment> assessarray= new ArrayList<>();
    private ArrayList<Comments> commentarray=new ArrayList<>();

    Course(){
        studarray.add(new Student("S0"));
        studarray.add(new Student("S1"));
        studarray.add(new Student("S2"));
        instarray.add(new Instructor("I0"));
        instarray.add(new Instructor("I1"));
    }

    ArrayList<Student> getstudarray(){
        return studarray;
    }

    ArrayList<Instructor> getinstrcutarray(){
        return instarray;
    }

    void viewinstruct(){
        System.out.println("Instructors: ");
        for (int i = 0; i < instarray.size(); i++) {
            System.out.println((i) +" - "+ instarray.get(i).getname());
        }
    }

    void viewstud(){
        System.out.println("Students: ");
        for (int i = 0; i < studarray.size(); i++) {
            System.out.println((i) + " - " + studarray.get(i).getname());
        }
    }

    void addclassmaterial(Instructor inst){
        Scanner s=new Scanner(System.in);
        System.out.println("1. Add Lecture Slide\n2. Add Lecture Video");
        String waste;
        System.out.println("Enter option");
        int opt=s.nextInt();
        waste=s.nextLine();
        if (opt==1) {
            System.out.println("enter topic of slides");           
            String topic= s.nextLine();
            System.out.println("enter number of slides");
            int num=s.nextInt();
            waste=s.nextLine();
            LecSlides newlec= new LecSlides(topic, num, inst);
            classmatarray.add(newlec);
            System.out.println("Enter content of slides: ");
            for (int i = 1; i <= num; i++) {
                System.out.println("Content of slide "+ (i));                
                String newcon= s.nextLine();
                newlec.addcontent(newcon);
            }
        }
        else if(opt ==2){
            System.out.println("enter topic of video: ");
            
            String topic=s.nextLine();
            System.out.println("enter filename of video: ");
            String file=s.next();
            if (file.length()<=4) {
                
                System.out.println("invalid file name");
                return;
            }
            if (!file.substring(file.length()-4).equals(".mp4")) {
                
                System.out.println("invalid file name");
                return;
            }
            LecVideos newvid= new LecVideos(topic, file, inst);
            classmatarray.add(newvid);
        }else{
            System.out.println("wrong option");
            return;
        }
    }

    void addassessment(){
        String w;
        Scanner s=new Scanner(System.in);
        System.out.println("1. Add Assignment\n2. Add Quiz");
        System.out.println("Enter option");
        int opt=s.nextInt();
        w=s.nextLine();
        if (opt==1) {
            System.out.println("Enter problem statement: ");            
            String stat=s.nextLine();
            System.out.println("enter max marks: ");
            int maxmarks= s.nextInt();
            w=s.nextLine();
            Assignment newass= new Assignment(stat, maxmarks);
            assessarray.add(newass);
            for (Student stud : studarray) {
                stud.getassessarray().add(new Assignment(stat, maxmarks));        
            }
        }else if(opt==2){
            System.out.println("enter quiz question: ");
            
            String ques= s.nextLine();
            Quiz newquiz=new Quiz(ques);
            assessarray.add(newquiz);
            for (Student stud : studarray) {
                stud.getassessarray().add(new Quiz(ques));        
            }
        }else{
            System.out.println("wrong option");
            return;
        }
    }

    void viewlectmat(){
        for (Classmaterial cmat : classmatarray) {
            cmat.viewclassmat();
            System.out.println();
        }
    }

    void viewassess(){
        for (int i = 0; i < assessarray.size(); i++) {
            System.out.print("ID "+ (i) + ": ");
            assessarray.get(i).viewassess();
        }
    }

    void gradeass(Instructor choseninst){
        Scanner s=new Scanner(System.in);
        System.out.println("List of assessments: ");

        if (assessarray.size()==0) {
            System.out.println("no assessments");
            return;
        }
        
        for (int i = 0; i < assessarray.size(); i++) {            
            System.out.print("ID " + (i) +" ");            
            assessarray.get(i).viewassess();
        }

        System.out.println("enter id of assessment to view submissions: ");
        int id=s.nextInt();
        Assessment chosenassess=assessarray.get(id);
        System.out.println("choose id from these ungraded submissions: ");
        boolean flag= true;

        for (int i = 0; i < studarray.size(); i++) {
            if (!studarray.get(i).getassessarray().get(id).isitgraded() && !studarray.get(i).getassessarray().get(id).ispending()) {
                flag=false;
                System.out.println(i +" "+ studarray.get(i).getname());
            }
        }

        if (flag) {
            System.out.println("no ungraded submissions");
            return;
        }

        System.out.println("enter id: ");
        int id2=s.nextInt();
        Student chosenstud=studarray.get(id2);
        System.out.print("Submission: ");
        System.out.println(chosenstud.getassessarray().get(id).getsubmission());
        System.out.println("...............");
        chosenstud.getassessarray().get(id).gradeassess(choseninst);
        System.out.println("Max marks: " + chosenassess.getmaxmarks());
        System.out.println("Marks scored: ");
        int marksscored=s.nextInt();
        if (marksscored>chosenassess.getmaxmarks()) {
            System.out.println("marks entered are greater than max marks");
            return;
        }
        chosenstud.getassessarray().get(id).markit(marksscored);
    }

    void closeassess(){
        Scanner s= new Scanner(System.in);
        System.out.println("List of open assessments");
        boolean flag=true;
        for (int i = 0; i < assessarray.size(); i++) {
            if (assessarray.get(i).isitaccepting()) {
                flag=false;
                System.out.print("ID " + (i));
                assessarray.get(i).viewassess();
            }
        }

        if (flag) {
            System.out.println("no open assessments");
            return;
        }

        System.out.println("enter id of assessment to close: ");
        int closeid= s.nextInt();
        assessarray.get(closeid).closeassess();
        for (Student stu1 : studarray) {
            stu1.getassessarray().get(closeid).closeassess();
        }
    }

    void viewcomm(){
        for (Comments comm : commentarray) {
            comm.viewcomments();
            System.out.println();
        }
    }

    void addcomm(String comm, Person pers){  
        Comments newcomm= new Comments(comm, pers);
        commentarray.add(newcomm);
    }
    
    void submitassess(Student chosenstud){
        String w;
        Scanner s=new Scanner(System.in);
        System.out.println("Pending Assessment: ");
        boolean flag1=true;
        for (int i = 0; i < chosenstud.getassessarray().size(); i++) {
            if (chosenstud.getassessarray().get(i).ispending() && assessarray.get(i).isitaccepting()) {
                flag1=false;
                System.out.print("ID " + (i)+ ": ");
                assessarray.get(i).viewassess();
            }
        }

        if (flag1) {
            System.out.println("No pending assignments");
            return;
        }

        System.out.println("Enter id of assessment");
        int assessid = s.nextInt();
        w=s.nextLine();
        Assessment chosenasses=chosenstud.getassessarray().get(assessid);

        if (chosenasses.gettype().equals("Assignment")) {                        
            System.out.println("enter filename of assignment: ");            
            String filename=s.nextLine();
            chosenasses.submitit(filename);            
        }else if(chosenasses.gettype().equals("Quiz")){            
            System.out.println(chosenasses.getassess());           
            String answer=s.nextLine();
            chosenasses.submitit(answer);           
        }   
    }

    void viewgraded(Student chosenstud){
        System.out.println("graded submissions: ");
        for (Assessment assess : chosenstud.getassessarray()) {
            if (assess.isitgraded()) {
                System.out.print("Submission: ");
                System.out.println(assess.getsubmission());
                System.out.println("Marks scored: "+ assess.getmarks());
                System.out.println("Graded by "+ assess.getgradedby());
            }            
        }
        System.out.println();
         
        System.out.println("ungraded submissions: ");
        for (Assessment assess : chosenstud.getassessarray()) {
            if (!assess.isitgraded() && !assess.ispending()) {
                System.out.print("Submission: ");
                System.out.println(assess.getsubmission());
            }
        }
    }
}

class BackPack{
    void start(){
        Scanner s=new Scanner(System.in);
        
        String insorstud="1. Enter as instructor\n"+
        "2. Enter as student\n" +
        "3. Exit";

        String instmenu="1. Add class material\n" +
        "2. Add Assessment\n" +
        "3. View lecture materials\n" +
        "4. View Assessment\n" +
        "5. Grade Assessment\n" +
        "6. Close assessment\n"+
        "7. View comments\n"+
        "8. Add comments\n"+
        "9. Logout";

        String studmenu="1. View lecture materials\n" +
        "2. View Assessment\n"+
        "3. Submit assessment\n"+
        "4. View grades\n"+
        "5. View comments\n"+
        "6. Add comments\n"+
        "7. Logout";

        Course course=new Course();
        
        while (true) {
            System.out.println("Welcome to BackPack");
            System.out.println(insorstud);
            int userid=s.nextInt();
            if (userid==1) {
                course.viewinstruct();
                System.out.println("choose id");
                int insi=s.nextInt();
                String w=s.nextLine();
                Instructor instructor=course.getinstrcutarray().get(insi);
                
                while (true) {
                    System.out.println("welcome "+ instructor.getname());
                    System.out.println(instmenu);
                    int instoption=s.nextInt();
                    w=s.nextLine();
                    if (instoption==1) {
                        course.addclassmaterial(instructor);
                    }
                    else if(instoption==2){
                        course.addassessment();
                    }
                    else if(instoption==3){
                        course.viewlectmat();
                    }
                    else if(instoption==4){
                        course.viewassess();
                    }
                    else if(instoption==5){
                        course.gradeass(instructor);
                    }
                    else if (instoption==6) {
                        course.closeassess();
                    }
                    else if (instoption==7) {
                        course.viewcomm();
                    }
                    else if(instoption==8){
                        System.out.println("enter a comment: ");                        
                        String comm=s.nextLine();
                        course.addcomm(comm, instructor);
                    }
                    else if(instoption==9){
                        break;
                    }
                    else{
                        System.out.println("Wrong option");
                    }
                    System.out.println();
                }
            }
            else if (userid==2) {
                course.viewstud();
                System.out.println("choose id");
                int studi=s.nextInt();
                String w1=s.nextLine();
                Student student=course.getstudarray().get(studi);

                while (true) {
                    System.out.println("welcome "+ student.getname());
                    System.out.println(studmenu);
                    int studoption=s.nextInt();
                    w1=s.nextLine();
                    if (studoption==1) {
                        course.viewlectmat();
                    }
                    else if(studoption==2){
                        course.viewassess();
                    }
                    else if(studoption==3){
                        course.submitassess(student);
                    }
                    else if(studoption==4){
                        course.viewgraded(student);
                    }
                    else if(studoption==5){
                        course.viewcomm();
                    }
                    else if(studoption==6){
                        System.out.println("enter a comment: ");                        
                        String comm=s.nextLine();
                        course.addcomm(comm, student);
                    }
                    else if(studoption==7){
                        break;
                    }
                    else{
                        System.out.println("Wrong option");
                    }
                    System.out.println();
                }
            }  
            else if(userid==3){
                return;
            }  
            else{
                System.out.println(" Wrong option ");
            } 
            System.out.println();       
        }
    }    
}

public class ap_ass2 {
    public static void main(String[] args) {
        BackPack bp=new BackPack();
        bp.start();
    }
}    