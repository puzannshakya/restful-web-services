package com.assignment;

import com.assignment.departmentstorequeues.CashRegisterNotClosedException;
import com.assignment.departmentstorequeues.CashRegisterNotOpenedException;
import com.assignment.departmentstorequeues.Customer;
import com.assignment.departmentstorequeues.DepartmentStore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Ed
 */
public class DepartmentStoreTest {
    
    public DepartmentStoreTest() {
    }

    @Test
    public void testOpenCashRegister() throws Exception {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS,SCAN_EFFICIENCY);
        // no registers open at first
        assertTrue(store.getCashRegisters().isEmpty());
        // open a register
        store.openCashRegister();
        // should be 1 register now
        assertEquals(1, store.getCashRegisters().getLength());                
    }
    
    @Test
    public void testOpenTooManyCashRegisters() {
        final int MAX_REGISTERS = 2;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS,SCAN_EFFICIENCY);
        // no registers open at first
        assertTrue(store.getCashRegisters().isEmpty());
        // open a register
        boolean twoRegistersOpen = false;
        boolean registerExceptionThrown = false;
        try {
            store.openCashRegister();
            store.openCashRegister();
            assertEquals(2, store.getCashRegisters().getLength());  
            twoRegistersOpen = true;
            store.openCashRegister(); // this should throw exception since max registers is 2
        } catch (CashRegisterNotOpenedException e) {
            registerExceptionThrown = true;
        }        
        assertTrue(twoRegistersOpen);
        assertTrue(registerExceptionThrown);
    }    
    
    @Test
    public void testCloseCashRegister() throws Exception {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS,SCAN_EFFICIENCY);
        // no registers open at first
        assertTrue(store.getCashRegisters().isEmpty());
        // open a register
        store.openCashRegister();
        // should be 1 register now
        assertEquals(1, store.getCashRegisters().getLength());          
        store.closeCashRegister();        
        assertEquals(0, store.getCashRegisters().getLength());  
    }    

    @Test
    public void testOpenCloseSeveralCashRegisters() throws Exception {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS,SCAN_EFFICIENCY);
        // no registers open at first
        assertTrue(store.getCashRegisters().isEmpty());
        // open a register
        store.openCashRegister();
        // should be 1 register now
        assertEquals(1, store.getCashRegisters().getLength());          
        store.openCashRegister();
        store.openCashRegister();
        assertEquals(3, store.getCashRegisters().getLength());                  
        store.closeCashRegister();        
        assertEquals(2, store.getCashRegisters().getLength());                          
        store.openCashRegister();
        assertEquals(3, store.getCashRegisters().getLength());                          
        try {
            store.openCashRegister(); // max registers is 3 so this should fail        
        } catch (CashRegisterNotOpenedException e) {
            // this is okay for this test
            System.out.println("Exception corretly got thrown");
        } finally {
            assertEquals(3, store.getCashRegisters().getLength()); 
        }
        store.closeCashRegister();
        store.closeCashRegister();
        store.closeCashRegister();
        assertEquals(0, store.getCashRegisters().getLength());  
    }      
    
    
    @Test
    public void testCloseCashRegisterWithCustomer()  {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS, SCAN_EFFICIENCY);
        // no registers open at first
        assertTrue(store.getCashRegisters().isEmpty());
        // open a register
        try {
            store.openCashRegister();
        } catch (CashRegisterNotOpenedException e) {
            System.out.println(e.getMessage());
        }
        // should be 1 register now
        store.getCashRegisters().getEntry(0).setCustomer(new Customer("Ed", 7));
        boolean registerClosed = false;
        try {
            // this should fail/throw exception because you can't close a register if a Customer is present
            store.closeCashRegister();        
            registerClosed = true;
        } catch (CashRegisterNotClosedException e) {
            System.out.println("Register not closed because Customer is present, " + e.getMessage());
        }
        assertFalse(registerClosed);
        assertEquals(1, store.getCashRegisters().getLength());  
    }      
    
    @Test
    public void testAddCustomerToLine() {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS, SCAN_EFFICIENCY);
        assertEquals(0, store.getCustomerLine().getLength());
        Customer customer1 = new Customer("Ed", 4);
        Customer customer2 = new Customer("Joe", 2);
        store.addCustomerToLine(customer1);
        assertEquals(1, store.getCustomerLine().getLength());
        store.addCustomerToLine(customer2);
        assertEquals(2, store.getCustomerLine().getLength());
    }
    
    @Test
    public void testManageCashRegistersEmptyLine() throws Exception {    
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS, SCAN_EFFICIENCY);        
        assertEquals(0, store.getCustomerLine().getLength());
        store.manageCashRegisters();
        assertEquals(0, store.getCashRegisters().getLength());
    }
    
    @Test
    public void testManageCashRegisters1() throws Exception {    
        final int MAX_REGISTERS = 1;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS, SCAN_EFFICIENCY);        
        assertEquals(0, store.getCustomerLine().getLength());
        Customer customer1 = new Customer("Ed", 1);
        store.addCustomerToLine(customer1);
        store.manageCashRegisters();
        assertEquals(1, store.getCashRegisters().getLength());
        store.manageCashRegisters();      
        assertEquals(1, store.getCashRegisters().getLength());    
        store.processCustomers();
        store.processCustomers();
        store.processCustomers();
        store.processCustomers();
        store.processCustomers();        
        store.manageCashRegisters();
        // customer finished but register should stay open
        assertEquals(0, store.getCustomerLine().getLength());
        assertEquals(1, store.getCashRegisters().getLength());  
        
        
    }    
    
    @Test
    public void test10Customers() throws Exception {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS, SCAN_EFFICIENCY);        
        assertEquals(0, store.getCustomerLine().getLength());
        Customer customer1 = new Customer("Ed", 4);
        Customer customer2 = new Customer("Joe", 2);
        Customer customer3 = new Customer("George", 11);
        Customer customer4 = new Customer("Jill", 5);
        Customer customer5 = new Customer("Fred", 8);
        Customer customer6 = new Customer("Gary", 20);
        Customer customer7 = new Customer("Sally", 1);
        Customer customer8 = new Customer("Rob", 6);
        Customer customer9 = new Customer("Lisa", 8);
        Customer customer0 = new Customer("Mary", 3);
        store.addCustomerToLine(customer1);
        store.addCustomerToLine(customer2);
        store.addCustomerToLine(customer3);
        store.addCustomerToLine(customer4);
        store.addCustomerToLine(customer5);
        store.addCustomerToLine(customer6);
        store.addCustomerToLine(customer7);
        store.addCustomerToLine(customer8);
        store.addCustomerToLine(customer9);
        store.addCustomerToLine(customer0);
        assertEquals(10, store.getCustomerLine().getLength());
        // no registers open yet
        assertEquals(0, store.getCashRegisters().getLength()); // this should open 1 register
        store.manageCashRegisters();
        assertEquals(1, store.getCashRegisters().getLength());
        store.processCustomers();
        assertEquals(9, store.getCustomerLine().getLength());
        store.manageCashRegisters(); // should open another register
        assertEquals(9, store.getCustomerLine().getLength());
        assertEquals(2, store.getCashRegisters().getLength());
        store.processCustomers();
        assertEquals(8, store.getCustomerLine().getLength());
        store.manageCashRegisters(); // should open another register
        assertEquals(3, store.getCashRegisters().getLength());        
        store.processCustomers();
        assertEquals(7, store.getCustomerLine().getLength());
        store.manageCashRegisters(); // should NOT open another register since none left
        assertEquals(3, store.getCashRegisters().getLength());        
        store.processCustomers();
        assertEquals(7, store.getCustomerLine().getLength());
        store.manageCashRegisters(); // should NOT open another register since none left        
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(7, store.getCustomerLine().getLength());
        store.manageCashRegisters(); // should NOT open another register since none left      
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(7, store.getCustomerLine().getLength()); 
        store.manageCashRegisters(); // should NOT open another register since none left  
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(5, store.getCustomerLine().getLength()); // two customers should be pulled from line   
        store.manageCashRegisters();     
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(5, store.getCustomerLine().getLength());   
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(5, store.getCustomerLine().getLength()); 
        store.manageCashRegisters();  
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(5, store.getCustomerLine().getLength()); 
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(5, store.getCustomerLine().getLength()); 
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(5, store.getCustomerLine().getLength()); 
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(4, store.getCustomerLine().getLength()); 
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(3, store.getCustomerLine().getLength());         
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(2, store.getCustomerLine().getLength());         
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(2, store.getCustomerLine().getLength());         
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(2, store.getCustomerLine().getLength());  
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(2, store.getCustomerLine().getLength());
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(1, store.getCustomerLine().getLength());
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(1, store.getCustomerLine().getLength());
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(1, store.getCustomerLine().getLength());
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(0, store.getCustomerLine().getLength());     
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(0, store.getCustomerLine().getLength());   
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(0, store.getCustomerLine().getLength());   
        store.manageCashRegisters();         
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(0, store.getCustomerLine().getLength());  
        store.manageCashRegisters();               
        assertEquals(3, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(0, store.getCustomerLine().getLength()); 
        store.manageCashRegisters();         
        assertEquals(2, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(0, store.getCustomerLine().getLength());  
        store.manageCashRegisters();         
        assertEquals(1, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(0, store.getCustomerLine().getLength());   
        store.manageCashRegisters();         
        assertEquals(1, store.getCashRegisters().getLength());          
        store.processCustomers();
        assertEquals(0, store.getCustomerLine().getLength());         
    }    
   
    
}
