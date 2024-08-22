import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Auditorium {
    private Node<Seat> first;
    private int numOfSeat;
    private int numOfRow;
    private int bestRow = 0, bestSeat = 0;
    public Auditorium(String filename) throws FileNotFoundException {
        setAuditorium(filename);
    }

    private void setAuditorium(String filename) throws FileNotFoundException {
        first = null;
        String line;
        boolean res = false;
        int row = 0;
        FileInputStream inputStream = new FileInputStream(filename);
        Scanner inFs = new Scanner(inputStream);
    
        numOfSeat = 0;
    
        Node<Seat> prevRow = null; // Initialize prevRow to null
    
        while (inFs.hasNextLine()) {
            line = inFs.nextLine();
            res = false;
            Node<Seat> currRow = null;
    
            for (int col = 0; col < line.length(); col++) {
                char type = line.charAt(col);
                if (type != '.') {
                    res = true;
                }
                Seat seat = new Seat(type, row, col, res);
                Node<Seat> newNode = new Node<>(seat);
    
                if (currRow == null) {
                    currRow = newNode;
    
                    if (prevRow != null) {
                        prevRow.setDown(currRow);
                    }
                } else {
                    Node<Seat> currSeat = currRow;
                    while (currSeat.getNext() != null) {
                        currSeat = currSeat.getNext();
                    }
                    currSeat.setNext(newNode);
                }
    
                if (first == null) {
                    first = currRow;
                }
                numOfSeat = col + 1;
            }
            prevRow = currRow;
            row++;
            numOfRow = row;
        }
        inFs.close();
    }
    
    public void print_Auditorium() {
        Node<Seat> currRow = first;
        int rowNum = 0;
        System.out.println("Auditorium Seating:");
    
        System.out.print("  ");
        for (int col = 0; col < numOfSeat; col++) {
            System.out.print((char) ('A' + col));
        }
        System.out.println();
    
        while (currRow != null) {
            System.out.print(rowNum + 1 + " ");
            Node<Seat> currSeat = currRow;
            rowNum++;
            while (currSeat != null) {
                Seat seat = currSeat.getPayload();
                char type = seat.getTicketType();
    
                if (type != '.') {
                    System.out.print('#');
                } else {
                    System.out.print(type);
                }
                currSeat = currSeat.getNext();
            }
            System.out.println();
    
            currRow = currRow.getDown();
        }
    }
    public boolean check_Auditorium(int tt, int row, int seat) {
        if (first == null) {
            return false;
        }
    
        Node<Seat> currentRow = first;
        int i = 0;
    
        // Traverse to the correct row
        while (i < row && currentRow != null) {
            currentRow = currentRow.getDown();
            i++;
        }
    
        // Ensure the row exists
        if (currentRow == null) {
            return false;
        }
    
        i = 0;
        Node<Seat> currentSeat = currentRow;
    
        // Traverse to the correct seat
        while (i < seat && currentSeat != null) {
            currentSeat = currentSeat.getNext();
            i++;
        }
    
        // Ensure the seat exists
        if (currentSeat == null) {
            return false;
        }
    
        // Check if tt consecutive seats are available
        i = 0;
        while (i < tt && currentSeat != null) {
            if (currentSeat.getPayload().getTicketType() != '.') {
                return false;
            }
            currentSeat = currentSeat.getNext();
            i++;
        }
    
        return true;
    }
    public void book_Auditorium(int row, int seat, int a, int c, int s){
        Node<Seat> current = first;
        int i = 1;
        while(i< row){
            current = current.getDown();
            i++;
        }
        i = 65;
        while(i < seat){
            current = current.getNext();
            i++;
        }
        i = 1;
        while(i <= a){
            current.getPayload().setTicketType('A');
            current = current.getNext();
            i++;
        }
        i = 1;
        while(i <= c){
            current.getPayload().setTicketType('C');
            current = current.getNext();
            i++;
        }
        i = 1;
        while(i <= s){
            current.getPayload().setTicketType('S');
            current = current.getNext();
            i++;
        }
    }
    public void write_File() throws FileNotFoundException {
        FileOutputStream outputStream = new FileOutputStream("A1.txt");
        PrintWriter write = new PrintWriter(outputStream);
    
        Node<Seat> currentRow = first;
        while (currentRow != null) {
            Node<Seat> current = currentRow;
            while (current != null) {
                Seat s = current.getPayload();
                write.print(s.getTicketType()); // Print the ticket type
                current = current.getNext();
            }
            currentRow = currentRow.getDown();
            write.println(); // Move to the next row in the output file
        }
        write.close(); // Close the PrintWriter when done writing
    }
    public void calcOutput() {
        Node<Seat> currentRow = first;
        int sold_Adult = 0, sold_Child = 0, sold_Sen = 0, totalSeat = 0;
    
        while (currentRow != null) {
            Node<Seat> current = currentRow;
    
            while (current != null) {
                Seat s = current.getPayload();
    
                if (s.getTicketType() == 'A') {
                    sold_Adult++;
                } else if (s.getTicketType() == 'C') {
                    sold_Child++;
                } else if (s.getTicketType() == 'S') {
                    sold_Sen++;
                }
    
                totalSeat++;
                current = current.getNext(); // Move to the next seat
            }
    
            currentRow = currentRow.getDown(); // Move to the next row
        }
    
        int totalNumTicket = sold_Adult + sold_Child + sold_Sen;
        double totalSale = sold_Adult * 10 + sold_Child * 5 + sold_Sen * 7.5;
    
        System.out.println("Total Seats: " + totalSeat);
        System.out.println("Total Tickets: " + totalNumTicket);
        System.out.println("Adult Tickets: " + sold_Adult);
        System.out.println("Child Tickets: " + sold_Child);
        System.out.println("Senior Tickets: " + sold_Sen);
        System.out.printf("Total Sales: $" + "%.2f",totalSale);
    }
    public boolean best_Available(int quantity) {
        int centerRow = numOfRow / 2;
        int centerSeat = numOfSeat / 2;
        if(numOfSeat % 2 == 1){
            centerSeat -= 1;
        }
  
        System.out.println("Center: " + centerRow + " "  + centerSeat);
        int minDistance = Integer.MAX_VALUE;
        bestRow = -1;
        bestSeat = -1;
    
        Node<Seat> currRow = first;
        int rowIdx = 0;
        while (currRow != null) {
            Node<Seat> currSeat = currRow;
            int seatIdx = 0;
            while (currSeat != null) {
                if (check_Auditorium(quantity, rowIdx, seatIdx)) {
                    double distance = calculateDistance(rowIdx,seatIdx, centerRow, centerSeat);
                    System.out.println("distance: " + distance + "row: " + rowIdx + "col: " + (char) ('A' + seatIdx));
                    if (distance < minDistance) {
                        minDistance = (int)distance;
                        bestRow = rowIdx;
                        bestSeat = seatIdx;
                    }else if(distance == minDistance){
                        if(seatIdx < bestSeat || rowIdx < bestRow){
                            minDistance = (int)distance;
                            bestSeat = seatIdx;
                            bestRow = rowIdx;
                        }
                    }
                }
                currSeat = currSeat.getNext();
                seatIdx++;
            }
            currRow = currRow.getDown();
            rowIdx++;
        }
    
        if (bestRow !=-1 && bestSeat != -1) {
            System.out.println(formatBestAvailable(quantity));
            return true;
        } else {
            return false; // No best available seats found
        }
    }
    private double calculateDistance(int row, int seat, int centerRow, int centerSeat) {
        return Math.sqrt(Math.pow(row - centerRow, 2) + Math.pow(seat - centerSeat, 2));
    }
    public int getBestRow() {
        return bestRow;
    }

    public int getBestSeat() {
        return bestSeat;
    }
    public int getTotalRow(){
        return numOfRow;
    }
    public String formatBestAvailable(int quantity) {
        char startingSeat = (char) ('A' + bestSeat); // Convert seat to a letter
        char endingSeat = (char) ('A' + bestSeat + quantity - 1); // Calculate the ending seat letter
        bestRow = bestRow +1;
        bestSeat = bestSeat + 'A';
        if(quantity >1){
            return (bestRow) + "" + startingSeat + " - " + (bestRow) + "" + endingSeat;
        }else{
            return (bestRow) + "" + startingSeat;
        }
    }
    public boolean check_Auditorium2(int tt, int row, int seat){
        if(first == null){
            return false;
        }
        Node<Seat> current = first;
        int i = 1;
        while(i< row){
            current = current.getDown();
            i++;
        }
        i = 65;
        while(i < seat){
            current = current.getNext();
            i++;
        }
        i = 1;
        while(i<= tt){
            if(current.getPayload().getTicketType() != '.'){
                return false;
            }else{
                current = current.getNext();
                i++;
            }
        }
        return true;
    }
    
}
