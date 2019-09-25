package org.forest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationSearch {
  public static void main(String[] args) {
    SpringApplication.run(ApplicationSearch.class, args);
  }
}
