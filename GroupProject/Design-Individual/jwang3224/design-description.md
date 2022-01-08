Job Comparison App Design Description


The diagram is set up such that each class is distinguished by bold text and pascal case, while functions are indicated by underlining and camel case. 
Each class has related functions listed and each function has related input variables listed. Their dependencies and relations between classes, functions, and outputs are illustrated with arrows. There are four unique classes in this application: JobComparisonApp, EnterJobInfo, AdjustWeights, and CompareJobs.


The main class: JobComparisonApp, connects the user’s device to the rest of the application, and ties together the related classes. 
Upon opening the app, the user is given the option of entering any job-related information (EnterJobInfo), change the weights for job consideration (AdjustWeights) and then to compares jobs within the app (CompareJobs).


The first related class is EnterJobInfo. Here the user is given several options: to add their current job information, their job offer information, to quit, or to save. In the diagram these are: addJob, currentJob, saveJob, quit. None of these return a value as they are designed to collect information from the user, and to build the job data base. The following describe the functions in this class. 

  + addJob: user can input the following job details for any job offers he receives. 
        jobTitle: String, 
        company: String, 
        location: String, 
        costOfLiving: integer, 
        salary: float, 
        bonus: float, 
        remoteDays: Integer, 
        vacayDays: Integer, 
        gymMember: float
				
  + currentJob: user can input current employment information, the detail inputs are the same as for addJob. 
	
  + saveJob: will save the job information into the job database (represented as a rhombus). There will be checks, and warnings designed within the function to make sure the user does not input any invalid data that the application cannot process in future steps.
	
  + quit: this will clear the user interface and exit back to the main menu, without saving the job information the user inputted.


The second class is AdjustWeights. Here the user is allowed to put in manual inputs for the following variables: salaryWeight, bonusWeight, remoteDaysWeight, vacayDaysWeight, gymMembWeight. By default, the inputs for these are all 1.0, meaning they will be equally weighted if the user does not specify weights to his or her liking. The weight used for the job consideration calculation, for example: salaryWeightAdjusted, will be divided by the sum of all user input weights: salaryWeight/(sum of all other weights). 

The last class is CompareJobs. This class has the following functions: selectJob, computeJobWeights, bestJob, and checkData. 

  + checkData: First the class needs to check that the data is valid before proceeding to other parts of the application. There needs to be at least one job offer, and complete current employment information for the user to proceed in using other functionalities within this class. 
	
  + selectJob: the user will get to select two jobs for the application to process the comparison. The two unique identifiers of the job offers (job title and company) the user selects will be saved into a string, and the two strings will be saved as an array, to be passed into the bestJob function.
	
  + computeJobWeights: String indicating the specified job the user wants calculated is passed in, so that the weighted value of this job can be calculated. The job weight for each job selected is computed using the following formula:
    (please note: the rational behind the value of the vacation and remote work days is in relation to the employee's value in time)
        salaryWeightAdjusted * salary + 
        bonusWeightAdjusted * bonus + 
        gymMembWeightAdjusted * gymMember +
        vacayDaysWeightAdjusted * (vacayDays * salary/260)  -
        remoteDaysWeightAdjusted  * ((260 - 52 * remoteDats) * (salary / 260) / 8) 
				
  + bestJob: the array indicating the jobs selected from the selectJob function is passed in, and the computeJobWeights function is called for each job selected. It then returns the name of the job (string) that indicates the better job offer. This last function is the only part of the program that will return something back to the user within the app. 


The user interface is not included in the diagram. The user interface will be designed so that it is easy to navigate, and intuitive to the user on how to properly input the correct information into the application. The app runs on a single system. 
