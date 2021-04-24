package com.model;
import java.util.ListResourceBundle;
public class Dictionary_ta extends ListResourceBundle{
	Object obj[][]= {
			{"username","\r\n" +
					"பெயர்" },{"password","\r\n" +
							"கடவுச்சொல்"
							},
			{"sign in","\r\n" +
					"நுழைக\r\n" +
					""
			},{"register","\r\n" +
					"பதிவு"
			},{"customer","\r\n" +
					"வாடிக்கையாளர்"
			},
			{"management","\r\n" +
					"மேலாண்மை"
			},
			{"store-admin","\r\n" +
					"கடை நிர்வாகி"
			},
			{"select a role","\r\n" +
					"ஒரு பாத்திரத்தைத் தேர்ந்தெடுக்கவும்"
			}
	};
	@Override
	protected Object[][] getContents() {
		return obj;
	}
}