/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortoperations;

/**
 *
 * @author DocMad
 */
public class SortOperations {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       int[] arr = new int[] {5,2,4,1,3};
       
       //bubbleSort(arr);
       //selectionSort(arr);
       insertionSort(arr);
       printArray(arr);
    }
    
    static void bubbleSort(int[] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] > input[j]) {
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }
    }
    
    static void selectionSort(int[] list) {
        int i, j, minIndex, tmp;
        int n = list.length;
        
        for (i = 0; i < n - 1; i++) {
            minIndex = i;
            for (j = i + 1; j < n; j++) 
                if (list[j] < list[minIndex])
                    minIndex = j;
                
            if (minIndex != i) {
                tmp = list[i];
                list[i] = list[minIndex];
                list[minIndex] = tmp;
            }
        }
    }
    
    static void insertionSort(int[] list) {
        int i, j, newValue;
        
        for (i = 1; i < list.length; i++) {
            newValue = list[i];
            j = i;
            while(j > 0 && list[j-1] > newValue) {
                list[j] = list[j-1];
                j--;
            }
            list[j] = newValue;
        }
    }
    
    static void printArray(int[] input) {
        
        String array = "";
        
        for (int i = 0; i < input.length; i++) {
           array += i == input.length - 1 ? input[i] + "" : input[i] + ", ";
        }
        
        System.out.println(array);
    }
    
}

