package openevents.controllers;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import database.HibernateHelper;

@WebServlet(urlPatterns = { "/create" })
public class CreateTables extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Session session = HibernateHelper.getSessionFactory().getCurrentSession();
    }
}
