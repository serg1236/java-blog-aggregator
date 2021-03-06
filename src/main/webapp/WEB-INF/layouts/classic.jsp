<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
    <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@taglib uri="http://tiles.apache.org/tags-tiles-extras" prefix="tilesx" %>
    <%@ include file="./taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title"/></title>

<script src="//code.jquery.com/jquery-2.1.4.min.js"></script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>



</head>
<body>

<tilesx:useAttribute name="current"/>

<div class="container">

      <!-- Static navbar -->
      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href='<spring:url value="/" />'>JBA</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="${current=='index'?'active':''}"><a href='<spring:url value="/" />'>Home</a></li>
               <security:authorize access="hasRole('ROLE_ADMIN')">
             	 <li class="${current=='users'?'active':''}"><a href='<spring:url value="/users.html" />'>Users</a></li>
              </security:authorize>
              <li class="${current=='user-register'?'active':''}"><a href='<spring:url value="/register.html" />'>Registration</a></li>
              <security:authorize access="! isAuthenticated()">
              	<li><a class="${current=='login'?'active':''}" href='<spring:url value="/login.html" />'>Login</a></li>
              </security:authorize>
              <security:authorize access="isAuthenticated()">
              		<li><a href='<spring:url value="/logout" />'>Logout</a></li>
              </security:authorize>
             </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>


    



	<tiles:insertAttribute name="body"/>
	
	<br><br>
	<center>
		<tiles:insertAttribute name="footer"/>
	</center>
	</div> <!-- /container -->
</body>
</html>