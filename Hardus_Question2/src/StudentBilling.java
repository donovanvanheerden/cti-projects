/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DocMad
 */
public class StudentBilling {
    
    public StudentBilling() { }
    
    public double calculateBill(double TuitionFees) {
        return TuitionFees * 1.14;
    }
    
    public double calculateBill(double TuitionFees, double TextbookFees) {
        return (TuitionFees + TextbookFees) * 1.14;
    }
    
    public double calculateBill(double TuitionFees, double TextbookFees, double Coupon) {
        return (TuitionFees + TextbookFees - Coupon) * 1.14;
    }
}
