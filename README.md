# Smart Aggregator API

A RESTful Spring Boot service that aggregates and tracks users' financial portfolios in real-time, providing up-to-date price information for stocks and cryptocurrencies along with relevant financial news and market sentiment.

## Features

- 🔐 **JWT Authentication** - Secure API endpoints with token-based authentication
- 📊 **Stock Tracking** - Real-time stock prices via Alpha Vantage API
- 💰 **Crypto Tracking** - Live cryptocurrency prices via CoinGecko API
- 📰 **Financial News** - Aggregated financial news with sentiment analysis
- 👤 **User Portfolios** - Track multiple users with personalized stock/crypto holdings
- 📈 **Portfolio Summary** - Consolidated view of all user investments

## Tech Stack

- **Framework:** Spring Boot 3.x
- **Security:** Spring Security + JWT
- **Database:** PostgreSQL
- **ORM:** Hibernate/JPA
- **Build Tool:** Maven
- **APIs:** Alpha Vantage (stocks), CoinGecko (crypto)

## Prerequisites

- Java 17+
- Maven 3.6+
- PostgreSQL 12+
- API Keys:
  - [Alpha Vantage API Key](https://www.alphavantage.co/support/#api-key)
  - [CoinGecko API Key](https://www.coingecko.com/en/api) (optional for free tier)

## Setup

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/smart-aggregator.git
cd smart-aggregator
```

### 2. Configure database
Create a PostgreSQL database:
```sql
CREATE DATABASE smartaggregator;
```

### 3. Configure application properties
Copy the template and add your credentials:
```bash
cp src/main/resources/application.properties.template src/main/resources/application.properties
```

Edit `application.properties` with your actual values:
- Database credentials
- API keys
- JWT secret

### 4. Build and run
```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

### Authentication

#### Login
```http
POST /auth/login
Content-Type: application/json

{
  "username": "zain",
  "password": "supersecret"
}
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

Use this token in subsequent requests:
```http
Authorization: Bearer {token}
```

### User Management

#### Add/Update User Portfolio
```http
POST /users/add
Authorization: Bearer {token}
Content-Type: application/json

{
  "username": "john",
  "stocks": ["AAPL", "GOOGL", "TSLA"],
  "cryptos": ["bitcoin", "ethereum", "solana"]
}
```

#### Get All Users
```http
GET /users/allUsers
Authorization: Bearer {token}
```

### Portfolio Summary

#### Get User Summary
```http
GET /summary?user=john
Authorization: Bearer {token}
```

Response includes:
- Real-time stock prices
- Real-time crypto prices
- Latest financial news

### Stock Information

#### Get Stock Price
```http
GET /stocks/price?symbol=AAPL
Authorization: Bearer {token}
```

### Crypto Information

#### Get Crypto Price
```http
GET /crypto/price?coin=bitcoin
Authorization: Bearer {token}
```

## Database Schema

- **app_user** - User information
- **user_stocks** - User's tracked stocks
- **user_cryptos** - User's tracked cryptocurrencies
- **stock** - Stock data cache
- **crypto** - Crypto data cache
- **news_article** - Financial news articles
- **asset** - Generic asset information

## Project Structure
```
src/main/java/com/smartaggregator/
├── controller/       # REST endpoints
├── service/          # Business logic
├── entity/           # JPA entities
├── repository/       # Data access
├── security/         # JWT & authentication
└── dto/              # Data transfer objects
```

## Environment Variables (Optional)

Instead of application.properties, you can use environment variables:
```bash
export DB_URL=jdbc:postgresql://localhost:5432/smartaggregator
export DB_USERNAME=postgres
export DB_PASSWORD=yourpassword
export JWT_SECRET=your-secret-key
export ALPHAVANTAGE_KEY=your-key
```

## Future Enhancements

- [ ] Add unit and integration tests
- [ ] Implement caching for API responses
- [ ] Add WebSocket support for real-time updates
- [ ] Portfolio performance analytics
- [ ] Price alerts and notifications
- [ ] Historical price charts

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request


## Contact

Zain Morshed - zainmorshed@gmail.com

Project Link: https://github.com/zainmorshed/smart_aggregator
