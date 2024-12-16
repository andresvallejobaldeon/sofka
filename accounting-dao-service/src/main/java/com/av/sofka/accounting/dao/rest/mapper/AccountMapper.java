package com.av.sofka.accounting.dao.rest.mapper;

import com.av.sofka.accounting.dao.model.account.Account;
import com.av.sofka.accounting.dao.rest.model.UpdateAccountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    void updateAccountFromUpdateAccountRequest(UpdateAccountRequest updateAccountRequest, @MappingTarget Account account);

}
