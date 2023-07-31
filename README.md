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
![image](https://github.com/codebyamir/springboot3-springsecurity6-authentication/assets/54147931/f22717bd-bc81-4732-9646-8a6ab3ddfd02)


### Forgot Password page

- URL: http://localhost:8080/forgot

<img width="1460" alt="image" src="https://github.com/codebyamir/springboot3-springsecurity6-authentication/assets/54147931/37f121f9-ef15-4f00-9b52-724fbf3eab97">

### Reset Password page

- URL: `http://localhost:8080/reset?token=`

<img width="1464" alt="image" src="https://github.com/codebyamir/springboot3-springsecurity6-authentication/assets/54147931/26d9f071-dde5-41d0-b6c0-2cb74268f9c5">


## Password Reset Flow
The password reset and email verify features use a stateless token. The advantage of a stateless token is that it doesn't need to be saved to the database.  And it is still associated with an email address and expiration.  The disadvantage is that the link can be used multiple times prior to expiration.

1. User visits Forgot Password page at http://localhost:8080/forgot.
2. User enters their email and clicks Submit.
3. The app will lookup the email and if it finds an account that is enabled, it will generate a password reset token using a secret key, email, and expiration length.
4. A confirmation message is always shown to the user.  For security purposes, the message doesn't reveal if the email is associated with an account.
5. The app sends an email with a password reset link.
6. The user clicks on the link and they are taken to the Reset Password page.
  - If the token has expired or is invalid, the page will show an error.
  - If the token is valid, the page will allow the user to enter a new password. A hidden input field on the form is populated with the user's email address.
7. When the user submits the form, the app resets their password and redirects them back to the login page.


## License

This project is licensed under the terms of the MIT license. See the [LICENSE](LICENSE.md) file.
