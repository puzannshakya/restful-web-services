package com.assignment;

import com.assignment.departmentstorequeues.CashRegister;
import com.assignment.departmentstorequeues.Customer;
import com.assignment.departmentstorequeues.CustomerStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ed
 */
public class CashRegisterTest {
    
    public CashRegisterTest() {
    }
    
    /**
     * Tests processCustomer with no Customer at register      
     */
    @Test
    public void testProcessCustomerNoCustomer() {
        CashRegister cashRegister = new CashRegister(1, 3);
        cashRegister.processCustomer();
        assertNull(cashRegister.getCustomer());
    }
    
    /**
     * Tests processCustomer with Customer that has 1 item
     * and scan efficiency of 3 so the customer status
     * should change each time we call process customer
     */
    @Test
    public void testProcessCustomer1Item() {
        CashRegister cashRegister = new CashRegister(1, 3);
        Customer customer = new Customer("Ed", 1);
        // customer starts in a SHOPPING status
        assertEquals(CustomerStatus.SHOPPING,customer.getCustomerStatus());
        customer.setCustomerStatus(CustomerStatus.WAITING_IN_LINE);
        cashRegister.setCustomer(customer);
        assertEquals(CustomerStatus.WAITING_IN_LINE,cashRegister.getCustomer().getCustomerStatus());
        cashRegister.processCustomer();
        assertEquals(CustomerStatus.SCANNING_MERCHANDISE,cashRegister.getCustomer().getCustomerStatus());
        cashRegister.processCustomer();  
        assertEquals(CustomerStatus.PROCESSING_PAYMENT,cashRegister.getCustomer().getCustomerStatus());
        cashRegister.processCustomer();
        assertEquals(CustomerStatus.PURCHASE_COMPLETE,cashRegister.getCustomer().getCustomerStatus());
    }    
    
    /**
     * Tests processCustomer with Customer that has 7 items
     * and scan efficiency of 3 so the customer status
     * 
     */
    @Test
    public void testProcessCustomer7Items() {
        CashRegister cashRegister = new CashRegister(1, 3);
        Customer customer = new Customer("Ed", 7);
        // customer starts in a SHOPPING status
        assertEquals(CustomerStatus.SHOPPING,customer.getCustomerStatus());
        customer.setCustomerStatus(CustomerStatus.WAITING_IN_LINE);
        cashRegister.setCustomer(customer);
        assertEquals(CustomerStatus.WAITING_IN_LINE,cashRegister.getCustomer().getCustomerStatus());
        cashRegister.processCustomer();
        assertEquals(7, cashRegister.getCustomer().getNumberOfItems());
        assertEquals(CustomerStatus.SCANNING_MERCHANDISE,cashRegister.getCustomer().getCustomerStatus());
        cashRegister.processCustomer();  
        assertEquals(4, cashRegister.getCustomer().getNumberOfItems());
        assertEquals(CustomerStatus.SCANNING_MERCHANDISE,cashRegister.getCustomer().getCustomerStatus());
        cashRegister.processCustomer();  
        assertEquals(1, cashRegister.getCustomer().getNumberOfItems());        
        assertEquals(CustomerStatus.SCANNING_MERCHANDISE,cashRegister.getCustomer().getCustomerStatus());
        cashRegister.processCustomer();  
        assertEquals(0, cashRegister.getCustomer().getNumberOfItems());                
        assertEquals(CustomerStatus.PROCESSING_PAYMENT,cashRegister.getCustomer().getCustomerStatus());
        cashRegister.processCustomer();
        assertEquals(CustomerStatus.PURCHASE_COMPLETE,cashRegister.getCustomer().getCustomerStatus());
    }     

    
}
