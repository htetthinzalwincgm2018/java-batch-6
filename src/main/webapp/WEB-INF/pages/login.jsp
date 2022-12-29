<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container mt-3">

  <div class="row justify-content-md-center mb-2">
    <div class="col-md-6 p-5 rounded shadow-sm">
      <h1 class="text-center">Login</h1>
      <font color="red">
        ${SPRING_SECURITY_LAST_EXCEPTION.message} </font>
      <form:form action="login" method="post">
        <div class="form-group">
          Email <span class="text-danger">*</span> <input type="text"
            name="username" class="form-control" />
        </div>
        <div class="form-group">
          Password <span class="text-danger">*</span> <input
            type="password" name="password" class="form-control" />
        </div>
        <div class="text-center">
          <input type="submit" value="Login" class="btn btn-primary" />
        </div>
      </form:form>
      <div class="text-center mt-3">
        <a href="passwordReset">Password Reset</a>
      </div>
    </div>
  </div>
</div>
