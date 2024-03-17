import java.util.Scanner;

public class Summa {
}
    public class Person{
        private String name;
        private String surname;
        private String email;

        public Person(String name, String surname, String email){
            this.name = name;
            this.surname = surname;
            this.email = email;
        }
        public String getname(){
            return name;
        }
        public void setname(String name){
            this.name  = name;
        }
        public String getsurname(){
            return surname;
        }
        public void setsurname(String surname){
            this.surname = surname;
        }
        public String getemail(){
            return email;
        }
        public void setemail(String email){
            this.email = email;
        }
        public void personalinfo(){
            System.out.println("Name: "+ getname());
            System.out.println("Surname: "+ getsurname());
            System.out.println("E-mail: "+ getemail());
        }
    }
public class Ticket{
    private String row_letter;
    private int column_number;
    private int price;
    private Person customer;
    public int setPrice(){
        if(column_number<=5 && column_number>=1){
            return this.price = 200;
        } else if(column_number<=9 && column_number>=6){
            return this.price = 150;
        } else{
            return this.price = 180;
        }
    }
    public Ticket(String row_letter,int column_number, int price){
        this.row_letter = row_letter;
        this.column_number = column_number;
        this.price = price;
    }
    public String getRow_letter(){
        return row_letter;
    }

    public int getColumn_number(){
        return column_number;
    }

    public int getPrice(){
        return price;
    }

    public Person getcustomer(){
        return  customer;
    }

    public void ticketinfo(){
        System.out.println("Seat: " + getRow_letter().toUpperCase()+getColumn_number());
        getPrice();
        System.out.println("Price: " + getPrice());
    }
}
import java.util.Scanner;

public class w2051841_PlaneManagement {

    public static void main(String[] args) {
        int[][] seats = new int[4][];
        seats[0] = new int[14];
        seats[1] = new int[12];
        seats[2] = new int[12];
        seats[3] = new int[14];
        Ticket sold_tickets[] = new Ticket[52];

        boolean condition = true;
        Scanner input = new Scanner(System.in);
        while (condition) {
            menu();
            int option = input.nextInt();
            switch (option) {
                case 0:
                    condition = false;
                    System.out.println("Program exciting.....");
                    break;
                case 1:
                    buy_seat(seats, sold_tickets);
                    break;
                case 2:
                    cancel_seat(seats, sold_tickets);
                    break;
                case 3:
                    find_first_available(seats);
                    break;
                case 4:
                    show_seating_plan(seats);
                    break;
                case 5:
                    print_tickets_info(sold_tickets);
                    break;
                default:
                    condition = false;
                    System.out.println(" ");
                    break;
            }
        }

    }

    private static void menu() {

        System.out.println("Welcome to the Plane Management application");
        System.out.println("*************************************************\n*                 MENU OPTIONS                  *\n*************************************************");
        System.out.println("     1)  Buy a seat\n     2) Cancel a seat\n     3) Find a first available seat\n     4) Show seating plan\n     5) Print tickets information and total sales\n     7) Search tickets\n     0) Quit");
        System.out.println("*************************************************");
        System.out.print("Please select an option: ");

    }

