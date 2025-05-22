package peaksoft.java15.demo111.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.java15.demo111.service.EmailService;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> send() {
        emailService.sendEmail("elamanabjalov824@gmail.com", "Test subject", "Hello from Spring Boot!");
        return ResponseEntity.ok("Email sent!");
    }
}
