package tankGame;

public class MapLoader 
{
	
	int width;
	int height;
	int spawnPoint[][];
	
	MapLoader(String mapName)
	{
		
	}
	
	void loadMap()
	{
		spawnPoint = new int[2][2];
		width = 10000;
		height = 10000;
					
		spawnPoint[0][0] = 500;
		spawnPoint[0][1] = 500;
		
		spawnPoint[1][0] = 9500;
		spawnPoint[1][1] = 9500;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeigth()
	{
		return height;
	}
	
	public int[][] getSpawnPoints()
	{
		return spawnPoint;
	}
	
	class Map
	{
		
	}
}
