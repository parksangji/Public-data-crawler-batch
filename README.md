# Data Crawler Batch

This project is a Spring Boot-based batch application designed to periodically crawl current exchange rate information from a public data URL and store it in a database.

## Key Features

* **Current Exchange Rate Crawling:** Fetches real-time exchange rate data by calling the current exchange rate API provided by the Export-Import Bank of Korea.
* **Scheduled Execution:** Utilizes Spring Scheduler to automatically perform crawling tasks at configured intervals.
* **Spring Batch-based Processing:** Employs the Spring Batch framework for efficient processing and management of large datasets.
* **Data Persistence:** Stores the crawled exchange rate information in a PostgreSQL database for management.

## Technical Stack

* **Java:** 21
* **Spring Boot:** 3.x
* **Spring Batch:** Batch processing framework
* **Spring Data JPA:** Database interaction
* **PostgreSQL:** Database
* **RestTemplate:** REST API calls
* **Jackson:** JSON data parsing
* **Lombok:** Automatic generation of boilerplate code
* **Spring Scheduler:** Task scheduling
* **Slf4j & Logback:** Logging

## Project Structure
```
data-crawler/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/datacrawler/
│   │   │       ├── batch/
│   │   │       │   ├── ExchangeRateBatchConfig.java  // Batch job configuration
│   │   │       │   ├── processor/
│   │   │       │   │   └── ExchangeRateProcessor.java  // Data processing (currently logging)
│   │   │       │   ├── reader/
│   │   │       │   │   └── ExchangeRateReader.java     // API call and data reading
│   │   │       │   └── writer/
│   │   │       │       └── ExchangeRateWriter.java     // Database storage
│   │   │       ├── config/
│   │   │       │   └── RestTemplateConfig.java         // RestTemplate configuration
│   │   │       ├── dto/
│   │   │       │   └── ExchangeRateDto.java            // API response data DTO
│   │   │       ├── entity/
│   │   │       │   └── ExchangeRate.java               // Database Entity
│   │   │       ├── repository/
│   │   │       │   └── ExchangeRateRepository.java       // Database access
│   │   │       ├── scheduler/
│   │   │       │   └── ExchangeRateScheduler.java      // Scheduling configuration
│   │   │       ├── service/
│   │   │       │   └── ExchangeRateService.java        // API call service (initial version)
│   │   │       └── DataCrawlerApplication.java       // Main application
│   │   └── resources/
│   │       └── application.properties (or application.yml) // Configuration file
│   └── test/
│       └── ...
├── pom.xml (for Maven)
└── build.gradle (for Gradle)
```

## Configuration

Application settings are managed in the `application.properties` or `application.yml` file. Key configuration items include:

* **Database Connection:**
    ```properties
    spring.datasource.url=jdbc:postgresql://your_host:5432/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.datasource.driver-class-name=org.postgresql.Driver
    ```
* **Spring Batch Table Auto-Creation (Development Environment):**
    ```properties
    spring.batch.jdbc.initialize-schema=always
    ```
    (For production environments, using database migration tools is recommended)
* **Scheduler Configuration:**
    ```properties
    crawler.schedule.enabled=true  // Enable or disable the scheduler
    crawler.schedule.cron=0 0 * * * * // Execution frequency (every hour at the top of the hour)
    ```
* **Exchange Rate API Configuration:**
    ```properties
    crawler.exchange.authkey=YOUR_AUTH_KEY  // API authentication key
    crawler.exchange.url=[https://www.koreaexim.go.kr/site/program/financial/exchangeJSON](https://www.koreaexim.go.kr/site/program/financial/exchangeJSON)  // API URL
    ```

## How to Run

1.  **Clone the Repository:**
    ```bash
    git clone [repository URL]
    cd data-crawler
    ```
2.  **Build:**
    * **Maven:**
        ```bash
        mvn clean install
        ```
    * **Gradle:**
        ```bash
        ./gradlew clean build
        ```
3.  **Run:**
    * **Maven:**
        ```bash
        mvn spring-boot:run
        ```
    * **Gradle:**
        ```bash
        ./gradlew bootRun
        ```

Once the application starts, it will crawl the exchange rate information and store it in the database according to the configured schedule. You can monitor the progress through the logs.

## Future Development

* **Develop Data Retrieval API:** Create a REST API to query the stored exchange rate information.
* **Implement Exchange Rate Change Notifications:** Add functionality to send notifications based on specific exchange rate conditions.
* **Enhance Error Handling and Logging:** Improve error handling and logging strategies for better batch job stability.
* **Write Tests:** Add unit and integration tests to enhance code quality.

## Commit Message Convention

This project follows the following commit message convention:

&lt;type>(&lt;scope>): &lt;subject>

&lt;body>

&lt;footer>


  * `<type>`: Commit type (feat, fix, docs, style, refactor, test, chore, etc.)
  * `<scope>`: Scope of the change (e.g., batch, scheduler, service, entity) (optional)
  * `<subject>`: Concise summary of the change (maximum 50 characters)
  * `<body>`: Detailed explanation of the change (optional)
  * `<footer>`: Related issue numbers, etc. (optional)

**Example:**

feat: Integrate current exchange rate crawling with Spring Batch

Implemented the functionality to call the current exchange rate API from the public data portal and integrate it with a Spring Batch Job for periodic execution.

Created and configured related classes such as ExchangeRateDto, Service, BatchConfig, and Scheduler.
Added API URL, authentication key, and scheduling settings to application.properties.
The next step will be to implement the functionality to store the crawled data in the database.


## Contributing

Contributions to the project are welcome. Please submit a Pull Request with your proposed changes, ensuring adherence to the code style and conventions.
