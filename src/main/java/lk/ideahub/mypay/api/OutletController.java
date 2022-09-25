package lk.ideahub.mypay.api;

import lk.ideahub.mypay.dto.CustomerDTO;
import lk.ideahub.mypay.dto.OutletDTO;
import lk.ideahub.mypay.entity.Outlet;
import lk.ideahub.mypay.service.OutletService;
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
@RequestMapping("/api/v1/outlets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OutletController {

    @Autowired
    private OutletService outletService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> saveOutlet(@RequestBody OutletDTO outletDTO) {
        Long id = outletService.saveOutlet(outletDTO);
        return new ResponseEntity<>(
                new StandardResponse(201, "success", id),
                HttpStatus.CREATED);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<StandardResponse> updateOutlet(@RequestBody OutletDTO outletDTO) {
        Long id = outletService.updateOutlet(outletDTO);
        return new ResponseEntity<>(
                new StandardResponse(204, "success", id),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> deleteOutlet(@PathVariable Long id) {
        Long outletId = outletService.deleteOutlet(id);
        return new ResponseEntity<>(
                new StandardResponse(204, "success", outletId),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> getOutletById(@PathVariable Long id) {
        OutletDTO outletById = outletService.getOutletById(id);
        return new ResponseEntity<>(
                new StandardResponse(200, "success", outletById),
                HttpStatus.OK);
    }
}
