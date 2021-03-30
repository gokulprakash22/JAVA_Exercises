package model;

import java.util.ListResourceBundle;

public class Dictionary_en extends ListResourceBundle{
	Object obj[][]= {
			{"email","Email Address"},
			{"password","Password"},
			{"name","Name"},
			{"address","Address"},
			{"phone","Phone No"}
	};
	@Override
	protected Object[][] getContents() {
		return obj;
	}
}