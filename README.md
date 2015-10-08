# UML-editor
##Overview
The idea is to implement visual diagram editor that allows to create UML class diagrams and generate source  code out of them.
##Requirements
-ulDiagram editor should support all features of UML2 standard class diagram.
-ulEditor is able to create new class diagram.
-ulOpen existing diagram.
-ulEdit existing diagram.
-ulSave diagram to file.
-ulEditor should be able to generate created classes and dependencies to Java source code.
-ulOptional: Editor should be able to export diagrams into image format.


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
For tasks

-ulTask created in tracker (New)
-ulTask assigned to developer to develop (Assigned)
-ulTask ready for review (For review)
-ulTask assigned to developer for code review (In review) – from this State task can be moved either to next state or be assigned back to 

developer responsible for development.

-ulTask ready for functional testing (Ready to test) 
-ulTask in test (In test) – can be moved back to (Assigned) state or to (Done)

-ulTask closed (Done)

Task cannot be developer reviewed and tested by the same developer, all these activities should be done by separate persons. In case of only 

two developers on a project, code review and functional test duties can be joined.