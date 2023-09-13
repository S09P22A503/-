package com.sshmarket.trade.infra;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
class OrderMongoRepositoryTest {

    @Autowired
    private OrderMongoRepository orderMongoRepository;

    @DisplayName("생성")
    @Test
    void create() {
        // given
        OrderCurrent orderCurrent = new OrderCurrent("test", "job");
        // when
        OrderCurrent actual = orderMongoRepository.save(orderCurrent);
        // then
        assertThat(actual).isNotNull();
    }
}