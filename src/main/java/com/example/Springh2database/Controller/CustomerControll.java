package com.example.Springh2database.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Springh2database.entity.Customer;
import com.example.Springh2database.repository.CustomerRepo;

@RestController
public class CustomerControll {
    @Autowired 
    CustomerRepo custrepo;

    @PostMapping("/customers")
    public ResponseEntity<Customer> save(@RequestBody Customer customer)
    {
        try{
        return new ResponseEntity<>(custrepo.save(customer),HttpStatus.CREATED);
    }
    catch(Exception e)
    {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
     }

     @GetMapping("/customers")
     public ResponseEntity<List<Customer>> getallcustomer() {
        try {
            List<Customer> list = custrepo.findAll();
            if (list.isEmpty() || list.size() == 0) {
                return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/customers/{id}")
     public ResponseEntity<Customer> getSingleCustomer(@PathVariable Long id) {
        
       Optional<Customer> customer = custrepo.findById(id);
       if(customer.isPresent())
       {
        return new ResponseEntity<Customer>(customer.get(),HttpStatus.OK);
       }
       return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/customers")
     public ResponseEntity<Customer> getOneCustomer(@RequestBody Long id) {
        
       Optional<Customer> customer = custrepo.findById(id);
       if(customer.isPresent())
       {
        return new ResponseEntity<Customer>(customer.get(),HttpStatus.OK);
       }
       return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
       }


       @PutMapping("/customers/{id}")
   public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer)
   {
    try
    {
        return new ResponseEntity<Customer>(custrepo.save(customer),HttpStatus.OK);
    }
    catch(Exception e)
    {
       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
   }
   @DeleteMapping("/customers/{id}")
   public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id)
   
   {
    try
    {
        Optional<Customer> customer = custrepo.findById(id);
        if(customer.isPresent())
        {
            custrepo.delete(customer.get());
        }
            return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }

    catch(Exception e)
    {
       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
