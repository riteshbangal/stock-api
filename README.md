# Stock-API

In this Part, we covered the below microservices:s
- `db-service` - For interactive with MySQL DB
- `stock-service` - For pulling Stock Price from YahooFinance API
- `eureka-service` - Service Registry for registering all microservices
- `zuul-service` - API Gateway/Proxy
- `stock-viewer` - For user interface

# Technology Stacks
Developing a stock-api micro-services using multiple technology stack.
- `Java 8` - Language used to write backend business logic
- `Spring` - Used spring framework for backend
- `Microservices` - Design Pattern using the Discovery Service
- `Spring Cloud Eureka`
- `Hystrix` - Circuit breakers
- `Zuul` - API Gateway/Proxy


## Architecture Diagram:
![Architecture](design/architecture.png)

# Actual URL:
#### DB: http://localhost:7000/data/user/rcb
#### Stock: http://localhost:7001/stock/welcome/rcb

# Proxy URL
#### Zuul: http://localhost:7003/zuul/api/db-service/data/user/rcb
#### Zuul: http://localhost:7003/zuul/api/stock-service/stock/welcome/rcb

#### Eureka: http://localhost:7002

#### DB: http://localhost:7002/api/db-service/data/user/rcb
#### Stock: http://localhost:7002/api/stock-service/stock/welcome/rcb

# Reference
[Tech Primers](https://www.youtube.com/watch?v=-luHIZJ6or0&list=PLTyWtrsGknYdZlO7LAZFEElWkEk59Y2ak)
