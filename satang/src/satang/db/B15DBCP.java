package satang.db;

import java.sql.*;
import javax.sql.*;
import javax.naming.InitialContext;

/**
 * 이 클래스는 커넥션 풀에 있는 커넥션을 이용해서
 * 데이터베이스 작업을 할 유틸리티 클래스이다.
 * @author 전진형
 * @since 2022/11/10
 */

public class B15DBCP {
   // 커넥션 풀 관리할 변수
   public DataSource ds;
   
   /*
    * 이 클래스를 누군가 new 시키면
    * context.xml 파일에 등록된 자원을 가지고 와야 한다.
    * 이처럼 context.xml 파일에 등록된 자원을 가지고 오는 기법을 
    * JNDI(Java Naming and Directory Interface) 라고 한다.
    */
   
   public B15DBCP() {
      try {
         // 1. context.xml에 등록된 자원을 알아낸다.
         InitialContext context = new InitialContext();

         // 2. 그 중에 필요한 자원을 꺼내온다.
         ds = (DataSource) context.lookup("java:comp/env/jdbc/TestDB");
         /*
          * 찾을 이름을 정하는 규칙
          * 
          *       java:/comp/env/찾을이름
          * 
          *       ==> java:comp/env/jdbc/TestDB
          * 
          * */
         
      } catch (Exception e) {
         System.out.println(e);
      }
   }
   
   
   // 커넥션 풀에 있는 커넥션 하나 꺼내서 반환하는 함수
   public Connection getCon() {
      Connection con = null;
      try {
         // DataSource에서 하나 꺼내온다.
         con = ds.getConnection();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return con;
   }
   
   // Statement를 반환 함수
   public Statement getStmt(Connection con) {
      Statement stmt = null;
      
      try {
         stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return stmt;
   }
   
   // PreparedStatement 반환 함수
   public PreparedStatement getPstmt(Connection con, String sql) {
      PreparedStatement pstmt = null;
      try {
         pstmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
      } catch (Exception e) {
         e.printStackTrace();
      }
      return pstmt;
   }
   
   // 사용하지 않는 자원 반환 함수
   public void close(Object o) {
      // 어떤 자원을 반환해줘야 할지 모르기 때문에 입력받고 형변환해서 반환
      try {
         if (o instanceof Connection) {
            ((Connection)o).close();
         }else if (o instanceof Statement) {
            ((Statement)o).close();
         }else if (o instanceof PreparedStatement) {
            ((PreparedStatement)o).close();
         }else if (o instanceof ResultSet) {
            ((ResultSet)o).close();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}