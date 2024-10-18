Here is the translation:

# Software Engineer Challenge - BTG Pactual

## Scope
Process orders and generate a report.

## Tasks
1. Prepare and submit a work plan.
   - Break down your tasks into steps
   - Estimate hours for each task
2. Create an application using the technology of your choice (JAVA, DOTNET, NODEJS).
3. Design and implement a database (PostgreSQL, MySQL, MongoDB).
4. Create a microservice that consumes data from a RabbitMQ queue and stores the data to enable listing the following information:
   - Total order value
   - Number of orders per customer
   - List of orders placed by each customer

Example of the message to be consumed:

```
   {
       "orderCode": 1001,
       "customerCode": 1,
       "items": [
           {
               "product": "pencil",
               "quantity": 100,
               "price": 1.10
           },
           {
               "product": "notebook",
               "quantity": 10,
               "price": 1.00
           }
       ]
   }
```

5. Create a REST API to allow querying the following information:
   - Total order value
   - Number of orders per customer
   - List of orders placed by each customer

6. Technical Report explaining briefly the following aspects:
   - Work Plan (planned vs actual)
   - If there were deviations between the original plan and execution, explain what happened.
   - If the work plan was followed without deviation, comment on the reasons for this result.
   - Technologies used
   - Languages, Versions, IDEs, OSs
   - Architecture diagram
   - Database design
   - Solution deployment diagram
   - Infrastructure diagram showing the cloud resources used (machine, OS, specific products, etc.)
   - Evidence of functional tests for the application
   - Publish the generated code on your GitHub profile (https://github.com/)
   - In the report, include:
     - Your GitHub profile and the URL(s) where the generated code is located
     - References used
     - Any other relevant items you consider important (Testing frameworks or techniques, methodologies, etc.)
     - If Docker was used for environment setup, publish the final images on your DockerHub profile (http://hub.docker.com)
     - In the report, include your DockerHub profile and the URL(s) where the generated images are located.
