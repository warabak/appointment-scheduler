# Next Steps
This is a quick-and-dirty demo of how to most efficiently leverage the power of Spring Boot to build a CRUD app.
Needless to say, many more hours can be spent making this more production-ready.

To take this into a production environment, consider this a non-comprehensive list of next steps for appointment-scheduler.

### Security
The API should use some kind of authorization to implement access controls and permissions.
We can imagine an admin / doctor / patient hierarchy fairly immediately, and think through
how each role should be able to create and edit appointments.

### Business Rule Enforcement
Appointment scheduler is pretty lax with its bookings. In fact, a doctor may book any number of overlapping schedules for a given day.
We enforce some constraints, like that a schedule must be in the future, but not too much else.

As business rules are defined, we would want to create constraints within the database and handle them judiciously within the request flow.

### Test Coverage
Improving the JUnit test coverage, as well as generating coverage reports would be a great next step.
Tools like [Coveralls](https://coveralls.io/) for open-source projects are free, and would allow us to measure the breadth of our coverage as we add tests over time.

### Error Handling and the User Experience
While leaning into `javax.validation.constraints` saves us from a lot of boilerplate, there are certainly more edge cases to consider.
Most critically, a greater focus should be placed on error conditions - helping enable the end user to work through their own problems with the API 
makes for happy users and reduces stress on support processes like customer support and wordy technical documentation.