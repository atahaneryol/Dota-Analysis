
public class PlayerStat {
	
	int winsAgainst;
	int lossAgainst;
	Long id;
	
	public PlayerStat(Long givenId)
	{
		id = givenId;
		winsAgainst = 0;
		lossAgainst = 0;
	}
	
	public int getWinsAgainst()
	{
		return winsAgainst;
	}
	
	public int getLossagainst()
	{
		return lossAgainst;
	}
	
	public void incWinsAgainst()
	{
		winsAgainst++;
	}
	
	public void incLossAgainst()
	{
		lossAgainst++;
	}
	
	public String toString()
	{
		String s = "ID: " + id + " Wins Against: " + winsAgainst + " Loss Against: " + lossAgainst;
		return s;
	}
	

}
