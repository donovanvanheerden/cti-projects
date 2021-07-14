
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Donovan van Heerden | EL2014-0043
 */
public class ca_question2 {

    private int[] numbers = new int[10];
    
    public void populate() {
        Random rng = new Random();
        
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = rng.nextInt(1001);
        }
    }
    
    public int elementAt(int index) {
        return numbers[index];
    }
    
    public static void main(String[] args) {
        ca_question2 question = new ca_question2();
        question.populate();
        
        Scanner scanner = new Scanner(System.in);
        
        try {
            int index = scanner.nextInt();
            System.out.println(question.elementAt(index));
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Out of Bounds");
        }
    }
    
}
