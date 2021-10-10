import java.util.*;

class Vaccine{
    private final String vaccname;
    private int doses, gap;

    Vaccine(String name, int doses, int gap){
        this.vaccname=name;
        this.doses=doses;
        this.gap=gap;
    }
    
    void displayvaccine(){
        System.out.println("Vaccine name: " + vaccname+ ", Number of doses: "+ doses+ ", gap between doses: "+ gap);
    }  

    String getname(){
        return vaccname;
    }

    int getdoses(){
        return doses;
    }

    int getgap(){
        return this.gap;
    }
}

class Hospital{
    private static int hoscount=0;
    private final String hospitalname;
    private final String hospitalpincode;
    private String hospitalid;
    private int hospitalidnumber;
    private ArrayList<Slots> hospitalslot = new ArrayList<>();

    Hospital(String name, String pin){
        this.hospitalname=name;
        this.hospitalpincode=pin;
        this.hospitalidnumber=hoscount;
        this.hospitalid=String.valueOf(hoscount);
        while (hospitalid.length()<6) {
            hospitalid="0"+hospitalid;
        }
        hoscount++;
    }

    ArrayList<Slots> gethosslotarray(){
        return hospitalslot;
    }

    boolean hasslots(String vaccreq){
        for (Slots sl : hospitalslot) {
            if (sl.getvacc().equals(vaccreq) && !sl.isempty()) {
                return true;
            }
        }
        return false;
    }

    String gethosname(){
        return hospitalname;
    }

    String gethospin(){
        return hospitalpincode;
    }

    String gethosid(){
        return hospitalid;
    }

    int getinthosid(){
        return hospitalidnumber;
    }

    void displayhospital(){
        System.out.println("hospital name: "+ hospitalname+ ", pincode: "+ hospitalpincode+ ", unique id: "+ hospitalid);
    }

    void addslot(int day, int quantity, String vaccine){
        hospitalslot.add(new Slots(day, quantity, vaccine));
    }

    void printslots(String vname){
        for (int i = 0; i < hospitalslot.size(); i++) {
            if (vname.equals(hospitalslot.get(i).getvacc())) {
                System.out.println(i+ ". day: "+ hospitalslot.get(i).getday()+
                ", vaccine: "+ hospitalslot.get(i).getvacc()+", qty: "+ hospitalslot.get(i).getquan());
            }            
        }
    }

    void printslots(){
        for (int i = 0; i < hospitalslot.size(); i++) {
            System.out.println(i+ ". day: "+ hospitalslot.get(i).getday()+
            ", vaccine: "+ hospitalslot.get(i).getvacc()+", qty: "+ hospitalslot.get(i).getquan());
        }
    }
}
class Citizen{
    private final String citizenname;
    private final int age;
    private final String citizenid;
    private String vaccinestatus;
    private String vaccgiven;
    private int numofdoses=0;
    private int nextduedate=-1;

    Citizen(String name, int age, String id){
        this.citizenname=name;
        this.age=age;
        this.citizenid=id;
        if (age>18) {
            this.vaccinestatus="REGISTERED";
        }
    }

    void setduedate(int date){
        nextduedate=date;
    }

    int getduedate(){
        return nextduedate;
    }

    String getcitname(){
        return citizenname;
    }

    int getcitage(){
        return age;
    }

    String getcitid(){
        return citizenid;
    }

    int getnumofdoses(){
        return numofdoses;
    }

    void setvaccinestatus(String status){
        vaccinestatus=status;
    }

    void incrementnumofdose(){
        this.numofdoses++;
    }

    void displaycitizen(){
        System.out.println("citizen name: "+ citizenname+ ", age: "+ age+ ", unique id: "+ citizenid);
        if (age<=18) {
            System.out.println("Only above 18 are allowed.");
        }
    }

    String getvaccgiven(){
        return vaccgiven;
    }

    void setvacc(String vacc){
        vaccgiven=vacc;
    }

    String getstat(){
        return vaccinestatus;
    }

