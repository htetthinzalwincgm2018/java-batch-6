<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container mt-3">
  <c:if test="${not empty message}">
    <div class="alert alert-warning alert-dismissible fade show"
      role="alert">${message}
      <button type="button" class="close" data-dismiss="alert"
        aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
  </c:if>
  <div class="row">
    <div class="col">
      <h1>All Employee</h1>
    </div>
    <div class="col justify-content-end d-flex">
      <a href="addEmployee" class="btn btn-primary mb-3"> Add
        Employee </a>

      <c:url value="/export" var="exportUrl" />
      <a href="${exportUrl}" class="btn btn-primary mb-3 ml-3">Export<i
        class="fa-solid fa-download ms-2"></i></a>

      <c:url value="/import?${_csrf.parameterName}=${_csrf.token}"
        var="empImport"></c:url>
      <form:form action="${empImport}" method="post"
        enctype="multipart/form-data" id="importForm">
        <input type="file" name="file" class="form-control d-none"
          accept=".xlsx" id="empImportExcel">
        <label class="btn btn-primary mb-3 ml-3" for="empImportExcel">Import<i
          class="fa-solid fa-file-import ms-2"></i></label>

      </form:form>
    </div>
  </div>

  <div class="row">
    <table class="table table-hover">
      <thead>
        <tr>
          <th scope="col">ID</th>
          <th scope="col">Name</th>
          <th scope="col">Email</th>
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
            <td>${emp.email}</td>
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
<script src="<c:url value='/resources/js/import.js'/>"></script>