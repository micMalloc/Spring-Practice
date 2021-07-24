package kr.heesu.practice.elasticsearch.mapper;

import javax.annotation.Generated;
import kr.heesu.practice.elasticsearch.domain.Account;
import kr.heesu.practice.elasticsearch.dto.AccountDto.Response;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-07-24T14:05:42+0900",
    comments = "version: 1.4.0.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
public class AccountDtoMapperImpl implements AccountDtoMapper {

    @Override
    public Response responseFromAccount(Account account) {
        if ( account == null ) {
            return null;
        }

        Response response = new Response();

        response.setAccountNumber( account.getAccountNumber() );
        response.setBalance( account.getBalance() );
        response.setFirstname( account.getFirstname() );
        response.setLastname( account.getLastname() );
        response.setAge( account.getAge() );
        response.setGender( account.getGender() );
        response.setAddress( account.getAddress() );
        response.setEmployer( account.getEmployer() );
        response.setEmail( account.getEmail() );
        response.setCity( account.getCity() );
        response.setState( account.getState() );

        return response;
    }
}
