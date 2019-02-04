<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style><%@include file="/WEB-INF/stylesheet.css" %></style>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DrawPicker</title>
</head>
<body>

	<header>
	
		<h1>DrawTyper</h1>
		<ul>
			<li><a href="teamTypes">Team Types</a></li>
			<li><a href="matchTypes">Match Types</a></li>
			<li><a href="singleLeague">League Table</a></li>
			<li><a href="update">Update</a></li>
		</ul>

	</header>
	
	<form action="/DrawPredictioner/matchTypes">Result limit:
			<input type="text" id="limit" name="limit">
		<input type="submit" value="CONFIRM">
	
	</form>

	<table>
		<c:set var="count" value="1" scope="page" />
		<tr>
			<th>Rank</th>
			<th>Date</th>
			<th>Home Team</th>
			<th>VS</th>
			<th>Away Team</th>
			<th>Probability</th>
		</tr>
		<c:forEach var="match" items="${matchList}">
			<tr>
				<th>${count}</th>
				<th>${match.getMatchDay()}</th>
				<th>${match.getHomeTeam().getName()}</th>
				<th></th>
				<th>${match.getAwayTeam().getName()}</th>
				<th>${match.getProbabilityInPercents()}%</th>
				<c:set var="count" value="${count + 1}" scope="page" />
			</tr>
		</c:forEach>
	</table>

</body>
</html>