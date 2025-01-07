# Currency Conversion API

A Spring Boot application that provides currency exchange rates and performs currency conversions. The application uses a public API to fetch real-time exchange rates and caches the data in a database for subsequent requests.

---

## Features

- **Fetch Exchange Rates**: Get real-time exchange rates for a base currency.
- **Convert Currency**: Convert an amount from one currency to another using the exchange rates.
- **Fallback Mechanism**: If the external API or database is unavailable, handles errors gracefully.

---

## Endpoints

### 1. Fetch Exchange Rates
**GET** `/api/rates?base=USD`

Fetches exchange rates for the given base currency.

- **Request Parameters**:
  - `base` (optional): Base currency (default: `USD`).

- **Sample Response**:
  ```json
  {
    "base": "USD",
    "rates": {
      "EUR": 0.945,
      "GBP": 0.755,
      "JPY": 130.5
    }
  }
  ```

---

### 2. Convert Currency
**POST** `/api/convert`

Converts an amount from one currency to another.

- **Request Body**:
  ```json
  {
    "from": "USD",
    "to": "EUR",
    "amount": 100
  }
  ```

- **Sample Response**:
  ```json
  {
    "from": "USD",
    "to": "EUR",
    "amount": 100,
    "convertedAmount": 94.5
  }
  ```

---

## Getting Started

### Prerequisites
- **Java 21** or higher
- **Maven**
- **MySQL** or any database of your choice

---

### Installation and Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/currency-conversion-api.git
   cd currency-conversion-api
   ```

2. **Configure the Database**:
   - Update the database configuration in `application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/currency_db
     spring.datasource.username=root
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build the Project**:
   ```bash
   mvn clean install
   ```

4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

5. **Access the API**:
   - The application will run on `http://localhost:8080`.

---

## Testing with Postman

1. **Fetch Exchange Rates**:
   - Method: `GET`
   - URL: `http://localhost:8080/api/rates?base=USD`

2. **Convert Currency**:
   - Method: `POST`
   - URL: `http://localhost:8080/api/convert`
   - Body (raw JSON):
     ```json
     {
       "from": "USD",
       "to": "EUR",
       "amount": 100
     }
     ```

---

## Technologies Used

- **Spring Boot**: Backend framework
- **MySQL**: Database
- **RestTemplate**: For external API calls
- **Lombok**: Simplifies code with annotations
- **Postman**: For testing APIs

---

## Troubleshooting

- **Base currency not found in the database**:
  - Ensure rates are fetched and stored correctly in the database.
  - Manually insert rates into the database if needed for testing.

- **API connection issues**:
  - Verify the external API is reachable.
  - Check your network/firewall settings.

- **Database connection issues**:
  - Ensure the database is running and credentials are correct.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

---

## Author

- Konduri Praneeth
- https://github.com/praneethkonduri09

---

## Acknowledgments

- [ExchangeRate-API](https://www.exchangerate-api.com/) for real-time exchange rates. highlight the headings and give me in a single file

