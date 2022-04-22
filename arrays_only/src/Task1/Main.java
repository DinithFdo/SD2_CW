package Task1;

import java.io.*;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        //initializes all cabins by checking cabin status into empty
        initializeCabins(cabins,cabinIsEmpty);

        while(true){
            displayMenu();//displays the menu
            chkUserResponse();//checks for responses to catergorize them to the tasks
            boolean doQuit;

            while(true){

                System.out.print("Do you want to return to menu(y/n): ");// asks user whether to return to menu or quit the program
                String userResponse = input.next();

                if(userResponse.equalsIgnoreCase("y")){
                    doQuit = false;//the do quit variable is set to false
                    break;
                }else if(userResponse.equalsIgnoreCase("n")){
                    doQuit = true;//the do quit variable is set to true
                    break;
                }else{
                    System.out.println("Invalid Response !");
                }
            }

            //if the doQuit variable is true the program ends
            if (doQuit){
                break;
            }
        }
    }

    public static String[] cabins = new String[12];//setting up an String array called as cabins
    public static boolean[] cabinIsEmpty = new boolean[12]; //setting up an boolean array to check the cabin status

    //takes in string array of cabins and boolean array of cabinEmpty as parameters and initializes them;
    public static void initializeCabins(String[] allCabins, boolean[] cabinEmpty){
        for(int i=0;i< cabins.length;i++){
            allCabins[i]="Empty Cabin";
            cabinEmpty[i] = true;
        }
    }

    //This method displays the menu
    public static void displayMenu(){
        String[] menuItems = {"A: Add to cabin","V: View all Cabins","E: Display Empty Cabins","D: Delete Customer From Cabin","F: Find Cabin From Customer Name","S: Store Program Data Into File","L: Load Program Data From File","O: View Passengers Ordered Alphabetically By Name","Q: Quit the Application"};
        System.out.println();
        System.out.println("...............................Task1.Main Menu..................................");
        System.out.println();
        for(int i =0;i< menuItems.length;i++){
            System.out.println(menuItems[i]);
        }
        System.out.println();
    }

    //checks for the user response to see which method is to be performed
    public static void chkUserResponse(){
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.print("What function do you want to execute(letter): ");
            String usrResponse = input.next();

            //These are the different types of conditionals to check the user input at the menu;
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
                deleteCustomer();
                break;
            } else if (usrResponse.equalsIgnoreCase("f")) {
                findCustomer();
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
            }else if(usrResponse.equalsIgnoreCase("q")){
                break;
            } else {
                System.out.println("Response Invalid !");
            }
        }
    }

    //checks all the cabins and returns whether the cabin is empty or full;
    public static boolean isCabinEmpty(boolean[] cabinEmpty){
        for(int i=0;i< cabinIsEmpty.length;i++){
            if(cabinEmpty[i]){
                return true;
            }
        }
        return false;
    }

    //this function is used to add passengers to the cabin;
    public static void addToCabin(){
        while(true){
            try {
                if (isCabinEmpty(cabinIsEmpty)) { //checks to see if the cabins are empty and available
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Enter the cabin number(1-12): ");
                    int cabinNum = scan.nextInt() - 1;
                    scan.nextLine();//to consume the scan.nextInt();
                    if (cabinIsEmpty[cabinNum]) { //checks to see if the user inputted cabin is empty or not
                        System.out.print("Enter Passenger Name: ");
                        String passengerName = scan.nextLine();//takes in the passenger name
                        while (passengerName.equals("")) { //checks whether the passenger name is not entered !
                            System.out.println("Passenger name cannot be empty !");
                            System.out.print("Enter the name of passenger: ");
                            passengerName = scan.nextLine();
                        }
                        cabins[cabinNum] = passengerName; //sets the cabin array index to be equal to passenger name
                        cabinIsEmpty[cabinNum] = false; // sets the cabin array index as occupied !
                        System.out.println("Welcome aboard: " + cabins[cabinNum]);
                        break;
                    } else {
                        System.out.println("The cabin you entered is occupied");
                    }
                }else{
                    System.out.println("All the cabins are occupied");
                    break;
                }
            } catch (Exception e ){
                System.out.println("Please enter an valid cabin number from (1-12)");
                System.out.println();
            }
        }
    }

    //displays all the cabins
    public static void viewAllCabins(){
        for (int i =0;i< cabins.length;i++){
            int cabinNo = i+1;
            System.out.println("Cabin "+cabinNo+": "+cabins[i]);
        }
    }

    //displays all the cabins which are empty
    public static void displayEmpty(){
        for(int i=0;i<cabins.length;i++){
            int cabinNo = i+1;
            if(cabins[i].equals("Empty Cabin")){
                System.out.println("Cabin no "+cabinNo+" is empty");
            }
        }
    }
    //This method return a false if there is even one occupied cabin
    public static boolean isFalseThere(boolean[] cabinIsEmpty){
        boolean empty = true;
        boolean seachVal = false;

        for(boolean x:cabinIsEmpty){
            if(x == seachVal){
                empty = false;
                break;
            }
        }
        return empty;
    }

    //deletes the cabin user requests
    public static void deleteCustomer(){

        if(isFalseThere(cabinIsEmpty)){ //checks to see whether there is an even one empty cabin
            System.out.println("All the cabins are empty !");
            return;
        }

        int removePassengerNum;
        int cabinIndex;

        viewAllCabins();

        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.println();
                System.out.print("Select the cabin number of the customer to remove: ");
                removePassengerNum = input.nextInt();
                cabinIndex = removePassengerNum - 1;
                if(cabins[cabinIndex].equals("Empty Cabin")){
                    System.out.println("The cabin is already empty !");
                    break;
                }
                System.out.println(cabins[cabinIndex] + " is removed from the cabin");
                cabins[cabinIndex] = "Empty Cabin";
                cabinIsEmpty[cabinIndex] = true;
                break;

            } catch (InputMismatchException exception) {
                System.out.println("Select a valid cabin number ! ");
            }catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Select a valid cabin number ! ");
            }
        }
    }
    //this method uses the name of the passenger to give out the cabin no
    public static void findCustomer(){

        if(isFalseThere(cabinIsEmpty)){ //checks to see whether there is an even one empty cabin
            System.out.println("All the cabins are empty !");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Enter Customer Name: ");
        String passengerName = input.nextLine();
        boolean findPassenger = false;

        //uses this for loop to go through the name array to search for the name
        for(int i=0;i< cabins.length;i++){
            if (cabins[i].equalsIgnoreCase(passengerName) ){
                System.out.println(cabins[i]+" is in cabin "+(i+1));
                findPassenger = true;
                break;
            }
        }

        //checks to see for the boolean value if the passenger was found
        if(!findPassenger){
            System.out.println("Sorry there's no such passenger");
        }

    }

    // writes the cabin details into a data file/text file
    public static void storeDataFile(){
        try{
            FileWriter writer = new FileWriter("cabinDetails.txt");

            for(int i =0;i< cabins.length;i++){
                String saveData = cabins[i];
                int cabinNum = i+1;
                String output = "Cabin "+cabinNum+": "+saveData;
                writer.write(output+"\n");

            }

            writer.close();
            System.out.println("Data saved successfully");

        }catch (IOException e) {
            System.out.println("An Error Occurred While Writing to the file");
        }

    }

    //reads and display the data from the text file
    public static void loadDataFile(){
        File inputFile = new File("cabinDetails.txt");
        String fileLine;

        try{
            Scanner readFile = new Scanner(inputFile);
            while (readFile.hasNext()){
                fileLine = readFile.nextLine();
                System.out.println(fileLine);
            }

            readFile.close();

        }catch (IOException e){
            System.out.println("Unable to read the file");
        }

    }

    //displays all the cabin names in alphabetical order using bubble sort
    public static void viewInAlphabetical(){

        if(isFalseThere(cabinIsEmpty)){
            System.out.println("All the cabins are still empty !");
            return;
        }

        for(int i = 0;i<cabins.length;i++){
            for(int j =1;j< cabins.length;j++){
                if(cabins[j].compareToIgnoreCase(cabins[j-1])<0){
                    String temp = cabins[j];
                    cabins[j] = cabins[j-1];
                    cabins[j-1]=temp;
                }
            }
        }

        for (String cabin : cabins) {
            if (!cabin.equals("Empty Cabin")) {
                System.out.println(cabin);
            }
        }

    }
}
