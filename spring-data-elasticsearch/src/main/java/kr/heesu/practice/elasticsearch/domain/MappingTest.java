package kr.heesu.practice.elasticsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "mapping_test", createIndex = false)
public class MappingTest {

    @Id
    private Long id;

    @Field(type = FieldType.Long)
    private Long no;

    @Field(type = FieldType.Text)
    private String text;

    @Field(type = FieldType.Keyword)
    private String keyword;
}
