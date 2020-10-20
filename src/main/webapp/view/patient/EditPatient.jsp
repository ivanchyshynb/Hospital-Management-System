<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.10.2020
  Time: 0:54
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
<div class="wrapper">
    <div class="form-body">
        <div class="form-title">
            <h2>Edit Form</h2>
        </div>
        <form method="post" action="patient">
            <div class="form-row">
                <input type="hidden" name="id" value="${patient.id}">
                <div class="form-group col-md-6">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${patient.name}">
                </div>
                <div class="form-group col-md-6">
                    <label for="lastname">Last Name</label>
                    <input type="text" class="form-control" id="lastname" name="lastname" value="${patient.lastname}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="age">Age</label>
                    <input type="text" class="form-control" id="age" name="age" value="${patient.age}">
                </div>
                <div class="form-group col-md-6">
                    <label for="sex">Gender</label>
                    <input type="text" class="form-control" id="sex" name="sex" value="${patient.sex}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="phone">Phone</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="${patient.phone}">
                </div>
                <div class="form-group col-md-6">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" id="address" name="address" value="${patient.address}">
                </div>
            </div>
            <input type="hidden" name="state" value="updatePatient">
            <button type="submit" class="btn btn-primary">Edit</button>
        </form>
    </div>
</div>
</body>
</html>
