package satang.dao;

import java.sql.*;
import java.util.ArrayList;

import oracle.net.aso.b;
import satang.db.B15DBCP;
import satang.sql.MemberSQL;
import satang.vo.MemberVO;

public class MemberDao {
	private B15DBCP db;
	private	Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private MemberSQL mSQL;
	private String sql;
	public MemberDao() {
		db = new B15DBCP();
		mSQL = new MemberSQL();
	}
	
	// 아이디 카운트 조회 전담 처리함수
	public int getIdCnt(String id) {
//		1. 반환값 변수 생성
		int cnt = 0;
//		2. 커넥션 꺼내고
		con = db.getCon();
//		3. 질의명령 가져오고
		sql = mSQL.getSQL(mSQL.SEL_ID_CNT);
//		4. 명령전달도구 준비
		pstmt = db.getPstmt(con, sql);
		try {
	//		5. 질의명령 완성
			pstmt.setString(1, id);
	//		6. 질의명령 보내고 결과받고
			rs = pstmt.executeQuery();
	//		7. 결과에서 데이터 꺼내고
			rs.next();
			cnt = rs.getInt("cnt");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
//		8. 반환
		return cnt;
	}
	//회원 이름 리스트 조회 전담 처리함수
	public ArrayList<MemberVO> getNameList(){
//		1.반환값 변수생성
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
//		2.커넥션 추출
		con = db.getCon();
//		3.질의명령 가져오기
		sql = mSQL.getSQL(mSQL.SEL_ALL_MEMB);
//		4.명령전달도구 준비
		stmt = db.getStmt(con);
		try {
	//		5. 질의명령 송신, 결과수신
			rs= stmt.executeQuery(sql);
			while(rs.next()) {
		//		6. 반복데이터 추출 VO저장
				MemberVO vo = new MemberVO();
				int mno = rs.getInt("mno");
				String name = rs.getNString("name");
				
				vo.setMno(mno);
				vo.setName(name);
		//		7. list에 VO저장
				list.add(vo);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
//		8. list 반환
		return list;
	}
	//회원 메일 처리함수
	public String getMail (String mno) {
		String ml = "";
		con= db.getCon();
		sql = mSQL.getSQL(mSQL.SEL_MAIL);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, mno);
			rs = pstmt.executeQuery();
			rs.next();
			ml = rs.getString("mail");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return ml;
	}
}
