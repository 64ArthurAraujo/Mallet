package mallet.controller.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mallet.cache.SearchesCache;

public class SearchCompleter extends AjaxHttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		writeResponse(SearchesCache.getAllSearchEntries().toString(), response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		writeResponse(SearchesCache.getAllSearchEntries().toString(), response);
	}
}
