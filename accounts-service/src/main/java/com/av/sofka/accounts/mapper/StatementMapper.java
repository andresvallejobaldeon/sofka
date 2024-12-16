package com.av.sofka.accounts.mapper;

import com.av.sofka.accounts.model.Movement;
import com.av.sofka.accounts.model.Statement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StatementMapper {

    StatementMapper INSTANCE = Mappers.getMapper(StatementMapper.class);

    @Mapping(target = "movementDate", source = "movementDate")
    @Mapping(target = "customerName", source = "account.customer.name")
    @Mapping(target = "accountNumber", source = "account.accountNumber")
    @Mapping(target = "accountType", source = "account.accountType")
    @Mapping(target = "initialBalance", source = "initialBalance")
    @Mapping(target = "status", source = "account.status")
    @Mapping(target = "movementAmount", source = "amount")
    @Mapping(target = "balance", source = "balance")
    Statement toStatement(Movement movement);

    List<Statement> toStatementList(List<Movement> movementList);
}
