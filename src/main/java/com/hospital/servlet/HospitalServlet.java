package com.hospital.servlet;

import com.hospital.dao.DepartmentDao;
import com.hospital.dao.HospitalDao;
import com.hospital.model.Department;
import com.hospital.model.Hospital;
import com.hospital.service.DepartmentService;
import com.hospital.service.HospitalService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HospitalServlet extends HttpServlet {
    HospitalService hospitalService;

    @Override
    public void init() throws ServletException {
        hospitalService = new HospitalService(new HospitalDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("listHospital")) {
            listHospital(request, response);
        } else if (state.equalsIgnoreCase("editHospital")) {
            editHospital(request, response);
        } else if (state.equalsIgnoreCase("deleteHospital")) {
            deleteHospital(request, response);
        } else if (state.equalsIgnoreCase("newHospital")) {
            showRegisterForm(request, response);
        }
    }


    private void listHospital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Hospital> hospitalList = hospitalService.getAll();
        request.setAttribute("listHospital", hospitalList);
        request.getRequestDispatcher("/view/hospital/ListHospital.jsp").forward(request, response);
    }

    private void editHospital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Hospital hospital = hospitalService.getById(id);
        request.setAttribute("hospital", hospital);
        request.getRequestDispatcher("/view/hospital/EditDoctor.jsp").forward(request, response);
    }

    private void deleteHospital(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        hospitalService.deleteById(id);
        response.sendRedirect(request.getContextPath() + "/hospital?state=listHospital");
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/hospital/HospitalForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("updateHospital")) {
            updateHospital(request, response);
        } else if (state.equalsIgnoreCase("createHospital")) {
            createHospital(request, response);
        }
    }

    private void updateHospital(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Hospital hospital = new Hospital();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        hospital.setId(id);
        hospital.setName(name);
        hospital.setAddress(address);
        hospitalService.update(hospital);
        response.sendRedirect(request.getContextPath() + "/hospital?state=listHospital");
    }

    private void createHospital(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        Hospital hospital = new Hospital();
        hospital.setName(name);
        hospital.setAddress(address);
        hospitalService.create(hospital);
        response.sendRedirect(request.getContextPath() + "/hospital?state=listHospital");
    }
}
