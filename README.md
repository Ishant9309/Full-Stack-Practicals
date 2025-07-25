# Hibernate API CRUD Project

This is a simple console-based Java application built using **Hibernate (JPA)** and **PostgreSQL** to perform **CRUD operations** on two tables:

- regions  
- countries

## Technologies Used

- Java (JDK 11+)
- Hibernate 5.6
- PostgreSQL
- Maven
- JDBC
- Console-based Input (Scanner)

## Database Schema

```sql
CREATE TABLE regions (
    region_id SERIAL PRIMARY KEY,
    region_name VARCHAR(25) DEFAULT NULL
);

CREATE TABLE countries (
    country_id CHAR(2) PRIMARY KEY,
    country_name VARCHAR(40) DEFAULT NULL,
    region_id INT NOT NULL,
    FOREIGN KEY (region_id) REFERENCES regions(region_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
```
## Contact 
- Name: Ishant Teli
- Email: ishantteli27@gmail.com
- GitHub: Ishant9309
