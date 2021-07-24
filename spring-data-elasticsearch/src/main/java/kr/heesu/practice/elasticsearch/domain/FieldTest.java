package kr.heesu.practice.elasticsearch.domain;

import kr.heesu.practice.elasticsearch.constant.IndexConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Mapping(mappingPath = "mappings/field_test.json")
@Document(indexName = IndexConstant.FIELD_TEST)
public class FieldTest {

    @Id
    private Long id;

    private Long no;

    private String text;

    private String keyword;
}
