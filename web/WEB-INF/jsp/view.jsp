<%@ page import="ru.javawebinar.basejava.model.ParagraphSection" %>
<%@ page import="ru.javawebinar.basejava.model.PlaceSection" %>
<%@ page import="ru.javawebinar.basejava.util.HtmlUtil" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <hr>
    <table cellpadding="2">
        <tbody>
        <c:forEach var="section" items="${resume.sections}">
            <jsp:useBean id="section"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.Section>"/>
            <tr>
                <td colspan="2">
                    <h2>${section.key.title}</h2>
                </td>
            </tr>
            <c:choose>
                <c:when test="${section.key=='OBJECTIVE'}">
                    <tr>
                        <td colspan="2">
                            <h3>${section.value}</h3>
                        </td>
                    </tr>
                </c:when>
                <c:when test="${section.key=='PERSONAL'}">
                    <tr>
                        <td colspan="2">
                                ${section.value}
                        </td>
                    </tr>
                </c:when>
                <c:when test="${section.key=='QUALIFICATIONS' || section.key=='ACHIEVEMENT'}">
                    <c:forEach var="item" items="<%=((ParagraphSection)section.getValue()).getParagraphList()%>">
                        <tr>
                            <td>
                                <ui>
                                    <li>${item}</li>
                                </ui>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:when test="${section.key=='EXPERIENCE' || section.key=='EDUCATION'}">
                    <c:forEach var="place" items="<%=((PlaceSection) section.getValue()).getPlaces()%>">
                        <tr>
                            <td colspan="2">
                                <c:choose>
                                    <c:when test="${empty place.homePage.url}">
                                        <h3>${place.homePage.name}</h3>
                                    </c:when>
                                    <c:otherwise>
                                        <h3><a href="${place.homePage.url}">${place.homePage.name}</a></h3>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <c:forEach var="period" items="${place.periodList}">
                            <jsp:useBean id="period" type="ru.javawebinar.basejava.model.Place.Period"/>
                            <tr>
                                <td width="15%" style="vertical-align: top"><%=HtmlUtil.parseDate(period)%>
                                </td>
                                <td><b>${period.position}</b><br>${period.description}</td>
                            </tr>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        </tbody>
    </table>
    <p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
