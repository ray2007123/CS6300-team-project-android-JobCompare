# Test Plan

**Author**: Team 020

## 1 Testing Strategy

### 1.1 Overall strategy

For this project, we will implement self-testing strategies at the Unit-, Integreation- (putting the pieces together, big bang strategy), and Regression- testing level using JUnit. We will create a set of test cases for the contracts specified for each of our files and ensure high levels of line coverage. 

Unit testing is the practice of testing small pieces of code, typically individual functions, alone and isolated. We will set tests which can express each unit functions of the application.

Integration tests will test each individual contract between components, ensuring the integraity of overall system design, proper interaction of compoents, and we will run simple system-level tests. Our tests will focus on our service layer as much as possible. 

Regression testing is the repeated verification of existing tests based on unit testing and integration testing. The unit tests, integreation test, and regression tests will have some overlap. 

At the System testing level, we will detail a set of behavioral test cases, supplying inputs and examining expected outputs by hand to test the integrity of the system as a whole, and ensure the compliance with functional requirements. It will also test the non-functional property like performance testing, reliability testing, and strss testing. This will also test the contract between the service layer and the controller layer, given a sufficiently simple controller.

### 1.2 Test Selection

We will use both black-box and/or white-box techniques. 

Black box testing is simply testing as if the software itself was a black box. When doing black box testing, the only concerned would be the inputs and outputs. It either works or it doesn’t. White box testing is pretty much the opposite of black box testing. With white box testing, it will generate some idea of what is going on inside the software. We will create tests that target those scenarios that supposed to be happen when using the application.

We will primarily use the category-partition method. This will allow us to thoroughly identify, characterise, and optimize the parameters. Our system level testing will be implemented using a combination of category-partition and FSM testing.

### 1.3 Adequacy Criterion

To assess the quality of our unit, integration, and regression tests we will rely primarily on code coverage. We can rely on our IDE's coverage tool for this.

However, we will also identify key boundary conditions using the methods and verify that they have been satisfied. Our system-level testing will need to rely on our analysis from the previous section to ensure all conditions are met.

### 1.4 Bug Tracking

Bugs will be tracked using the Issues tab in the repository in Github. With the growth of the project, we may choose to use another bug-tracking system such as Jira.

### 1.5 Technology

We will use JUnit for non-system testing where possible.

## 2 Test Cases

