package application.service;

import application.model.users.Customer;

public interface CustomerService {
    boolean isPresent(Customer customer);

    Customer save(Customer customer);

    void updateCustomer(Customer customer);

    boolean isPresent(Integer id);

    Customer findCustomerByTelegramId(Integer id);

}
