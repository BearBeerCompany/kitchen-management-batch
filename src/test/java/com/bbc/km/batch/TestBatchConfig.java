package com.bbc.km.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Profile("test")
public class TestBatchConfig {

  @Bean
  @Primary
  public FlatFileItemReader<OrdersAckCsv> testReader() {
    return new FlatFileItemReaderBuilder<OrdersAckCsv>()
        .name("ordersAckReader")
        .resource(new ClassPathResource("input/orders_ack.csv"))
        .delimited()
        .names(
            "id",
            "orderNumber",
            "tableNumber",
            "insertDate",
            "insertTime",
            "clientName",
            "takeAway",
            "orderNotes",
            "quantity",
            "menuItemId",
            "menuItemName",
            "menuItemNotes",
            "categoryId",
            "ack")
        .fieldSetMapper(
            new BeanWrapperFieldSetMapper<OrdersAckCsv>() {
              {
                setTargetType(OrdersAckCsv.class);
              }
            })
        .build();
  }
}
