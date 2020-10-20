<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12.10.2020
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<html>
<head>
    <title>List Registry</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css" type="text/css">
    <jsp:include page="/view/template/nav.jsp"/>
    <jsp:include page="/view/template/footer.jsp"/>
    <script src="https://use.fontawesome.com/8183895222.js"></script>
</head>
<body>
<div class="container">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8">
                        <h2>Registry List</h2>
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
                    <th>Patient</th>
                    <th>Doctor</th>
                    <th>Admission date</th>
                    <th>Dismission Date</th>
                    <th>Disease</th>
                    <th>Room number</th>
                    <c:choose>
                        <c:when test="${sessionScope.admin!=null}">
                            <th>Action</th>
                        </c:when>
                    </c:choose>
                </tr>
                <c:forEach var="registry" items="${listRegistry}">
                    <tr>
                        <td><c:out value="${registry.number}"/></td>
                        <td><c:out value="${registry.patient.lastname}"/></td>
                        <td><c:out value="${registry.doctor.lastname}"/></td>
                        <td><c:out value="${registry.admission}"/></td>
                        <td><c:out value="${registry.dismission}"/></td>
                        <td><c:out value="${registry.disease}"/></td>
                        <td><c:out value="${registry.roomNumber}"/></td>
                        <c:choose>
                            <c:when test="${sessionScope.admin!=null}">
                                <td>
                                    <a href="registry?state=editRegistry&number=${registry.number}"
                                       class="btn btn-warning">Edit</a>
                                    <a href="registry?state=deleteRegistry&number=${registry.number}"
                                       class="btn btn-danger">Delete</a>
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
                        <a href="registry?state=newRegistry" class="btn btn-success">Add</a>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
</body>
</html>
