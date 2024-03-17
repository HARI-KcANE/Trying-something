import java.util.Scanner;


public class Main {

    static Ticket[] ticketInfoArray = new Ticket[52]; //Creating static array type Ticket of size 52
    static int ticketSoldCount = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the Plane Management application");
        int[][] seating = new int[4][]; // 4 rows
        seating[0] = new int[14]; // Row A has 14 columns
        seating[1] = new int[12]; // Row B has 12 columns
        seating[2] = new int[12]; // Row C has 12 columns
        seating[3] = new int[14]; // Row D has 14 columns


        // Explicitly Set each element to 0
        for (int i = 0; i < seating.length; i++) {
            for (int j = 0; j < seating[i].length; j++) {
                seating[i][j] = 0;
                System.out.print(seating[i][j]); // printing current seating plan
            }
            System.out.println();
        }
        boolean condition = true;
        Scanner input = new Scanner(System.in); //user Input to select option
        //Display Menu
        menu();

        //To run the program until condition returns false
        while (condition) {
            String decision = inputOption();
            switch (decision) { //switch case control structure instead of IF
                case "0":
                    System.out.println("Exiting program");
                    condition = false;
                    input.close();
                    break;
                case "1":
                    System.out.println("Lets buy a seat");
                    buy_seat(seating);
                    System.out.println("\n");
                    menu();
                    break;
                case "2":
                    System.out.println("Lets cancel a seat");
                    cancel_seat(seating);
                    System.out.println("\n");
                    menu();
                    break;
                case "3":
                    System.out.println("Lets fine the first available seat");
                    find_first_available(seating);
                    System.out.println("\n");
                    menu();
                    break;
                case "4":
                    System.out.println("Lets see the seating plan");
                    seating_plan(seating);
                    System.out.println("\n");
                    menu();
                    break;
                case "5":
                    System.out.println("Lets Print tickets information and sales");
                    print_tickets_info();
                    System.out.println("\n");
                    menu();
                    break;
                case "6":
                    System.out.println("Search ticket");
                    search_ticket(seating);
                    System.out.println("\n");
                    menu();
                    break;
                default:
                    System.out.println("enter a reasonable number");
                    System.out.println("\n");
                    menu();
            }
        }
    }

    public static String inputOption() {
        boolean cond = true;

        String decision = null;
        while (cond) {
            decision = new Scanner(System.in).nextLine();
            if (!decision.matches("[0-6]")) {
                System.out.println("invalid option try again");
                menu();
            } else {
                cond = false;
            }
        }
        return decision;
    } //Prompting user input for selecting menu option

    public static String numToRow(int num) { //Used to return corresponding alphabet to input row number
        return switch (num) {               //Returns String value from switch parameters
            case 0 -> "A";
            case 1 -> "B";
            case 2 -> "C";
            case 3 -> "D";
            default -> null;
        };
    }

    public static int rowToNum(String x) { //Used to return corresponding alphabet to input row number
        int num = 8;
        switch (x) {
            case "A":
                num = 0;
                break;
            case "B":
                num = 1;
                break;
            case "C":
                num = 2;
                break;
            case "D":
                num = 3;
                break;
            default:
                System.out.println("Invalid input,Please try Again");
        }
        return num;
    }

    public static boolean Range_check(String x, int y) { //Checks if row entered is correct
        if (x.matches("[A-D]")) {
            return column_check(y);
        } else {
            System.out.println("Incorrect row entered");
            System.out.println("\n");
            System.out.println();
            menu();
        }
        return true;
    }

    public static boolean column_check(int y) { //Checks if column entered is correct
        if (y <= 14 && y > 0) {
            return false;
        } else {
            System.out.println("Out of range column number entered");
        }
        return true;
    }

    public static int inputColumnCheck() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input seat column number: ");
            if (scanner.hasNextInt()) {
                // User input is an integer
                return scanner.nextInt();  //returning user input directly
            } else {
                // User input is not an integer
                System.out.println("Invalid input, Please enter a number.");
                scanner.nextLine(); // Consume the non-integer input
                //This line dissolves the currently inputted data
            }
        }
    }

    public static String inputRowCheck(String row_letter) {
        boolean condition = true;
        Scanner scanner = new Scanner(System.in);
        while (condition) {
            System.out.print("Input seat row letter: ");
            String temp = scanner.nextLine();
            temp = temp.toUpperCase();
            if (temp.matches("[A-Z]+")) {
                row_letter = temp;
                condition = false;
            } else {
                System.out.println("Invalid input, Please enter a valid letter.");
                // Consume the non-letter input
            }
        }
        return row_letter;
    }

    public static int seatPrice(int seat_col) {
        return switch (seat_col) {  //Returns int output from switch
            case 1, 2, 3, 4, 5 -> 200;
            case 6, 7, 8, 9 -> 150;
            case 10, 11, 12, 13, 14 -> 180;
            default -> 0;
        };
    }

    public static void menu() { //menu display method
        System.out.println();
        System.out.println("*".repeat(50));
        System.out.println("*\t\t\t\t MENU OPTIONS  \t\t\t\t\t *");
        System.out.println("*".repeat(50));
        System.out.println("1) Buy a seat");
        System.out.println("2) Cancel a seat");
        System.out.println("3) Find first available seat");
        System.out.println("4) Show seating plan");
        System.out.println("5) Print tickets information and total sales");
        System.out.println("6) Search ticket");
        System.out.println("0) Quit");
        System.out.println("*".repeat(50));
        System.out.print("Please select an option: ");
    }

    public static void buy_seat(int[][] seating) { // Checks seat availability and enables user to buy a seat

        int seat_price;
        boolean condition = true;
        String seat_row = null;
        int seat_row_num = 0;
        int seat_col = 0;
        while (condition) {
            boolean cond = true;
            while (cond) {

                seat_row = inputRowCheck(seat_row);
                seat_row_num = rowToNum(seat_row);
                cond = false;
            }
            System.out.println();
            seat_col = inputColumnCheck();
            condition = Range_check(seat_row, seat_col); // Range checking if user input is valid
        }

        if (seating[seat_row_num][seat_col - 1] == 0) { //Check seat availability and sells seat
            System.out.println("Seat is available\n");
            seating[seat_row_num][seat_col - 1] = 1;
            System.out.println("Let us quickly get some personal information before proceeding\n");

            System.out.print("Enter Your Name: ");
            String name = new Scanner(System.in).nextLine();

            System.out.print("Enter Your Surname: ");
            String surname = new Scanner(System.in).nextLine();

            System.out.print("Enter Your Email: ");
            String email = new Scanner(System.in).nextLine();

            seat_price = seatPrice(seat_col);

            Person personTempObj = new Person(name, surname, email); //Creating Object from Person class.
            Ticket ticketTempObj = new Ticket(seat_row, seat_col, seat_price, personTempObj);

            for(int i=0;i<ticketInfoArray.length;i++){
                if(ticketInfoArray[i]==null){
                    ticketInfoArray[i]=ticketTempObj;
                    break;
                }
            }
            ticketSoldCount++;
            System.out.println("Seat is sold to you");
            /*
            for (int i = 0; i < ticketInfoArray.length; i++) {
                Ticket ticketTempObj2 = ticketInfoArray[i];  //Creating Ticket class object and assigning ticketInfoArray value
                if (ticketTempObj2 != null) {
             */
        } else {
            System.out.println("Seat is Already sold");
        }
    }

    public static void cancel_seat(int[][] seating) { //cancel seating method
        int seat_col = 0;
        boolean condition = true;
        int seat_row_num = 0;
        String seat_row = null;
        while (condition) {
            boolean cond = false;
            while (!cond) {
                seat_row = inputRowCheck(seat_row);
                seat_row_num = rowToNum(seat_row);
                cond = true;
            }
            System.out.println();
            seat_col = inputColumnCheck();
            condition = Range_check(seat_row, seat_col); // Range checking if user input is valid
        }
        if (seating[seat_row_num][seat_col - 1] == 1) { //Check seat availability and sells seat
            System.out.println("Seat is already booked \nSeat is being Cancelled now");
            seating[seat_row_num][seat_col - 1] = 0;
            for (int i = 0; i < ticketInfoArray.length; i++) {
                Ticket ticketTempObj2 = ticketInfoArray[i];  //Creating Ticket class object and assigning ticketInfoArray value
                if (ticketTempObj2 != null) {
                    if (seat_row.equals(ticketTempObj2.getRow()) && seat_col == ticketTempObj2.getSeat()) {
                        //Checking user input against ticketobject of current array iteration to find matching seat-row and seat-col
                        //to delete only the correct index of array
                        ticketInfoArray[i] = null;
                        break;
                    }
                }
            }
            System.out.println();
            ticketSoldCount--;
            System.out.println("no of tickets sold: " + ticketSoldCount);
        } else {
            System.out.println("Seat was Already Available");
        }
    }

    public static void find_first_available(int[][] seating) {
        String row; //Double for loop to traverse row and column ,
        for (int i = 0; i < seating.length; i++) {
            for (int j = 0; j < seating[i].length; j++) {
                if (seating[i][j] == 0) {
                    row = numToRow(i); //called numtorow method to return alphabet
                    System.out.println("  " + row + (j + 1) + " is free");
                    return;
                }
            }
        }
    }

    public static void seating_plan(int[][] seating) { //Seating plan is printed
        for (int[] ROW : seating) {
            for (int COL : ROW) {
                if (COL == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    public static void print_tickets_info() {
        int price = 0;
        //Creating Ticket class object and assigning ticketInfoArray value
        for (Ticket ticketTempObj2 : ticketInfoArray) {
            if (ticketTempObj2 != null) {
                price += ticketTempObj2.getPrice();
                ticketTempObj2.printTicketInfo();
            }
        }
        System.out.println("\n\t\tNumber of tickets sold :" + ticketSoldCount+ " \nTotal price of Tickets sold during session: " + price);
    }

    public static void search_ticket(int[][] seating) {
        int seat_row_num = 0;
        boolean condition = true;
        String seat_row = null;
        int seat_col = 0;
        while (condition) {
            boolean cond = true;
            while (cond) {
                seat_row = inputRowCheck(seat_row);
                seat_row_num = rowToNum(seat_row);
                cond = false;
            }
            seat_col = inputColumnCheck();
            condition = Range_check(seat_row, seat_col); // Range checking if user input is valid
        }
        if (seating[seat_row_num][seat_col - 1] == 1) { //Check seat availability and sells seat
            System.out.println("\nSeat is already sold to someone else\n");
            //Creating Ticket class object and assigning ticketInfoArray value
            for (Ticket ticketTempObj2 : ticketInfoArray) {
                if (ticketTempObj2 != null) {
                    if (seat_row.equals(ticketTempObj2.getRow()) && seat_col == ticketTempObj2.getSeat()) {
                        ticketTempObj2.printTicketInfo();
                    }
                }
            }
        }else{
            System.out.println("Seat is available\n");
        }
    }

}

class Ticket {
    private final String row;

    private final int seat;
    private final int price;

    private final Person personOBJ;

    public String getRow() {
        return row;
    }

    public int getSeat(){
        return seat;
    }
    public int getPrice() {
        return price;
    }

    Ticket(String row,int seat, int price , Person personOBJ){
        this.row=row;
        this.seat=seat;
        this.price=price;
        this.personOBJ=personOBJ;

    }
    public void printTicketInfo(){
        System.out.println(row+seat+" Price is " + price);
        personOBJ.printPersonInfo();
    }
}

class Person {
    private final String name;
    private final String surname;
    private final String email;

    // Constructor
    Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Method to print information
    public void printPersonInfo() {
        System.out.println("These are the confirmed Details for above booked seat");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email +"/n");
    }
}