    String getvaccstatus(){
        if (numofdoses==0) {
            vaccinestatus="REGISTERED";
        }
        return vaccinestatus;
    }

    String getvaccstatus(Vaccine vacc){        
        if(numofdoses<vacc.getdoses()){
            vaccinestatus="PARTIALLY VACCINATED";
        }
        if(numofdoses==vacc.getdoses()){
            vaccinestatus="FULLY VACCINATED";
        }
        return vaccinestatus;
    }
}

class Slots{
    private int daynum, quantity;
    private final String vacc;

    Slots(int day, int quan, String vaccine){
        this.daynum=day;
        this.quantity=quan;
        this.vacc=vaccine;
    }

    int getday(){
        return daynum;
    }

    int getquan(){
        return quantity;
    }

    String getvacc(){
        return vacc;
    }

    void usevaccine(){
        this.quantity--;
    }

    boolean isempty(){
        if (quantity==0) {
            return true;
        }
        return false;
    }
}

class Covin{
    private ArrayList<Hospital> hospitalarray= new ArrayList<>();
    private ArrayList<Vaccine> vaccinearray= new ArrayList<>();
    private ArrayList<Citizen> patientarray= new ArrayList<>();

    void addvacc(String name, int doses, int gap){
        Vaccine newvacc= new Vaccine(name, doses, gap);
        vaccinearray.add(newvacc);
        newvacc.displayvaccine();
    }

    void addhos(String name, String pin){
        Hospital newhos= new Hospital(name, pin); 
        hospitalarray.add(newhos);
        newhos.displayhospital();
    }

    void addcitizen(String name, int age, String id){
        Citizen newcit= new Citizen(name, age, id);
        if (newcit.getcitage()>18) {
            patientarray.add(newcit);
        }
        newcit.displaycitizen();    
    }

    void createslot(String id, int numofslots){
        Scanner s=new Scanner(System.in);
        boolean flag=true;
        Hospital chosenhosp=null;
        for (Hospital hi1 : hospitalarray) {
            if (id.equals(hi1.gethosid())) {
                chosenhosp=hi1;
                flag=false;
                break;
            }
        }

        if (flag) {
            System.out.println("Inavlid hospital id");
            return;
        }

        for (int i = 0; i < numofslots; i++) {
            System.out.println("enter day number: ");
            int day=s.nextInt();
            System.out.println("enter quantity: ");
            int quan= s.nextInt();
            System.out.println("select vacc: ");
            for (int j = 0; j < vaccinearray.size(); j++) {
                System.out.println(j+ ". "+ vaccinearray.get(j).getname());
            }
            int choice=s.nextInt();
            chosenhosp.addslot(day, quan, vaccinearray.get(choice).getname());
            System.out.println("slot added by hospital "+ id+ " for day: "+ day+ ", available quantity: "+ quan+
            " of vaccine "+ vaccinearray.get(choice).getname());
        }

    }

    void bookslotpin(String id, String pin){
        Scanner s=new Scanner(System.in);
        Citizen chosencit=null;
        boolean flag1=true, flag2=true;

        for (Citizen ci : patientarray) {
            if (ci.getcitid().equals(id)) {
                chosencit=ci;
                flag1=false;
                break;
            }
        }

        if (flag1) {
            System.out.println("Invalid Citizen ID");
            return;
        }

        for (Hospital hi : hospitalarray) {
            if (pin.equals(hi.gethospin())) {
                System.out.println(hi.gethosid()+" "+ hi.gethosname());
                flag2=false;
            }
        }

        if (flag2) {
            System.out.println("Invalid Code");
            return;
        }

        System.out.println("Enter Hospital ID: ");
        String hosid=s.next();

        bookslothelp(hosid, chosencit, 1, " ");
    }

