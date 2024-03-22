import java.util.Scanner;

public class MainK {
    static Ticket[] sold_tickets = new Ticket[52];
    static Scanner input = new Scanner(System.in);

    private static int[][] seats;

    static {
        // Initialize seats array
        seats = new int[4][];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];

    }


    public static void main(String[] args) {
        boolean runCondition = true;

        while (runCondition) {
            menu();
            String decision = decisionOption();
            switch (decision) {
                case "0":
                    System.out.println("Exiting program...");
                    runCondition = false;
                    input.close();
                    break;
                case "1":
                    buy_seat();
                    break;
                case "2":
                    cancel_seat();
                    break;
                case "3":
                    find_first_available();
                    break;
                case "4":
                    show_seating_plan();
                    break;
                case "5":
                    print_tickets_info();
                    break;
                case "6":
                    search_ticket();
                default:
                    System.out.println("Invalid option! Please select a valid option.");
                    break;
            }
        }
    }

    public static String decisionOption() {
        boolean run = true;
        if(!input.hasNextLine()){
            input.nextLine();
        }
        String decision = null;
        while (run) {
            decision = input.nextLine();
            if (!decision.matches("[0-6]")) {
                System.out.println("invalid options try again");
                menu();
            } else {
                run = false;
            }
        }
        return decision;
    }
    public static void menu() {

        System.out.println("Welcome to the Plane Management application");
        System.out.println("*************************************************\n*                 MENU OPTIONS                  *\n*************************************************");
        System.out.println("     1)  Buy a seat\n     2) Cancel a seat\n     3) Find a first available seat\n     4) Show seating plan\n     5) Print tickets information and total sales\n     6) Search tickets\n     0) Quit");
        System.out.println("*************************************************");
        System.out.print("Please select an option: ");

    }



    // Create a new ticket based on the user input
    private static Ticket promptTicketInfo(Scanner input, String row_letter, int column_number) {
        System.out.println("Input your details below");

        System.out.print("\nEnter your name: ");
        input.nextLine();
        String name = input.nextLine();

        System.out.print("\nEnter your surname: ");
        String surname = input.nextLine();

        System.out.print("\nEnter your email address: ");
        String email = input.nextLine();


        Person data = new Person(name, surname, email);
        return new Ticket(row_letter, column_number, price(column_number), data);
    }
    public static void buy_seat() {
        boolean valid=false;
        while (!valid) {
            try {
                System.out.print("Enter row letter: ");
                String row_letter = input.nextLine().toUpperCase();

                if (isValidRowLetter(row_letter)) {
                    int column_number = getValidColumnNumber(row_letter);
                        // Check if the seat is already booked , returned boolean is checked
                        if (isSeatBooked(row_letter, column_number)) {
                            System.out.println("Seat has been booked already");
                            break;
                        } else {
                            // Seat is available, proceed with booking
                            // Update the seats array
                            int row = getRowNumber(row_letter);
                            seats[row][column_number - 1] = 1;

                            // Create a new ticket
                            Ticket ticket = promptTicketInfo(input, row_letter, column_number);
                            // Assign the ticket to the first available slot in the sold_tickets array
                            for (int i = 0; i < sold_tickets.length; i++) {
                                if (sold_tickets[i] == null) {
                                    sold_tickets[i] = ticket;
                                    break;
                                }
                            }//assigns ticket into array
                            System.out.println("seat has been booked now");
                        }
                        valid=true;
                } else {
                    System.out.println("Invalid row letter! Enter A - D.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Try again...");
            }
        }
    }


    //helper methods of cancel_seat
    private static void cancelSeat12(int column_number, int row) {
        if (column_number <= 12) {
            if (seats[row][column_number - 1] == 1) {
                System.out.println("Seat Cancelled");
                seats[row][column_number - 1] = 0;
            } else {
                System.out.println("Seat available");
            }
        } else {
            System.out.println("Invalid column number.");
        }
    }
    private static void cancelSeat14(int column_number, int row) {
        if (column_number <= 14) {
            if (seats[row][column_number - 1] == 1) {
                System.out.println("Seat Cancelled");
                seats[row][column_number - 1] = 0;
            } else {
                System.out.println("Seat already available");
            }
        } else {
            System.out.println("Invalid column number.");
        }
    }
    //
    //
    public static void cancel_seat() {
        boolean continueLoop = true; // Set to true initially
        while (continueLoop) {
            try {
                System.out.print("Enter row letter: ");
                String row_letter = input.next().toUpperCase();

                if (!isValidRowLetter(row_letter)) {
                    System.out.println("Invalid row letter! Enter A - D.");
                } else {
                    int column_number = getValidColumnNumber(row_letter);
                    if (!isSeatBooked(row_letter, column_number)) {
                        System.out.println("Seat was already available");
                        break;
                    }else {
                        int row = getRowNumber(row_letter);
                        switch (row) {
                            case 0:
                                cancelSeat14(column_number, 0);
                                break;
                            case 1:
                                cancelSeat12(column_number, 1);
                                break;
                            case 2:
                                cancelSeat12(column_number, 2);
                                break;
                            case 3:
                                cancelSeat14(column_number, 3);
                                break;
                            default:
                                break;
                        }
                        for (int i = 0; i < sold_tickets.length; i++) {
                            Ticket ticketTemp = sold_tickets[i];
                            if (ticketTemp != null) { //immediately avoids null data to avoid null exception.
                                if (row_letter.equals(ticketTemp.getRow()) && column_number == ticketTemp.getSeat()) {
                                    //will check input until matching row,col appears
                                    sold_tickets[i] = null;
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch(Exception e){
                System.out.println("Invalid input! Try again...");
            }
            continueLoop = false; // Exit the loop after processing input
        }
    }



    public static void find_first_available() {
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 0) {
                    if (i == 0) {
                        System.out.println("First available seat in row A: " + "A" + (j + 1));
                    } else if (i == 1) {
                        System.out.println("First available seat in row B: " + "B" + (j + 1));
                    } else if (i == 2) {
                        System.out.println("First available seat in row C: " + "C" + (j + 1));
                    } else {
                        System.out.println("First available seat in row D: " + "D" + (j + 1));
                    }
                    return; // Return after printing the first available seat
                }
            }
        }
        System.out.println("No available seats."); // Print if all seats are booked
    }


    
    public static void show_seating_plan (){
            for (int[] value : seats) {
                for (int value1 : value) {
                    if (value1 == 0) {
                        System.out.print("O ");
                    } else {
                        System.out.print("X ");
                    }
                }
                System.out.println();
            }
    }

    public static void print_tickets_info() {
        int price=0;
        for (int i=0;i<sold_tickets.length;i++) {
            Ticket temp2=sold_tickets[i];
            if (temp2 != null) {
                 price += temp2.getPrice();
                temp2.ticketInfo();
            }
        }
        System.out.println("\n\t Total amount from Tickets sold: " + price);
    }

    public static void search_ticket() {
        boolean continueLoop = true; // Set to true initially
        while (continueLoop) {
            try {
                System.out.print("Enter row letter: ");
                String row_letter = input.next().toUpperCase();

                if (!isValidRowLetter(row_letter)) {
                    System.out.println("Invalid row letter! Enter A - D.");
                } else {
                    int column_number = getValidColumnNumber(row_letter);
                    if (!isSeatBooked(row_letter, column_number)) {
                        System.out.println("Seat was already available");
                        break;
                    } else {
                        for (int i=0;i<sold_tickets.length;i++){
                            Ticket temp3=sold_tickets[i];
                            if (temp3!=null && (column_number==temp3.getSeat() && row_letter.equals(temp3.getRow()))){
                            temp3.ticketInfo();
                            }
                        }
                    }
                }
            }catch(Exception e){
                System.out.println("Invalid input! Try again...");
            }
            continueLoop = false; // Exit the loop after processing input
        }
    }

    //more helper methods for cancel and buy seats
    // Check if the seat is already booked
    private static int price(int column_number) {
        int Tprice = 0;
        switch (column_number) {
            case 1, 2, 3, 4, 5:
                Tprice = 200;
                break;
            case 6, 7, 8, 9:
                Tprice = 150;
                break;
            case 10, 11, 12, 13, 14:
                Tprice = 180;
                break;
            default:
        }
        return Tprice;
    }

    private static boolean isSeatBooked(String row_letter, int column_number) {
        int row = getRowNumber(row_letter);
        return seats[row][column_number - 1] == 1;
    }

    // Check if the row letter is valid (A, B, C, or D)
    private static boolean isValidRowLetter(String row_letter) {
        return row_letter.equals("A") || row_letter.equals("B") || row_letter.equals("C") || row_letter.equals("D");
    }

    // Get a valid column number based on the row letter
    private static int getValidColumnNumber(String row_letter) {
        int maxColumnNumber = (row_letter.equals("A") || row_letter.equals("D")) ? 14 : 12;

        while (true) {
            System.out.print("Enter column number: ");
            int column_number = input.nextInt();

            if (column_number >= 1 && column_number <= maxColumnNumber) {
                return column_number;
            } else {
                System.out.println("Invalid column number for the given row letter. Please try again.");
            }
            input.nextLine(); // Consume newline character
        }
    }

    // Get the row number based on the row letter
    private static int getRowNumber(String row_letter) {
        return switch (row_letter) {
            case "A" -> 0;
            case "B" -> 1;
            case "C" -> 2;
            case "D" -> 3;
            default -> -1; // Invalid row letter
        };
    }

    // Assign the ticket to the first available slot in the sold_tickets array
    private static void assignTicket(Ticket ticket) {
        for (int i = 0; i < sold_tickets.length; i++) {
            if (sold_tickets[i] == null) {
                sold_tickets[i] = ticket;
                break;
            }
        }
    }
}

class Ticket{

    private String row;
    private int seat;
    private int price;
    private Person person;

    public Ticket(String row,int seat, int price,Person customer){
        this.row = row;
        this.seat = seat;
        this.price = price;
        person = customer;
    }
    public String getRow(){
        return row;
    }
    public void setRow(String Letter){
        this.row=Letter;
    }
    public Person getPerson(){
        return  person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public int getSeat(){
        return seat;
    }
    public void setSeat(int number) {
        this.seat = number;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int seatPrices) {
        this.price = seatPrices;
    }

    public void ticketInfo(){
        System.out.println("Seat: " + getRow()+getSeat()+" cost: " + getPrice());
        person.personalInfo();
    }
}

class Person{
    private String name;
    private String surname;
    private String email;

    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname=surname;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void personalInfo(){
        System.out.println("Name: "+ getName());
        System.out.println("Surname: "+ getSurname());
        System.out.println("E-mail: "+ getEmail());
    }
}


