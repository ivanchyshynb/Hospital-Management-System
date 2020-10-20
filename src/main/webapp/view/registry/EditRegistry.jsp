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
<div class="wrapper">
    <div class="form-body">
        <div class="form-title">
            <h2>Edit Form</h2>
        </div>
        <form method="post" action="registry">
            <div class="form-row">
                <input type="hidden" name="number" value="${registry.number}">
                <div class="form-group col-md-6">
                    <label for="patient">Patient</label>
                    <select name="patient" class="form-control" id="patient">
                        <c:forEach var="patient" items="${listPatient}">
                            <option selected hidden value="${registry.patient.id}">${registry.patient.lastname}</option>
                            <option value="${patient.id}">${patient.lastname}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label for="doctor">Doctor</label>
                    <select name="doctor" class="form-control" id="doctor">
                        <c:forEach var="doctor" items="${listDoctor}">
                            <option selected hidden value="${registry.doctor.id}">${registry.doctor.lastname}</option>
                            <option value="${doctor.id}">${doctor.lastname}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="admission">Date admission</label>
                    <input type="date" class="form-control" id="admission" name="admission"
                           value="${registry.admission}">
                </div>
                <div class="form-group col-md-6">
                    <label for="dismission">Category</label>
                    <input type="date" class="form-control" id="dismission" name="dismission"
                           value="${registry.dismission}">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="disease">Disease</label>
                    <input type="text" class="form-control" id="disease" name="disease"
                           value="${registry.disease}">
                </div>
                <div class="form-group col-md-6">
                    <label for="roomNumber">Room number</label>
                    <input type="text" class="form-control" id="roomNumber" name="roomNumber"
                           value="${registry.roomNumber}">
                </div>
            </div>
            <input type="hidden" name="state" value="updateRegistry">
            <button type="submit" class="btn btn-primary">Edit</button>
        </form>
    </div>
</div>
</body>
</html>
