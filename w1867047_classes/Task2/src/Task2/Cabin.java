package Task2;

public class Cabin{

    boolean isCabinEmpty;
    String[] passengerString = new String[3];
    String[] names = new String[3];
    double[] expenses = new double[3];


    public Cabin(boolean isCabinEmpty){
      this.isCabinEmpty = isCabinEmpty;
    }

    public boolean getCabinStatus(){
        return isCabinEmpty;
    }

    public void setPassenger(int i,String output){
        passengerString[i] = output;
    }

    public void setIsCabinEmpty(boolean isCabinEmpty){
        this.isCabinEmpty = isCabinEmpty;
    }

    public void initialize(){
        for(int i=0;i<3;i++){
            passengerString[i]= "Passenger "+(i+1)+": Unavailable";
        }
    }

    public void setName(int i,String name){
        names[i] = name;
    }

    public void setExpenses(int i,double expenses){
        this.expenses[i] = expenses;
    }

}