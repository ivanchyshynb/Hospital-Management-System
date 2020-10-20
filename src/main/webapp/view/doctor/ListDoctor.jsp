<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13.10.2020
  Time: 0:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css" type="text/css">
    <jsp:include page="/view/template/nav.jsp"/>
    <jsp:include page="/view/template/footer.jsp"/>
    <script src="https://use.fontawesome.com/8183895222.js"></script>
</head>
<body>
<div class="container-fluid">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8">
                        <h2>Doctor List</h2>
                    </div>
                    <div class="col-sm-4">
                        <div class="search-table">
                            <i class="fa fa-search" aria-hidden="true"></i>
                            <input type="text" id="search" class="form-control" placeholder="Enter Surname">
                        </div>
                    </div>
                </div>
            </div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Specialization</th>
                    <th>Category</th>
                    <th>Department</th>
                    <th>Hospital</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Email</th>
                    <c:choose>
                        <c:when test="${sessionScope.admin!=null}">
                            <th>Action</th>
                        </c:when>
                    </c:choose>
                </tr>
                <c:forEach var="doctor" items="${listDoctor}">
                    <tr>
                        <td><c:out value="${doctor.id}"/></td>
                        <td><c:out value="${doctor.name}"/></td>
                        <td><c:out value="${doctor.lastname}"/></td>
                        <td><c:out value="${doctor.specialization.name}"/></td>
                        <td><c:out value="${doctor.category.name}"/></td>
                        <td><c:out value="${doctor.department.name}"/></td>
                        <td><c:out value="${doctor.hospital.name}"/></td>
                        <td><c:out value="${doctor.username}"/></td>
                        <td><c:out value="${doctor.password}"/></td>
                        <td><c:out value="${doctor.email}"/></td>
                        <c:choose>
                            <c:when test="${sessionScope.admin!=null}">
                                <td>
                                    <a href="doctor?state=editDoctor&id=${doctor.id}" class="btn btn-warning">Edit</a>
                                    <a href="doctor?state=deleteDoctor&id=${doctor.id}" class="btn btn-danger">Delete</a>
                                </td>
                            </c:when>
                        </c:choose>
                    </tr>
                </c:forEach>
                </thead>
            </table>
            <c:choose>
                <c:when test="${sessionScope.admin!=null}">
                    <div class="d-flex justify-content-center">
                        <a href="doctor?state=newDoctor" class="btn btn-success">Add</a>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
