# TaskManager with Spring JPA 


## Implentation of following end points

### API Endpoints 

- GET /tasks - returns all tasks
- GET /tasks/{id} - returns a task with the given id
- POST /tasks - creates a new task
- PATCH /tasks/{id} - updates a task with the given id
- DELETE /tasks/{id} - deletes a task with the given id
- GET /tasks?title={title} - returns all tasks with the given title
- GET /tasks?completed={completed} - returns all tasks with the given completed status
- GET /tasks/{id}/notes - returns all notes for a task with the given id
- POST /tasks/{id}/notes - creates a new note for a task with the given id
- DELETE /tasks/{id}/notes/{noteId} - deletes a note with the given id for a task with the given id

### Tests

@DataJpaTest

- TasksRepositoryTest.java
- TasksServiceTest.java
