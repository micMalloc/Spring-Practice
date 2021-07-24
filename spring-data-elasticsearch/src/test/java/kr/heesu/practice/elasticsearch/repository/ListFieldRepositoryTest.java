package kr.heesu.practice.elasticsearch.repository;

import kr.heesu.blog.practice.elasticsearch.domain.ListField;
import kr.heesu.blog.practice.elasticsearch.dto.ListFieldDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ListFieldRepositoryTest {

    @Autowired ListFieldRepository repository;

    @DisplayName("저장 테스트")
    @Test
    void saveTest() {
        // given
        long id = 1L;
        List<String> keywords = Arrays.asList("희수", "석준", "윤진", "재길");

        ListFieldDto.Create create = ListFieldDto.Create.builder()
                .id(id)
                .keywords(keywords)
                .build();

        // when
        ListField saved = repository.save(create.toEntity())
                .block();

        // then
        assertThat(saved).isNotNull();

        assertThat(saved.getId()).isEqualTo(id);
        assertThat(saved.getKeywords()).containsExactlyElementsOf(keywords);
    }

    @DisplayName("")
    @Test
    void getRequestSize() {
        // given
        int size = 3;

        // when


        // then
    }
}