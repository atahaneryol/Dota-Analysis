import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SingleMatchParser {
	
	private JSONObject singleMatch;
	Long matchId;
	Boolean radWin;
	int myPosition;
	
	public SingleMatchParser(JSONObject givenMatch)
	{
		//This is created for each hero or later can be used as factory design
		singleMatch = givenMatch;
		matchId = (Long)singleMatch.get("match_id");
		radWin = matchWon();
		myPosition = -1;
	}
	
	public ArrayList<Long> getPlayerList()
	{
		ArrayList<Long> list = new ArrayList<Long>(); 
		
		JSONArray players = ( (JSONArray)(singleMatch.get("players")) );
		Iterator tmp = players.iterator();
		int pos = 0;
		while (tmp.hasNext()) {
			//list.add( (Long)tmp.next() );
			JSONObject singlePlayer = (JSONObject)tmp.next();
			Long accId = (Long) singlePlayer.get("account_id");
			//System.out.println("Account Id: " + singlePlayer.get("account_id"));
			list.add(accId);
//			if(accId == 35202280)
//			{
//				System.out.println("Found me! at position: " + pos);
//				myPosition = pos;
//			}
//			pos++;
		}
		return list;
	}
	
	private boolean matchWon()
	{
		//https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=76B0087D72B1F2A5ED4EA292757BEA68&match_id=572935238
		JSONParser parser = new JSONParser();
		String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=76B0087D72B1F2A5ED4EA292757BEA68&match_id=";
		url = url + "" + matchId;
		
	    String jsonString = JSONUtil.URLtoJSONString(url); 
		try {
			JSONObject matchDetail = (JSONObject) parser.parse(jsonString);
			Boolean res = (Boolean)(((JSONObject)matchDetail.get("result")).get("radiant_win"));
			return res;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
		return false;
		
	}

	public boolean getRadWin()
	{
		return radWin;
	}
	
	public int getMyPosition()
	{
		return myPosition;
	}
	
}
