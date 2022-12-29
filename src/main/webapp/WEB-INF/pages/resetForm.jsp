<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container mt-3">
  <div class="row justify-content-md-center mb-2">
    <div class="col-md-6 p-5 rounded shadow-sm">
      <c:url value="/resetPassword" var="reset" />
      <form:form action="${reset}" method="POST"
        modelAttribute="resetForm" class="form-horizontal">
        <div class="row g-3">

          <input type="hidden" name="${_csrf.parameterName}"
            value="${_csrf.token}" />
          <form:hidden path="token" value="${resetForm.token}" />

          <div class="col-12">
            <label for="email" class="form-label">Password <span
              class="text-danger">*</span></label>
            <form:input type="password" path="password" name="password"
              id="password" placeholder="" class="form-control" />
            <form:errors path="password"
              class="fadeIn fourth text-danger" />
          </div>
          <div class="col-12">
            <label for="email" class="form-label">Confirm
              Password <span class="text-danger">*</span>
            </label>
            <form:input type="password" path="confirm_password"
              placeholder="" id="confirm_password" class="form-control" />
            <form:errors path="confirm_password"
              class="fadeIn fourth text-danger" />
          </div>
          <c:if test="${not empty errorMsg }">
            <div class="fadeIn fourth text-danger">${errorMsg }</div>
          </c:if>

          <div class="col-12 d-grid text-center my-4">
            <button class="btn btn-primary" type="submit">Change</button>
          </div>
        </div>

      </form:form>
    </div>
  </div>

</div>
