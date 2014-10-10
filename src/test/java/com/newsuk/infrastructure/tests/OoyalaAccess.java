package com.newsuk.infrastructure.tests;

import java.util.HashMap;
import org.junit.Test;
import com.newsuk.common.utilities.OoyalaAPI;

public class OoyalaAccess {
	private OoyalaAPI api = new OoyalaAPI("lmd2UxOh-U7-4A4qifM8rIwCEcRA.idpt0",
			"rWoRf_AuwGZj_ZQJgpUW110Z-Mw7cOxiZY7cjfMP");

	public void getMetaData(String assetId) {

		HashMap<String, String> parameters = new HashMap<String, String>();
		String apiString = "assets/" + assetId + "/metadata";
		try {
			System.out.println(api.getRequest(apiString, parameters));
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void putMetaData(String assetId, HashMap<String, Object> parameter) {

		String apiString = "assets/" + assetId + "/metadata";
		try {
			System.out.println(api.putRequest(apiString, parameter));

		} catch (Exception e) {

			e.printStackTrace();
		}
	}


}
