/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.fraction;

import java.util.StringTokenizer;

/**
 *
 * @author Bojo Alcisto
 */
public class Fraction{
    private int numerator;
    private int denominator;
    private int wholeNumber;
    private static boolean reduce = true;
    /**
     * Set if it is preferred to reduce fractions manually
     * @param x boolean default set to true
     */
    public static void autoReduce(boolean x){
        Fraction.reduce = x;
    }
    
    public Fraction(int wholeNum, int numerator, int denominator){
        if(denominator == 0) throw new ArithmeticException("Cannot have 0 as denominator");
        this.numerator = numerator;
        this.denominator = denominator;
        this.wholeNumber = wholeNum;
        if(reduce == true) this.reduce(this.wholeNumber, this.numerator, this.denominator);
    }
    
    public Fraction(String fraction){
        StringTokenizer t = new StringTokenizer(fraction, " /", true);
        
        String[] tokens = new String[t.countTokens()];
        int i = 0;
        while(t.hasMoreTokens()){
            tokens[i++] = t.nextToken();
        }
        
        if(tokens.length == 1){
            this.numerator = Integer.parseInt(tokens[0]);
            this.denominator = 1;
            this.wholeNumber = 0;
        }else if(
                tokens.length == 3 &&
                (tokens[0].matches("\\d+") || tokens[0].matches("-\\d+")) && 
                tokens[1].equals("/") &&
                tokens[2].matches("\\d+")
                ){
                
                this.numerator = Integer.parseInt(tokens[0]);
                this.denominator = Integer.parseInt(tokens[2]);
                this.wholeNumber = 0;
        }else if(tokens.length == 5 &&
                tokens[0].matches("\\d+") &&
                tokens[1].equals(" ") &&
                tokens[2].matches("\\d+") &&
                tokens[3].equals("/") &&
                tokens[4].matches("\\d+")
                ){
                this.wholeNumber = Integer.parseInt(tokens[0]);
                this.numerator = Integer.parseInt(tokens[2]);
                this.denominator = Integer.parseInt(tokens[4]);
        }else{
            throw new ArithmeticException("Wrong Fraction Format");
        }
        if(this.denominator == 0) throw new ArithmeticException("Cannot have 0 as denominator");
        if(reduce == true) this.reduce(this.wholeNumber, this.numerator, this.denominator);
    }
    
    public Fraction(){
        this(0,0,1);
    }
    
    public Fraction(int numerator, int denominator){
        this(0,numerator, denominator);
    }
    
    public Fraction(double fraction){
        this.wholeNumber = (int)fraction;
       
        String check = Double.toString(fraction);
        StringTokenizer s = new StringTokenizer(check, ".", false); //converts to string and tokenize the
                                                                    //to isolate decimal value
        
        String token = new String();
        while(s.hasMoreTokens()) token = s.nextToken(); //isolate decimal value
        
        int placeValue = 1; //for loop to find placeValue of the decimal value
        for(int i = 0; i<token.length(); i++)placeValue *= 10;
        
        
        double tempFraction = fraction - (double)this.wholeNumber; //delete the wholenumber
        //System.out.println(tempFraction);
        this.numerator = (int)(tempFraction*(double)placeValue); //assign class variables
        
        this.denominator = placeValue;
        if(this.denominator == 0) throw new ArithmeticException("Cannot have 0 as denominator");
        if(reduce == true) this.reduce(this.wholeNumber, this.numerator, this.denominator);
    }
    
    public Fraction(int wholeNumber){
        this(0,wholeNumber,1);
    }
    
    public static Fraction copy(Fraction fraction){
        return new Fraction(fraction.getWholeNumber(), fraction.getNumerator(), fraction.denominator);
    }
    
    public int getNumerator(){
        return this.numerator;
    }
    public int getDenominator(){
        return this.denominator;
    }
    public int getWholeNumber(){
        return this.wholeNumber;
    }
    
    public String getFraction(){
        String retval;
        
        retval = String.valueOf(this.getNumerator());
        
        if(this.getWholeNumber() != 0){
            retval = this.getWholeNumber() + " " + retval;
        }
        if(this.getNumerator() != 0 && this.getDenominator() != 1){
            retval = retval + "/" + this.getDenominator();
        }
        
        return retval;
    }
    
    public void setFraction(String fraction){
        Fraction f = new Fraction(fraction);
        
        this.numerator = f.getNumerator();
        this.denominator = f.getDenominator();
        this.wholeNumber = f.getWholeNumber();
    }
    
    public void setFraction(int wholeNumber, int numerator, int denominator){
        Fraction f = new Fraction(wholeNumber, numerator, denominator);
        this.numerator = f.getNumerator();
        this.denominator = f.getDenominator();
        this.wholeNumber = f.getWholeNumber();
    }
    
