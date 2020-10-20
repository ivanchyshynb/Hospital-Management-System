package com.hospital.servlet;

import com.hospital.dao.PatientDao;
import com.hospital.model.Patient;
import com.hospital.service.PatientService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PatientServlet extends HttpServlet {
    PatientService patientService;

    @Override
    public void init() throws ServletException {
        patientService = new PatientService(new PatientDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("listPatient")) {
            listPatient(request, response);
        } else if (state.equalsIgnoreCase("editPatient")) {
            editPatient(request, response);
        } else if (state.equalsIgnoreCase("deletePatient")) {
            deletePatient(request, response);
        } else if (state.equalsIgnoreCase("newPatient")) {
            showRegisterForm(request, response);
        }
    }


    private void listPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Patient> patientList = patientService.getAll();
        request.setAttribute("listPatient", patientList);
        request.getRequestDispatcher("/view/patient/ListPatient.jsp").forward(request, response);
    }

    private void editPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Patient patient = patientService.getById(id);
        request.setAttribute("patient", patient);
        request.getRequestDispatcher("/view/patient/EditPatient.jsp").forward(request, response);
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        patientService.deleteById(id);
        response.sendRedirect(request.getContextPath() + "/patient?state=listPatient");
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/patient/PatientForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("updatePatient")) {
            updatePatient(request, response);
        } else if (state.equalsIgnoreCase("createPatient")) {
            createPatient(request, response);
        }
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Patient patient = new Patient();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        patient.setId(id);
        patient.setName(name);
        patient.setLastname(lastname);
        patient.setAge(age);
        patient.setSex(sex);
        patient.setPhone(phone);
        patient.setAddress(address);
        patientService.update(patient);
        response.sendRedirect(request.getContextPath() + "/patient?state=listPatient");
    }

    private void createPatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        int age = Integer.parseInt(request.getParameter("age"));
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Patient patient = new Patient();
        patient.setName(name);
        patient.setLastname(lastname);
        patient.setAge(age);
        patient.setSex(sex);
        patient.setPhone(phone);
        patient.setAddress(address);
        patientService.create(patient);
        response.sendRedirect(request.getContextPath() + "/patient?state=listPatient");
    }

}
