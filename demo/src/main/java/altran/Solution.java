package altran;
//
//Example : 
//    Input : {2, 5, -10, -5}
//    Output : true
// Example : 
//    Input : {2, 5, -10, -11}
//    Output : false
import java.io.*;
import java.util.*;
 class Solution {
    public static boolean hasTwoNumbersHavingSumZero(Integer[] input) {
    	
    	boolean result = false;
    	for(int i=0;i<input.length;i++) {
    		for(int j=i+1;j<input.length;j++) {
    			if(input[i]+input[j]==0) {
    				result =  true;
    				break;
    			}
    		}
    	}
    	return result;
    } 
    
    public static void main(String[] args) {
            System.out.println( hasTwoNumbersHavingSumZero(new Integer[] {2, 5, -10, -5}) );
        System.out.println( hasTwoNumbersHavingSumZero(new Integer[] {2, 5, -10, -11}) );
    }
 }
