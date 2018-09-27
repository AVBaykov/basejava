<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page import="ru.javawebinar.basejava.model.Section" %>
<%@ page import="ru.javawebinar.basejava.model.ParagraphSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя</dt>
            <dd><input type="text" name="fullName" size="50" value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size="30" value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <% pageContext.setAttribute("String", SectionType.ACHIEVEMENT.name());%>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <dl>
                <c:choose>
                    <c:when test="${type.ordinal() le 1}">
                        <dt>${type.title}</dt>
                        <dd><input type="text" name="${type.name()}" size="30" value="${resume.getSection(type)}"></dd>
                    </c:when>
                    <c:when test="${type.ordinal() ge 2 && type.ordinal() le 3}">
                        <dt>${type.title}</dt>
                        <dd><<textarea name="${type.name()}" id="" cols="30" rows="10">${resume.getSection(type)}</textarea>></dd>
                    </c:when>
                </c:choose>
            </dl>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
