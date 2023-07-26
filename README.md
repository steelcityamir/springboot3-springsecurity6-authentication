# Starter app with Spring Boot 3.x and Spring Security 6.x
This project is a starter application with authentication using Spring Boot 3.x and Spring Security 6.x.

## Prerequisites
To run this project, you need:
- JDK 17 or above

## Getting Started
To get the project up and running on your local machine, do the following:

### 1. Clone the repo:
```bash
git clone https://github.com/codebyamir/springboot3-springsecurity6-authentication.git
```

### 2. Navigate to the project directory:

```bash
cd springboot3-springsecurity6-authentication/demo/
```

### 3. Build the project:

```bash
./mvnw clean install
```

### 4. Run the project:
```bash
./mvnw spring-boot:run
```

This will start the application it will be accessible at `http://localhost:8080`.

## Database

The app runs an in-memory H2 database and comes seeded with some sample users.

- Users are stored in the `account` table.
- Login attempts are stored in the `login` table.

## Pages

### Login page

- URL: http://localhost:8080/login
- Username: `admin@example.com`
- Password: `admin`


<img width="1469" alt="Screenshot 2023-07-26 at 1 49 39 PM" src="https://github.com/codebyamir/springboot3-springsecurity6-authentication/assets/54147931/92fa6c26-8c82-413a-989e-71476548ecaf">

### Register page

- URL: http://localhost:8080/register
<img width="1465" alt="Screenshot 2023-07-26 at 1 49 51 PM" src="https://github.com/codebyamir/springboot3-springsecurity6-authentication/assets/54147931/4840f89f-aae0-4e4b-afd6-e656399ebb72">




## License

This project is licensed under the terms of the MIT license. See the [LICENSE](LICENSE.md) file.
