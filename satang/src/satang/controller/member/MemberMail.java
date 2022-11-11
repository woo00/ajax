package satang.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import satang.dao.MemberDao;
import satang.vo.MemberVO;

/**
 * Servlet implementation class MemberMail
 */
@WebServlet("/member/getMail.smart")
public class MemberMail extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("mno");
		MemberDao mDao = new MemberDao();
		
		String ml = mDao.getMail(sno);
		
		//String ml = new String();
		
		StringBuffer buff = new StringBuffer("{ "); // ==> {"mail": "1001"}
		
		buff.append("\"mail\": \"" + ml + "\" }");
		PrintWriter pw = response.getWriter();
		pw.print(buff.toString());
	}

}
