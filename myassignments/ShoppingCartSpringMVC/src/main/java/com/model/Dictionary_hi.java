package com.model;

import java.util.ListResourceBundle;

public class Dictionary_hi extends ListResourceBundle{
	Object obj[][]= {
			{"email","ईमेल" },
			{"password","पारण शब्द" },
			{"name","नाम"},
			{"address","पता"},
			{"phone","फ़ोन नंबर"}
	};
	@Override
	protected Object[][] getContents() {
		return obj;
	}
}