package Task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{

    static Scanner input = new Scanner(System.in);


    public static Cabin[] cabins = new Cabin[12];


    public static void main(String []args){

        //initializing the cabins
        for(int i =0;i<cabins.length;i++){
            cabins[i] = new Cabin(true);
        }

        //initializing the names
        for(int i=0;i<cabins.length;i++){
            for(int j=0;j<3;j++){
                cabins[i].names[j] = "Passenger "+(j+1)+" is unavailable";
            }
        }

        while(true){
            boolean doQuit;
            displayMenu();
            chkUserResponse();

            while(true){
                input.nextLine();
                System.out.print("Do you want to return to menu(y/n): ");
                String userResponse = input.next();

                if(userResponse.equalsIgnoreCase("y")){
                    doQuit = false;
                    break;
                }else if(userResponse.equalsIgnoreCase("n")){
                    doQuit = true;
                    break;
                }else{
                    System.out.println("Invalid Response !");
                }
            }

            if (doQuit){
                break;
            }
        }


    }


    //checks whether all the cabins are full
    public static boolean isCabinEmpty(){
        for(int i = 0;i< cabins.length;i++){
            if(cabins[i].isCabinEmpty){
                return true;
            }
        }
        return false;
    }

    //displays the menu
    public static void displayMenu(){
        String[] menuItems = {"A: Add to cabin","V: View all Cabins","E: Display Empty Cabins","D: Delete Customer From Cabin","F: Find Cabin From Customer Name","S: Store Program Data Into File","L: Load Program Data From File","O: View Passengers Ordered Alphabetically By Name","T: Display Expenses","Q: Quit the Application"};
        System.out.println();
        System.out.println("...............................Task2..Main Menu..................................");
        System.out.println();
        for(int i =0;i< menuItems.length;i++){
            System.out.println(menuItems[i]);
        }
        System.out.println();

    }

    //checks for user response and classifies them accordingly
    public static void chkUserResponse(){
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.print("What function do you want to execute(letter): ");
            String usrResponse = input.next();

            if (usrResponse.equalsIgnoreCase("a")) {
                addToCabin();
                break;
            } else if (usrResponse.equalsIgnoreCase("v")) {
                viewAllCabins();
                break;
            } else if (usrResponse.equalsIgnoreCase("e")) {
                displayEmpty();
                break;
            } else if (usrResponse.equalsIgnoreCase("d")) {
                deleteCabin();
                break;
            } else if (usrResponse.equalsIgnoreCase("f")) {
                findPassenger();
                break;
            } else if (usrResponse.equalsIgnoreCase("s")) {
                storeDataFile();
                break;
            } else if (usrResponse.equalsIgnoreCase("l")) {
                loadDataFile();
                break;
            } else if (usrResponse.equalsIgnoreCase("o")) {
                viewInAlphabetical();
                break;
            } else if(usrResponse.equalsIgnoreCase("t")) {
                displayExpenses();
                break;
            } else if(usrResponse.equalsIgnoreCase("q")){
                break;
            } else {
                System.out.println("Response Invalid !");
            }
        }
    }

    public static double totalExpenses;

    public static Passenger p = new Passenger();

    public static void addToCabin(){

        while(true){
            try{
                if(isCabinEmpty()){
                    System.out.print("Enter the cabin number[1-12]: ");
                    int cabinNum = input.nextInt() -1;

                    if(cabins[cabinNum].getCabinStatus()){
                        System.out.print("How many passengers in cabin[1-3]: ");
                        int memberNum = input.nextInt();//takes in the number of passengers in the cabin
                        cabins[cabinNum].initialize();

                        while (!(memberNum>=1 && memberNum<=3)){//checks to see if the member count is in the correct range
                            try{
                                System.out.println();
                                System.out.println("Please enter the passenger num(1-3) !");
                                System.out.print("How many passengers in cabin[1-3]: ");
                                memberNum = input.nextInt();

                            }catch(InputMismatchException e){
                                System.out.println("Enter ");
                            }
                        }


                        for (int i = 0; i < memberNum; i++) {

                            System.out.println();
                            System.out.println("Passenger No: " + (i + 1));

                            input.nextLine();// to consume input.nextInt()
                            System.out.print("Enter Your First Name: ");
                            String fName = input.nextLine();
                            while (fName.equals("")) {
                                System.out.println("First Name cannot be empty !");
                                System.out.print("Enter Your First Name: ");
                                fName = input.nextLine();
                            }
                            System.out.print("Enter your SurName: ");
                            String sName = input.nextLine();
                            while (sName.equals("")) {
                                System.out.println("Surname cannot be empty !");
                                System.out.print("Enter your SurName: ");
                                sName = input.nextLine();
                            }

                            try {
                                System.out.print("Enter your expense: ");
                                int expenses = input.nextInt();
                                p.setExpenses(expenses);
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter your expense correctly !");
                                return;
                            }

                            p.setFName(fName); //sets the passenger firstname as the input
                            p.setSName(sName); //sets the passenger secondname as the input


                            p.getWelcome();// prints out the user welcome

                            cabins[cabinNum].setName(i, p.getName()); //stores the names in the relevant cabins
                            cabins[cabinNum].setPassenger(i, p.getOutput(i)); //stores the passenger get output in the cabins
                            cabins[cabinNum].setExpenses(i, p.getExpenses()); //stores the passenger get expenses into the cabins
                            totalExpenses = totalExpenses + p.getExpenses(); //sets the total expenses

                            System.out.println("....................................................................");

                        }
                        //prints out the cabin details after inputting the values
                        for(int x = 0;x<3;x++){
                            System.out.println(cabins[cabinNum].passengerString[x]);
                        }


                        cabins[cabinNum].setIsCabinEmpty(false);//sets the relevant cabin number boolean to false to indicate the cabin is occupied
                        System.out.println();
                        input.nextLine();
                        break;

                    }else{
                        System.out.println("The cabin you entered is occupied !");
                    }
                }else{
                    System.out.println("All cabins are full !");
                    break;
                }

            }catch(InputMismatchException e){
                System.out.println("Please enter a valid cabin number(1-12)");
                input.next();
            }catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Please enter a valid cabin number(1-12)");
            }
        }
    }

    //prints out all the cabins
    public static void viewAllCabins(){
        for(int i = 0;i<cabins.length;i++){
            System.out.println("Cabin number: "+(i+1));
            for(int j = 0;j<3;j++){
                if(cabins[i].passengerString[j]==null){
                    System.out.println("Passenger "+(j+1)+": Unavailable");
                }else{
                    System.out.println(cabins[i].passengerString[j]);
                }
            }
            System.out.println(".................................");
        }
    }

    //displays all the empty cabins
    public static void displayEmpty(){
        for(int i = 0;i< cabins.length;i++){
            if(cabins[i].isCabinEmpty){
                System.out.println("Cabin "+(i+1)+" is empty");
            }
        }
    }

    //This method return a false if there is even one occupied cabin
    public static boolean isFalseThere(){
        boolean empty = true;

        for(int i = 0;i< cabins.length;i++){
            if(!cabins[i].isCabinEmpty){
                empty = false;
            }
        }

        return empty;
    }

    //deletes a cabin
    public static void deleteCabin(){
        if(isFalseThere()){
            System.out.println("All the cabins are still empty !");
            return;
        }

        viewAllCabins();
        while(true) {
            try {
                System.out.print("Enter the cabin number to delete: ");
                int cabinNo = input.nextInt() - 1;

                if (cabins[cabinNo].isCabinEmpty) {
                    System.out.println("This cabin is already empty !");
                } else if (!cabins[cabinNo].isCabinEmpty) {
                    for (int i = 0; i < 3; i++) {
                        cabins[cabinNo].passengerString[i] = null;
                        cabins[cabinNo].names[i] = "Passenger " + (i + 1) + " is unavailable";
                        totalExpenses = totalExpenses - cabins[cabinNo].expenses[i];
                        cabins[cabinNo].setExpenses(i, 0);
                    }

                    System.out.println("Cabin " + (cabinNo + 1) + " successfully deleted !");
                    cabins[cabinNo].isCabinEmpty = true;
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid cabin num(1-12) !");
                input.nextLine();
            } catch(ArrayIndexOutOfBoundsException e){
                System.out.println("Please enter a valid cabin num(1-12) !");
                input.nextLine();
            }
        }
    }

    //method used to find passengers
    public static void findPassenger(){

        if(isFalseThere()){
            System.out.println("All the cabins are empty still !");
            return;
        }
        System.out.print("Enter customer name: ");
        String name = input.nextLine();
        boolean findPassenger = false;

        for(int i = 0;i< cabins.length;i++){
            for(int j=0;j<3;j++){
                if(cabins[i].names[j].equalsIgnoreCase(name)){
                    System.out.println("Passenger is in cabin: "+(i+1));
                    findPassenger = true;
                    break;
                }
            }
        }

        if(!findPassenger){
            System.out.println("Sorry there's no such passenger");
        }
    }

    //used to store cabin details into a text file
    public static void storeDataFile(){
        try{
            FileWriter writer = new FileWriter("cabinDetails.txt");
            for(int i = 0;i<cabins.length;i++){
                writer.write("Cabin number: "+(i+1)+"\n");
                for(int j = 0;j<3;j++){
                    if(cabins[i].passengerString[j]==null){
                        writer.write("Passenger "+(j+1)+": Unavailable"+"\n");
                    }else{
                        writer.write(cabins[i].passengerString[j]+"\n");
                    }
                }
                writer.write("................................."+"\n");
            }
            writer.close();
            System.out.println("Data Saved Successfully !");


        }catch(IOException e){
            System.out.println("An error occurred when writing to file !");
        }
    }

    //loads the saved text file back into the program
    public static void loadDataFile(){
        File inputFile = new File("cabinDetails.txt");
        String fileLine;

        try{
            Scanner readFile = new Scanner(inputFile);
            while(readFile.hasNext()){
                fileLine = readFile.nextLine();
                System.out.println(fileLine);
            }

            readFile.close();

        }catch(IOException e){
            System.out.println("Unable to read the file !");
        }
    }

    public static String[] allNames = new String[36];//allnames array is used to collect the names and to use it to check the order

    //method used to view in alphabetical order
    public static void viewInAlphabetical(){

        if(isFalseThere()){
            System.out.println("All the cabins are empty !");
        }

        for(int i=0;i< allNames.length;i++){
            allNames[i] = "empty";
        }

        int x= 0;
        while(x<36) {
            for (int i = 0; i < cabins.length; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!cabins[i].names[j].equals("Passenger "+(j+1)+" is unavailable")) {
                        allNames[x] = (cabins[i].names[j]);
                    }
                    x++;
                }
            }
        }

        for(int i=0;i< allNames.length;i++){
            if(!allNames[i].equals("empty")){
                for(int j =1;j< allNames.length;j++){
                    if(allNames[j].compareToIgnoreCase(allNames[j-1])<0){
                        String temp = allNames[j];
                        allNames[j] = allNames[j-1];
                        allNames[j-1] = temp;
                    }
                }
            }
        }

        for(int i = 0;i< allNames.length;i++){
            if(!allNames[i].equals("empty")) {
                System.out.println(allNames[i]);
            }
        }



    }

    //displays expenses
    public static void displayExpenses(){

        if(isFalseThere()){
            System.out.println("All cabins are still empty !");
            return;
        }

        for(int i = 0;i< cabins.length;i++){
            System.out.println("Cabin No: "+(i+1));
            for(int j =0;j<3;j++){
                System.out.println("Passenger "+(j+1)+" Expenses: "+cabins[i].expenses[j]);
            }
            System.out.println("....................................................");
        }


        System.out.println("Total Expenses: "+totalExpenses);
        System.out.println();

    }

}