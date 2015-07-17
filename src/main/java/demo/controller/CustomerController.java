package demo.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.model.Customer;
import demo.repo.CustomerRepository;

@Log4j2
@Controller
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		log.entry(model);
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("editCustomer", new Customer());
		return log.exit("customer");
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute Customer customer, Model model) {
		log.entry(customer, model);
		customer = customerRepository.save(customer);
		return log.exit("redirect:/customer");
	}
	
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") String id, Model model) {
		log.entry(id, model);
		customerRepository.delete(id);
		return log.exit("redirect:/customer");
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model model) {
		log.entry(id, model);
		Customer customer = customerRepository.findOne(id);
		if (null != customer) {
			model.addAttribute("editCustomer", customer);	
		}
		model.addAttribute("customers", customerRepository.findAll());
		return log.exit("customer");
	}

}
