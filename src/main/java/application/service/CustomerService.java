package application.service;

import application.model.users.Customer;

public interface CustomerService {
    boolean isPresent(Customer customer);

    void save(Customer customer);

    void updateCustomer(Customer customer);

}
