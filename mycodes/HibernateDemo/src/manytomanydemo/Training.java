package manytomanydemo;


import java.time.LocalDate;
import java.util.Set;

public class Training {
public int tid;
public String tname;
public LocalDate tstartdate;
public Set<Student> students;
public Training() {
	// TODO Auto-generated constructor stub
}
public int getTid() {
	return tid;
}
public void setTid(int tid) {
	this.tid = tid;
}
public String getTname() {
	return tname;
}
public void setTname(String tname) {
	this.tname = tname;
}
public LocalDate getTstartdate() {
	return tstartdate;
}
public void setTstartdate(LocalDate tstartdate) {
	this.tstartdate = tstartdate;
}
public Set<Student> getStudents() {
	return students;
}
public void setStudents(Set<Student> students) {
	this.students = students;
}

}
