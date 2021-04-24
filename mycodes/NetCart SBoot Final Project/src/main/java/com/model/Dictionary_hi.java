package com.model;
import java.util.ListResourceBundle;
public class Dictionary_hi extends ListResourceBundle{
	Object obj[][]= {
			{"username","\r\n" +
					"उपयोगकर्ता नाम" },{"password","\r\n" +
							"पारण शब्द" },
			{"select a role","\r\n" +
			"एक भूमिका का चयन करें" }
	,{"sign in","\r\n" +
	"साइन इन करें"
			}
	,{"register","\r\n" +
			"रजिस्टर करें"
	},
	{"customer","\r\n" +
			"ग्राहक"
	},
	{"management","\r\n" +
			"प्रबंध"
	},
	{"store-admin","\r\n" +
			"\r\n" +
			"स्टोर व्यवस्थापक"
	}
	}
	
	;
	@Override
	protected Object[][] getContents() {
		return obj;
	}
}