<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container mt-3">
  <h1>All Employee</h1>
  <a href="addEmployee" class="btn btn-primary"> Add Employee </a>
  <div class="row">

    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">Name</th>
          <th scope="col">Department</th>
          <th scope="col">Salary</th>
          <th scope="col">Address</th>
          <th scope="col">Edit</th>
          <th scope="col">Delete</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="emp" items="${employee}">
          <tr>
            <td class="table-plus">${emp.id}</td>
            <td>${emp.name}</td>
            <td>${emp.department}</td>
            <td>${emp.salary}</td>
            <td>${emp.address}</td>
            <td><a href="editEmployee/${emp.id}"
              class="btn btn-warning"> Edit </a></td>
            <td><a href="deleteEmployee/${emp.id}"
              class="btn btn-danger"> Delete </a></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </div>
</div>
