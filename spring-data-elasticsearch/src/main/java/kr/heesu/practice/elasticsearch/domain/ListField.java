package kr.heesu.practice.elasticsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "list_field", createIndex = false)
public class ListField {

    @Id
    private Long id;

    private List<String> keywords;
}
