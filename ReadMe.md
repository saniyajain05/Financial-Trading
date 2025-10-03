# 💹 Financial Trading Simulation

A **Java-based trading system** that simulates order books, user interactions, and market data publishing.  
This project demonstrates software engineering principles applied to a simplified financial exchange, using design patterns like **Factory, Observer, DTO, Facade, and Flyweight**.

---

## ✨ Features
- **Order Book Simulation**  
  Manage buy/sell sides with `BookSide`, `ProductBook`, and `ProductBookSide`.

- **Market Data Publishing**  
  Pushes current market info to observers (`CurrentMarketObserver`, `CurrentMarketPublisher`).

- **User & Product Management**  
  Classes for users (`User`, `UserManager`) and financial instruments (`ProductManager`).

- **Error Handling**  
  Custom exceptions for invalid operations (`OrderNotFoundException`, `InvalidPriceOperation`, etc.).

- **Design Patterns Used**  
  - Factory Pattern (`PriceFactory`)  
  - Observer Pattern (`CurrentMarketPublisher`)  
  - DTO Pattern (`OrderDTO`)  
  - Facade Pattern (`TrafficSim`)  
  - Flyweight & Monostate in `Price`  

---

## 📂 Project Structure
src/financial_trading/
BookSide.java
CurrentMarketObserver.java
CurrentMarketPublisher.java
CurrentMarketSide.java
CurrentMarketTracker.java
DataValidationException.java
InvalidPriceOperation.java
Main.java
Order.java
OrderDTO.java
OrderNotFoundException.java
Price.java
PriceFactory.java
ProductBook.java
ProductBookSide.java
ProductManager.java
TrafficSim.java
User.java
UserManager.java

---

## 🚀 How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/saniyajain05/Financial-Trading.git
   cd Financial-Trading/src

2. Compile:
    ```bash
    javac financial_trading/*.java

3. Run simulation:
    ```bash
    java financial_trading.Main

## 📊 Example Output
- **Sample console output from the trading simulation:** 
    - [Market] AAPL @ $150 x 100 BUY
    - [Market] AAPL @ $151 x 50 SELL
    - [Trade] Executed: AAPL 50 @ $150.5

## 📈 Future Work
- **Integrate with Yahoo Finance API for real-time data**
- **Add a backtesting engine with historical datasets**
- **Implement risk management & portfolio analytics**
- **Create a visual dashboard (JavaFX or Python Streamlit)**

## 🛠 Tech Stack
- **Java 17**
- **Object-Oriented Design Patterns**
- **JUnit (for testing)**

## 📜 License
This project is licensed under the MIT License © 2025 Saniya Jain