    private static void buy_seat(int[][] seat, Ticket[] sold_Tickets) {
        boolean condition = true;
        while (condition) {
            Scanner input = new Scanner(System.in);

            System.out.print("Enter your name: ");
            String name = input.next();

            System.out.print("Enter your surname: ");
            String surname = input.next();

            System.out.print("Enter your email address: ");
            String email = input.next();
            Person data = new Person(name,surname,email);

            try {


                System.out.print("Enter row letter: ");
                String row_letter = input.next().toUpperCase();


                if (row_letter.equals("A") || row_letter.equals("B") || row_letter.equals("C") || row_letter.equals("D")) {
                    System.out.print("Enter column number: ");
                    int column_number = input.nextInt();


                    for (int i = 0; i < sold_Tickets.length; i++) {
                        Ticket ticket = new Ticket(row_letter,column_number,);
                        int row = 0;
                        switch (row_letter) {
                            case "A":
                                row = 0;
                                if (column_number <= 14) {
                                    if (seat[row][column_number - 1] == 0) {
                                        data.personalinfo();
                                        System.out.println("Seat booked");
                                        sold_Tickets[i] = new Ticket(row_letter,column_number,ticket.getPrice());
                                        seat[row][column_number - 1] = 1;
                                    } else {
                                        System.out.println("Seat sold.");
                                    }
                                } else {
                                    System.out.println("Invalid column number.");
                                }
                                break;

                            case "B":
                                row = 1;
                                if (column_number <= 12) {
                                    if (seat[row][column_number - 1] == 0) {
                                        data.personalinfo();
                                        ticket.ticketinfo();
                                        System.out.println("Seat booked");
                                        seat[row][column_number - 1] = 1;
                                        sold_Tickets[i] = new Ticket(row_letter,column_number, ticket.getPrice());
                                    } else {
                                        System.out.println("Seat sold.");
                                    }
                                } else {
                                    System.out.println("Invalid column number.");
                                }
                                break;

                            case "C":
                                row = 2;
                                if (column_number <= 12) {
                                    if (seat[row][column_number - 1] == 0) {
                                        data.personalinfo();
                                        ticket.ticketinfo();
                                        System.out.println("Seat booked");
                                        seat[row][column_number - 1] = 1;
                                        sold_Tickets[i] = new Ticket(row_letter,column_number, ticket.getPrice());
                                    } else {
                                        System.out.println("Seat sold");
                                    }
                                } else {
                                    System.out.println("Invalid column number.");
                                }
                                break;
                            case "D":
                                row = 3;
                                if (column_number <= 12) {
                                    if (seat[row][column_number - 1] == 0) {
                                        data.personalinfo();
                                        ticket.ticketinfo();
                                        System.out.println("Seat booked");
                                        seat[row][column_number - 1] = 1;
                                        sold_Tickets[i] = new Ticket(row_letter,column_number, ticket.getPrice());
                                    } else {
                                        System.out.println("Seat sold");
                                    }
                                } else {
                                    System.out.println("Invalid column number.");
                                }
                                break;

                            default:
                                condition = false;
                                break;
                        }
                        break;
                    }
                } else {
                    System.out.println("Invalid row letter! Enter A - D.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Try again...");
            }
            break;
        }
    }

    private static void cancel_seat(int[][] seat, Ticket[] sold_Tickets) {
        boolean condition = true;
        while (condition) {
            Scanner input = new Scanner(System.in);

            System.out.print("Enter row letter: ");
            String row_letter = input.next().toUpperCase();

            if (row_letter.equals("A") || row_letter.equals("B") || row_letter.equals("C") || row_letter.equals("D")) {
                System.out.print("Enter column number: ");
                int column_number = input.nextInt();

                int row = 0;
                for (int i = 0; i < sold_Tickets.length; i++) {
                    Ticket ticket = new Ticket(row_letter,column_number,ticket.setPrice());
                    switch (row_letter.toUpperCase()) {
                        case "A":
                            row = 0;
                            if (column_number <= 14) {
                                if (seat[row][column_number - 1] == 1 && sold_Tickets[i].getRow_letter().equals(row_letter) && sold_Tickets[i].getColumn_number() == column_number) {
                                    System.out.println("Seat Cancelled");
                                    seat[row][column_number - 1] = 0;
                                    sold_Tickets[i] = null;
                                } else {
                                    System.out.println("Seat available");
                                }
                            }
                            break;
                        case "B":
                            row = 1;
                            if (column_number <= 14) {
                                if (seat[row][column_number - 1] == 1 && sold_Tickets[i].getRow_letter().equals(row_letter) && sold_Tickets[i].getColumn_number() == column_number) {
                                    System.out.println("Seat Cancelled");
                                    seat[row][column_number - 1] = 0;
                                    sold_Tickets[i] = null;
                                } else {
                                    System.out.println("Seat available");
                                }
                            }
                            break;
                        case "C":
                            row = 2;
                            if (column_number <= 14) {
                                if (seat[row][column_number - 1] == 1 && sold_Tickets[i].getRow_letter().equals(row_letter) && sold_Tickets[i].getColumn_number() == column_number) {
                                    System.out.println("Seat Cancelled");
                                    seat[row][column_number - 1] = 0;
                                    sold_Tickets[i] = null;
                                } else {
                                    System.out.println("Seat available");
                                }
                            }
                            break;
                        case "D":
                            row = 3;
                            if (column_number <= 14) {
                                if (seat[row][column_number - 1] == 1 && sold_Tickets[i].getRow_letter().equals(row_letter) && sold_Tickets[i].getColumn_number() == column_number) {
                                    System.out.println("Seat Cancelled");
                                    seat[row][column_number - 1] = 0;
                                    sold_Tickets[i] = null;
                                } else {
                                    System.out.println("Seat available");
                                }
                            }
                            break;
                        default:
                            condition = false;
                            break;

                    }
                    break;
                }
            } else {
                System.out.println("Invalid row letter! Enter A - D.");
            }
            condition = false;
            break;
        }
    }

    private static void find_first_available(int[][] seat) {
        boolean condition = true;
        while (condition) {
            for (int i = 0; i < seat.length; i++) {
                for (int j = 0; j < seat[i].length; j++) {
                    if (seat[i][j] == 0) {
                        if (i == 0) {
                            System.out.println("First available seat in row A: " + "A" + (j + 1));
                        } else if (i == 1) {
                            System.out.println("First available seat in row B: " + "B" + (j + 1));
                        } else if (i == 2) {
                            System.out.println("First available seat in row C: " + "C" + (j + 1));
                        } else {
                            System.out.println("First available seat in row D: " + "D" + (j + 1));
                        }
                        break;
                    }
                }
            }
            break;
        }
    }

    private static void show_seating_plan(int[][] seat) {
        boolean condition = true;
        while (condition) {
            for (int[] value : seat) {
                for (int value1 : value) {
                    if (value1 == 0) {
                        System.out.print("O ");
                    } else {
                        System.out.print("X ");
                    }
                }
                System.out.println();
            }
            break;
        }
    }
    private static void print_tickets_info(Ticket[] sold_tickets){
        int total_sales = 0;

        for (Ticket ticket: sold_tickets){
            if (ticket != null){
                ticket.getPrice();
                total_sales = total_sales + ticket.getPrice();
            }
        }
        System.out.println("Total sales:" + total_sales);
    }

}




