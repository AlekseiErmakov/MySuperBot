package application.dao;

import application.model.users.Customer;

public interface CustomerRepository {
    Customer findByTelegramId(Integer telegramId);

    void save(Customer customer);
}
