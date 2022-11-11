package satang.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import satang.dao.MemberDao;
import satang.vo.MemberVO;

@WebServlet("/member/memberList.smart")
public class MemberList extends HttpServlet {
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//이 함수는 /satang/member/memberList.smart 라고 서버에게 요청하면 호출되는 함수 
		// 이 함수가 하는 일은 데이터베이스 조회결과 리스트를 클라이언트에게 Json형식으로 전달돼야함
		
//		1. 데이터베이스에서 조회한 리스트 받기
		MemberDao mDao = new MemberDao();
		ArrayList<MemberVO> list = mDao.getNameList();
		
//		2. 전달받은 리스트를 응답문서로 변환
		// ==> [데이터1, 데이터2, ...] ==> [{},{},{}]
		
		StringBuffer buff = new StringBuffer("{ \"result\": [");
		
		for(int i = 0 ; i < list.size(); i++) {
			MemberVO vo = list.get(i);
			buff.append("{ \"mno\": \"" + vo.getMno() + "\", ");
			buff.append(" \"name\": \"" + vo.getName() + "\" }");
			if (i < list.size() - 1) {
				buff.append(",");
			}
		}
		
		buff.append("]}");
		
		resp.setCharacterEncoding("UTF-8");
//		3. 응답문서로 응답.
		PrintWriter pw = resp.getWriter();
		pw.print(buff.toString());
	}
}