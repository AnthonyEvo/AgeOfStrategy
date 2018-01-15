package gameSourceLib;

public class WorldLoader 
{
	public static World world;
	
	public WorldLoader(int worldSeed)
	{
		world = createNewWorld(worldSeed, "New_world");
	}
	
	public WorldLoader(SavedWorld world)
	{
		
	}
	
	private World createNewWorld(int worldSeed, String worldName)
	{
		/*
		if(worldSeed <= 9999999)
			
		{
			worldSeed += 10000000;
		}
		else if(worldSeed > 99999999)
		{
			worldSeed = (worldSeed % 10000000) + 10000000;
		}
		
		double seedSqrt = Math.sqrt(worldSeed);
		int width = (int) seedSqrt + (worldSeed % 10);
		int heigth = (int) seedSqrt;
		
		System.out.println(worldSeed);
		System.out.println(Math.sqrt(worldSeed));
		
		return new World(worldName, "Acid-desert", width, heigth);
		*/
		
		return new World(worldName, "Acid-desert", 9, 9);
	}
}
