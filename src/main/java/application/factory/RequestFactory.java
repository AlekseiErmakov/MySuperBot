package application.factory;

import application.model.users.Customer;
import application.model.users.UserRequest;

public class RequestFactory {
    public static UserRequest createRequest(Customer customer, String text) {
        UserRequest userRequest = new UserRequest();
        String command = getCommand(text);
         if (!command.isEmpty()){
            userRequest.setCommand(command);
            userRequest.setBody(text.replaceAll(command+" ",""));
         } else {
             userRequest.setCommand("");
             userRequest.setBody(text);
         }
         userRequest.setCustomer(customer);
         return userRequest;
    }

    private static String getCommand(String text){
        if (text.startsWith("/")){
            return text.split(" ")[0];
        } else return "";
    }
}
