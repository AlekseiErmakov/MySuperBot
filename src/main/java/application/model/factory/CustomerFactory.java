package application.model.factory;

import application.model.users.Customer;
import org.telegram.telegrambots.meta.api.objects.User;

public class CustomerFactory {
    public static Customer createCustomer(User from, Long chatId){
        String firstName = from.getFirstName();
        String lastName = from.getLastName();
        Boolean bot = from.getBot();
        Integer id = from.getId();
        String languageCode = from.getLanguageCode();
        String userName = from.getUserName();
        return  new Customer(firstName,lastName,bot,id,languageCode,userName,chatId);
    }
}
