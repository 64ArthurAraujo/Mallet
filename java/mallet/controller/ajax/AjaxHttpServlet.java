package mallet.controller.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public abstract class AjaxHttpServlet extends HttpServlet {
	/**
	 * Sends a minimal response containing only the content of the parameter "wroteResponse"
	 *  
	 * @param wroteResponse
	 * @param response
	 */
	
	protected void writeResponse(String wroteResponse, HttpServletResponse response) {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			
			PrintWriter out = response.getWriter();

			out.print(wroteResponse);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
