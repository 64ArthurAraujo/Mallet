<%@page import="mallet.cache.SearchesCache"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${param.q} | Shop - Mallet</title>
	</head>
	<body>
		<div id="search-box">
			<form action="search.jsp" method="GET">
				<input type="text" name="q" id="search-bar" value="${param.q}">
				<button>Search</button>
			</form>
			
			<p id="autocomplete-string"></p>
		</div>
		
		
		<%-- TODO change to taglib --%>
		<% SearchesCache.addSearchEntry(request.getParameter("q")); %>
		<script src="../assets/javascript/auto_complete.js"></script>
	</body>
</html> 