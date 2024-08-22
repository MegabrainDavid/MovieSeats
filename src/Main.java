import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        Auditorium aud_obj = null;
        int choice = 0, pickRow = 0, numAdult = 0, numChild = 0, numSen = 0, totalNumTicket = 0;
        char pickSeat;
        boolean avaliable;
        Scanner scnr = new Scanner(System.in);
        System.out.println("Enter filename: ");
        String filename = scnr.next();
        aud_obj = new Auditorium(filename);
        do{
            System.out.println("1. Reserve seat");
            System.out.println("2. Exit");
            boolean valid = true;
            while(valid){
                    String input = scnr.next();
                    try{
                        choice = Integer.parseInt(input);
                        valid = false;
                    }catch(Exception e){
                        System.out.println("Wrong input type again");
                    }
                
            }
            System.out.println("choice: " + choice);
            if(choice ==  1){
                aud_obj.print_Auditorium();
                int TotalRow = aud_obj.getTotalRow();
                valid = true;
                while(valid){
                    System.out.println("Enter row number: ");
                    String input = scnr.next();
                    try{
                        
                        pickRow = Integer.parseInt(input);
                        if(pickRow <= TotalRow){
                            valid = false;
                        }else{
                            System.out.println("Way too much row enter row again");
                        }
                    }catch(Exception e){
                        System.out.println("Wrong input type again");
                    }
                
                }
                 
                System.out.println("row input: " + pickRow);
                while (true) {
                    System.out.println("Enter a seat letter: ");
                    String input = scnr.next();
        
                    if (input.length() == 1 && !Character.isDigit(input.charAt(0))) {
                        pickSeat = input.charAt(0);
                        break;
                    } else {
                        System.out.println("Wrong input, retype again (non-numeric character only).");
                    }
                }
                System.out.println("seat input: " + pickSeat);
                
                valid = true;
                while(valid){
                    System.out.println("Number of Adult ticket: ");
                    String input = scnr.next();
                    try{
                        numAdult = Integer.parseInt(input);
                        valid = false;
                    }catch(Exception e){
                        System.out.println("Wrong input type again");
                    }
                
                }
                System.out.println("Adult input: " + numAdult);
                valid = true;
                while(valid){
                    System.out.println("Number of child ticket: ");
                    String input = scnr.next();
                    try{
                        numChild = Integer.parseInt(input);
                        valid = false;
                    }catch(Exception e){
                        System.out.println("Wrong input type again");
                    }
                
                }
                System.out.println("Child input: " + numChild);
                valid = true;
                while(valid){
                    System.out.println("Number of Senior ticket: ");
                    String input = scnr.next();
                    try{
                        numSen = Integer.parseInt(input);
                        valid = false;
                    }catch(Exception e){
                        System.out.println("Wrong input type again");
                    }
                
                }
                System.out.println("Sen input "+ numSen);
                totalNumTicket = numAdult + numChild + numSen;

                avaliable = aud_obj.check_Auditorium2(totalNumTicket, pickRow, pickSeat);
                System.out.println(avaliable);
                if(avaliable){
                    aud_obj.book_Auditorium(pickRow, pickSeat, numAdult, numChild, numSen);
                }else{
                    if(aud_obj.best_Available(totalNumTicket)){
                        System.out.println("Do you want to accept that seat");
                        String response = scnr.next();
                        if(response.equals("Y")){
                            int bestRow = aud_obj.getBestRow();
                            int bestSeat = aud_obj.getBestSeat();
                            aud_obj.book_Auditorium(bestRow, bestSeat, numAdult, numChild, numSen);
                        }
                    }else{
                        System.out.println("no seats avaliable");
                    }
                }
             
            }
            if(choice == 2){
                aud_obj.write_File();
                aud_obj.calcOutput();
            }
       }while(choice != 2);
       scnr.close();
    }
}
