package model;

import java.util.ListResourceBundle;

public class Dictionary_ta extends ListResourceBundle{
	Object obj[][]= {
			{"email","மின்னஞ்சல் முகவரி" },
			{"password","கடவுச்சொல்" },
			{"name","பெயர்"},
			{"address","வீட்டு முகவரி"},
			{"phone","தொலைபேசி எண்"}
	};
	@Override
	protected Object[][] getContents() {
		return obj;
	}
}