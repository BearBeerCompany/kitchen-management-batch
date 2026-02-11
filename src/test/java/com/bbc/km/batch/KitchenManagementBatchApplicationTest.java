package com.bbc.km.batch;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
class KitchenManagementBatchApplicationTest {

  @Test
  void mainMethodShouldBootApplication() {
    KitchenManagementBatchApplication.main(
        new String[] {
          "--spring.profiles.active=test",
          "--spring.datasource.url=jdbc:h2:mem:maintest;DB_CLOSE_DELAY=-1",
          "--spring.datasource.driver-class-name=org.h2.Driver",
          "--spring.datasource.username=sa",
          "--spring.datasource.password=",
          "--spring.batch.jdbc.initialize-schema=always"
        });
  }
}
