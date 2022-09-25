package lk.ideahub.mypay.api;

import lk.ideahub.mypay.dto.CounterDTO;
import lk.ideahub.mypay.dto.CustomerDTO;
import lk.ideahub.mypay.service.CounterService;
import lk.ideahub.mypay.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

/**
 * @author : Nipun Chathuranga <nipunc1999@gmail.com>
 * @since : 9/24/2022
 **/

@RestController
@RequestMapping("/api/v1/counters")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CounterController {

    @Autowired
    private CounterService counterService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> saveCounter(@RequestBody CounterDTO counterDTO) {
        Long id = counterService.saveCounter(counterDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "success", id),
                HttpStatus.CREATED);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> updateCounter(@RequestBody CounterDTO counterDTO) {
        Long id = counterService.updateCounter(counterDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "success", id),
                HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable Long id) {
        Long customerId = counterService.deleteCounter(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "success", customerId),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{outletId}")
    public ResponseEntity<StandardResponse> getAllCounterByOutlet(@PathVariable Long outletId) {
        List<CounterDTO> allCounterHasOutlet = counterService.getAllCounterHasOutlet(outletId);
        return new ResponseEntity<>(
                new StandardResponse(200, "success", allCounterHasOutlet),
                HttpStatus.OK);
    }


}
