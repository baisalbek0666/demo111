package peaksoft.java15.demo111.service;

import peaksoft.java15.demo111.dto.request.UserRequest;
import peaksoft.java15.demo111.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);

    List<UserResponse> getAllUser();

    UserResponse getUserById(Long id);

    UserResponse updateUser(Long id, UserRequest request) ;

    void deleteUser(Long id) ;

}
