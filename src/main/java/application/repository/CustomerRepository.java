package application.repository;

import application.model.users.Customer;

public interface CustomerRepository {
    Customer findByTelegramId(Integer telegramId);

    Customer save(Customer customer);
}
