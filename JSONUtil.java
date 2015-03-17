import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.JSONParser;


public class JSONUtil {
	
	public static String URLtoJSONString(String url)
	{
		URL tempos = null;
		try {
			tempos = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String jsonFile = "";
	    BufferedReader in;
	    
		try {
			in = new BufferedReader(new InputStreamReader(tempos.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null){
				jsonFile = jsonFile + inputLine + "\n";
				//System.out.println(inputLine);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonFile;

	}
	
	public static void updatePlayerStat(HashMap<Long, PlayerStat> givenMap, ArrayList<Long> givenPlayers, boolean radWin)
	{
		int start = 0;
		int end = 5;
		boolean weWin = false;
		if(getSide(givenPlayers))
		{
			weWin = radWin;
			start = 5;
			end = 10;
		}
		else
		{
			weWin = !radWin;
		}
		for(int i = start; i < end; i++)
		{
			Long accId = givenPlayers.get(i);
			updateMap(givenMap,accId,weWin);
		}
	}
	
	private static void updateMap(HashMap<Long, PlayerStat> givenMap, Long accId, boolean weWin) 
	{
		PlayerStat temp = givenMap.get(accId);
		if(temp == null)
		{
			PlayerStat addt = new PlayerStat(accId);
			if(weWin)
			{
				addt.incWinsAgainst();
			}
			else
			{
				addt.incLossAgainst();
			}
			givenMap.put(accId, addt);
		}
		else
		{
			if(weWin)
			{
				temp.incWinsAgainst();
			}
			else
			{
				temp.incLossAgainst();
			}
		}
		
	}

	private static boolean getSide( ArrayList<Long> givenPlayers ) // Returns true if on radiant side
	{
		for(int i = 0; i < givenPlayers.size(); i++)
		{
			if(givenPlayers.get(i) == 35202280)
			{
				if(i > 4)
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		
		return false;
	}

}
