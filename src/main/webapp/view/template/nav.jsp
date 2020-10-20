<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.10.2020
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark blue-navbar">
    <div class="container">
        <a class="navbar-brand" href="#">Hospital Information System</a>
        <c:choose>
            <c:when test="${sessionScope.doctor!=null}">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="HomePage.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/patient?state=listPatient">Patients</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/registry?state=listRegistry">Registry</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/doctor?state=profileDoctor">Profile</a>
                        </li>
                    </ul>
                </div>
                <div class="ml-auto collapse navbar-collapse" id="navbarNav">
                    <ul class="ml-auto navbar-nav">
                        <span class="navbar-text">Welcome, ${doctor.lastname}</span>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Exit</a>
                        </li>
                    </ul>
                </div>
            </c:when>
            <c:when test="${sessionScope.admin!=null}">
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="HomePage.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/patient?state=listPatient">Patients</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/registry?state=listRegistry">Registry</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/doctor?state=listDoctor">Doctors</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/doctor?state=profileDoctor">Profile</a>
                        </li>
                    </ul>
                </div>
                <div class="ml-auto collapse navbar-collapse" id="navbarNav">
                    <ul class="ml-auto navbar-nav">
                        <span class="navbar-text">Welcome, ${admin.lastname}</span>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/logout">Exit</a>
                        </li>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Home</a>
                        </li>
                    </ul>
                </div>
                <div class="ml-auto collapse navbar-collapse" id="navbarNav">
                    <ul class="ml-auto navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
                        </li>
                    </ul>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</nav>
</body>
</html>