| Test ID    | Purpose               | Prereqs    | Tester    | Expected result     | Actual result  | pass/fail |  comments     |
| -----      | --------------------- | ---------  | --------  | ---------------     | -------------- | --------- | ------------- |
| test1      | Verify user can find all the functions on the main menu  | App builds and runs | David  |  User will see all the buttons on main menu page | User sees all the buttons on main menu page |  pass  |                |
| test2      | Check main menu UI buttons        | App builds and runs | Jessie |  Every button will work as expected | Every button works as expected |  pass  |               |
| test3      | Verify user can enter his current job info   | App builds and runs     | Mingming   | User can enter and view the job after entered | User can enter and view the job after entered |  pass   |               |
| test4      | Verify user can save job offer info | App builds and runs   | David  | The user can enter and save the job offer in the database | The user can enter and save the job offer in the database | pass      |               |
| test5      | Verify user can enter comparison weights  | App builds and runs  | Jessie | default value = 1, the user will be able to edit the value | The user can edit comparison weights |  pass     |               |
| test6      | Verify user can return to main menu after enter second/third function page   | App builds and runs   | Mingming | There will be a "Cancel" or "Back" button in each section on the UI  | There is a "Cancel" or "Back" button in each section on the UI | pass      |               |
| test7      | Calculation check  | App builds and runs | David  | The expected score of the Job will be calculated correctly | The expected score of the Job is calculated correctly |   pass    |               |
| test8      | Verify user can compare Jobs | App builds and runs | Jessie | When user click the "Compare jobs" button, it will allow the user to select jobs to compare and compare them | When user click the "Compare jobs" button, the user can select jobs to compare and compare them | pass    |               |
| test9      | Verify user can save/cancel an edit content attempt | App builds and runs | Mingming | Database is called to save attempt is the user click "save" button, otherwise, the input is cancelled | Database is called to save attempt is the user click "save" button, otherwise, the input is cancelled |  pass   |               |
| test10     | Verify the Job ID can be automatically generated | App builds and runs | David | Job ID will be generated in the database | Job ID is generated in the database | pass    | Since it's not part of the requirements, job ID is only visible while debugging              |
| test11     | If the user input a not valid character, the app will popup a error message | App builds and runs | Jessie | User will be notified when invalid input is attempted  | User is notified when invalid input is attempted |  pass   |               |
| test12     | Verify user can edit his current job info  | App builds and runs  | Mingming | Current job info will be editable | Current job info is editable | pass    |               |
| test13     | Comparison method can generate a table for the job comparison | App builds and runs  | David | There will be a table showed to the user with job comparison info| There is a table with job comparison info |  pass   |               |
| test14     | Verify the user is able to rank the jobs | App builds and runs | Jessie | List of current job and job offers will be displayed in ranked order on Compare jobs screen| List of current job and job offers is displayed in ranked order on Compare jobs screen |   pass  |              |
| test15     | The system will automatically remember the user's information | App builds and runs | Mingming | User information will be persisted across sessions  | User information is persisted across sessions    | pass    |          |
| test16     | Verify the functions in this class: DBController | JUnit | Mingming | Test if the user can insert comparison settings, insert job offers, update comparison setting, get comparison setting information, and update job offers, check all jobs, and job ID  | User can insert/update/check all the information mentioned above, and got updated in the database   | pass    |     |
| test17     | Verify the functions in this class: JobManager | JUnit | Mingming | Test if the user can enter current job, including the detail information asked in the requirement, enter job offers with detail information. Also need to test if the user is able to adjust the comparison setting, then compare the jobs in the interface  | User can enter/edit the corresponding information mentioned above when enter the interface of the app  | pass    |    |
| test18     | Verify the functions in this class: AdjustComparisonSettingsActivity | JUnit | Jessie | Tests that AdjustComparisonSettingsActivity is producing an expected error message for blank, invalid, and too large input values. | 3 out of 3 unit tests pass |   pass  |  |
| test19     | Verify the functions in this class: EnterEditCurrentJobActivityTest | JUnit | Jessie | Tests that EnterEditCurrentJobActivityTest is producing an expected error message for blank, invalid, and too large input values. | 3 out of 3 unit tests pass |   pass  |  |
| test20     | Verify the functions in this class: EnterJobOfferActivityTest | JUnit | Jessie | Tests that EnterJobOfferActivityTest is producing an expected error message for blank, invalid, and too large input values. | 3 out of 3 unit tests pass |   pass  |  |
| test21     | Verify the functions in this class: ComparisonSettings | JUnit | Mingming | Test if the app can extract the comparison setting information with null/not null condition | The app can extract the comparison setting information of the job  | pass    |    |
| test22     | Verify the functions in this class: CurrentJob | JUnit | Mingming | Verify if the user can save the current job with the detail information he entered on the interface  | The user is able to save the current job in the app  | pass    |    |
| test23     | Verify the functions in this class: Job | JUnit | Mingming | Test if the user can got the job information, calculate the job score by the weight values with null/not null condition |  User can check all the jobs he entered and get the job score by the weight values  | pass    |    |
| test24     | Verify the functions in this class: User | JUnit | Mingming | Test if the user can check and set the comparison settings of the job with null/not null condition |  User can check and set the comparison settings of the job with different values  | pass    |    |
| test25     | Verify the functions in this class: IntUtil | JUnit | Jessie |  Test that IntUtil will return a true value with a valid expected string input. Invalid string, null string, empty string inputs will return a false value. Valid string inputs, but with an unexpected value, such as "1" will also return a false. | 5 out of 5 unit tests pass |   pass  |  |
| test26     | Verify the functions in this class: Money | JUnit | Jessie | Test that the Money utility is able to accept a value from a valid string. In constrast, invalid string, null string, and empty string inputs will produce a value of 0.   | 4 out of 4 unit tests pass |   pass  |  |




