package application.all_listeners;

import application.model.factory.CustomerFactory;
import application.model.factory.RequestFactory;
import application.model.users.Customer;
import application.model.users.UserRequest;
import application.service.CustomerService;
import application.service.RequestService;
import core.OnAllUpdatesListener;
import core.TelegramBotConfig;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class CustomerListener implements OnAllUpdatesListener {
    private CustomerService customerService;
    private RequestService requestService;
    private TelegramBotConfig telegramBotConfig;

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        User from = message.getFrom();

        if (update.hasMessage() && message.hasText()) {
            Customer customer = CustomerFactory.createCustomer(from, message.getChatId());
            UserRequest userRequest = RequestFactory.createRequest(customer, message.getText());
            processCustomer(customer);
            processRequest(userRequest);
        }
    }

    private void processCustomer(Customer customer) {
        if (!customerService.isPresent(customer)) {
            customerService.save(customer);
        } else {
            customerService.updateCustomer(customer);
        }
    }

    private void processRequest(UserRequest request) {
        if (!telegramBotConfig.isActiveScript()){
            requestService.save(request);
        }

    }

}
