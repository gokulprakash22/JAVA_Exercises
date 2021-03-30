package model;

import java.util.ListResourceBundle;

public class Dictionary_te extends ListResourceBundle{
	Object obj[][]= {
			{"email","ఇమెయిల్" },
			{"password","పాస్వర్డ్" },
			{"name","పేరు"},
			{"address","ఇంటి చిరునామ"},
			{"phone","ఫోను నంబరు"}
	};
	@Override
	protected Object[][] getContents() {
		return obj;
	}
}