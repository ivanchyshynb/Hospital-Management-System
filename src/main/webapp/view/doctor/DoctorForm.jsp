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
            <h2>New Doctor</h2>
        </div>
        <form method="post" action="doctor">
            <div class="form-row">
                <input type="hidden" name="id">
                <div class="form-group col-md-6">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name">
                </div>
                <div class="form-group col-md-6">
                    <label for="lastname">Last Name</label>
                    <input type="text" class="form-control" id="lastname" name="lastname"">
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="specialization">Specialization</label>
                    <select name="specialization" class="form-control" id="specialization">
                        <c:forEach var="specialization" items="${listSpecialization}">
                            <option selected hidden disabled>Choose</option>
                            <option value="${specialization.id}">${specialization.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label for="category">Category</label>
                    <select name="category" class="form-control" id="category">
                        <c:forEach var="category" items="${listCategory}">
                            <option selected hidden disabled>Choose</option>
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="hospital">Hospital</label>
                    <select name="hospital" class="form-control" id="hospital">
                        <c:forEach var="hospital" items="${listHospital}">
                            <option selected hidden disabled>Choose</option>
                            <option value="${hospital.id}">${hospital.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-6">
                    <label for="department">Departmant</label>
                    <select name="department" class="form-control" id="department">
                        <c:forEach var="department" items="${listDepartment}">
                            <option selected hidden disabled>Choose</option>
                            <option value="${department.id}">${department.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username">
                </div>
                <div class="form-group col-md-6">
                    <label for="password">Password</label>
                    <input type="text" class="form-control" id="password" name="password">
                </div>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name="email">
            </div>
            <input type="hidden" name="state" value="createDoctor">
            <button type="submit" class="btn btn-primary">Create</button>
        </form>
    </div>
</div>
</body>
</html>
