package com.bbc.km.batch;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;

class OrdersAckBatchConfigTest {

  private final OrdersAckBatchConfig config = new OrdersAckBatchConfig();

  @Test
  void readerShouldBeCreated() {
    FlatFileItemReader<OrdersAckCsv> reader = config.reader();

    assertNotNull(reader);
  }

  @Test
  void writerShouldBeCreated() {
    DataSource dataSource = mock(DataSource.class);

    JdbcBatchItemWriter<OrdersAckCsv> writer = config.writer(dataSource);

    assertNotNull(writer);
  }
}
