package kr.heesu.practice.r2dbc.repository;

import kr.heesu.practice.r2dbc.entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.r2dbc.core.DatabaseClient;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DataR2dbcTest
class DataBaseClientTest {

    @Autowired DatabaseClient client;

    @DisplayName("")
    @Test
    void selectAllQueryTest() {
        // given
        String query = "select * from member";

        // when
        List<Map<String, Object>> flux = client.sql(query)
                .fetch()
                .all()
                .collectList()
                .block();

        // then
        assertThat(flux).isNotNull();
        flux.forEach(stringObjectMap -> System.out.println("stringObjectMap = " + stringObjectMap));
    }

    @DisplayName("")
    @Test
    void selectQueryTest() {
        // given
        Long id = 1L;
        String query = "select * from member where member.member_id = :memberId";

        // when
        List<Map<String, Object>> flux = client.sql(query)
                .bind("memberId", id)
                .fetch()
                .all()
                .collectList()
                .block();

        // then
        assertThat(flux).isNotNull();
        flux.forEach(stringObjectMap -> System.out.println("stringObjectMap = " + stringObjectMap));
    }

    @DisplayName("")
    @Test
    void innerJoinQueryTest() {
        // given
        String query = "select member.member_id, member_name, order_id, order_name, order_status from orders inner join member on orders.member_id = member.member_id";

        // when
        List<Order> rows = client.sql(query)
                .fetch()
                .all()
                .map(Order::create)
                .collectList()
                .block();

        // then
        assertThat(rows).isNotNull();
        System.out.println("rows = " + rows);
    }

    @DisplayName("")
    @Test
    void leftOuterJoinTest() {
        // given
        //"select member.*, order_id, order_name, order_status from member " +
        Long id = 1L;
        String query =
                "select " +
                        "member.member_name, member.member_id as m_member_id, " +
                        "orders.order_name, orders.order_status, orders.order_id, orders.member_id as order_member_id " +
                        "from member " +
                        "left outer join orders " +
                        "on orders.member_id = member.member_id " +
                        "where member.member_id = :id";
//                "select member.*, orders.order_id, orders.order_name, orders.order_status from member " +
//                "join orders " +
//                "on orders.member_id = member.member_id " +
//                "where member.member_id = :memberId";

        // when
        List<Map<String, Object>> maps = client.sql(query)
                .bind("id", id)
                .fetch()
                .all()
                .collectList()
                .block();

        // then
        assertThat(maps).isNotNull();
        System.out.println("maps = " + maps);
    }
}
