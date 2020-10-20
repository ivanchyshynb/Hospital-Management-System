package com.hospital.servlet;

import com.hospital.dao.*;
import com.hospital.model.*;
import com.hospital.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RegistryServlet extends HttpServlet {
    DoctorService doctorService;
    PatientService patientService;
    RegistryService registryService;

    @Override
    public void init() throws ServletException {
        doctorService = new DoctorService(new DoctorDao());
        patientService = new PatientService(new PatientDao());
        registryService = new RegistryService(new RegistryDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("listRegistry")) {
            listRegistry(request, response);
        } else if (state.equalsIgnoreCase("editRegistry")) {
            editRegistry(request, response);
        } else if (state.equalsIgnoreCase("deleteRegistry")) {
            deleteRegistry(request, response);
        } else if (state.equalsIgnoreCase("newRegistry")) {
            showRegisterForm(request, response);
        }
    }


    private void listRegistry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Registry> registryList = registryService.getAll();
        request.setAttribute("listRegistry", registryList);
        request.getRequestDispatcher("/view/registry/ListRegistry.jsp").forward(request, response);
    }

    private void editRegistry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int number = Integer.parseInt(request.getParameter("number"));
        Registry registry = registryService.getById(number);
        request.setAttribute("registry", registry);
        request.setAttribute("listPatient", patientService.getAll());
        request.setAttribute("listDoctor", doctorService.getAll());
        request.getRequestDispatcher("/view/registry/EditRegistry.jsp").forward(request, response);
    }

    private void deleteRegistry(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int number = Integer.parseInt(request.getParameter("number"));
        registryService.deleteById(number);
        response.sendRedirect(request.getContextPath() + "/registry?state=listRegistry");
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listPatient", patientService.getAll());
        request.setAttribute("listDoctor",doctorService.getAll());
        request.getRequestDispatcher("/view/registry/RegistryForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("updateRegistry")) {
            try {
                updateRegistry(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if (state.equalsIgnoreCase("createRegistry")) {
            try {
                createRegistry(request, response);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateRegistry(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String pattern = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(pattern);

        Registry registry = new Registry();
        int number = Integer.parseInt(request.getParameter("number"));
        int patientId = Integer.parseInt(request.getParameter("patient"));
        int doctorId = Integer.parseInt(request.getParameter("doctor"));
        Date date_ad = dateFormat.parse(request.getParameter("admission"));
        Date date_dis = dateFormat.parse(request.getParameter("dismission"));
        String disease = request.getParameter("disease");
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));
        registry.setNumber(number);

        Patient patient = new Patient();
        patient.setId(patientId);
        registry.setPatient(patient);

        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        registry.setDoctor(doctor);

        registry.setAdmission(date_ad);
        registry.setDismission(date_dis);
        registry.setDisease(disease);
        registry.setRoomNumber(roomNumber);

        registryService.update(registry);
        response.sendRedirect(request.getContextPath() + "/registry?state=listRegistry");
    }

    private void createRegistry(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String pattern = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(pattern);

        Registry registry = new Registry();
        int patientId = Integer.parseInt(request.getParameter("patient"));
        int doctorId = Integer.parseInt(request.getParameter("doctor"));
        Date date_ad = dateFormat.parse(request.getParameter("admission"));
        Date date_dis = dateFormat.parse(request.getParameter("dismission"));
        String disease = request.getParameter("disease");
        int roomNumber = Integer.parseInt(request.getParameter("roomNumber"));

        Patient patient = new Patient();
        patient.setId(patientId);
        registry.setPatient(patient);

        Doctor doctor = new Doctor();
        doctor.setId(doctorId);
        registry.setDoctor(doctor);

        registry.setAdmission(date_ad);
        registry.setDismission(date_dis);
        registry.setDisease(disease);
        registry.setRoomNumber(roomNumber);

        registryService.create(registry);
        response.sendRedirect(request.getContextPath() + "/registry?state=listRegistry");
    }
}
