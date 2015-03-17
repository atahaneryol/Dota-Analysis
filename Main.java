import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main {

	public static void main(String[] args) throws IOException {
		
		//Example api call: 
		//https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key=<key>&account_id=XXXXX
		//Api key: 76B0087D72B1F2A5ED4EA292757BEA68
		//Steam Id: 35202280
		//============================
		//Match details:
		//https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?key=<key>&match_id=XXXXX
		//Api key: 76B0087D72B1F2A5ED4EA292757BEA68
		//Steam Id: 35202280
		
		JSONParser matchParser = new JSONParser();
		
		HashMap<Long, PlayerStat> playerInfo = new HashMap<Long, PlayerStat>();
		
		
		String urlString = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key=76B0087D72B1F2A5ED4EA292757BEA68&account_id=35202280&hero_id=1";
		String jsonFile = JSONUtil.URLtoJSONString(urlString);
		
		try {
			JSONObject parsedMatches =(JSONObject) matchParser.parse(jsonFile); 
			JSONArray matchlist = (JSONArray) ((JSONObject) parsedMatches.get("result")).get("matches");
			Iterator mt = matchlist.iterator();
			while (mt.hasNext()) //Iterates through each match for a given hero
			{
				JSONObject singleMatch = (JSONObject)mt.next();
				Long singleMatchID = (Long) singleMatch.get("match_id");
				System.out.println( singleMatchID );
				SingleMatchParser prs = new SingleMatchParser( singleMatch );
				JSONUtil.updatePlayerStat(playerInfo, prs.getPlayerList(),prs.getRadWin()); //Gets all the players including original player
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(playerInfo);
		//System.out.println(jsonFile);
	}

}
