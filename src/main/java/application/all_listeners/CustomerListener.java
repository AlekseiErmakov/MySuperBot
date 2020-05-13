package application.all_listeners;

import application.factory.CustomerFactory;
import application.factory.RequestFactory;
import application.model.users.Customer;
import application.model.users.UserRequest;
import application.service.CustomerService;
import application.service.RequestService;
import core.OnAllUpdatesListener;
import core.TelegramBotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
@Service
public class CustomerListener implements OnAllUpdatesListener {

    private CustomerService customerService;
    private RequestService requestService;
    private TelegramBotConfig telegramBotConfig;

    @Autowired
    public CustomerListener(CustomerService customerService, RequestService requestService) {
        this.customerService = customerService;
        this.requestService = requestService;
        telegramBotConfig = TelegramBotConfig.getInstance();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        User from = message.getFrom();

        Long chatId = message.getChatId();
        Customer customer = processCustomer(from,chatId);

        processRequest(customer,message);
    }

    private void processRequest(Customer customer, Message message) {
        UserRequest userRequest = RequestFactory.createRequest(customer,message.getText());
        requestService.save(userRequest);
    }

    private Customer processCustomer(User from,Long chatId) {
        if (customerService.isPresent(from.getId())) {
            return customerService.findCustomerByTelegramId(from.getId());
        } else {
            Customer customer = CustomerFactory.createCustomer(from, chatId);

            return customerService.save(customer);
        }
    }

}
