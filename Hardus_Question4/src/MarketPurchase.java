
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
public class MarketPurchase {
    
    String Name;
    int groupCount;
    double groupPrice;
    int numberBought;
    
    public MarketPurchase() { }
    
    void setPrice(int count, double costForCount) {
        this.groupCount = count;
        this.groupPrice = costForCount;
    }
    
    void setNumberBought(int number) {
        this.numberBought = number;
    }
    
    void setName(String newName) { 
        this.Name = newName;
    }
    
    double getTotalCost() {
        return numberBought * getUnitCost();
    }
    
    double getUnitCost() {
        return groupPrice / groupCount;
    }
    
    int getNumberBought() {
        return this.numberBought;
    }
    
    public void readInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name of the fruit");
        setName(sc.nextLine());

        System.out.println("Enter the number in groupa nd cost of group");
        System.out.println("For example like 5 for 2.99, 5 is the number in group and 2.99 is the cost of group");
        
        String[] numberAndCost = sc.nextLine().split(" ");
        setPrice(Integer.parseInt(numberAndCost[0]), Double.parseDouble(numberAndCost[1]));
        
        System.out.println("Enter total of fruits bought");
        setNumberBought(Integer.parseInt(sc.nextLine()));
    }
    
    public void writeOutput() {
        System.out.println(this.numberBought + " " + this.Name + " at " + this.groupCount + " for R" + this.groupPrice);
        System.out.println("Cost each R" + getUnitCost());
        System.out.println("Total cost R" + getTotalCost());
    }
}
