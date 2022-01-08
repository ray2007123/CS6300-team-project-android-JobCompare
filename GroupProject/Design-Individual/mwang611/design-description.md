## **Design Description**

1)The user is presented with the main menu. The 'user' class was added and can provide the action in the main menu including (a) 'enter job offer', (b) 'enter or edit current job', (c) 'compare' jobs offers and (d) adjust the comparison settings (disabled if no job offers were entered yet). 

2)When choosing to enter current job details, a user will:
a. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
i. Title
ii. Company
iii. Location (entered as city and state)
iv. Cost of living in the location (expressed as an index)
v. Yearly salary
vi. Yearly bonus
vii. Allowed weekly telework days (expressed as the number of days per week allowed for remote work, inclusively between 0 and 5)
viii. Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)
ix. Gym membership allowance ($0 to $500 annually)

b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.

- 'enter/edit current job' action has the related requirement which need to be filed by the user. If it is the first job for the user, nothing will happen, else the method 'notTheFirstJob' in 'User' class will show to notice the user. The method 'savejob()' can save the current job. The method 'cancel ()' will cancel and exit without saving. 
- The 'current job' is a class associated with the 'enter/edit current job' action.
-'job' is a subclass of the 'current jobs' class and has all the details of jobs as items.

3)When choosing to enter job offers, a user will:
a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
b. Be able to either save the job offer details or cancel.
c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer (if they saved it) with the current job details (if present).

- 'enter job offer' contains all the details of the job offers which are same items in 'job' class. 
- SaveJob() method will save the job offer. Cancel() method will cancel the input information. EnterAnother() method would allow user input another job offer. The ExitToMenu() method will return to the main menu. The CompareJobOffer() method can provide the function of comparison of the job offer with 'current jobs'. 
- Class 'Job Offer' is associated with the 'enter job offer' action which also refer to the 'Jobs' class. 

4)When adjusting the comparison settings, the user can assign integer weights to:
a. Yearly salary
b. Yearly bonus
c. Allowed weekly telework days
d. Leave time
e. Gym membership allowance
If no weights are assigned, all factors are considered equal.

- Users can adjust 'Comparison Setting' which has the items (weights for Yearly salary, Yearly bonus, Allowed weekly telework days, Leave time, Gym membership allowance). The default value are all 1 which means all factors are considered equal if no weights are assigned.

5)When choosing to compare job offers, a user will:
a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
b. Select two jobs to compare and trigger the comparison.
c. Be shown a table comparing the two jobs, displaying, for each job:
i. Title
ii. Company
iii. Location
iv. Yearly salary adjusted for cost of living
v. Yearly bonus adjusted for cost of living
vi. Allowed weekly telework days
vii. Leave time
viii. Gym Membership Allowance

d. Be offered to perform another comparison or go back to the main menu.

- 'Compare Job Offers' will allow users to compare two job offers with detail infomations. 
- It can create a 'Rank' class via 'Ranking()' method. It will show an ordered job list according to the computing results.
- Method 'another compare()' would allow another comparison.
- 'exit to main menu()' method will back to main menu().

6)When ranking jobs, a job’s score is computed as the weighted sum of:
AYS + AYB + GYM + (LT * AYS / 260) - ((260 - 52 * RWT) * (AYS / 260) / 8)
where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
GYM = Gym Membership Allowance ($0 to $500 annually)
LT = leave time
RWT = telework days per week
The rationale for the RWT subformula is:
a. value of an employee hour = (AYS / 260) / 8
b. commute hours per year (assuming a 1-hour/day commute) =
1 * (260 - 52 * RWT)
c. therefore travel-time cost = (260 - 52 * RWT) * (AYS / 260) / 8

For example, if the weights are 2 for the yearly salary, 2 for Gym Membership, and 1 for
all other factors, the score would be computed as:
2/7 * AYS + 1/7 * AYB + 2/7 * GYM + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) *
(AYS / 260) / 8)

- 'Ranking()' method will leading to an ordered job list according to the computing results. It will show a table in the designed GUI.

7)The user interface must be intuitive and responsive.

- This is not represented in my design, as it will be handled entirely within the GUI implementation.

8. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