    public void setFraction(double fraction){
        Fraction f = new Fraction(fraction);
        this.numerator = f.getNumerator();
        this.denominator = f.getDenominator();
        this.wholeNumber = f.getWholeNumber();
    }
    
    public void setFraction(int numerator, int denominator){
        Fraction f = new Fraction(numerator, denominator);
        this.numerator = f.getNumerator();
        this.denominator = f.getDenominator();
        this.wholeNumber = f.getWholeNumber();
    }
    
    public void setFraction(int wholeNumber){
        Fraction f = new Fraction(wholeNumber);
        this.numerator = f.getNumerator();
        this.denominator = f.getDenominator();
        this.wholeNumber = f.getWholeNumber();
    }
    
    public void setDenominator(int denominator){
        this.denominator = denominator;
    }
    
    public void setNumerator(int numerator){
        this.numerator = numerator;
    }
    
    public void setWholeNumber(int wholeNumber){
        this.wholeNumber = wholeNumber;
    }
    
     public static Fraction mixedToImproper(Fraction x) {
    	Fraction ret;
    	
        int newNum = x.getDenominator()*x.getWholeNumber()+x.getNumerator();
        String s = Integer.toString(newNum)+"/"+Integer.toString(x.getDenominator());
        //System.out.println(s);
        ret = new Fraction(s);
        return ret;
    }
     
    public Fraction mixedToImproper(){
         Fraction f = mixedToImproper(this);
         
         this.setFraction(f.getWholeNumber(), f.getNumerator(), f.getDenominator());
         
         return f;
    }
    
    public static Fraction improperToMixed(Fraction improper){
        Fraction ret;
        
        int WN;
        if(improper.getWholeNumber() == 0)
            WN = improper.numerator / improper.denominator;
        else{
            WN = improper.wholeNumber + (improper.numerator/improper.denominator);
        }
    	int numerator = improper.numerator % improper.denominator;
        
        String output;
        if(numerator == 0) {
            output = Integer.toString(WN) + "/1";
        }else{
            output = Integer.toString(WN) + " " + numerator+"/"+improper.denominator;
        }
        ret = new Fraction(output);
    	return ret;
    }
    
    public Fraction improperToMixed(){
        Fraction f = improperToMixed(this);
        this.setFraction(f.wholeNumber, f.numerator, f.denominator);
        
        return f;
    }
    
    private void reduce(int wholeNumber, int numerator, int denominator){
        boolean negative = false; 
        if(numerator < 0){
            numerator *= -1;
             negative = true;
         }
        
        if(wholeNumber != 0 && numerator >= denominator){
            int WN = wholeNumber + (numerator/denominator);
            
            numerator = numerator % denominator;

            if(numerator == 0) {
                wholeNumber = 0;
                numerator = WN;
                denominator = 1;
            }else{
                wholeNumber = WN;
            }
        }
           if(numerator != 0){ 
            int smaller;
            smaller = numerator < denominator ? numerator: denominator;
            int HCF = 0;
            for(int i=smaller;i>0;--i){
                if(numerator%i==0 && denominator%i== 0){
                        HCF = i;

                        break;
                }
            }

              numerator = numerator/HCF;
              denominator = denominator/HCF;

              if(negative){
                  numerator *= -1;
              }
           }
            this.wholeNumber = wholeNumber;
            this.numerator = numerator;
            this.denominator = denominator;
    }
    
    public static Fraction reduce(Fraction sum) {
		// TODO Auto-generated method stub
                boolean negative = false; 
                if(sum.numerator < 0){
                    sum.numerator *= -1;
                    negative = true;
                 }
                
            if(sum.numerator == 0) return new Fraction(0);    
            if(sum.wholeNumber != 0 && sum.numerator >= sum.denominator){
                int WN = sum.wholeNumber + (sum.numerator/sum.denominator);

                sum.numerator = sum.numerator % sum.denominator;

                if(sum.numerator == 0) {
                    sum.wholeNumber = 0;
                    sum.numerator = WN;
                    sum.denominator = 1;
                }else{
                    sum.wholeNumber = WN;
                }
            }
            
            int smaller;
	      Fraction ret;
	      String finale;
	      smaller = sum.numerator < sum.denominator ? sum.numerator:sum.denominator;
	      
	      int HCF = 0;
	      for(int i=smaller;i>0;--i){
	    	  if(sum.numerator%i==0 && sum.denominator%i== 0){
	    		  HCF = i;
	    		  
	    		  break;
	    	  }
	      }
	      sum.numerator = (sum.numerator/HCF);
              if(negative) sum.numerator *= -1;
	      sum.denominator = sum.denominator/HCF;
	      ret = new Fraction(sum.wholeNumber, sum.numerator, sum.denominator);
	      return ret;
    }
    
    public Fraction reduce(){
        Fraction f = Fraction.reduce(this);
        this.setFraction(f.wholeNumber, f.numerator, f.denominator);
        
        return f;
    }
    
