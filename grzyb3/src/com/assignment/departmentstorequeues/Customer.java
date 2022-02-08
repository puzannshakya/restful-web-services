package com.assignment.departmentstorequeues;

/**
 * Class for Customer
 *
 * @author abc
 */
public class Customer {
    private String name;
    private CustomerStatus status;
    private int numberOfItems;

    /**
     * Constructor for Customer Class
     * @param name
     * @param numberOfItems
     */
    public Customer(String name,int numberOfItems){
      this.name = name;
      this.status = CustomerStatus.SHOPPING;
      this.numberOfItems = numberOfItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerStatus(CustomerStatus status) {
        this.status = status;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public String getName() {
        return name;
    }

    public CustomerStatus getCustomerStatus() {
        return status;
    }


    public int getNumberOfItems() {
        return numberOfItems;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", status=" + status +
                ", numberOfItems=" + numberOfItems +
                '}';
    }
}
