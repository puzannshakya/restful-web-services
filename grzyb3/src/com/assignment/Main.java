package com.assignment;

import com.assignment.departmentstorequeues.*;
import com.assignment.postfixcalculator.PostfixCalculator;
import com.assignment.postfixcalculator.PostfixCalculatorException;


public class Main {
    static PostfixCalculator calculator;

    public static void main(String[] args) {
        calculator = new PostfixCalculator();
        try{
            int a = calculator.evaluate("100 100 * 100 / 100 + 50 / ");
        }catch(IllegalArgumentException| PostfixCalculatorException e){
            System.err.print(e);
        }

        System.out.println("Customer Test Cases ");
        Customer customer = new Customer("Ed", 3);
        // a customer status should always start at 'SHOPPING'
        if(CustomerStatus.SHOPPING.equals(customer.getCustomerStatus())){
            System.out.println("True1");
        }
        if("Ed".equals(customer.getName())){
            System.out.println("True2");
        }
        if(3 == customer.getNumberOfItems()){
            System.out.println("True3");
        }
        System.out.println("");
        System.out.println("");


        System.out.println("CashRegister Test Cases ");

         //Tests processCustomer with no Customer at register

        CashRegister cashRegister = new CashRegister(1, 3);
        cashRegister.processCustomer();
        if(cashRegister.getCustomer() == null){
            System.out.println("True1");
        }

        testProcessCustomer1Item();
        testProcessCustomer7Items();

        System.out.println("DepartmentStore Test Cases ");
        try{
            testOpenCashRegister();
            testOpenTooManyCashRegisters();
            testCloseCashRegister();
            testOpenCloseSeveralCashRegisters();
            testCloseCashRegisterWithCustomer();
            testAddCustomerToLine();
            testManageCashRegistersEmptyLine();
            testManageCashRegisters1();
            test10Customers();
        }catch(Exception e){
            System.err.print(e);
        }


    }

    /**
     * Tests processCustomer with Customer that has 1 item
     * and scan efficiency of 3 so the customer status
     * should change each time we call process customer
     */
    public static void testProcessCustomer1Item() {
        CashRegister cashRegister = new CashRegister(1, 3);
        Customer customer = new Customer("Ed", 1);
        // customer starts in a SHOPPING status
        if(CustomerStatus.SHOPPING.equals(customer.getCustomerStatus())){
            System.out.println("True1");
        }
        customer.setCustomerStatus(CustomerStatus.WAITING_IN_LINE);
        cashRegister.setCustomer(customer);
        if(CustomerStatus.WAITING_IN_LINE.equals(cashRegister.getCustomer().getCustomerStatus())){
            System.out.println("True2");
        }
        //System.out.println(" aaaaa " + cashRegister);
        cashRegister.processCustomer();
        //System.out.println(" aaaaa " + cashRegister);
        //System.out.println(" BBBB " + cashRegister.getCustomer().getCustomerStatus());
        if(CustomerStatus.SCANNING_MERCHANDISE.equals(cashRegister.getCustomer().getCustomerStatus())){
            System.out.println("True3");
        }
        cashRegister.processCustomer();
        if(CustomerStatus.PROCESSING_PAYMENT.equals(cashRegister.getCustomer().getCustomerStatus())){
            System.out.println("True4");
        }
        cashRegister.processCustomer();
        if(CustomerStatus.PURCHASE_COMPLETE.equals(cashRegister.getCustomer().getCustomerStatus())){
            System.out.println("True5");
        }

    }

    /**
     * Tests processCustomer with Customer that has 7 items
     * and scan efficiency of 3 so the customer status
     **/

