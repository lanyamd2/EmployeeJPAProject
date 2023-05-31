# Employee JPA Project

## Table of Contents

1. [About](#about)  
2. [General Requirements](#general-requirements)
3. [Project Requirements Part 1](#project-requirements-part-1)
4. [Project Requirements Part 2](#project-requirements-part-2)
5. [Contributing](#contributing)


## About

### Part 1

Developed by team Bootswana.

The application searches for requested data from a MySQL database and presents it to the user.

Purpose:
- Explore JPA/Hibernate
- Use Spring Boot Testing
- Implement DAO and DTO classes as appropriate

### Part 2

Developed by POSTman PATCH's van crew.

Expands on part 1 to allow the user to access the database through an API.

Purpose:
- Explore the concepts of REST by implementing a service
- Implement multiple mapping types and parameter handling approaches
- Implement authentication for APIs
- Integrate a JPA/Hibernate back-end with a Spring REST API


## General Requirements

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


## Project Requirements Part 1

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



## Project Requirements Part 2

- Create an API to access the JPA entities created for the employees database in week 5
- Provide basic CRUD access for all relevant tables via the API
- Provide further CRUD access for the additional analytical methods
- Implement appropriate error handling and reporting, including logging
- Implement the generation of API keys for users and allow multiple levels of user based on the API key (this will involve the creation of additional database resources to store the keys and access levels and a distribution mechanism)
  - Basic user (read-only access)
  - Update user (CRU access)
  - Admin user (full CRUD)
- Include testing using Jackson/JUnit, Postman and Spring Boot Test as appropriate


## Contributing

#### Setting up development project

1. Clone from remote to local: `git clone https://github.com/Usman-Abubakr/Employees-Project.git`
2. Change to dev branch: `git checkout --track origin/dev`

#### Creating feature branch

1. Update local: `git pull`
2. Change to dev branch: `git checkout origin/dev`
3. Create new feature branch: `git checkout -b <branch-name>`
4. Push to remote feature branch (will create the new remote branch): `git push -u origin <branch-name>`

#### Updating feature branch from dev branch

1. Switch to dev branch: git `git checkout dev`
2. Update branch: `git pull`
3. Switch to feature branch: `git checkout <branch-name>`
4. Merging dev to feature branch:`git merge origin/dev`
5. Push update to remote feature branch `git push <remote-name> <branch-name>`

####  Alternative method to updating feature branch from dev branch

1. Push feature branch to remote feature branch `git push <remote-name> <branch-name>`
2. Use GitHub to merge from dev branch.

#### Commiting files to feature branch

1. Add commits `git add <file-name>` or `git add .`, then `git commit -m "<message>"`
2. Push to remote feature branch : `git push -u origin <branch-name>`
3. Let team know a feature is ready to merge with dev branch.
4. Create new branch for each new features.

