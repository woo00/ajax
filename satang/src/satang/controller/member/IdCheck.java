package satang.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import satang.dao.MemberDao;

/**
 * Servlet implementation class IdCheck
 */
@WebServlet("/member/idCheck.smart")
public class IdCheck extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//할일
		//1. 파라미터 꺼내고
		String id = request.getParameter("id");
		System.out.println("받은데이터" + id);
		//2. 데이터베이스 작업요청& 결과받기
		MemberDao mDao = new MemberDao();
		int cnt = mDao.getIdCnt(id);
		StringBuffer buff = new StringBuffer("{ \"result\": ");
		//3. 결과에 따른 처리
		if (cnt == 0) {
			//사용가능한 아이디인 경우
			buff.append("\"OK\"");
		}else {
			//사용불가능한 아이디인 경우 
			buff.append("\"NO\"");
		}
		buff.append(" }");
		//4. 응답문서 보내기
//		System.out.println(buff.toString());
		PrintWriter pw = response.getWriter();
		pw.println(buff.toString());
	}
}
