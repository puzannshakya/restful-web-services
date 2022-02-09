package com.assignment.departmentstorequeues;

/**
 * Class for CashRegister
 *
 * @author Ashish
 */
public class CashRegister {
    private int number;
    private Customer customer;
    private int scanEfficiency;

    /**
     * Constructor for CashRegister Class
     * @param number
     * @param scanEfficiency
     */
    public CashRegister(int number , int scanEfficiency){
        this.number = number;
        this.customer= null;
        this.scanEfficiency = scanEfficiency;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getNumber() {
        return number;
    }

    /**
     *this method will be called  once per “cycle” and  update the
     * Customer’s status each time through the cycle according to the if cases.
     */
    public void processCustomer(){
        System.out.println(toString());
        if(this.customer != null){

            /**
             * if the customer was WAITING_IN_LINE, then the customer status should change to
             * SCANNING_MERCHANDISE
             */
            if(this.customer.getCustomerStatus().equals(CustomerStatus.WAITING_IN_LINE)){
                this.customer.setCustomerStatus(CustomerStatus.SCANNING_MERCHANDISE);
                return;
            }

            /**
             * if the customer’s status is SCANNING_MERCHANDISE, and the number of items is > 0
             */
            if(this.customer.getCustomerStatus().equals(CustomerStatus.SCANNING_MERCHANDISE) && this.customer.getNumberOfItems() > 0){
                //Calculate the remaining items and if remaining items is 0 the customer status should change to PROCESSING_PAYMENT
                int remaining_items = this.customer.getNumberOfItems() - scanEfficiency ;
                if(remaining_items > 0 )
                {
                    this.customer.setNumberOfItems(remaining_items);
                }else{
                    this.customer.setNumberOfItems(0);
                    this.customer.setCustomerStatus(CustomerStatus.PROCESSING_PAYMENT);
                }
                return;
            }


            /**
             * If the customer’s status is PROCESSING_PAYMENT, change the status to
             * PURCHASE_COMPLETE
             */
            if(this.customer.getCustomerStatus().equals(CustomerStatus.PROCESSING_PAYMENT)){
                this.customer.setCustomerStatus(CustomerStatus.PURCHASE_COMPLETE);
                return;
            }

            /**
             * If the customer’s status is PURCHASE_COMPLETE, set the ‘customer’ to null to indicate
             * the Customer has left the register
             */
            if(this.customer.getCustomerStatus().equals(CustomerStatus.PURCHASE_COMPLETE)){
                customer = null;
                return;
            }

        }
    }

    @Override
    public String toString() {
        return "CashRegister{" +
                "number=" + number +
                ", customer=" + customer +
                ", scanEfficiency=" + scanEfficiency +
                '}';
    }
}
