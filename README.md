# Daily Finance API Automation – Rest Assured

---

## 📖 Project Overview
This project demonstrates **API testing and automation using Rest Assured** for the Daily Finance web application.  
The APIs were first identified by inspecting network requests from the browser Developer Tools and organized into a Postman collection.  
Later, the same collection was fully automated using **Rest Assured**.

---

## 🔗 Application URL
https://dailyfinance.roadtocareer.net/

---

## 🛠 Tools & Technologies
- Java
- Rest Assured
- TestNG 
- Postman
- Gradle
- JSON
- Bearer Token Authentication

---

## 📂 API Coverage

### 🔹 User Management APIs
- Register a new user
- Login as Admin  
- Get user list
- Search user by user ID
- Edit user information  
  - First name  
  - Phone number
- Log in as a normal user

---

### 🔹 Item Management APIs
- Get item list
- Add a new item
- Edit item name
- Delete an item from the item list

---

## 🔐 Authentication
- Bearer token–based authentication is implemented
- Tokens are generated dynamically during login and reused for secured endpoints

---

## 🧪 Automation Scope (Rest Assured)
- Positive test cases for all APIs
- Status code validation
- Response body validation
- API flow validation for Admin and User roles

---

## ▶️ How to Run the Tests
1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Run `gradle clean test`  
   or run test classes directly using TestNG/JUnit

---

## ✅ Expected Outcome
- All valid API requests return expected success responses
- Invalid scenarios return proper error responses
- End-to-end API workflows are validated successfully

---

## ▶️ Video Link of The Entire Test
https://somup.com/cOV12cSXg3

## ✅ Test Result
<img width="1914" height="1010" alt="image" src="https://github.com/user-attachments/assets/1626f4df-745d-44b9-931d-12efd3a3494a" />
<img width="1910" height="1011" alt="image" src="https://github.com/user-attachments/assets/da084eed-c457-4098-8523-893b5cea66c6" />

## 🧪 PostMan API Documentation 
https://documenter.getpostman.com/view/22303657/2sBXVeDrPb

## 📖 Test Cases Documentation 
https://docs.google.com/spreadsheets/d/15EQ6jNiS5V8wYAvxg78AiVNGy-d_fOaK/edit?usp=sharing&ouid=110308499909751989760&rtpof=true&sd=true




