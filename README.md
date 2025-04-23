# 🚀 data-crawler: Public Data URL Crawling Batch System

This repository implements a batch system using Spring Boot and Spring Batch to periodically crawl public data URLs. It is designed to collect and process data according to user-defined request times. Let's build a fantastic crawling batch repository together!

## 🛠️ Project Setup

Create a new Spring Boot project using an IDE such as IntelliJ IDEA or Eclipse. Please configure the project with the following settings:

* **Project:** Gradle Project
* **Language:** Java
* **Spring Boot:** (Latest stable version - select during project creation)
* **Group:** `com.example`
* **Artifact:** `data-crawler`
* **Name:** `data-crawler`
* **Description:** Public Data Crawling Batch
* **Package Name:** `com.example.datacrawler`
* **Packaging:** Jar
* **Java:** 21

## ⚙️ Dependencies

Add the following dependencies to your `build.gradle` file:

```gradle
plugins {
    id 'java'
    id 'org.springframework.boot' version '{Latest Spring Boot Version}'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'

java {
    sourceCompatibility = '21'
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-batch'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.springframework.batch:spring-batch-test'

    // (Optional) For Thymeleaf template engine
    // implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'

    // (Optional) For Spring Data JPA
    // implementation 'org.springframework.boot:spring-boot-starter-data-jpa'

    // (Optional) For PostgreSQL database
    // runtimeOnly 'org.postgresql:postgresql'
}

tasks.named('test') {
    useJUnitPlatform()
}
