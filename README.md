
: Your task is to build a simplified solution for customers to apply for a real estate loan. The solution should consist of a frontend and a backend where the frontend is optional. The Backend should consist of a database with a REST API handling customers loan applications and should contain data such as: *CustomerSSN, FullName, LoanAmount, EquityAmount and SalaryAmount

Spring boot v 3.0.8 is used to implement the backend service which uses SPRING dependencies and annotations providing endpoints over a REST controller . two endpoints are created .
Application is connected using MySQL database for the entity layer
Spring security for BASIC AUthentication is configured . 
  - UserDetails and UserInfo from spring security is used for conifguration along required entity in database. which will make the URL to allow only previledged users
    
CDK folder contains lib and bin folder which holds files for IaC .
Applciation is containarized using Docker file and the same is pushed to ECR repository.


For the Spring application hosted in Fargate , Spring security for Basic Auth is configured, 
- when a URL eg http://localhost:8080/application/v1/allApplications is called a spring security sign up page will pop up.
- user access for this is retrieved from database , to procure access http://localhost:8080/application/v1/new can be used (No Auth required for this link) request body
{
    "name": "Veer",
    "email": itzmeash00@gmail.com,
    "password": "12345",
    "roles": "ROLE_ADMIN"
}

 http://localhost:8080/application/v1/allApplications in your browser or postman 
upon hitting a username and password wil be prompted.
provide - Veer as username
12345 as password
