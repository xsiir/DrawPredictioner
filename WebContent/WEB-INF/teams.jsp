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
	
		<form action="/DrawPredictioner/teamTypes">
		Match type:
		<select name="matchType">
			<option value="ANY" <c:if test="${matchType eq ''}">selected</c:if>>ANY</option>
			<option value="TOTAL" <c:if test="${matchType eq 'TOTAL'}">selected</c:if>>TOTAL</option>
			<option value="HOME" <c:if test="${matchType eq 'HOME'}">selected</c:if>>HOME</option>
			<option value="AWAY" <c:if test="${matchType eq 'AWAY'}">selected</c:if>>AWAY</option>
		</select> Result Limit:
		<input type="text" id="limit" name="limit">
		<input type="submit" value="CONFIRM">
	
	</form>

	<table>
		<c:set var="count" value="1" scope="page" />
		<tr>
			<th>Rank</th>
			<th>Match Type</th>
			<th>Name</th>
			<th>Game Played</th>
			<th>Draws</th>
			<th>DrawAVG</th>
		</tr>
		<c:forEach var="team" items="${teamList}">
			<tr>
				<th>${count}</th>
				<th>${team.getStatistics().getGameType()}</th>
				<th>${team.getName()}</th>
				<th>${team.getStatistics().getPlayedGames()}</th>
				<th>${team.getStatistics().getDraw()}</th>
				<th>${team.getStatistics().getDrawAVG()}</th>
				<c:set var="count" value="${count + 1}" scope="page" />
			</tr>
		</c:forEach>
	</table>
	

</body>
</html>