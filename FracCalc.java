package fracCalc;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * APCS Project 1: FracCalc
 * Name: Shubham More
 * Period: 5
 * Checkpoint: Final
 * Date: November 12, 2019
 
 */
public class FracCalc {
	
    public static void main(String[] args) {

        // TODO: Read the input from the user and call produceAnswer with an equation
    	
    	//Ask fo the user input here
    	Scanner s = new Scanner(System.in);
        System.out.println("Enter a fraction: ");
        String input = s.nextLine();

        
        //Put the code in a loop so that it always keeps asking the question to add fractions
        while(!input.equalsIgnoreCase("quit")) {
            System.out.println(produceAnswer(input));
            System.out.println("Enter a fraction: ");
            input = s.nextLine();
        }
        s.close();
    }

    public static String produceAnswer(String input) {

        // TODO: Implement this function to produce the solution to the input
    	
    	//Divide the fractions into different sub areas like fraction 1 then operator and the the last half which is frac2
        String frac1 = input.substring(0, input.indexOf(' '));
        input = input.substring(input.indexOf(' ') + 1);
        String operator = input.substring(0, input.indexOf(' '));
        input = input.substring(input.indexOf(' ') + 1);
        String frac2 = input;
        
        //Call the functions to find the parts of the object
        int frac1Whole = findWhole(frac1);
        int frac1Num = findNumerator(frac1);
        int frac1Denom = findDenominator(frac1);
        int frac2Whole = findWhole(frac2);
        int frac2Num = findNumerator(frac2);
        int frac2Denom = findDenominator(frac2);
        
        
        //I have completed error handling so we are good
        if(frac1Denom ==  0 || frac2Denom == 0) {
        	return "error";
        }
        
        if(operator.length() > 1) {
        	return"error";
        }
        
        //To add multiple fractions in this was not able to do that.
        while(frac1 + operator + frac2 != null) {
        	produceAnswer(frac2);
        }
        
       
        

        //Basic program to find the numerator using the math available
        frac1Num = frac1Num + (frac1Whole * frac1Denom);
        frac2Num = frac2Num + (frac2Whole * frac2Denom);

        if(operator.equals("+")) {
        	int numF = (frac1Num * frac2Denom) + (frac1Denom * frac2Num);
        	int denomF = frac1Denom * frac2Denom;
        	String checkpoint3 = numF + "/" + denomF;
        	return reduce(checkpoint3);
        }
        
        //reduce all the zeroes to 0
        else if(operator.equals("*")) {
        	if(frac1Num == 0 || frac2Num == 0) {
        		return reduce("0/1");
        	}
        	else {
        		int numF = frac1Num * frac2Num;
        		int denomF = frac1Denom * frac2Denom;
        		String checkpoint3 = numF + "/" + denomF;
        		return reduce(checkpoint3);
        	}
        }

        else if(operator.equals("-")) {
        	if(frac2Num < 0) {
        		frac2Num = frac2Num * (-1);
            	int numF = (frac1Num * frac2Denom) + (frac1Denom * frac2Num);
               	int denomF = frac1Denom * frac2Denom;
               	String checkpoint3 = numF + "/" + denomF;
               	return reduce(checkpoint3);

        	}
        	else {
        		if(frac1Denom == frac2Denom) {
        			int numF = frac1Num - frac2Num;
        			String checkpoint3 = numF + "/" + frac2Denom;
                	return reduce(checkpoint3);
        		}
        		int numF = (frac1Num * frac2Denom) - (frac1Denom * frac2Num);
               	int denomF = frac1Denom * frac2Denom;
            	String checkpoint3 = numF + "/" + denomF;
            	return reduce(checkpoint3);
        	}
        }

        else if(operator.equals("/")){
        	if(frac1Num == 0) {
        		return reduce("0/1");
        	}
        	else{
        		int numF2 = frac2Denom;
        		int denomF2 = frac2Num;
        		int num = frac1Num * numF2;
        		int denom = frac1Denom * denomF2;
	        	String checkpoint3 = num + "/" + denom;
	        	return reduce(checkpoint3);
        	}
        }
        
        else {
        	return "error";
        }
        
       
    }

