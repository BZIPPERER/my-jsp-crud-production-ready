<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "https://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee-Manager</title>
<style>

#feld1:focus, #feld2:focus
{
	background-color:orange;
}

body
{
	font-family: 'montserrat', sans-serif;    /*AUTOM*/
}
tr:nth-of-type(odd)
{
    background:lightgray;color:white;
}
tr:nth-of-type(even)
{
    background:gray;color:white;
}
</style>
</head>
<body>
    <div id="container" align="center">
    <h1>JSP Anwendung (Production-Ready)
  
    </h1>
	<%-- Person Add/Edit logic --%>
	<c:if test="${requestScope.error ne null}">
		<strong style="color: red;"><c:out
				value="${requestScope.error}"></c:out></strong>
	</c:if>
	<c:if test="${requestScope.success ne null}">
		<strong style="color: green;"><c:out
				value="${requestScope.success}"></c:out></strong>
	</c:if>
	<c:url value="/addPerson" var="addURL"></c:url>
	<c:url value="/editPerson" var="editURL"></c:url>

	<%-- Edit Request --%>
	<c:if test="${requestScope.person ne null}">
		<form action='<c:out value="${editURL}">
		    </c:out>' method="post">
			    ID: 
			    <input type="text" value="${requestScope.person.id}"
				readonly="readonly" name="id">
				<br> 
				Name: <input
				type="text" 
				id="feld1"
				value="${requestScope.person.name}" name="name"><br>
			    Land:
			     <input type="text" value="${requestScope.person.country}"
				id="feld2"
				name="country"><br> <input type="submit"
				value="Edit Person">
		</form>
	</c:if>

	<%-- Add Request --%>
	<c:if test="${requestScope.person eq null}">
		<form action='<c:out value="${addURL}">
		</c:out>' method="post">
			<table>
				<thead>
					<tr style="background-color:white;color:black">
						<td>
						</td>
						<td>
						<td>
					</tr>
				</thead>
				<tbody>
						<tr style="background-color:white;color:black">
							<td>Name
							</td>
							<td>	<input type="text" name="name">
							</td>
						</tr>
						<tr>
							<td>Land
							</td>
							<td><input
				type="text" name="country">
							</td>
						</tr>
						
				</tbody>
			</table>
			<input type="submit"
				value="Person neu anlegen">

		</form>
	</c:if>

	<%-- Persons -- Listen - -Logic --%>
	<c:if test="${not empty requestScope.persons}">
		<table>
			<tbody>
			<!-- Body STAR -->
				<tr>
					<th>DB Nummer</th>
					<th>Name</th>
					<th>Land</th>
					<th>Bearbeiten</th>
					<th>Entfernen</th>
				</tr>
				<c:forEach items="${requestScope.persons}" var="person">
					<c:url value="/editPerson" var="editURL">
						<c:param name="id" value="${person.id}"></c:param>
					</c:url>
					<c:url value="/deletePerson" var="deleteURL">
						<c:param name="id" value="${person.id}"></c:param>
					</c:url>
					<tr>
						<td><c:out value="${person.id}"></c:out></td>
						<td><c:out value="${person.name}"></c:out></td>
						<td><c:out value="${person.country}"></c:out></td>
						<td><a
							href='<c:out value="${editURL}" escapeXml="true"></c:out>'>Bearbeiten</a></td>
						<td><a
							href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Entfernen</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</body>
</html>