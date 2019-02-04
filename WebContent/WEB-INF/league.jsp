<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DrawPicker</title>
<style><%@include file="/WEB-INF/stylesheet.css" %></style>
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
	
	<form action="/DrawPredictioner/singleLeague">
		League:
		<select name="league">
			<option value="BL1" <c:if test="${league eq 'BL1'}">selected</c:if>>Bundesliga</option>
			<option value="PL" <c:if test="${league eq 'PL'}">selected</c:if>>Premier League</option>
			<option value="PD" <c:if test="${league eq 'PD'}">selected</c:if>>Primera Division</option>
			<option value="DED" <c:if test="${league eq 'DED'}">selected</c:if>>Eredivisie</option>
			<option value="SA" <c:if test="${league eq 'SA'}">selected</c:if>>Serie A</option>
			
		</select>
		<input type="submit" value="CONFIRM">

	<table>
		<c:set var="count" value="1" scope="page" />
		<tr>
			<th>Rank</th>
			<th>Team Name</th>
			<th>Played Games</th>
			<th>Wins</th>
			<th>Draws</th>
			<th>Losses</th>
			<th>Points</th>
		</tr>
		<c:forEach var="league" items="${leagueTable}">
			<c:set var="team" value="${league.getStandings().getTable()}"
				scope="page" />

			<tr>
				<th>${team.getPosition()}</th>
				<th>${team.getTeam().getName()}</th>
				<th>${team.getPlayedGames()}</th>
				<th>${team.getWon()}</th>
				<th>${team.getDraw()}</th>
				<th>${team.getLost()}</th>
				<th>${team.getPoints()}</th>
				<c:set var="count" value="${count + 1}" scope="page" />
			</tr>
		</c:forEach>
	</table>
	

</body>
</html>