    void bookslotvacc(String id, String vacc){
        Scanner s=new Scanner(System.in);
        Citizen chosencit=null;
        boolean flag1=true, flag2=true;

        for (Citizen ci : patientarray) {
            if (ci.getcitid().equals(id)) {
                chosencit=ci;
                flag1=false;
                break;
            }
        }

        if (flag1) {
            System.out.println("Invalid Citizen ID");
            return;
        }
        for (Hospital hos: hospitalarray) {
            for (Slots sl : hos.gethosslotarray()) {
                if(vacc.equals(sl.getvacc())){
                    System.out.println(hos.gethosid()+ " "+ hos.gethosname());
                    flag2=false;
                }
            }
        }

        if (flag2) {
            System.out.println("Invalid Vaccine Name");
            return;
        }
        
        System.out.println("Enter Hospital ID: ");
        String hosid=s.next();

        bookslothelp(hosid, chosencit, 2, vacc);
    }

    void bookslothelp(String hosid, Citizen chosencit, int booktype, String vaccname){
        Scanner s=new Scanner(System.in);
        boolean flag=true;
        Hospital chosenhos=null;

        for (Hospital h2 : hospitalarray) {
            if (h2.gethosid().equals(hosid)) {
                chosenhos=h2;
                flag=false;
            }
        }

        for (Slots s2 : chosenhos.gethosslotarray()) {
            if (s2.getday()<chosencit.getduedate()) {
                System.out.println("No slots available");
                return;
            }
        }

        if (flag) {
            System.out.println("Hospital does not exist");
            return;
        }      
        if (booktype==1) {
            chosenhos.printslots();
        }
        if (booktype==2) {
            chosenhos.printslots(vaccname);
        }
        System.out.println("Choose slot: ");
        int sloti=s.nextInt();

        if (sloti>chosenhos.gethosslotarray().size()) {
            System.out.println("Wrong input");
            return;
        }

        Slots slotchosen= chosenhos.gethosslotarray().get(sloti);
        Vaccine chosenvacc=null;

        for (Vaccine vi : vaccinearray) {
            if (slotchosen.getvacc().equals(vi.getname())) {
                chosenvacc=vi;
                break;
            }
        }

        if (!chosenhos.hasslots(chosenvacc.getname())) {
            System.out.println("No slots available");
            return;
        }
        
        if (chosencit.getnumofdoses()>=chosenvacc.getdoses()) {
            System.out.println("Already Fully Vaccinated");
            return;
        }else {
            if (chosencit.getnumofdoses()==0) {
                chosencit.incrementnumofdose();
                chosencit.setvaccinestatus("PARTIALLY VACCINATED");
                chosencit.setvacc(chosenvacc.getname());
            }else {
                if(chosencit.getvaccgiven().equals(chosenvacc.getname())){
                    chosencit.incrementnumofdose();
                }else{
                    System.out.println("Same vaccine is mandatory");
                    return;
                }
            }
            slotchosen.usevaccine();       
        }

        System.out.println(chosencit.getcitname()+ " vaccinated with "+ chosenvacc.getname());
        chosencit.setduedate(slotchosen.getday()+chosenvacc.getgap());
    }


    void checkslot(String id){
        Hospital chosenhos=null;
        boolean flag=true;

        for (Hospital hospital : hospitalarray) {
            if (id.equals(hospital.gethosid())) {
                chosenhos=hospital;
                flag=false;
                break;
            }
        }

        if (flag) {
            System.out.println("Invalid Hospital id");
            return;
        }

        chosenhos.printslots();
    }

