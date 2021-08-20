package com.business.customeregistration.controller;

import com.business.customeregistration.model.Customer;
import com.business.customeregistration.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/customerRegistration")
    public ModelAndView init() {

        ModelAndView modelAndView = new ModelAndView("customerRegistration");
        modelAndView.addObject("customerObj", new Customer());
        Iterable<Customer> customerIterable = customerRepository.findAll();
        modelAndView.addObject("customers", customerIterable);

        return modelAndView;

    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveCustomer")
    public ModelAndView save(Customer customer) {
        customerRepository.save(customer);

        ModelAndView modelAndView = new ModelAndView("customerRegistration");
        Iterable<Customer> customerIterable = customerRepository.findAll();
        modelAndView.addObject("customers", customerIterable);
        modelAndView.addObject("customerObj", new Customer());

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/listCustomers")
    public ModelAndView customers() {

        ModelAndView modelAndView = new ModelAndView("customerRegistration");
        Iterable<Customer> customerIterable = customerRepository.findAll();
        modelAndView.addObject("customers", customerIterable);
        modelAndView.addObject("customerObj", new Customer());

        return modelAndView;

    }

    @GetMapping("/editCustomer/{customerId}")
    public ModelAndView edit(@PathVariable("customerId") Long customerId) {

        ModelAndView modelAndView = new ModelAndView("customerRegistration");
        Optional<Customer> customer = customerRepository.findById(customerId);
        modelAndView.addObject("customerObj", customer.get());

        return modelAndView;

    }

    @GetMapping("/deleteCustomer/{customerId}")
    public ModelAndView delete(@PathVariable("customerId") Long customerId) {

        customerRepository.deleteById(customerId);

        ModelAndView modelAndView = new ModelAndView("customerRegistration");
        modelAndView.addObject("customerObj", customerRepository.findAll());
        modelAndView.addObject("customerObj", new Customer());

        return modelAndView;
    }

    @PostMapping("**/searchCustomer")
    public ModelAndView findByName(@RequestParam("searchByName") String searchByName) {

        ModelAndView modelAndView = new ModelAndView("customerRegistration");
        modelAndView.addObject("customers", customerRepository.findByName(searchByName));
        modelAndView.addObject("customerObj", new Customer());
        return modelAndView;

    }

    @GetMapping("/services/{customerId}")
    public ModelAndView service (@PathVariable("customerId") Long customerId) {

        Optional<Customer> customer = customerRepository.findById(customerId);
        ModelAndView modelAndView = new ModelAndView("customerRegistration");
        modelAndView.addObject("customerObj", customer.get());

        return modelAndView;

    }
}

