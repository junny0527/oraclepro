package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhoneDao {
	//DAO (Data Access Object)
    //데이터베이스 관련 작업전담 클래스
    //등록, 수정, 조회, 삭제 등의 작업을 하는 클래스
    //CRUD ( CREATE, READ, UPDATE, DELETE )  작업
   
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String user = "phonedb";
    private String password = "phonedb";
    private int PersonId = 0;
    private String PersonName = "";
    private String PersonHp = "";
    private String PersonCompany = "";
    private String sql = "";
    private boolean result = false;
    private PersonVo vo = null;
    private ResultSet rs = null;
   
    public void connect() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void close(ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   
    public void close(PreparedStatement pst) {
        if(pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   
    public void close(Connection conn) {
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   
    //등록
    public boolean PersonInsert(String PersonName, String PersonHp, String PersonCompany) {
        try {
            connect();
            sql += " INSERT INTO person VALUES  (seq_person_id.nextval, ?, ?, ?) ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,PersonName);
            pstmt.setString(2, PersonHp);
            pstmt.setString(3, PersonCompany);
            int cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(conn);
        }
        return result;
    }
   

   
    public ArrayList<PersonVo> personSelect() {
        ArrayList<PersonVo> al = new ArrayList<PersonVo>();
        try {
            connect();
            sql += " SELECT   person_id, name, hp, company FROM person ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                //int row = rs.getRow();
                //PersonId = rs.getInt("PersonId);
                PersonId = rs.getInt(1);
                //name = rs.getString("name");
                PersonName = rs.getString(2);
                //phone = rs.getString("phone");
                PersonHp = rs.getString(3);
                //Company = rs.getString("Company");
                PersonCompany = rs.getString(4);
               
                PersonVo vo = new PersonVo(PersonId, PersonName ,PersonHp,PersonCompany);
                al.add(vo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
            close(conn);
        }
        return al;
    }
   
    public PersonVo persononeSelect(String search) {
        try {
            connect();
            sql += " SELECT * FROM person WHERE name = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, search);
            rs = pstmt.executeQuery();
            boolean isList = false;
            while(rs.next()) {
                PersonId = rs.getInt("PersonId");
                PersonName = rs.getString("PersonName");
                PersonHp = rs.getString("PersonHp");
                PersonCompany = rs.getString("PersonCompany");
                vo = new PersonVo(PersonId, PersonName ,PersonHp,PersonCompany);
                isList = true;
            }
            if(isList == false) {
                vo = new PersonVo();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(pstmt);
            close(conn);
        }
        return vo;
    }
   
    public boolean personUpdate(int PersonId, String PersonName, String PersonHp, String PersonCompany) {
        try {
            connect();
            sql += " UPDATE person ";
            sql += " SET ";
            sql += " name = ?, ";
            sql += " hp = ?, ";
            sql += " company = ? ";
            sql += " WHERE person_id = ? ";
            pstmt = conn.prepareStatement(sql);
           
            pstmt.setString(1,PersonName);
            pstmt.setString(2, PersonHp);
            pstmt.setString(3, PersonCompany);
            pstmt.setInt(4, PersonId);
            int cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(conn);
        }
        return result;
    }
   
    public boolean personDelete(int PersodId) {
        try {
            connect();
            sql += " DELETE FROM person WHERE person_id = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, PersonId);
           
            int cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(conn);
        }
        return result;
    }
}

