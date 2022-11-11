package satang.vo;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.*;

public class MemberVO {
	private int mno, ano, cnt;
	private String name, id, pw, mail, birth, gen, sname, oname, sdate;
	private Date jdate;
	private Time jtime;
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getOname() {
		return oname;
	}
	public void setOname(String oname) {
		this.oname = oname;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm:ss");
		sdate = form1.format(jdate) + form2.format(jtime);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public Date getJdate() {
		return jdate;
	}
	public void setJdate(Date jdate) {
		this.jdate = jdate;
	}
	public Time getJtime() {
		return jtime;
	}
	public void setJtime(Time jtime) {
		this.jtime = jtime;
		setSdate();
	}
	@Override
	public String toString() {
		return "{ mno=" + mno + ", ano=" + ano + ", cnt=" + cnt + ", name=" + name + ", id=" + id + ", pw=" + pw
				+ ", mail=" + mail + ", birth=" + birth + ", gen=" + gen + ", sname=" + sname + ", oname=" + oname
				+ ", sdate=" + sdate + ", jdate=" + jdate + ", jtime=" + jtime + "}";
	}
	
}
