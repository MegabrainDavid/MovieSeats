public class Seat{
    private char ticketType; // 'A' for adult, 'C' for child, 'S' for senior, '.' for empty seat
    private int row;
    private int column;
    private boolean reserved;

    public Seat(char ticketType, int row, int column, boolean reserved) {
        this.ticketType = ticketType;
        this.row = row;
        this.column = column;
        this.reserved = reserved;
    }

    public char getTicketType() {
        return ticketType;
    }

    public void setTicketType(char ticketType) {
        this.ticketType = ticketType;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
    
  
}
