package kr.heesu.practice.elasticsearch.mapper;

import kr.heesu.practice.elasticsearch.domain.Account;
import kr.heesu.practice.elasticsearch.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountDtoMapper {

    AccountDtoMapper INSTANCE = Mappers.getMapper(AccountDtoMapper.class);

    AccountDto.Response responseFromAccount(Account account);
}
