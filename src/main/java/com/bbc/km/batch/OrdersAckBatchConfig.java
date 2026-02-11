package com.bbc.km.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class OrdersAckBatchConfig {

    @Bean
    public FlatFileItemReader<OrdersAckCsv> reader() {
        return new FlatFileItemReaderBuilder<OrdersAckCsv>()
                .name("ordersAckReader")
                .resource(new FileSystemResource("input/orders_ack.csv"))
                .delimited()
                .names("id","orderNumber","tableNumber","insertDate","insertTime",
                        "clientName","takeAway","orderNotes","quantity",
                        "menuItemId","menuItemName","menuItemNotes","categoryId","ack")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<OrdersAckCsv>() {{
                    setTargetType(OrdersAckCsv.class);
                }})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<OrdersAckCsv> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<OrdersAckCsv>()
                .dataSource(dataSource)
                .sql("INSERT INTO orders_ack (id, order_number, table_number, insert_date, insert_time, client_name, take_away, order_notes, quantity, menu_item_id, menu_item_name, menu_item_notes, category_id, ack) " +
                        "VALUES (:id, :orderNumber, :tableNumber, :insertDate, :insertTime, :clientName, :takeAway, :orderNotes, :quantity, :menuItemId, :menuItemName, :menuItemNotes, :categoryId, :ack)")
                .beanMapped()
                .build();
    }

    @Bean
    public Step step(StepBuilderFactory stepBuilderFactory,
                     ItemReader<OrdersAckCsv> reader,
                     ItemProcessor<OrdersAckCsv, OrdersAckCsv> processor,
                     ItemWriter<OrdersAckCsv> writer) {

        return stepBuilderFactory.get("ordersAckStep")
                .<OrdersAckCsv, OrdersAckCsv>chunk(50)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, Step step) {
        return jobBuilderFactory.get("importOrdersJob")
                .start(step)
                .build();
    }
}
