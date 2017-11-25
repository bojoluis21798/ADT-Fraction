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
public class Fraction {
    private int numerator;
    private int denominator;
    private int wholeNumber;
    
    public Fraction(int wholeNum, int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
        this.wholeNumber = wholeNum;
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
                tokens[0].matches("\\d+") && 
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
            System.out.println("Wrong Format");
        }
        
        /*for(String token: tokens){
            System.out.println(token);
        }*/
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
    }
    
    public Fraction(int wholeNumber){
        this(0,wholeNumber,1);
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
        if(this.getDenominator() != 1){
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
}
