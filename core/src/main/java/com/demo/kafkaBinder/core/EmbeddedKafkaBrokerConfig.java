package com.demo.kafkaBinder.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

@Configuration
@Profile({"dev", "test"})
@Slf4j
public class EmbeddedKafkaBrokerConfig {

  private static final String TMP_EMBEDDED_KAFKA_LOGS =
      String.format("/tmp/embedded-kafka-logs-%1$s/", UUID.randomUUID());
  private static final String PORT = "port";
  private static final String LOG_DIRS = "log.dirs";
  private static final String LISTENERS = "listeners";
  private static final Integer KAFKA_PORT = 9092;
  private static final String LISTENERS_VALUE = "PLAINTEXT://localhost:" + KAFKA_PORT;
  private static final Integer ZOOKEEPER_PORT = 2181;

  private EmbeddedKafkaBroker embeddedKafkaBroker;

  /**
   * bean for the embeddedKafkaBroker.
   *
   * @return local embeddedKafkaBroker
   */
  @Bean
  @Qualifier("embeddedKafkaBroker")
  public EmbeddedKafkaBroker embeddedKafkaBroker() {
    Map<String, String> brokerProperties = new HashMap<>();
    brokerProperties.put(LISTENERS, LISTENERS_VALUE);
    brokerProperties.put(PORT, KAFKA_PORT.toString());
    brokerProperties.put(LOG_DIRS, TMP_EMBEDDED_KAFKA_LOGS);
    this.embeddedKafkaBroker =
        new EmbeddedKafkaBroker(1, true, 2,"demoTopic")
            .kafkaPorts(KAFKA_PORT)
            .zkPort(ZOOKEEPER_PORT)
            .brokerProperties(brokerProperties);
    return embeddedKafkaBroker;
  }

  /** close the embeddedKafkaBroker on destroy. */
  @PreDestroy
  public void preDestroy() {
    if (embeddedKafkaBroker != null) {
      log.warn("[EmbeddedKafkaBrokerConfig]  destroying kafka broker {}", embeddedKafkaBroker);
      embeddedKafkaBroker.destroy();
    }
  }
}
