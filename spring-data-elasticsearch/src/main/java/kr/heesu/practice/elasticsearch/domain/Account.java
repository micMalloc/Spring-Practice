package kr.heesu.practice.elasticsearch.domain;

import kr.heesu.practice.elasticsearch.constant.IndexConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Mapping;

@ToString
@EqualsAndHashCode(of = {"id", "accountNumber"})
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Mapping(mappingPath = "mappings/account.json")
@Document(indexName = IndexConstant.ACCOUNT)
public class Account {

    @Id
    private Long id;

    @Field(name = "account_number")
    private Long accountNumber;

    private Long balance;

    private String firstname;

    private String lastname;

    private int age;

    private String gender;

    private String address;

    private String employer;

    private String email;

    private String city;

    private String state;
}
