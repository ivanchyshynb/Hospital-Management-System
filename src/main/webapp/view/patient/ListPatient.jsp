<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.10.2020
  Time: 0:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
<div class="container">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-8">
                        <h2>Patient List</h2>
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
                    <th>Age</th>
                    <th>Gender</th>
                    <th>Address</th>
                    <th>Phone</th>
                    <c:choose>
                        <c:when test="${sessionScope.admin!=null}">
                            <th>Action</th>
                        </c:when>
                    </c:choose>
                </tr>
                <c:forEach var="patient" items="${listPatient}">
                    <tr>
                        <td><c:out value="${patient.id}"/></td>
                        <td><c:out value="${patient.name}"/></td>
                        <td><c:out value="${patient.lastname}"/></td>
                        <td><c:out value="${patient.age}"/></td>
                        <td><c:out value="${patient.sex}"/></td>
                        <td><c:out value="${patient.address}"/></td>
                        <td><c:out value="${patient.phone}"/></td>
                        <c:choose>
                            <c:when test="${sessionScope.admin!=null}">
                                <td>
                                    <a href="patient?state=editPatient&id=${patient.id}"
                                       class="btn btn-warning">Edit</a>
                                    <a href="patient?state=deletePatient&id=${patient.id}"
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
                        <a href="patient?state=newPatient" class="btn btn-success">Add</a>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
</div>
</body>
</html>
