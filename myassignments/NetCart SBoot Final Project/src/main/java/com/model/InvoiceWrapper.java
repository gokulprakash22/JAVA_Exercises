package com.model;

import java.util.HashMap;

import org.springframework.stereotype.Repository;


@Repository
public class InvoiceWrapper {
Invoice invoice;
Customer customer;

public Customer getCustomer() {
	return customer;
}
public void setCustomer(Customer customer) {
	this.customer = customer;
}
public Invoice getInvoice() {
	return invoice;
}
public void setInvoice(Invoice invoice) {
	this.invoice = invoice;
}
@Override
public String toString() {
	return "InvoiceWrapper [invoice=" + invoice + ", customer=" + customer + "]";
}


}
