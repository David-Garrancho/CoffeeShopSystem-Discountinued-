package za.ac.cput.interfaces;

/* CustomerRepository.java
Class for the CustomerRepository
Author: David Henriques Garrancho (221475982)
Date: 20 March 2023
*/

import za.ac.cput.domain.Customer;

import java.util.HashSet;
import java.util.Set;

public class CustomerRepository implements InterfaceCustomerRepository {

    private Set<Customer> customerDB = null;
    private static CustomerRepository repository = null;
    private CustomerRepository(){
        customerDB = new HashSet<Customer>();
    }

    public static CustomerRepository getRepository(){
        if(repository == null){
            repository = new CustomerRepository();
        }
        return repository;
    }

    @Override
    public Customer create(Customer customer) {
        boolean success = customerDB.add(customer);
        if(!success) {
            return null;
        }
        return customer;
    }

    @Override
    public Customer read(String customerID) {
        Customer c = customerDB.stream()
                .filter(e -> e.getCustomerID().equals(customerID))
                .findAny()
                .orElse(null);
        return c;
    }

    @Override
    public Customer update(Customer customer) {
        Customer oldCustomer = read(customer.getCustomerID());
        if(oldCustomer != null){
            customerDB.remove(oldCustomer);
            customerDB.add(customer);
            return customer;
        }
        return null;
    }

    @Override
    public boolean delete(String customerID) {
        Customer customerToDelete = read(customerID);
        if(customerToDelete == null){
            return false;
        }
        customerDB.remove(customerToDelete);
        return true;
    }

    @Override
    public Set<Customer> getAll() {
        return customerDB;
    }

}