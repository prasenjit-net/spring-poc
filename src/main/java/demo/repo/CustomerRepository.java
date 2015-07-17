package demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
