# golf_autotests

## Test Data
You can add test data in **xlsxFileWithTestData.xlsx** file located in main/resources directory.
It contains separate sheets for various types of tests.


## Run tests
1) Run all tests:
   `mvn clean test`
2) Get test report:
   `mvn allure:serve`
3) Generate report: `mvn allure:report`
 
   Report generated in folder: target/allure-reports, launch from index.html


