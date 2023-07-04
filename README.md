# spring-mvc-security
- In this project, I have initialized libraries such as Spring Data JPA, Spring Boot DevTools, Security, Thymeleaf, and the MySQL Driver to manipulate the project.
- I created a class in the security package and annotated @Configuration. In this class, I have created two methods and @Bean annotation.
  + The first method (userDetailsManager): This method configures and returns an instance of JdbcUserDetailsManager. It takes a DataSource as a parameter, which is used to connect to the database. It sets the SQL queries to retrieve user details and authorities/roles by username.
  + The Second method (filterChain): This method configures the security filter chain using the HttpSecurity object. It sets up authorization rules based on the HTTP request methods and the roles required to access specific URLs.
  + Configurations applied:
  + authorizeHttpRequests: In this method I have paginate for employees who can only access their page. Management can access their website and employees. Admins can access their site and both staff and management pages. Requires authentication for any other URL.
  + formLogin: Specify the custom login page URL and where the login form data should be submitted for validation. Then Allows access to the login page for all users.
  + logout: Configures the logout settings and allows access to the logout URL for all users.
  + exceptionHandling: Configures handling of exceptions, such as access denied and specifies the custom access denied page URL.
  + Finally, the method returns the built HttpSecurity object as a SecurityFilterChain.
- Employees (Username: john, Password: test123)
- Management (Username: mary, Password: test123)
- Admin (Username: susan, Password: test123)
