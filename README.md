# UML-editor
##Overview
The idea is to implement visual diagram editor that allows to create UML class diagrams and generate source  code out of them.
##Requirements
- Diagram editor should support all features of UML2 standard class diagram.
- Editor is able to create new class diagram.
- Open existing diagram.
- Edit existing diagram.
- Save diagram to file.
- Editor should be able to generate created classes and dependencies to Java source code.
- Optional: Editor should be able to export diagrams into image format.


##Source code
- Every project should use version control system and source code management, best choise would be GIT or TFS.
- Every project should use https://bitbucket.org/ or https://github.com/as repository (can use similar system with the mentors 
agreement, for example TFS). Each team member should have own login credentials. List of login names should be provided to the 
mentor to be able to identify each member of a team. Team should provide access to the bitbucket GIT repository to mentor, in case of 
lack of free account one of the team members should provide own credentials. Not private repositories can be used. 
- Teams should use github’s or bitbucket’s task tracking systems for managing tasks or bugs. Either any other free Agile board can be used. 
- Static analysis tools for code quality should be used on every project. Every project should be developed with no warnings from the analysis tools
- FindBugs and checkstyle should be used for the Java projects.ReSharper and Style Cop for the .Net projects.
- Optional: Continuous integration tools can be used such as TeamCity or Jenkins
- Optional: Sonar can be used as static analysis tool for any type of projects but it should be agreed with mentor.

##Workflow
Each task or bug-fix should follow correct workflow. The final workflow should be agreed with mentor. If not agreed it should be next:
###For tasks

- Task created in tracker (New)
- Task assigned to developer to develop (Assigned)
- Task ready for review (For review)
- Task assigned to developer for code review (In review) – from this State task can be moved either to next state or be assigned back to 

###developer responsible for development.

- Task ready for functional testing (Ready to test) 
- Task in test (In test) – can be moved back to (Assigned) state or to (Done)

- Task closed (Done)

Task cannot be developer reviewed and tested by the same developer, all these activities should be done by separate persons. In case of only 
two developers on a project, code review and functional test duties can be joined.
###For bugs:
- New or Reopened or Cloned
- Assigned
- In review
- Ready 
- In test
- Done

##Quality

- Each final product should be tested. Testing is done on a pear to pear basis, this means each developer should test features developed by colleague. 

- Each developed feature should be tested. 

- Definition of done: feature is developed, code is reviewed, functionality is tested and satisfying to requirements. 

- Test Cases for every feature and for every found bug should be created. Test cases are the part of final delivery and feature demonstration. Test Cases should be stored in the separate project’s folder.

- Each developed feature should be code reviewed by the team member not participated in the development of this feature.

- No bugs with the severity normal or higher should be present in the delivered features. (Delivered feature is a feature demonstrated on the demo meeting).

- Each team member should participate in testing activities.

- Not less than 60% of code should be covered by unit tests.

##Reporting and demonstration
* Detailed User Stories for projects should be described by teams and agreed with mentors. 

* Full backlog should be sored together with the projects files on GIT repository.

* Time of demonstration meetings should be agreed with mentor. It is recommended to demonstrate weekly.

* Weekly reports should be sent to mentors with next data:

 * Status (green/yellow/red) with the small description

 * Current features in development (list)

 * Already developed features (list)

 * Entire number of Test Cases done till the reporting time

 * Entire number of Unit tests done till the reporting time