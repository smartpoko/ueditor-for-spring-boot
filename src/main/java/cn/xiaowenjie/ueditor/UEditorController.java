package cn.xiaowenjie.ueditor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;

import com.baidu.ueditor.ActionEnter;

//@WebServlet(name = "UEditorServlet", urlPatterns = "/UEditor")  
public class UEditorController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 最后存放文件的静态目录
	 */
	final private String nasHome;

	public UEditorController(String nasHome) {
		this.nasHome = nasHome;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setHeader("Content-Type", "text/html");
		PrintWriter out = response.getWriter();

//		 ServletContext application = this.getServletContext();
//		 String rootPath = application.getRealPath("/");
		String rootPath = nasHome;

		String action = request.getParameter("action");
		String result = new ActionEnter(request, rootPath).exec();
		if (action != null && (action.equals("listfile") || action.equals("listimage"))) {
			rootPath = rootPath.replace("\\", "/");
			result = result.replaceAll(rootPath, "/").replace("//", "/");
		}

		out.write(result);
	}

}