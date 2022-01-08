# Design description document


## Requirements

The purpose of the application is so that a user can quantify the value of job offers in comparison to his own job, or in relation to other jobs using a mathematical ranking method.

The application consists of the following classes: JobManager, User, Job, CurrentJob, JobOffer, ComparisonSettings, Money, and Comparison. The JobManager class is the entry point into the application tying together the related classes. 
The user is able to add current job information, as well as job offer information using the functionalities (enterCurrentJobDetails, editCurrentJobDetails, enterJobOfferDetails) within the JobManager class. 

The user is also able to compare jobs using the functions compareJobs function within the Comparison class. This operation uses the two Job objects (job0 & job1) that are properties of the class and returns an integer indicating which job scores better. (If job0 scores better, then compareJobs returns -1; if job1 scores better, then compareJobs returns 1; if their scores are equal, compareJobs returns 0.) 

The getJobScore function within the Job class creates a job score for each job the user has inputted. This calculation is done using the following formula: 

2/7 * AYS + 1/7 * AYB + 2/7 * GYM + 1/7 * (LT * AYS / 260) - 1/7 * ((260 - 52 * RWT) * (AYS / 260) / 8)

Within the User class, the user will be able to adjust the comparison weights for each job consideration category in the formula above:  yearlySalaryWeight, yearlyBonusWeight, weeklyTeleworkDaysWeight, leaveTimeWeight, & gymMembershipAllowanceWeight. By default, the weights are set to 1. 

### Functionality to be performed by the GUI:
* Being able to save the job details, or exit
* Being able to return to the main menu
* Showing a comparison table of two jobs 

### The interface has not yet been designed. However, it will be intuitive for the user. The app is designed to run on a single user system. 


## Classes
* JobManager 
	* This is the class that represents the entry point to the system, and that ties the various pieces together.
* User
* Job
* CurrentJob
* JobOffer
* ComparisonSettings
* Money
* Comparison

## Attributes
* JobManager
	* currentJob : CurrentJob = null
	* jobs : List&lt;Job&gt; = empty list
* User
	* comparisonSettings : ComparisonSettings = new ComparisonSettings()
* Job
	* title : String = ""
	* company : String = ""
	* location : String = ""
	* costOfLiving : Integer = 0
	* yearlySalary : Money
	* yearlyBonus : Money
	* weeklyTeleworkDays : Integer = 0
	* leaveTime : Integer = 0
	* gymMembershipAllowance : Money
	* isCurrent : Boolean = false
* ComparisonSettings
	* yearlySalaryWeight : Integer = 1
	* yearlyBonusWeight : Integer = 1
	* weeklyTeleworkDaysWeight : Integer = 1
	* leaveTimeWeight : Integer = 1
	* gymMembershipAllowanceWeight : Integer = 1
* Comparison
	* job0
	* job1

## Operations
* JobManager
	* enterCurrentJobDetails(CurrentJob) : Void
	* editCurrentJobDetails(CurrentJob) : Void
	* enterJobOfferDetails(JobOffer) : Void
	* countJobs() : Integer
	* rankSortJobs() : Void
	* saveCurrentJob(): Void
* User
	* adjustComparisonSettings() : Void
* Job
	* getJobScore() : Float
* ComparisonSettings
	* setYearlySalaryWeight(Integer) : Void
	* setYearlyBonusWeight(Integer) : Void
	* setWeeklyTeleworkDaysWeight(Integer) : Void
	* setLeaveTimeWeight(Integer) : Void
	* setGymMembershipAllowanceWeight(Integer) : Void
* Comparison
    * compareJobs() : Integer
    * populateTable() : Void

## Relationships
* JobManager
	* depends on Job and User, and Comparison
* User
	* depends on ComparisonSettings
* Comparison
	* depends on Job
* CurrentJob
	* is a Job and inherit the attributes of Job
* JobOffer
	* is a Job and inherit the attributes of Job

