Client & Appointment Management System
A Java‑based console application for managing users, services, appointments, and payments. The system supports multiple roles (Client, Advisor, CSR) and demonstrates clean backend architecture, database integration, and structured software design.

Features
User registration & login (Client, Advisor, CSR)

Role‑based dashboards

Service browsing

Appointment booking

Payment recording

Advisor booking updates

CSR management tools

Technologies Used
Java (OOP, JDBC)

PostgreSQL

Maven

IntelliJ IDEA

UML Documentation (CRC Cards, Sequence & Communication Diagrams, Class Diagram)

Project Structure
src/        # Java source code  
database/   # SQL schema + sample data  
docs/       # UML & design documentation PDF  
pom.xml  
README.md
Database Setup
Create a PostgreSQL database named wealthcare

Run the SQL script in database/create_tables.sql

Insert sample services using sample_data.sql

Running the Project
Update your PostgreSQL username/password in DbConnection.java

Build the project using Maven or IntelliJ

Run Main.java
Documentation
All UML diagrams and design artifacts are included in:
docs/Client and Appointment Documentation.pdf