    public static void testProcessCustomer7Items() {
        CashRegister cashRegister = new CashRegister(1, 3);
        Customer customer = new Customer("Ed", 7);
        // customer starts in a SHOPPING status
        if(CustomerStatus.SHOPPING.equals(customer.getCustomerStatus())){
            System.out.println("True1");
        }
        customer.setCustomerStatus(CustomerStatus.WAITING_IN_LINE);
        cashRegister.setCustomer(customer);
        if(CustomerStatus.WAITING_IN_LINE.equals(cashRegister.getCustomer().getCustomerStatus())){
            System.out.println("True2");
        }
        cashRegister.processCustomer();
        if(7 == (cashRegister.getCustomer().getNumberOfItems())){
            System.out.println("True3");
        }

        if(CustomerStatus.SCANNING_MERCHANDISE.equals(cashRegister.getCustomer().getCustomerStatus())){
            System.out.println("True4");
        }
        cashRegister.processCustomer();
        if(4==(cashRegister.getCustomer().getNumberOfItems())){
            System.out.println("True5");
        }
        if(CustomerStatus.SCANNING_MERCHANDISE.equals(cashRegister.getCustomer().getCustomerStatus())){
            System.out.println("True6");
        }
        cashRegister.processCustomer();
        if(1==(cashRegister.getCustomer().getNumberOfItems())){
            System.out.println("True7");
        }
        if(CustomerStatus.SCANNING_MERCHANDISE.equals(cashRegister.getCustomer().getCustomerStatus())){
            System.out.println("True8");
        }
        cashRegister.processCustomer();
        if(0 == (cashRegister.getCustomer().getNumberOfItems())){
            System.out.println("True9");
        }
        if(CustomerStatus.PROCESSING_PAYMENT.equals(cashRegister.getCustomer().getCustomerStatus())){
            System.out.println("True10");
        }
        cashRegister.processCustomer();
        if(CustomerStatus.PURCHASE_COMPLETE.equals(cashRegister.getCustomer().getCustomerStatus())){
            System.out.println("True11");
        }
    }


