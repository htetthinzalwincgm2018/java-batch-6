<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<div class="container mt-3">

  <h1>Add Employee Form</h1>
  <form:form action="insertEmployee" modelAttribute="createEmpForm"
    method="post">

    <div class="row">
      <div class="col">
        <div class="form-group">
          <form:label path="name">Name</form:label>
          <form:input path="name" class="form-control"
            placeholder="Enter name" />
          <form:errors path="name" cssClass="text-danger" />
        </div>
      </div>
      <div class="col">
        <div class="form-group">
          <form:label path="email">Email</form:label>
          <form:input path="email" class="form-control"
            placeholder="Enter email" />
          <form:errors path="email" cssClass="text-danger" />
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col">
        <div class="form-group">
          <form:label path="password">Password</form:label>
          <form:password path="password" class="form-control"
            placeholder="Enter password" />
          <form:errors path="password" cssClass="text-danger" />
        </div>
      </div>
      <div class="col">
        <div class="form-group">
          <form:label path="type">Type</form:label>
          <div class="form-check">
            <form:radiobutton class="form-check-input" path="type"
              name="type-admin" value="1" checked="checked" />
            <label class="form-check-label" for="type_admin">
              Admin </label>
          </div>
          <div class="form-check">
            <form:radiobutton class="form-check-input" path="type"
              name="type-user" value="0" />
            <label class="form-check-label" for="type_usere">
              User </label>
          </div>
          <form:errors path="type" cssClass="text-danger" />
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col">
        <div class="form-group">
          <form:label path="department">Department</form:label>
          <form:input path="department" class="form-control"
            placeholder="Enter department" />
          <form:errors path="department" cssClass="text-danger" />
        </div>
      </div>
      <div class="col">
        <div class="form-group">
          <form:label path="salary">Salary</form:label>
          <form:input type="number" path="salary" class="form-control"
            placeholder="Enter salary" />
          <form:errors path="salary" cssClass="text-danger" />
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col">
        <div class="form-group">
          <form:label path="address">Address</form:label>
          <form:textarea rows="5" path="address" class="form-control"
            placeholder="Enter address" />
          <form:errors path="address" cssClass="text-danger" />
        </div>
      </div>
    </div>

    <a href="${pageContext.request.contextPath }/"
      class="btn btn-warning"> Back </a>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form:form>

</div>
