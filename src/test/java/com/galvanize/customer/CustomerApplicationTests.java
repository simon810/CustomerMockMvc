package com.galvanize.customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.customer.controller.CustomerController;
import com.galvanize.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerApplicationTests {

	ObjectMapper mapper=new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getCustomers() throws Exception {
		mockMvc.perform(get("/api/customers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").exists())
				.andDo(print());
	}

	@Test
	public void addCustomer() throws Exception {
		Customer cus=new Customer(123L,"john","sam");
		String customer=mapper.writeValueAsString(cus);

		mockMvc.perform(post("/api/customers")
		.contentType(MediaType.APPLICATION_JSON)
		.content(customer))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("john"))
				.andExpect(jsonPath("$.lastName").value("sam"))
				.andDo(print());
	}

	@Test
	public void getCustomerById() throws Exception {
		addCustomer();
		mockMvc.perform(get("/api/customers/123"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("john"))
				.andExpect(jsonPath("$.lastName").value("sam"))
				.andDo(print());
	}


	@Test
	public void updateCustomer() throws Exception {
		addCustomer();

		Customer cus=new Customer(123L,"mat","peter");
		String customerBody=mapper.writeValueAsString(cus);

		mockMvc.perform(put("/api/customers/123")
		.contentType(MediaType.APPLICATION_JSON)
		.content(customerBody)).andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("mat"))
				.andExpect(jsonPath("$.lastName").value("peter"))
				.andDo(print());
	}

	@Test
	public void deleteCustomerById() throws Exception {
		addCustomer();

		mockMvc.perform(delete("/api/customers/123")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		//getCustomers();


	}


}