    public static void testOpenCashRegister() throws Exception {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS,SCAN_EFFICIENCY);
        // no registers open at first
        if(store.getCashRegisters().isEmpty()){
            System.out.println("True1");
        }
        // open a register
        store.openCashRegister();
        // should be 1 register now
        if(1 == store.getCashRegisters().getLength()){
            System.out.println("True2");
        }
    }


    public static void testOpenTooManyCashRegisters() {
        final int MAX_REGISTERS = 2;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS,SCAN_EFFICIENCY);
        // no registers open at first
        if(store.getCashRegisters().isEmpty()){
            System.out.println("True1");
        }
        // open a register
        boolean twoRegistersOpen = false;
        boolean registerExceptionThrown = false;
        try {
            store.openCashRegister();
            store.openCashRegister();
            if(2 == store.getCashRegisters().getLength()){
                System.out.println("True2");
            }
            twoRegistersOpen = true;
            store.openCashRegister(); // this should throw exception since max registers is 2
        } catch (CashRegisterNotOpenedException e) {
            registerExceptionThrown = true;
        }
        if(twoRegistersOpen){
            System.out.println("True3");
        }
        if(registerExceptionThrown){
            System.out.println("True4");
        }
    }


    public static void testCloseCashRegister() throws Exception {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS,SCAN_EFFICIENCY);
        // no registers open at first
        if(store.getCashRegisters().isEmpty()){
            System.out.println("True1");
        }
        // open a register
        store.openCashRegister();
        // should be 1 register now
        if(1 == store.getCashRegisters().getLength()){
            System.out.println("True2");
        }
        store.closeCashRegister();
        if(0 == store.getCashRegisters().getLength()){
            System.out.println("True3");
        }
    }

    public static void testOpenCloseSeveralCashRegisters() throws Exception {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS,SCAN_EFFICIENCY);
        // no registers open at first
        if(store.getCashRegisters().isEmpty()){
            System.out.println("True1");
        }
        // open a register
        store.openCashRegister();
        // should be 1 register now
        if(1 == store.getCashRegisters().getLength()){
            System.out.println("True2");
        }
        store.openCashRegister();
        store.openCashRegister();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True3");
        }
        store.closeCashRegister();
        if(2 == store.getCashRegisters().getLength()){
            System.out.println("True4");
        }
        store.openCashRegister();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True5");
        }
        try {
            store.openCashRegister(); // max registers is 3 so this should fail
        } catch (CashRegisterNotOpenedException e) {
            // this is okay for this test
            System.out.println("Exception corretly got thrown");
        } finally {
            if(3 == store.getCashRegisters().getLength()){
                System.out.println("True6");
            }
        }
        store.closeCashRegister();
        store.closeCashRegister();
        store.closeCashRegister();
        if(0 == store.getCashRegisters().getLength()){
            System.out.println("True7");
        }
    }


    public static void testCloseCashRegisterWithCustomer()  {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS, SCAN_EFFICIENCY);
        // no registers open at first
        if(store.getCashRegisters().isEmpty()){
            System.out.println("True1");
        }

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
        if(registerClosed){
            System.out.println("True2");
        }
        if(1 == store.getCashRegisters().getLength()){
            System.out.println("True3");
        }
    }


    public static void testAddCustomerToLine() {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS, SCAN_EFFICIENCY);
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True1");
        }
        Customer customer1 = new Customer("Ed", 4);
        Customer customer2 = new Customer("Joe", 2);
        store.addCustomerToLine(customer1);
        if(1 == store.getCustomerLine().getLength()){
            System.out.println("True2");
        }
        store.addCustomerToLine(customer2);
        if(2 == store.getCustomerLine().getLength()){
            System.out.println("True3");
        }
    }


    public static void testManageCashRegistersEmptyLine() throws Exception {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS, SCAN_EFFICIENCY);
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True1");
        }
        store.manageCashRegisters();
        if(0 == store.getCashRegisters().getLength()){
            System.out.println("True2");
        }
    }


    public  static void testManageCashRegisters1() throws Exception {
        final int MAX_REGISTERS = 1;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS, SCAN_EFFICIENCY);
        if (0 == store.getCustomerLine().getLength()) {
            System.out.println("True1");
        }

        Customer customer1 = new Customer("Ed", 1);
        store.addCustomerToLine(customer1);
        store.manageCashRegisters();
        if (1 == store.getCashRegisters().getLength()) {
            System.out.println("True2");
        }

        store.manageCashRegisters();
        if (1 == store.getCashRegisters().getLength()) {
            System.out.println("True3");
        }
        store.processCustomers();
        store.processCustomers();
        store.processCustomers();
        store.processCustomers();
        store.processCustomers();
        store.manageCashRegisters();
        // customer finished but register should stay open
        if (0 == store.getCustomerLine().getLength()) {
            System.out.println("True4");
        }

        if (1 == store.getCashRegisters().getLength()) {
            System.out.println("True5");
        }
    }


    public static void test10Customers() throws Exception {
        final int MAX_REGISTERS = 3;
        final int SCAN_EFFICIENCY = 2;
        DepartmentStore store = new DepartmentStore(MAX_REGISTERS, SCAN_EFFICIENCY);
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True1");
        }
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
        if(10 == store.getCustomerLine().getLength()){
            System.out.println("True2");
        }
        // no registers open yet
        if(0 == store.getCashRegisters().getLength()){
            System.out.println("True3");
        }
        store.manageCashRegisters();
        System.out.println("True4 : store.getCashRegisters().getLength() : " + store.getCashRegisters().getLength());
        if(1 == store.getCashRegisters().getLength()){
            System.out.println("True4");
        }
        store.processCustomers();
        if(9 == store.getCustomerLine().getLength()){
            System.out.println("True5");
        }

        store.manageCashRegisters(); // should open another register
        if(9 == store.getCustomerLine().getLength()){
            System.out.println("True6");
        }

        if(2 == store.getCashRegisters().getLength()){
            System.out.println("True7");
        }
        store.processCustomers();
        if(8 == store.getCustomerLine().getLength()){
            System.out.println("True8");
        }

        store.manageCashRegisters(); // should open another register
        if(3 ==  store.getCashRegisters().getLength()){
            System.out.println("True9");
        }
        store.processCustomers();
        if(7 == store.getCustomerLine().getLength()){
            System.out.println("True10");
        }
        store.manageCashRegisters(); // should NOT open another register since none left
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True11");
        }
        store.processCustomers();
        if(7 == store.getCustomerLine().getLength()){
            System.out.println("True12");
        }
        store.manageCashRegisters(); // should NOT open another register since none left
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True13");
        }
        store.processCustomers();
        if(7 == store.getCustomerLine().getLength()){
            System.out.println("True14");
        }
        store.manageCashRegisters(); // should NOT open another register since none left
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True15");
        }
        store.processCustomers();
        if(7 == store.getCustomerLine().getLength()){
            System.out.println("True16");
        }
        store.manageCashRegisters(); // should NOT open another register since none left
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True17");
        }
        store.processCustomers();
        if(5 == store.getCustomerLine().getLength()){// two customers should be pulled from line
            System.out.println("True18");
        }
        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True19");
        }
        store.processCustomers();
        if(5 == store.getCustomerLine().getLength()){
            System.out.println("True20");
        }
        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True21");
        }
        store.processCustomers();
        if(5 == store.getCustomerLine().getLength()){
            System.out.println("True22");
        }
        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True23");
        }

        store.processCustomers();
        if(5 == store.getCustomerLine().getLength()){
            System.out.println("True24");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True25");
        }

        store.processCustomers();
        if(5 == store.getCustomerLine().getLength()){
            System.out.println("True26");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True27");
        }

        store.processCustomers();
        if(5 == store.getCustomerLine().getLength()){
            System.out.println("True28");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True29");
        }

        store.processCustomers();
        if(4 == store.getCustomerLine().getLength()){
            System.out.println("True30");
        }
        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True31");
        }
        store.processCustomers();
        if(3 == store.getCustomerLine().getLength()){
            System.out.println("True32");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True33");
        }

        store.processCustomers();
        if(2 == store.getCustomerLine().getLength()){
            System.out.println("True34");
        }

        store.manageCashRegisters();
        if(3 ==  store.getCashRegisters().getLength()){
            System.out.println("True35");
        }

        store.processCustomers();
        if(2 == store.getCustomerLine().getLength()){
            System.out.println("True36");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True37");
        }

        store.processCustomers();
        if(2 == store.getCustomerLine().getLength()){
            System.out.println("True38");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True39");
        }

        store.processCustomers();
        if(2 == store.getCustomerLine().getLength()){
            System.out.println("True40");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True41");
        }

        store.processCustomers();
        if(1 == store.getCustomerLine().getLength()){
            System.out.println("True42");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True43");
        }

        store.processCustomers();
        if(1 ==  store.getCustomerLine().getLength()){
            System.out.println("True44");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True45");
        }

        store.processCustomers();
        if(1 == store.getCustomerLine().getLength()){
            System.out.println("True46");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True47");
        }

        store.processCustomers();
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True48");
        }

        store.manageCashRegisters();
        if(3 ==  store.getCashRegisters().getLength()){
            System.out.println("True49");
        }

        store.processCustomers();
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True50");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True51");
        }

        store.processCustomers();
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True52");
        }

        store.manageCashRegisters();
        if(3 == store.getCashRegisters().getLength()){
            System.out.println("True53");
        }

        store.processCustomers();
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True54");
        }

        store.manageCashRegisters();
        if(3 ==  store.getCashRegisters().getLength()){
            System.out.println("True55");
        }

        store.processCustomers();
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True56");
        }

        store.manageCashRegisters();
        if(2 == store.getCashRegisters().getLength()){
            System.out.println("True57");
        }

        store.processCustomers();
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True58");
        }

        store.manageCashRegisters();
        if(1 == store.getCashRegisters().getLength()){
            System.out.println("True59");
        }

        store.processCustomers();
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True60");
        }

        store.manageCashRegisters();
        if(1 == store.getCashRegisters().getLength()){
            System.out.println("True61");
        }

        store.processCustomers();
        if(0 == store.getCustomerLine().getLength()){
            System.out.println("True62");
        }

    }

}
