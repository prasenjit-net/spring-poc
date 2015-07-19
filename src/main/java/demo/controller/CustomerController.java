package demo.controller;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${spring.datasource.platform}")
	private String dbPlatform;

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model) {
		log.entry(model);
		model.addAttribute("customers", customerRepository.findAll());
		model.addAttribute("editCustomer", new Customer());
		model.addAttribute("dbplatform", dbPlatform);
		return log.exit("customer");
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@ModelAttribute Customer customer, Model model) {
		log.entry(customer, model);
		customer = customerRepository.save(customer);
		index(model);
		return log.exit("customer :: all-customer-table");
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id, Model model) {
		log.entry(id, model);
		customerRepository.delete(id);
		index(model);
		return log.exit("customer :: all-customer-table");
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {
		log.entry(id, model);
		Customer customer = customerRepository.findOne(id);
		if (null != customer) {
			model.addAttribute("editCustomer", customer);
		}
		model.addAttribute("dbplatform", dbPlatform);
		return log.exit("customer :: customer-edit");
	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String newCustomer(Model model) {
		log.entry(model);
		model.addAttribute("editCustomer", new Customer());
		model.addAttribute("dbplatform", dbPlatform);
		return log.exit("customer :: customer-edit");
	}

}
