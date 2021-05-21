package com.example.customermanager3.controller;


import com.example.customermanager3.model.Customer;
import com.example.customermanager3.service.CustomerService;
import com.example.customermanager3.service.CustomerServiceImplement;
import com.example.customermanager3.service.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    //---------------HomePage
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "index";
    }
    //--------------------CREATE CUSTOMER
    @GetMapping("/customer/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }
    @PostMapping("/customer/save")
    public ModelAndView save(Customer customer) throws DuplicateEmailException {
            ModelAndView modelAndView = new ModelAndView("index");
            customerService.save(customer);
            modelAndView.addObject("success", "Saved customer successfully!");
            return modelAndView;
    }
    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/inputs-not-acceptable");
    }


    //-----------EDIT CUSTOMER
    @GetMapping("/customer/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "edit";
    }
    @PostMapping("/customer/update")
    public String update(Customer customer, RedirectAttributes redirect) {
        customerService.update(customer.getId(), customer);
        redirect.addFlashAttribute("success", "Edit customer successfully!");
        return "redirect:/";
    }
    //-------------DELETE CUSTOMER
    @GetMapping("/customer/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("customer",customerService.findById(id));
        return "delete";
    }
    @PostMapping("/customer/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Delete customer successfully!");
        return "redirect:/";
    }
    @GetMapping("/customer/{id}/view")
    public ModelAndView showInformation(@PathVariable int id) {
        try {
            ModelAndView modelAndView = new ModelAndView("/view");
            Optional<Customer> customer = customerService.findOne(id);
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } catch (Exception e) {
            return new ModelAndView("redirect:/");
        }
    }
}
