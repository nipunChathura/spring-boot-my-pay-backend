package lk.ideahub.mypay.api;

import lk.ideahub.mypay.dto.MerchantDTO;
import lk.ideahub.mypay.service.MerchantService;
import lk.ideahub.mypay.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/25/2022
 **/

@RestController
@RequestMapping("/api/v1/merchants")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> saveMerchant(@RequestBody MerchantDTO merchantDTO) {
        Long id = merchantService.saveMerchant(merchantDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "success", id),
                HttpStatus.CREATED);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> updateMerchant(@RequestBody MerchantDTO merchantDTO) {
        Long id = merchantService.updateMerchant(merchantDTO);
        return new ResponseEntity<>(
                new StandardResponse(204, "success", id),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> deleteMerchant(@PathVariable Long id) {
        Long merchantId = merchantService.deleteMerchant(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "success", merchantId),
                HttpStatus.OK);
    }
}
