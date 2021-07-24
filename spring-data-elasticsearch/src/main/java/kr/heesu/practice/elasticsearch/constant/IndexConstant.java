package kr.heesu.practice.elasticsearch.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IndexConstant {

    public static final String ACCOUNT = "account";
    public static final String FIELD_TEST = "field_test";

    public static final int INDEX_MAX_RESULT_WINDOW = 10000;
}
