# MyWebApp – Personal ToDo Management Application

## 📌 Project Overview

MyWebApp is a web-based ToDo management application developed using Java and Spring Boot. It allows users to register, securely log in, manage their personal tasks, and reset forgotten passwords through email OTP verification.

The application uses Spring Security for authentication and MySQL for persistent data storage.

## 🚀 Features

### User Authentication
- User registration.
- Secure login and logout.
- Password encryption using BCrypt.
- Custom authentication using Spring Security.
- Forgot password functionality using email OTP verification.
- OTP expiry and verification attempt limits.

### ToDo Management
- Create new tasks.
- View personal ToDo lists.
- Update existing tasks.
- Delete tasks.
- Store task descriptions, target dates, and status.
- Retrieve tasks associated with the logged-in user.

## 🛠️ Technologies Used

| Category | Technologies |
|---|---|
| Backend | Java, Spring Boot |
| Frontend | JSP, HTML, CSS, Bootstrap |
| Database | MySQL |
| ORM | Spring Data JPA, Hibernate |
| Security | Spring Security, BCrypt |
| Email | Spring Boot Starter Mail, Gmail SMTP |
| Build Tool | Maven |
| IDE | IntelliJ IDEA |
| Version Control | Git, GitHub |

## 🏗️ Project Structure

```text
MyWebApp/
│
├── src/main/java/com/Yashaswini/MyWebApp/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   ├── security/
│   └── MyWebAppApplication.java
│
├── src/main/resources/
│   ├── application.properties
│   └── META-INF/resources/WEB-INF/jsp/
│       ├── common/
│       ├── login.jsp
│       ├── register.jsp
│       ├── forgot-password.jsp
│       ├── verify-otp.jsp
│       └── reset-password.jsp
│
└── pom.xml
```

## ⚙️ Installation and Setup

### 1. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

### 2. Configure MySQL

Create a database:

```sql
CREATE DATABASE mywebapp;
```

Configure your database connection in `application.properties`.

### 3. Configure Email

Set the following environment variables:

```text
MAIL_USERNAME=your_email@gmail.com
MAIL_APP_PASSWORD=your_gmail_app_password
```

Never commit actual credentials to GitHub.

### 4. Run the Application

Open the project in IntelliJ IDEA and run:

```text
MyWebAppApplication.java
```

Access the application at:

```text
http://localhost:8080
```

## 🔐 Security

- Passwords are stored using BCrypt hashing.
- Spring Security manages authentication and authorization.
- Users access their own ToDo records.
- Password reset uses time-limited email OTP verification.

## 🎯 Learning Outcomes

Through this project, I gained practical experience in:

- Developing Java web applications using Spring Boot.
- Implementing authentication and authorization.
- Integrating MySQL with Spring Data JPA.
- Implementing email-based OTP verification.
- Building JSP-based web interfaces.
- Organizing applications using layered architecture.

## 👩‍💻 Author

**Yashaswini Rudrapaati**

Computer Science Engineering Graduate

GitHub: YOUR_GITHUB_PROFILE_URL

LinkedIn: YOUR_LINKEDIN_PROFILE_URL
