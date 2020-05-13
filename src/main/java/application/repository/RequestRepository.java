package application.repository;

import application.model.users.UserRequest;

public interface RequestRepository {

    void save(UserRequest request);
}
