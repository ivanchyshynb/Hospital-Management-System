package com.hospital.servlet;

import com.hospital.dao.CategoryDao;
import com.hospital.dao.DoctorDao;
import com.hospital.model.Category;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;
import com.hospital.service.CategoryService;
import com.hospital.service.DoctorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryServlet extends HttpServlet {
    CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        categoryService = new CategoryService(new CategoryDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("listCategory")) {
            listCategory(request, response);
        } else if (state.equalsIgnoreCase("editCategory")) {
            editCategory(request, response);
        } else if (state.equalsIgnoreCase("deleteCategory")) {
            deleteCategory(request, response);
        } else if (state.equalsIgnoreCase("newCategory")) {
            showRegisterForm(request, response);
        }
    }


    private void listCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAll();
        request.setAttribute("listCategory", categoryList);
        request.getRequestDispatcher("/view/category/ListCategory.jsp").forward(request, response);
    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.getById(id);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/view/category/EditDoctor.jsp").forward(request, response);
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.deleteById(id);
        response.sendRedirect(request.getContextPath() + "/category?state=listCategory");
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/category/CategoryForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("updateCategory")) {
            updateCategory(request, response);
        } else if (state.equalsIgnoreCase("createCategory")) {
            createCategory(request, response);
        }
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Category category = new Category();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        category.setId(id);
        category.setName(name);
        categoryService.update(category);
        response.sendRedirect(request.getContextPath() + "/category?state=listCategory");
    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        Category category = new Category();
        category.setName(name);
        categoryService.create(category);
        response.sendRedirect(request.getContextPath() + "/category?state=listCategory");
    }
}
