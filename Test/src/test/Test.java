/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author DocMad
 */
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Test
{
    public static void main(String[] args)
    {
//        Comparator<Integer> comparator = new StringLengthComparator();
//        PriorityQueue<Integer> queue = 
//            new PriorityQueue<Integer>(10, comparator);
//        queue.add(1);
//        queue.add(2);
//        queue.add(1);
//        queue.add(3);
//        queue.add(2);
//        while (queue.size() != 0)
//        {
//            System.out.println(queue.remove());
//        }
        
        String s = "aSimpleString";
        String z = "";
        
        for (int i = s.length() - 1; i >= 0; i--) {
            
            z += s.charAt(i);
        }
        
        System.out.println(z);
    }
}



class StringLengthComparator implements Comparator<Integer>
{
    @Override
    public int compare(Integer x, Integer y)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        if (x < y)
        {
            return -1;
        }
        if (x > y)
        {
            return 1;
        }
        return 0;
    }
}
