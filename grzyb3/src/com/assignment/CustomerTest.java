/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.assignment;

import com.assignment.departmentstorequeues.Customer;
import com.assignment.departmentstorequeues.CustomerStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ed
 */
public class CustomerTest {
    
    public CustomerTest() {
    }

    @Test
    public void testCustomer() {
        System.out.println("getStatus");
        Customer customer = new Customer("Ed", 3);
        // a customer status should always start at 'SHOPPING'
        assertEquals(CustomerStatus.SHOPPING, customer.getCustomerStatus());
        assertEquals("Ed", customer.getName());
        assertEquals(3, customer.getNumberOfItems());
    }


    
}
