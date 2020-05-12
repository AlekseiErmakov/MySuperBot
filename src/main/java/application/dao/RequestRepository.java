package application.dao;

import application.model.users.UserRequest;

public interface RequestRepository {

    void save(UserRequest request);
}
