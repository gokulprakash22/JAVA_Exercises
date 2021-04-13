package annoinheritancedemo;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Car")
@DiscriminatorValue(value="Car")
public class Car extends Automobile implements Serializable{
	
	public String carfeatures;
	
	public String getCarfeatures() {
		return carfeatures;
	}
	public void setCarfeatures(String carfeatures) {
		this.carfeatures = carfeatures;
	}
	public String toString() {
		return "Car:"+super.toString();
	}
	public void handle() {
		new VisitorImpl().process(this);
	}
}
