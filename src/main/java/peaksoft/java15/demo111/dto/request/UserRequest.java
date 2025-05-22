package peaksoft.java15.demo111.dto.request;

import lombok.Builder;

@Builder
public record UserRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {}
