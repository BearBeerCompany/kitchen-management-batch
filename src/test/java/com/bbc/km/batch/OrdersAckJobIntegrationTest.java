package com.bbc.km.batch;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBatchTest
@SpringBootTest
@ActiveProfiles("test")
class OrdersAckJobIntegrationTest {

  @Autowired private JobLauncherTestUtils jobLauncherTestUtils;

  @Autowired private JdbcTemplate jdbcTemplate;

  @BeforeEach
  void cleanUp() {
    jdbcTemplate.execute("DELETE FROM orders_ack");
  }

  @Test
  void jobShouldCompleteAndInsertAllRows() throws Exception {
    JobExecution jobExecution =
        jobLauncherTestUtils.launchJob(
            new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters());

    assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());

    Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM orders_ack", Integer.class);
    assertEquals(3, count);
  }

  @Test
  void stepShouldCompleteSuccessfully() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchStep("ordersAckStep");

    assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

    StepExecution stepExecution = jobExecution.getStepExecutions().iterator().next();
    assertEquals(3, stepExecution.getReadCount());
    assertEquals(3, stepExecution.getWriteCount());
    assertEquals(0, stepExecution.getSkipCount());
  }
}
