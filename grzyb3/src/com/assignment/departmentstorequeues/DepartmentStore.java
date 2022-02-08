package com.assignment.departmentstorequeues;




/**
 * Class for DepartmentStore
 *
 * @author abc
 */
public class DepartmentStore {
    private ListQueue<Customer> customerLine;
    private ArrayList<CashRegister> cashRegisters;
    private int maximumCashRegisters;
    private int scanEfficiency;
    static  int numberCashRegister=0;


    /**
     * Constructor for DepartmentStore Class
     * @param maximumCashRegisters
     * @param scanEfficiency
     */
    public  DepartmentStore(int maximumCashRegisters, int scanEfficiency){
        this.maximumCashRegisters = maximumCashRegisters;
        this.scanEfficiency = scanEfficiency ;
        customerLine = new ListQueue<>();
        cashRegisters = new ArrayList<>();
    }


    public ArrayList<CashRegister> getCashRegisters() {
        return cashRegisters;
    }

    /**
     * Open Cash Register if number of cashRegister has not exceeded maximumCashRegister
     * @throws CashRegisterNotOpenedException
     */
    public void openCashRegister() throws CashRegisterNotOpenedException {
        if(this.maximumCashRegisters == cashRegisters.getLength()){
            throw new CashRegisterNotOpenedException("maximum number of cash registers has been reached");
        } else{
            CashRegister cashRegister = new CashRegister(numberCashRegister+1,scanEfficiency);
            cashRegisters.insert(0,cashRegister);
            System.out.println("New Cash Register : " + cashRegister + " Created  and added to cashRegisters ArrayList : " + cashRegisters);;
        }

    }

    /**
     * If the CashRegister does not have a Customer , close it
     * @throws CashRegisterNotClosedException
     */
    public void closeCashRegister() throws CashRegisterNotClosedException{
        Boolean noCloseCashRegister = Boolean.TRUE;
        for(int i =0 ; i<cashRegisters.getLength(); i++){
            if(cashRegisters.getEntry(i).getCustomer() == null){
                System.out.println("CashRegister : " + cashRegisters.getEntry(i) + " removed as it has no customer" );
                cashRegisters.remove(i);
                noCloseCashRegister = Boolean.FALSE;
                return;
            }
        }
        if(noCloseCashRegister){
            throw new CashRegisterNotClosedException("The CashRegister cannot be removed or there is no CashRegister to close");
        }
    }

    /**
     * Managing the CashRegisters depending on if Cases
     */
    public void manageCashRegisters(){
        System.out.println("Customers in Line : " + customerLine.getLength() );
        System.out.println("cashRegisters number : " + cashRegisters.getLength() );

        /**
         * If there are no CashRegisters open and there are customers in line, then
         *  open a cash register
         */
        if(this.cashRegisters.isEmpty() && !this.customerLine.isEmpty()){
            CashRegister cashRegister = new CashRegister(numberCashRegister+1,scanEfficiency);
            this.cashRegisters.insert(0,cashRegister);
            System.out.println("As CashRegister Empty and CustomerLine NonEmpty , cashRegister : " + cashRegister.toString() + " added to CashRegister Array List : " + this.cashRegisters.toString());
           return;
        }

        /**
         * If the customer line has more than 3 Customers and have more cash
         * registers available,  open a cash register
         */
        if(customerLine.getLength() >3 && cashRegisters.getLength() < this.maximumCashRegisters){
            CashRegister cashRegister = new CashRegister(numberCashRegister+1,scanEfficiency);
            cashRegisters.insert(0,cashRegister);
            System.out.println("As customerLine.length > 3 and CashRegister Available , cashRegister : " + cashRegister.toString() + " added to CashRegister Array List : " + this.cashRegisters.toString());
            return;
        }

        /**
         * If the customer line is empty and there is more than one cash register without a
         * Customer checking out,  close ONE cash register
         */
        if(customerLine.isEmpty()){
            ArrayList<Integer> emptyCashRegisterList = new ArrayList<>();
            for(int i =0 ; i<cashRegisters.getLength(); i++){
                if(cashRegisters.getEntry(i).getCustomer() == null){
                    emptyCashRegisterList.insert(0,i);
                }
            }

            if(!emptyCashRegisterList.isEmpty()){
                if(emptyCashRegisterList.getLength() > 1){
                    System.out.println("CashRegister : " + cashRegisters.getEntry(emptyCashRegisterList.getEntry(emptyCashRegisterList.getLength()-1)) + " removed as it has no customer" );
                    cashRegisters.remove(emptyCashRegisterList.getEntry(emptyCashRegisterList.getLength()-1));
                }
            }
            return;
        }
    }

    public ListQueue<Customer> getCustomerLine() {
        return customerLine;
    }

    public void addCustomerToLine(Customer customer){
        customer.setCustomerStatus(CustomerStatus.WAITING_IN_LINE);
        customerLine.enqueue(customer);
    }

    /**
     * Iterate through each of the cash registers
     */
    public void processCustomers(){
        for(int i =0 ; i<cashRegisters.getLength(); i++){
            /**
             * If the cash register does not have a Customer and the customerLine has a
             * Customer waiting, the customer should be taken out of the line and assigned to
             * the CashRegister  Otherwise, if the CashRegister already has a customer present, the
             * ‘processCustomer’ method of that Customer should be called to continue
             * processing that Customer’s checkout
             */
            if(cashRegisters.getEntry(i).getCustomer() == null && !customerLine.isEmpty() ){
                Customer customerDequeued = customerLine.dequeue();
                System.out.println("Customer null in cashRegister " + cashRegisters.getEntry(i) + " of cashRegisters ArrayList : " + cashRegisters.toString());
                cashRegisters.getEntry(i).setCustomer(customerDequeued);
                System.out.println("Customer added in cashRegister " + cashRegisters.getEntry(i) + " of cashRegisters ArrayList : " + cashRegisters.toString());

            }else{
                cashRegisters.getEntry(i).processCustomer();
            }
        }
    }


    @Override
    public String toString() {
        return "DepartmentStore{" +
                "customerLine=" + customerLine +
                ", maximumCashRegisters=" + maximumCashRegisters +
                ", scanEfficiency=" + scanEfficiency +
                '}';
    }
}
