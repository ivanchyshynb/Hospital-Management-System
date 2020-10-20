package com.hospital.servlet;

import com.hospital.dao.DoctorDao;
import com.hospital.model.Doctor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = getServletContext()
                .getRequestDispatcher("/view/Login.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DoctorDao doctorDao = new DoctorDao();

        Doctor doctor = doctorDao.validate(username, password);
        if (doctor != null && doctor.getRole().equals("DOCTOR")) {
            HttpSession session = request.getSession();
            session.setAttribute("doctor", doctor);
            request.getRequestDispatcher("/view/HomePage.jsp").forward(request, response);
        } else if (doctor != null && doctor.getRole().equals("ADMIN")) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", doctor);
            request.getRequestDispatcher("/view/HomePage.jsp").forward(request, response);
        } else {
            request.setAttribute("message", "Invalid username or password");
            request.getRequestDispatcher("/view/Login.jsp").forward(request, response);
        }
    }
}