    // TODO: Fill in the space below with any helper methods that you think you will need


    public static int findNumerator(String str) {
        if(str.contains("_")) {
            if(Integer.parseInt(str.substring(0, str.indexOf('_'))) < 0) {
            	return Integer.parseInt("-" + str.substring(str.indexOf('_') + 1, str.indexOf('/')));
            }
            else {
            	return Integer.parseInt(str.substring(str.indexOf('_') + 1, str.indexOf('/')));
            }
        }
        else if(str.contains("/")) {
            return Integer.parseInt(str.substring(0, str.indexOf('/')));
        }
        else {
            return 0;
        }
    }
    
    public static int findWhole(String str) {
        //3_4/5     4/7     56
        // mixed number the "_" before is going to be for the mixed numbers
        if(str.contains("_")) {
            String whole = str.substring(0, str.indexOf('_'));
            return Integer.parseInt(whole);
        }
        //fractional part, this is a fractional part for the project, because it contains the "/" in there
        else if(str.contains("/")) {
            return 0;
        }
        //whole number the whole number is the big part of the string in the user input give the part before _
        else return Integer.parseInt(str);
    }

    public static int findDenominator(String str) {
    	//the denominator is always after the "/" so then this code just identifies that and then parses the value as a string.
        if(str.contains("/")){
            return Integer.parseInt(str.substring(str.indexOf("/") + 1));
        }
        else{
            return 1;
        }
    } 
    
    public static String reduce(String str) {
    	
    	//Reduce the numerator and denominator by first finding the numerator and denominator and then finding the actual simplified version
        int num = Integer.parseInt(str.substring(0, str.indexOf('/')));
        int denom = Integer.parseInt(str.substring(str.indexOf('/') + 1));
        int num1=num;
        int denom1=denom;
        String answer;
        int j = 2;
        if(num == 0) {
            return "0";
        }
        if (num == denom) {
            return "1";
        }
        num=Math.abs(num);
        denom = Math.abs(denom);
        
        //Whole Number Calculations; reduce the whole number in this code over here
        int wholeNum = 0;
        if (Math.abs(num) > Math.abs(denom)) {
        	if(denom == 1){
        		wholeNum = num;
            }
        	wholeNum = num / denom;
        	num = num % denom;
        	if(num == 0) {
        		answer = String.valueOf(wholeNum);
        	}
        	else {
        		if (denom % num == 0) {
	                denom = denom / num;
	                num = 1;
        		}
        		else {
        			while(num % j == 0 && denom % j == 0) {
                    num = num / j;
                    denom = denom / j;
        			}
        			j++;
        			
        			while (j < num) {
                    if (num % j == 0 && denom % j == 0) {
                        num = num / j;
                        denom = denom / j;
                    }
                    j++;
        			}
        		}
        		answer = wholeNum + "_" + num + "/" + denom;
        	}
        }
        //find the GCF with the denominator and the numerator by dividing them and then find the new number
        else if (denom % num == 0) {
            denom = denom / num;
            num = 1;
            answer = num + "/" + denom;
        }
        else {
            int i = num - 1;
            while (i > 1) {
                while (num % i == 0 && denom % i == 0) {
                    num = num / i;
                    denom = denom / i;
                }
                i--;
            }
            answer = num + "/" + denom;
        }
      //negative or positive, find whether the numerator or denominator is posivitive so we can find the "- -" answer.
        boolean negativeAnswer = false;
        if (num1 < 0 && denom1 < 0){
        	negativeAnswer=false;
        }
        if((num1 < 0 && denom1 > 0) || (num1 > 0 && denom1 < 0)){
        	negativeAnswer = true;
        }
        if(negativeAnswer) {
        	answer = "-"+answer;
        }

        return answer;
    }
}




	
	
	
	
	
	
	
	
	
	