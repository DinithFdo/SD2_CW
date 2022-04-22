package Task3;

public class Passenger{
    String fName;
    String sName;
    double expenses;
    Passenger[] passenger;
    int passengerNo = 1;

    Passenger(){
    }

    public void setFName(String fName){
        this.fName = fName;
    }

    public void setSName(String sName){
        this.sName = sName;
    }

    public void setExpenses(double expenses){
        this.expenses = expenses;
    }

    public double getExpenses(){
        return expenses;
    }

    public String getName(){
        String name = fName +" "+ sName;
        return name;
    }

    public String getWelcome(){
        String name = "Welcome aboard: "+fName+ " "+sName;
        return name;
    }

    public String getOutput(int noPassenger){
        String passengers = "";
        passengers = "passenger "+(noPassenger+1)+": "+fName+" "+sName+" - "+expenses;
        return passengers;
    }


}