# Question 1

```Java
import java.util.Random;

/**
 *
 * @author Donovan van Heerden | EL2014-0043
 */
public class ca_question1 {

    private int[][] matrix = new int[4][4];
    
    public void populateMatrix() {
        Random rng = new Random();
        
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = rng.nextInt(2);
                
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
    
    public void largestIndexes() {
        int rowCount, colCount;
        
        int rowIndex = 0;
        int highestRow = 0;
        
        int colIndex = 0;
        int highestCol = 0;
            
        for(int row = 0; row < matrix.length; row++) {
            rowCount = 0;
            colCount = 0;
            
            for(int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 1) {
                    rowCount++;
                }
                
                if (matrix[col][row] == 1) {
                    colCount++;
                }
            }
            
           if (rowCount > highestRow) {
               highestRow = rowCount;
               rowIndex = row;
           }
           
           if (colCount > highestCol) {
               highestCol = colCount;
               colIndex = row;
           }
        }
        
        System.out.println("The largest row index: " + rowIndex);
        System.out.println("The largest column index: " + colIndex);
    }
    
    public static void main(String[] args) {
        ca_question1 question = new ca_question1();
        
        question.populateMatrix();
        question.largestIndexes();
    }
    
}
```