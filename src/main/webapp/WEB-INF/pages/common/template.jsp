<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title" /></title>

<s:url var="url_jqlib" value="/resources/js/jquery-3.6.0.min.js" />
<s:url var="url_jblib" value="/resources/js/bootstrap.min.js" />
<script src="${url_jqlib}"></script>
<script src="${url_jblib}"></script>

<link rel="stylesheet"
  href="<c:url value="/resources/css/bootstrap.min.css" />"
  type="text/css" />

</head>

<body>

  <tiles:insertAttribute name="header" />

  <div class="content">
    <tiles:insertAttribute name="body" />
  </div>

  <tiles:insertAttribute name="footer" />

</body>
<tiles:insertAttribute name="javascript" ignore="true" />
</html>