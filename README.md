# Welcome to your personal Accounting Ledger API
## The Team
- Josh
- Christian
- Randy
- Abigiel
- Steven
- Andi
- Clancy

## The Plan
Based on Clancy's original Accounting Ledger App (https://github.com/clancycooper/AccountingLedgerApp) we decided to take this clunky CSV file reader/writer based application and reimagine it as a fast and convenient Springboot application.    
  # Step 1: Convert the CSV into a SQL Database using MySQL Workbench
  - ### Original:  
    ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/429b5882-e4e8-4807-bfcf-4f523464f5aa)
  - ### Updated:  
    ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/0eda6166-0320-48f7-9c48-58104016ab2e)

   # Step 2: Reconfigure our file systems from class chaos into an MVC structure
  - ### Original:  
  ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/9e93c099-4cd5-4e50-b609-2bba668eeafb)
  - ### Updated:
  ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/e15a51c2-2c79-4a23-9942-7d52ea5d2ccf)

  # Step 3: Fix bugs in our code with smoother logic by using SQL statements instead of complex Java logic
  - ### Original:
   ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/ec0e9f86-7702-4b99-982a-edfafbae6dbe)
  - ### Updated:
   ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/85e0deee-25f4-4ca0-a9cc-1df570fb11f6)

   # Step 4: Responsive testing with Postman versus the old fashioned way using the Command Line Interface
   - ### Original:
     ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/f48fe0b5-ed20-42b0-89e7-9c4440393d0f)

   - ### Updated:
     ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/90b55642-8c24-4966-b3e4-aeefe5431b62)

# List of tests we can now run in Postman:
## Create Transaction:
- Method: POST
- Endpoint: http://localhost:8080/transactions/create  
- Response: "Transaction created successfully."
### Body (example):
  {  
  "date": "2024-01-23",  
  "time": "11:30:35",  
  "description": "Paycheck",  
  "vendor": "Best Buy",  
  "amount": 100.00  
  }  
  ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/2fdc982a-f70a-4bbe-8a0f-708908d6306a)


## Get All Transactions:
- Method: GET
- Endpoint: http://localhost:8080/transactions/all
- Response: List of all transactions
  ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/f3c674a5-ddb9-470a-81c6-3457baf99d75)


## Get Transactions by Month to Date:
- Method: GET
- Endpoint: http://localhost:8080/transactions/month-to-date/{date}
- Replace {date} with the desired date (e.g., "2024-01-23")
- Response: List of transactions for the specified month to date
  ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/e66ccb0d-e71d-49b5-8df1-cd07cc488083)


## Get Transactions by Year to Date:
- Method: GET
- Endpoint: http://localhost:8080/transactions/year-to-date/{date}
- Replace {date} with the desired date (e.g., "2024")
- Response: List of transactions for the specified year to date
  ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/64ef86ee-7702-459b-b88a-867505da6b13)


## Get Transactions for Previous Month:
- Method: GET
- Endpoint: http://localhost:8080/transactions/previous-month/{date}
- Replace {date} with the desired date (e.g., "2024-01-23")
- Response: List of transactions for the previous month
  ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/b0364b9f-1c56-43da-800d-5b67c654851d)


## Get Transactions for Previous Year:
- Method: GET
- Endpoint: http://localhost:8080/transactions/previous-year/{date}
- Replace {date} with the desired date (e.g., "2024")
- Response: List of transactions for the previous year
  ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/944cfcf6-e847-4433-a1a3-27999b34ac4a)


## Get Payments:
- Method: GET
- Endpoint: http://localhost:8080/transactions/payments
- Response: List of payment transactions (amount < 0)
  ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/0520c8c7-3b8f-44d0-8433-5d37d8f24547)


## Get Deposits:
- Method: GET
- Endpoint: http://localhost:8080/transactions/deposits
- Response: List of deposit transactions (amount > 0)
  ![image](https://github.com/clancycooper/AccLedgerSpring/assets/141694194/0284968a-3f48-44a9-bb73-491d40b07ad6)








