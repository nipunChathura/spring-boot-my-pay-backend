package lk.ideahub.mypay.api;

import lk.ideahub.mypay.util.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@RestController
@RequestMapping("/api/v1/registrations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrationController {


    @PostMapping
    public ResponseEntity<StandardResponse> registerNewCustomer() {

        return null;
    }

    @GetMapping
    public ResponseEntity<StandardResponse> validOTP() {

        return null;
    }
}
