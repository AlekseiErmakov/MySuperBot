package application.service;

import application.dao.CustomerRepository;
import application.model.users.Customer;

public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    @Override
    public boolean isPresent(Customer customer) {
        Customer customerFromDb = customerRepository.findByTelegramId(customer.getTelegramId());
        return customerFromDb != null;
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        Customer customerFromDb = customerRepository.findByTelegramId(customer.getTelegramId());
        if (!customerFromDb.equals(customer)){
            updateCustomer(customer,customerFromDb);
        }
    }

    private void updateCustomer(Customer customer, Customer customerFromDb) {
        customerRepository.save(customerFromDb);
    }
}
