import java.io.FileWriter;
import java.io.IOException;

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

    public static void fileTicketInfo() {
        for (Ticket temp3 : MainK.sold_tickets) {
            if (temp3!=null){ //getting only the not null values in array
                String file=temp3.getRow()+temp3.getSeat()+".txt";
                try (FileWriter writes = new FileWriter(file)) { //
                    // storing ticket info to write when called later
                    String fileValues= temp3.forFileTicketInfo();
                    writes.write(fileValues);
                    System.out.println(" Current session tickets bought are saved as: "+ file);
                } catch (IOException e) {
                    System.out.println(" error occurred when saving information to " + file);
                }
            }
        }
    }

    public String forFileTicketInfo() {
        return getRow()+getSeat() + " cost: " + getPrice() + person.forFilePersonInfo();
    }
}