package com.galvanize.customer;

import com.galvanize.customer.model.Customer;
import com.galvanize.customer.repository.CustomerRepository;
import com.galvanize.customer.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

   @Test
    public void getAllCustomers(){
       when(customerRepository.findAll()).thenReturn(new ArrayList<>());
       List<Customer> customers=customerService.getCustomers();
       verify(customerRepository).findAll();
       assertThat(customers).isEmpty();
   }

   @Test
    public void addCustomer(){
       Customer customerInput=new Customer(1234L,"peter","sam");
       when(customerRepository.save(customerInput)).thenReturn(customerInput);
       Customer customerOut=customerService.addCustomer(customerInput);
       verify(customerRepository).save(customerInput);

       assertThat(customerOut).isEqualTo(customerInput);
   }


   @Test
    public void updateCustomer(){
       Customer customerInput=new Customer(1234L,"peter","sam");
       when(customerRepository.save(customerInput)).thenReturn(customerInput);
       Customer customerOut=customerService.addCustomer(customerInput);
       verify(customerRepository).save(customerInput);

       assertThat(customerOut).isEqualTo(customerInput);
   }

   @Test
    public void deleteCustomer(){
       //Customer customerInput=new Customer(1234L,"peter","sam");
       customerService.deleteCustomerById(133L);
       verify(customerRepository).deleteById(133L);
   }
}