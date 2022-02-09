package com.assignment;

import com.assignment.postfixcalculator.PostfixCalculator;
import com.assignment.postfixcalculator.PostfixCalculatorException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for PostfixCalculator
 * 
 * @author Ed
 */
public class PostfixCalculatorTest {
    
    PostfixCalculator calculator;
    
    public PostfixCalculatorTest() {
        calculator = new PostfixCalculator();
    }

    @Test
    public void testEvaluate1() throws Exception {        
        String postfixExpression = "3 4 +";        
        int expResult = 7;
        int result = calculator.evaluate(postfixExpression);
        assertEquals(expResult, result);        
    }
    
    @Test
    public void testEvaluate2() throws Exception {        
        String postfixExpression = "8 4 /";
        int expResult = 2;
        int result = calculator.evaluate(postfixExpression);
        assertEquals(expResult, result);        
    }  
    
    @Test
    public void testEvaluate3() throws Exception {        
        String postfixExpression = "33 8 10 + % ";
        int expResult = 15;
        int result = calculator.evaluate(postfixExpression);
        assertEquals(expResult, result);        
    }    
    
    @Test
    public void testEvaluate4() throws Exception {        
        String postfixExpression = "5 -2 2 + / ";
        boolean exceptionThrown = false;
        try {
            calculator.evaluate(postfixExpression);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);        
    }   
    
    @Test
    public void testEvaluate5() throws Exception {        
        String postfixExpression = "11 11 11 11 11 11 + + + + + 6 /";
        int expResult = 11;
        int result = calculator.evaluate(postfixExpression);
        assertEquals(expResult, result);          
    }     
    
    @Test
    public void testEvaluate6() throws Exception {        
        String postfixExpression = "1 2 3 4 5 * * * * 10 / 2 - 10 +";
        int expResult = 20;
        int result = calculator.evaluate(postfixExpression);
        assertEquals(expResult, result);          
    }  
    
    @Test
    public void testEvaluate7() throws Exception {        
        String postfixExpression = "5 6 (";
        boolean exceptionThrown = false;
        try {
            calculator.evaluate(postfixExpression);
        } catch (PostfixCalculatorException e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);           
    }

        @Test
    public void testEvaluate8() throws Exception {        
        String postfixExpression = "0 4 * 3 + 3 + 4 * 4 + 7 + 7 /";
        int expResult = 5;
        int result = calculator.evaluate(postfixExpression);
        assertEquals(expResult, result);          
    }
    
    @Test
    public void testEvaluate9() throws Exception {        
        String postfixExpression = "18 11 % 7 * 42 -";
        int expResult = 7;
        int result = calculator.evaluate(postfixExpression);
        assertEquals(expResult, result);          
    }

        @Test
    public void testEvaluate10() throws Exception {        
        String postfixExpression = "100 100 * 100 / 100 + 50 / ";
        int expResult = 4;
        int result = calculator.evaluate(postfixExpression);
        assertEquals(expResult, result);          
    }
    
    
}
