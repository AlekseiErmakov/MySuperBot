package application.service;

import application.dao.RequestRepository;
import application.model.users.UserRequest;

public class RequestServiceImpl implements RequestService{
    private RequestRepository requestRepository;

    @Override
    public void save(UserRequest request) {
        requestRepository.save(request);
    }
}
