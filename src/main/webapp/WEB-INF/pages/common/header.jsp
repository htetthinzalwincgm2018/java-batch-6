<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
  uri="http://www.springframework.org/security/tags"%>


<header class="border-bottom py-3">
  <div class="container">
    <div class="row">
      <div class="col">
        <h3>Spring MVC CRUD Header</h3>
      </div>

      <div class="col text-right">
        <sec:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
          <form:form action="logout" method="POST">
            <input type="submit" value="Logout" class="btn"/>
          </form:form>
        </sec:authorize>
      </div>

    </div>
  </div>
</header>