    void checkvacstat(String id){
        Citizen chosencit=null;
        boolean flag=true;

        for (Citizen ci : patientarray) {
            if (ci.getcitid().equals(id)) {
                chosencit=ci;
                flag=false;
                break;
            }
        }
        if (flag) {
            System.out.println("id does not exist");
        }
        if (chosencit.getvaccstatus().equals("REGISTERED")) {
            System.out.println("Citizen REGISTERED");
            return;
        }

        Vaccine chosenvacc=null;
        for (Vaccine v1 : vaccinearray) {
            if (chosencit.getvaccgiven().equals(v1.getname())) {
                chosenvacc=v1;                
                break;
            }
        } 
        System.out.println(chosencit.getvaccstatus(chosenvacc));
        
        if (chosencit.getvaccstatus(chosenvacc).equals("FULLY VACCINATED")) {
            System.out.println("Vaccine given: "+ chosencit.getvaccgiven());
            System.out.println("number of doses given: "+ chosencit.getnumofdoses());
        }
        else if(chosencit.getvaccstatus(chosenvacc).equals("PARTIALLY VACCINATED")){
            System.out.println(" vaccine given: "+ chosencit.getvaccgiven());
            System.out.println("number of doses given: "+ chosencit.getnumofdoses());
            System.out.println("Next dose due date: "+ chosencit.getduedate());
        }
    }
}


class Cowin_Portal{

    public void start(){
        Scanner s=new Scanner(System.in);
        Covin covinportal=new Covin();
        String menu= "1. Add Vaccine\n" +
        "2. Register Hospital\n" +
        "3. Register Citizen\n"+
        "4. Add Slot for Vaccination\n"+
        "5. Book Slot for Vaccination\n"+
        "6. List all slots for a hospital\n"+
        "7. Check Vaccination Status\n"+
        "8. Exit";
        System.out.println("COVIN Portal initialised...\n...................");

        while (true) {
            System.out.println(menu);
            int choice=s.nextInt();

            if (choice==1) {
                System.out.println("Vaccine name: ");
                String name=s.next();
                System.out.println("Number of doses: ");
                int dose=s.nextInt();
                int gap;
                if (dose==1) {
                    gap=0;
                }else{
                    System.out.println("Gap between Doses: ");
                    gap=s.nextInt();
                }                
                covinportal.addvacc(name, dose, gap);
                System.out.println("...................");
            }
            else if(choice==2){
                System.out.println("Hospital Name: ");
                String hosname=s.next();
                System.out.println("Pincode: ");
                String pin=s.next();
                covinportal.addhos(hosname, pin);
                System.out.println("...................");
            }
            else if(choice==3){
                System.out.println("Citizen Name: ");
                String citname=s.next();
                System.out.println("Age: ");
                int age=s.nextInt();
                System.out.println("Unique ID: ");
                String citid=s.next();
                covinportal.addcitizen(citname, age, citid);
                System.out.println("...................");
            }
            else if(choice==4){
                System.out.println("Enter Hospital ID");
                String hosid=s.next();
                System.out.println("Enter number of slots: ");
                int numslots=s.nextInt();
                covinportal.createslot(hosid, numslots);
                System.out.println("...................");
            }
            else if(choice==5){
                System.out.println("Enter Patient Unique ID: ");
                String id=s.next();
                System.out.println("1. Search by area\n2. Search by Vaccine\n3. Exit");
                System.out.println("Enter option: ");
                int insearch=s.nextInt();
                if (insearch==1) {
                    System.out.println("Enter Pincode: ");
                    String pin=s.next();                    
                    covinportal.bookslotpin(id, pin);
                }
                else if (insearch==2) {
                    System.out.println("Enter vaccine name: ");
                    String vacc4=s.next();
                    covinportal.bookslotvacc(id, vacc4);
                }
                else if(insearch==3){
                    continue;
                }
                else{
                    System.out.println("Wrong input");
                }

                System.out.println("...................");
            }
            else if(choice==6){
                System.out.println("Enter hospital id: ");
                String hosid=s.next();
                covinportal.checkslot(hosid);
                System.out.println("...................");
            }
            else if(choice==7){
                System.out.println("Enter patient id: ");
                String pid=s.next();
                covinportal.checkvacstat(pid);
                System.out.println("...................");
            }
            else if(choice==8){
                System.out.println("End of Test Case");
                break;
            }else{
                System.out.println("Wrong option");
            }
        }
    }
}

public class ap_ass1{
    public static void main(String[] args) {
        Cowin_Portal portal = new Cowin_Portal();
        portal.start();
    }
}