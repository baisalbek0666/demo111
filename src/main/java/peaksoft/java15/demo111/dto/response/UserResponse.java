package peaksoft.java15.demo111.dto.response;

import lombok.Builder;
import peaksoft.java15.demo111.enums.Role;
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
