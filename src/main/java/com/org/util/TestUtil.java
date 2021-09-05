package com.org.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {

    //This is a utility to read the JSON responses
    public static JSONObject responseJSON;
    public static String getValueByJPath(JSONObject responseJSON, String jPath) {
       Object obj = responseJSON;
       for (String s : jPath.split("]"))
           if (!s.isEmpty())
               if (!(s.contains("[") || s.contains("]"))) //if required attribute is outside the array
                   obj = ((JSONObject) obj).get(s);
               else if (s.contains("[") || s.contains("]")) //if required attribute is inside the array
                   obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
       return obj.toString();
   }
}
