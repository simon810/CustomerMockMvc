package com.galvanize.customer.responsedto;

import com.galvanize.customer.model.Customer;

import java.util.List;

public class Response {
    private int statusCode;
    private String status;

    private List<Customer> customers;

    public Response(int statusCode, String status, List<Customer> customers) {
        this.statusCode = statusCode;
        this.status = status;
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
