package com.cquptkiller.a_guarder.data;

public class PermNameList {
	private final static String Internate1 = "android.permission.CHANGE_NETWORK_STATE";
	private final static String Internate2 = "android.permission.CHANGE_WIFI_STATE";
	private final static String Internate3 = "android.permission.ACCESS_NETWORK_STATE";
	private final static String Internate4 = "android.permission.ACCESS_WIFI_STATE";
	private final static String Internate5 = "android.permission.INTERNET";
	private final static String Tel = "android.permission.CALL_PHONE";
	private final static String TextMsg = "android.permission.SEND_SMS";
	private final static String getLocationInfo1 = "android.permission.ACCESS_FINE_LOCATION";
	private final static String getLocationInfo2 = "android.permission. ACCESS_COARSE_LOCATION";
	private final static String getLocationInfo3 = "android.permission.INTERNET";
	private final static String getLocationInfo4 = "android.permission.ACCESS_COARSE _LOCATION";
	private final static String getContactsInfo = "android.permission.READ_CONTACTS";
	public static boolean equals(String PerName){
		if(PerName.equals(Internate1)==false&&PerName.equals(Tel)==false&&
		   PerName.equals(TextMsg)==false&&PerName.equals(getContactsInfo)==false&&
	       PerName.equals(getLocationInfo1)==false&&PerName.equals(Internate2)==false&&
	       PerName.equals(Internate3)==false&&PerName.equals(Internate4)==false&&
	       PerName.equals(Internate5)==false&&PerName.equals(getLocationInfo2)==false&&
	       PerName.equals(getLocationInfo3)==false&&PerName.equals(getLocationInfo4)==false){
			return false;
		}else {
			return true;
		}
	}
}
