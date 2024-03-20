# Salmon-SpringBoot-Starter

Salmon-SpringBoot-Starter is a highly customizable Spring Boot starter for simplifying the integration, configuration, and optimization of MQ (Message Queue) systems within your Spring Boot applications. This starter aims to streamline the process of setting up and managing various MQ parameters, implementing tuning strategies, and ensuring seamless messaging operations.

## Features

- **Easy Configuration**: Simplify the configuration of your MQ parameters with Spring Boot's application properties, offering a straightforward approach to set up your messaging system.

- **Performance Tuning**: Comes with out-of-the-box support for performance tuning, allowing you to optimize your MQ usage according to your application's needs.

- **Flexible Strategies**: Implement custom strategies for message consumption, error handling, and retry mechanisms, tailoring the behavior of the MQ to suit your specific requirements.

- **Health Monitoring**: Integrates with Spring Boot's health monitoring system, providing insights into the status of your MQ connections and facilitating early detection of issues.

## Getting Started

### Prerequisites

- Java 8 or later
- Spring Boot 2.x or later

### Adding the Starter to Your Project

Add the `salmon-springboot-starter` dependency to your Maven `pom.xml`:

```xml
<dependency>
    <groupId>com.example</groupId>
    <artifactId>salmon-springboot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
