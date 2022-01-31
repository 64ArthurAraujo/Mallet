<%@page import="mallet.database.table.ProductsManager, mallet.cache.ProductsCache"%>

<%

	new ProductsManager().queryProduct(1);
%>