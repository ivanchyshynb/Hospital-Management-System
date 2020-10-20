package com.hospital.servlet;

import com.hospital.dao.CategoryDao;
import com.hospital.dao.DepartmentDao;
import com.hospital.model.Category;
import com.hospital.model.Department;
import com.hospital.service.CategoryService;
import com.hospital.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DepartmentServlet extends HttpServlet {
    DepartmentService departmentService;

    @Override
    public void init() throws ServletException {
        departmentService = new DepartmentService(new DepartmentDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("listDepartment")) {
            listDepartment(request, response);
        } else if (state.equalsIgnoreCase("editDepartment")) {
            editDepartment(request, response);
        } else if (state.equalsIgnoreCase("deleteDepartment")) {
            deleteDepartment(request, response);
        } else if (state.equalsIgnoreCase("newDepartment")) {
            showRegisterForm(request, response);
        }
    }


    private void listDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departmentList = departmentService.getAll();
        request.setAttribute("listDepartment", departmentList);
        request.getRequestDispatcher("/view/department/ListDepartment.jsp").forward(request, response);
    }

    private void editDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Department department = departmentService.getById(id);
        request.setAttribute("department", department);
        request.getRequestDispatcher("/view/department/EditDoctor.jsp").forward(request, response);
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        departmentService.deleteById(id);
        response.sendRedirect(request.getContextPath() + "/department?state=listDepartment");
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/department/DepartmentForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("updateDepartment")) {
            updateDepartment(request, response);
        } else if (state.equalsIgnoreCase("createDepartment")) {
            createDepartment(request, response);
        }
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Department department = new Department();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        department.setId(id);
        department.setName(name);
        department.setAmount(amount);
        departmentService.update(department);
        response.sendRedirect(request.getContextPath() + "/department?state=listDepartment");
    }

    private void createDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        int amount = Integer.parseInt(request.getParameter("amount"));
        Department department = new Department();
        department.setName(name);
        department.setAmount(amount);
        departmentService.create(department);
        response.sendRedirect(request.getContextPath() + "/department?state=listDepartment");
    }

}
