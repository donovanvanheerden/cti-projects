
import java.text.DecimalFormat;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DocMad
 */
public class BillTesting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        StudentBilling sb = new StudentBilling();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Type 'exit' to exit");
        System.out.println("Please enter in the tuition fees, textbook fees and the coupon amount - separated with a ',':");
        
        while(sc.hasNext()) {

            String input = sc.next();
            
            if (input.toLowerCase().equals("exit")) break;
            
            String[] values = input.split(",");
            
            double tuitionFee = Double.parseDouble(values[0]);
            double textbookFee = Double.parseDouble(values[1]);
            double couponAmount = Double.parseDouble(values[2]);
            
            System.out.println("Total Due for only Tuition Fees: R " + sb.calculateBill(tuitionFee));
            System.out.println("Total Due for Tuition Fees & Textbook Fees: R " + sb.calculateBill(tuitionFee, textbookFee));
            System.out.println("Total Due for Tuition Fees, Textbook Fees with a Coupon: R " + sb.calculateBill(tuitionFee, textbookFee, couponAmount));
            
        }
        
        
    }
}
