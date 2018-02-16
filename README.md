# Stock-API

In this Part, we covered the below microservices:s
- `db-service` - For interactive with MySQL DB
- `stock-service` - For pulling Stock Price from YahooFinance API
- `eureka-service` - Service Registry for registering all microservices
- `stock-viewer` - For user interface

# Technology Stacks
Developing a stock-api micro-services using multiple technology stack.
- `Java 8` - Language used to write backend business logic
- `Spring` - Used spring framework for backend

## Architecture Diagram:
![Architecture](design/architecture.png)

# URL
## Eureka: http://localhost:7002/

## DB: http://localhost:7002/api/db-service/data/rcb/
## Stock: http://localhost:7002/api/stock-service/stock/rcb/

## Normal:
####  DB: http://localhost:7000/data/rcb
#### Stock: http://localhost:7001/stock/rcb

