package com.hospital.servlet;

import com.hospital.dao.*;
import com.hospital.model.*;
import com.hospital.service.*;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DoctorServlet extends HttpServlet {
    DoctorService doctorService;
    SpecializationService specializationService;
    HospitalService hospitalService;
    CategoryService categoryService;
    DepartmentService departmentService;

    @Override
    public void init() throws ServletException {
        doctorService = new DoctorService(new DoctorDao());
        specializationService = new SpecializationService(new SpecializationDao());
        hospitalService = new HospitalService(new HospitalDao());
        categoryService = new CategoryService(new CategoryDao());
        departmentService = new DepartmentService(new DepartmentDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("listDoctor")) {
            listDoctor(request, response);
        } else if (state.equalsIgnoreCase("editDoctor")) {
            editDoctor(request, response);
        } else if (state.equalsIgnoreCase("deleteDoctor")) {
            deleteDoctor(request, response);
        } else if (state.equalsIgnoreCase("newDoctor")) {
            showRegisterForm(request, response);
        }
    }


    private void listDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Doctor> doctorList = doctorService.getAll();
        request.setAttribute("listDoctor", doctorList);
        request.getRequestDispatcher("/view/doctor/ListDoctor.jsp").forward(request, response);
    }

    private void editDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Doctor doctor = doctorService.getById(id);
        request.setAttribute("doctor", doctor);
        request.setAttribute("listCategory", categoryService.getAll());
        request.setAttribute("listHospital", hospitalService.getAll());
        request.setAttribute("listDepartment", departmentService.getAll());
        request.setAttribute("listSpecialization", specializationService.getAll());
        request.getRequestDispatcher("/view/doctor/EditDoctor.jsp").forward(request, response);
    }

    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        doctorService.deleteById(id);
        response.sendRedirect(request.getContextPath() + "/doctor?state=listDoctor");
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listCategory", categoryService.getAll());
        request.setAttribute("listHospital", hospitalService.getAll());
        request.setAttribute("listDepartment", departmentService.getAll());
        request.setAttribute("listSpecialization", specializationService.getAll());
        request.getRequestDispatcher("/view/doctor/DoctorForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("updateDoctor")) {
            updateDoctor(request, response);
        } else if (state.equalsIgnoreCase("createDoctor")) {
            createPatient(request, response);
        }
    }

    private void updateDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Doctor doctor = new Doctor();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        int specializationId = Integer.parseInt(request.getParameter("specialization"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        int hospitalId = Integer.parseInt(request.getParameter("hospital"));
        int departmentId = Integer.parseInt(request.getParameter("department"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        doctor.setId(id);
        doctor.setName(name);
        doctor.setLastname(lastname);

        Specialization specialization = new Specialization();
        specialization.setId(specializationId);
        doctor.setSpecialization(specialization);

        Category category = new Category();
        category.setId(categoryId);
        doctor.setCategory(category);

        Hospital hospital = new Hospital();
        hospital.setId(hospitalId);
        doctor.setHospital(hospital);

        Department department = new Department();
        department.setId(departmentId);
        doctor.setDepartment(department);

        doctor.setUsername(username);
        doctor.setPassword(password);
        doctor.setEmail(email);
        doctorService.update(doctor);
        response.sendRedirect(request.getContextPath() + "/doctor?state=listDoctor");
    }

    private void createPatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Doctor doctor = new Doctor();
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        int specializationId = Integer.parseInt(request.getParameter("specialization"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        int hospitalId = Integer.parseInt(request.getParameter("hospital"));
        int departmentId = Integer.parseInt(request.getParameter("department"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        doctor.setName(name);
        doctor.setLastname(lastname);

        Specialization specialization = new Specialization();
        specialization.setId(specializationId);
        doctor.setSpecialization(specialization);

        Category category = new Category();
        category.setId(categoryId);
        doctor.setCategory(category);

        Hospital hospital = new Hospital();
        hospital.setId(hospitalId);
        doctor.setHospital(hospital);

        Department department = new Department();
        department.setId(departmentId);
        doctor.setDepartment(department);

        doctor.setUsername(username);
        doctor.setPassword(password);
        doctor.setEmail(email);
        doctorService.create(doctor);
        response.sendRedirect(request.getContextPath() + "/doctor?state=listDoctor");
    }
}
