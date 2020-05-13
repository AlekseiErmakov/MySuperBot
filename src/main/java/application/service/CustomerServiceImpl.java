package application.service;

import application.repository.CustomerRepository;
import application.model.users.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public boolean isPresent(Customer customer) {
        Customer customerFromDb = customerRepository.findByTelegramId(customer.getTelegramId());
        return customerFromDb != null;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        Customer customerFromDb = customerRepository.findByTelegramId(customer.getTelegramId());
        if (!customerFromDb.equals(customer)){
            updateCustomer(customer,customerFromDb);
        }
    }

    @Override
    public boolean isPresent(Integer id) {
        Customer customerFromDb = customerRepository.findByTelegramId(id);
        return customerFromDb != null;
    }

    @Override
    public Customer findCustomerByTelegramId(Integer id) {
        return customerRepository.findByTelegramId(id);
    }

    private void updateCustomer(Customer customer, Customer customerFromDb) {
        customerRepository.save(customerFromDb);
    }
}
