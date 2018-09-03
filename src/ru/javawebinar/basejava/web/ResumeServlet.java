package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.ParagraphSection;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.TextSection;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {
    Storage storage = Config.get().getStorage();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        response.setContentType("text/html; charset=UTF-8");
        StringBuilder sb = new StringBuilder();
        if (uuid == null) {
            sb.append("<table border =\"1\">");
            String link = "http://localhost:8080/resumes/resume?uuid=";
            storage.getAllSorted().forEach(r -> sb.append(String.format("<tr> <td> <a href=\"%s\"> %s </a> </td> </tr>", link + r.getUuid(), r.getUuid())));
            response.getWriter().write(sb.toString());
        } else {
            Resume resume = storage.get(uuid);

            sb.append(String.format("<h2> %s </h2>", resume.getFullName()));
            resume.getContacts().forEach((t, v) -> sb.append(String.format("%s : %s <br />", t.getTitle(), v)));
            sb.append("<hr />");
            resume.getSections().forEach((t,v) -> {
                switch (t) {
                    case PERSONAL:
                    case OBJECTIVE:
                        sb.append(String.format("<h2> %s </h2>", t.getTitle()));
                        sb.append(String.format("%s <br />", ((TextSection)v).getContent()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        sb.append(String.format("<h2> %s </h2>", t.getTitle()));
                        sb.append("<ul>");
                        ((ParagraphSection) v).getParagraphList().forEach(p -> {
                            sb.append(String.format("<li> %s </li>", p));
                        });
                        sb.append("</ul>");
                        break;
                }
            });

            String contacts = sb.toString();
            response.getWriter().write(contacts);



        }
    }
}
