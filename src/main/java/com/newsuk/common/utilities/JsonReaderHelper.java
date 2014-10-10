package com.newsuk.common.utilities;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.jayway.jsonpath.JsonPath;

public class JsonReaderHelper {
	
	String json;
	
	public JsonReaderHelper(String json){
		this.json = json;
	}
	
	public int getIndexOfNodeWithValue(String jsonPathToList, String searchValue){
		List<String> jsonList = JsonPath.read(json, jsonPathToList);
		int index =-1;
		for(int i=0; i<jsonList.size(); i++){
			String decodedJsonValue = null;
			try {
				decodedJsonValue = java.net.URLDecoder.decode(jsonList.get(i), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(decodedJsonValue.equals(searchValue)){
				index = i;
				break;
			}

		}
		return index;
	}
	
	public String stripInvalidJson(){
		String result = json.replace("nuk.jsonpReadingPane([", "");
		result = result.replace("])", "");
		return result;
	}
}