    public static Fraction add(Fraction x,Fraction y){
    	 int WN = 0;
    	 
    	 Fraction ret1 = x,ret2 = y;
    	 
    	 if(x.getWholeNumber() !=0 && y.getWholeNumber()!=0) {
	    	 ret1 = mixedToImproper(x);
	    	 ret2 = mixedToImproper(y);
    	 }else if(x.getWholeNumber() !=0 && y.getWholeNumber() == 0){
    		 ret1 = mixedToImproper(x);
    	 }else if(y.getWholeNumber() !=0 && x.getWholeNumber() == 0){
    		 ret2 = mixedToImproper(y);
    	 }else {
    		 ret1 = x;
    		 ret2 = y;
    	 }
    	 
    	 String finale = null;
    	 Fraction rico;
    	 
         if(ret1.getDenominator() == ret2.getDenominator()) {
        	 int sumNum = ret1.numerator + ret2.numerator;
        	 int sumDenum = ret1.denominator;
        	 finale = Integer.toString(WN) + " " + Integer.toString(sumNum)+"/"+Integer.toString(sumDenum);
                 rico = new Fraction(WN,sumNum,sumDenum);
         }else {
        	 int lcm = lcm(ret1.denominator,ret2.denominator);
        	 int n1 = lcm/ret1.denominator*ret1.numerator;
        	 int n2 = lcm/ret2.denominator*ret2.numerator;
        	 int sumNum = n1+n2;
        	 int sumDenum = lcm;
        	 finale = Integer.toString(WN) + " " + Integer.toString(sumNum)+"/"+Integer.toString(sumDenum);
        	 rico = new Fraction(WN,sumNum,sumDenum); 
         }
         return rico;
    }
    
    public Fraction add(Fraction x){
        Fraction sum = Fraction.add(this, x);
        this.setFraction(sum.wholeNumber, sum.numerator, sum.denominator);
        return sum;
    }
    
    public static int lcm(int d1,int d2){
    	 int lcm = (d1>d2)?d1:d2;
    	 while(true) {
    		 if(lcm %d1 == 0 && lcm %d2==0) {
    			 break;
    		 }
    		 ++lcm;
    	 }
    	 return lcm;
    }
    public static Fraction subtract(Fraction x, Fraction y){
    	 int WN = 0;
    	 
    	 Fraction ret1 = x,ret2 = y;
    	 
    	 if(x.getWholeNumber() !=0 && y.getWholeNumber()!=0) {
	    	 ret1 = mixedToImproper(x);
	    	 ret2 = mixedToImproper(y);
    	 }else if(x.getWholeNumber() !=0 && y.getWholeNumber() == 0){
    		 ret1 = mixedToImproper(x);
    	 }else if(y.getWholeNumber() !=0 && x.getWholeNumber() == 0){
    		 ret2 = mixedToImproper(y);
    	 }else {
    		 ret1 = x;
    		 ret2 = y;
    	 }
    	 
    	 String finale = null;
    	 Fraction rico;
    	 
         if(ret1.getDenominator() == ret2.getDenominator()) {
        	 int diffNum = ret1.numerator - ret2.numerator;
        	 int diffDenum = ret1.denominator;
                 rico = new Fraction(WN, diffNum, diffDenum);
         }else {
        	 int lcm = lcm(x.denominator,y.denominator);
        	 int n1 = lcm/x.denominator*x.numerator;
        	 int n2 = lcm/y.denominator*y.numerator;
        	 int diffNum = n1-n2;
        	 int diffDenum = lcm;
                 rico = new Fraction(WN, diffNum, diffDenum);
         }
         return rico;
    }
    
    public Fraction subtract(Fraction x){
        Fraction diff = Fraction.subtract(this, x);
        this.setFraction(diff.wholeNumber, diff.numerator, diff.denominator);
        
        return diff;
    }
    
    public static Fraction multiply(Fraction x, Fraction y){
        x.mixedToImproper();
        y.mixedToImproper();
        
        int numerator = x.getNumerator() * y.getNumerator();
        int denominator = x.getDenominator() * y.getDenominator();
        
        return new Fraction(numerator,denominator);
    }
    
    public Fraction multiply(Fraction x){
        Fraction ret = Fraction.multiply(this, x);
        
        this.setFraction(ret.getWholeNumber(), ret.getNumerator(), ret.getDenominator());
        
        return ret;
    }
    
    public static Fraction divide(Fraction x, Fraction y){
        return Fraction.multiply(new Fraction(x.wholeNumber ,x.numerator, x.denominator),
                new Fraction(y.wholeNumber, y.denominator, y.numerator));
    }
    
    public Fraction divide(Fraction x){
        Fraction quotient = Fraction.divide(this, x);
        
        this.setFraction(quotient.wholeNumber, quotient.numerator, quotient.denominator);
        
        return quotient;
    }
}
