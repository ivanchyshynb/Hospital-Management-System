package com.hospital.servlet;

import com.hospital.dao.HospitalDao;
import com.hospital.dao.SpecializationDao;
import com.hospital.model.Hospital;
import com.hospital.model.Specialization;
import com.hospital.service.HospitalService;
import com.hospital.service.SpecializationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SpecializationServlet extends HttpServlet {
    SpecializationService specializationService;

    @Override
    public void init() throws ServletException {
        specializationService = new SpecializationService(new SpecializationDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("listSpecialization")) {
            listSpecialization(request, response);
        } else if (state.equalsIgnoreCase("editSpecialization")) {
            editSpecialization(request, response);
        } else if (state.equalsIgnoreCase("deleteSpecialization")) {
            deleteSpecialization(request, response);
        } else if (state.equalsIgnoreCase("newSpecialization")) {
            showRegisterForm(request, response);
        }
    }


    private void listSpecialization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Specialization> specializationList = specializationService.getAll();
        request.setAttribute("listSpecialization", specializationList);
        request.getRequestDispatcher("/view/specialization/ListSpecialization.jsp").forward(request, response);
    }

    private void editSpecialization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Specialization specialization = specializationService.getById(id);
        request.setAttribute("specialization", specialization);
        request.getRequestDispatcher("/view/specialization/EditDoctor.jsp").forward(request, response);
    }

    private void deleteSpecialization(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        specializationService.deleteById(id);
        response.sendRedirect(request.getContextPath() + "/specialization?state=listSpecialization");
    }

    private void showRegisterForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/view/specialization/SpecializationForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        if (state.equalsIgnoreCase("updateSpecialization")) {
            updateSpecialization(request, response);
        } else if (state.equalsIgnoreCase("createSpecialization")) {
            createSpecialization(request, response);
        }
    }

    private void updateSpecialization(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Specialization specialization = new Specialization();
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        specialization.setId(id);
        specialization.setName(name);
        specializationService.update(specialization);
        response.sendRedirect(request.getContextPath() + "/specialization?state=listSpecialization");
    }

    private void createSpecialization(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        Specialization specialization = new Specialization();
        specialization.setName(name);
        specializationService.create(specialization);
        response.sendRedirect(request.getContextPath() + "/specialization?state=listSpecialization");
    }
}
