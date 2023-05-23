### Week 5 - JPA/Hibernate

### General Requirements

All projects:

- Must be run as Scrum projects
- Must apply SOLID & OO principles
- Must use the MVC pattern where there is a user interface
- Should use well-known design patterns where appropriate
- Must include comprehensive ```JUnit``` testing or equivalent
- Should begin with the creation of tests, in line with a test-driven development approach
- Must use logging or equivalent
- Must implement appropriate exception handling
- Must be hosted on GitHub and thoroughly documented, through a `README.md` file

#### Purpose

In addition to the general goals of projects, this project will give trainees an opportunity to:

- Explore JPA/Hibernate
- Use Spring Boot Testing
- Implement DAO and DTO classes as appropriate

#### Requirements

- Develop JPA entities and repositories for all 6 tables in the employees sample database in MySQL:
  - `departments`
  - `dept_emp`
  - `dept_manager`
  - `employees`
  - `salaries`
  - `titles`
- Create DAO and DTO classes to abstract the data access layer
- Add methods to enable the following functionality:
  - Find employees by last name
  - Find all the employees who worked in a named department on a given date
  - What is the average salary for a named department on a given date?
  - Given a job title name, what is the range of salary values within a given year?
  - Provide a summary of the size of each department (number of staff) over a given period (start year to end year)
  - Is there a gender pay gap? If so, quantify it
- Add 3 methods of your own to investigate similar questions
- Configure a Spring Boot application to host the JPA classes
- Use Spring Boot Test to create a comprehensive suite for your JPA classes and your DAO/DTO classes
- Include the result of your testing in the ```README.md``` file for the project on GitHub
- Include code coverage data for all JPA classes, with explanations for any low coverage levels, in the `README.md`

#### Groups

Please let your trainer know who will be acting as Scrum Master and Git Manager for this project.
