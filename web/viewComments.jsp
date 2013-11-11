<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Просмотр комментариев</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        
        <script type="text/javascript" src="js/mootools.js"></script>
        <script type="text/javascript" src="js/calendar.js"></script>
        <link rel="stylesheet" type="text/css" href="css/calendar.css" media="screen" />

        <script type="text/javascript">
        <!--
        function validate_form()
        {
            isError = false;

            if (document.viewForm.start_date.value == "" || document.viewForm.end_date.value == "")
            {
                alert ("Введите, пожалуйста, начальную и конечную даты.");
                isError = true;
            }

            return !isError;
        }

        window.addEvent('domready', function() {
            startCal = new Calendar({ start_date: 'd.m.Y' }, { direction: 0, tweak: { x: 6, y: 0 } });
            endCal = new Calendar({ end_date: 'd.m.Y' }, { direction: 0, tweak: { x: 6, y: 0 } });
        });
        //-->
        </script>
    </head>
    <body>
        <h3>Для того, чтобы просмотреть комментарии, выберите период.</h3>
        <form name="viewForm" action="ViewComments" method="post" onSubmit="validate_form();">
            <table border="0">
                <tr>
                    <td>с</td>
                    <td><input id="start_date" name="start_date" type="text"></td>
                    <td>по</td>
                    <td><input id="end_date" name="end_date" type="text"></td>
                    <td><input type="submit" name="append" value="Просмотреть"></td>
                </tr>
                    
            </table>
        </form>
        <%
            String startDate = request.getParameter("start_date");
            String endDate = request.getParameter("end_date");
            
            if (startDate != null && endDate != null)
            {
                if (!startDate.isEmpty() && !endDate.isEmpty())
                {
        %>
                    <p>Все комментарии с <%=startDate%> по <%=endDate%>:</p>
                    <table border="1" style="border-collapse: collapse;">
                        <tr align="center">
                            <th>#</th>
                            <th>Дата</th>
                            <th>Комментарий</th>
                        </tr>
                        <c:forEach items="${comments}" var="comment">
                            <tr align="center">
                                <td><c:out value="${comment.id}"/></td>
                                <td><c:out value="${comment.date}"/></td>
                                <td><c:out value="${comment.text}"/></td>
                            </tr>
                        </c:forEach>
                    </table>        
        <%
                }
            }
        %>
    </body>
</html>
