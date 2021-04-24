package com.model;

import java.util.ListResourceBundle;

public class Dictionary_en extends ListResourceBundle{
	Object obj[][]= {
			{"username","\r\n" + 
					"Username" },{"password","\r\n" + 
							"Password" },{"select a role","\r\n" + 
							"Select a role" },{"sign in","\r\n" + 
							"Sign in" },{"register","\r\n" + 
							"Register" },{"customer","\r\n" + 
							"customer" },{"store-admin","\r\n" + 
							"store-admin" },{"management","\r\n" + 
							"management" },
	};
	@Override
	protected Object[][] getContents() {
		return obj;
	}
}
