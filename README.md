# Students & Groups 
A service for managing students at the university.

**Stack:** Java 17, Spring boot, Hibernate, MySQL, Maven, Junit, Mockito
## Description.
Functions
- Getting a sorted list of all groups from the database: `GET /groups`
- Adding a new group: `POST /groups`
- Getting a list of students of a certain group `GET /groups/{id}`
- Removing a student from a group `DELETE /students/{id}`
- Adding a student to a group `POST /students`
