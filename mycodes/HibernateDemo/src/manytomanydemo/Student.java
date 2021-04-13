package manytomanydemo;

import java.util.Set;

public class Student {
public int sid;
public String sname;
public int marks;
public Set<Training> trainings;
public Student() {
	// TODO Auto-generated constructor stub
}
public int getSid() {
	return sid;
}
public void setSid(int sid) {
	this.sid = sid;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public int getMarks() {
	return marks;
}
public void setMarks(int marks) {
	this.marks = marks;
}
public Set<Training> getTrainings() {
	return trainings;
}
public void setTrainings(Set<Training> trainings) {
	this.trainings = trainings;
}

}
