package com.av.sofka.accounting.dao.rest.mapper;

import com.av.sofka.accounting.dao.model.customer.Customer;
import com.av.sofka.accounting.dao.rest.model.CustomerResponse;
import com.av.sofka.accounting.dao.rest.model.UpdateCustomerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    void updateCustomer(UpdateCustomerRequest updateCustomerRequest, @MappingTarget Customer customer);
    CustomerResponse toCustomerResponse(Customer customer);
    List<CustomerResponse> toCustomerResponseList(List<Customer> customerList);
}
