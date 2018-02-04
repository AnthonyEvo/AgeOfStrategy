package Aditional;

public class EventPool 
{
	Event[] poolOfEvents;
	Event[] poolPrevState;
	
	String PoolName;
	int cCount = 0;
	
	EventPool(String PoolName)
	{
		this.PoolName = PoolName;
		poolOfEvents = new Event[10];
		
		for(int i = 0; i < poolOfEvents.length; i++)
		{
			poolOfEvents[i] = new Event();
		}
	}
	
	public boolean isPoolStateChanged()
	{
		try
		{
			for(int i = 0; i < poolPrevState.length; i++)
			{
				if(poolPrevState[i].eventName != poolOfEvents[i].eventName && poolPrevState[i].flag != poolOfEvents[i].flag) return true;
			}
		}
		catch(NullPointerException Ex)
		{
			System.out.println(PoolName + ".iPSC.Value is not exist yet");
		}
		return false;
	}
	
	public boolean isEventStateChanged(String EventName)
	{
		try
		{
			for(int i = 0; i < poolPrevState.length; i++)
			{
				if(poolPrevState[i].eventName == EventName && poolPrevState[i].flag != poolOfEvents[i].flag) return true;
			}
		}
		catch(NullPointerException Ex)
		{
			System.out.println(PoolName + ".iESC.Value is not exist yet");
		}
		return false;
	}
	
	public void addEvent(String EventName)
	{
		poolOfEvents[checkEventPool()].setName(EventName);
	}
	
	public boolean removeEvent(String EventName)
	{
		for(int i = 0; i < poolOfEvents.length; i++)
		{
			if(poolOfEvents[i].eventName == EventName) 
			{
				poolOfEvents[i].flag = false;
				poolOfEvents[i].eventName = null;
				return true;
			}
		}
		return false;
	}
	
	public String[] getEvents()
	{
		int count = 0;
		String[] buf;
		for(int i = 0; i < poolOfEvents.length; i++)
		{
			if(poolOfEvents[i].eventName != null)
			{
				count++;
			}
		}
		
		buf = new String[count];
		
		for(int i = 0; i < poolOfEvents.length; i++)
		{
			if(poolOfEvents[i].eventName != null)
			{
				count--;
				buf[count] = poolOfEvents[i].eventName;
			}
		}
		
		return null;
	}
	
	public boolean setEventState(String EventName, boolean flag)
	{
		for(int i = 0; i < poolOfEvents.length; i++)
		{
			if(poolOfEvents[i].eventName == EventName) 
			{
				poolOfEvents[i].setFlag(flag);
				return true;
			}
		}
		return false;
	}
	
	public boolean getEventState(String EventName)
	{
		for(int i = 0; i < poolOfEvents.length; i++)
		{
			if(poolOfEvents[i].eventName == EventName) 
			{
				return poolOfEvents[i].flag;
			}
		}
		System.out.print("gPS");
		return false;
	}
	
	public boolean setEventValue(String EventName, int Value)
	{
		for(int i = 0; i < poolOfEvents.length; i++)
		{
			if(poolOfEvents[i].eventName == EventName) 
			{
				poolOfEvents[i].setValue(Value);
				return true;
			}
		}
		return false;
	}
	
	public int[] getEventValue(String EventName)
	{
		for(int i = 0; i < poolOfEvents.length; i++)
		{
			if(poolOfEvents[i].eventName == EventName) 
			{
				return poolOfEvents[i].value;
			}
		}
		System.out.print("gPV");
		return null;
	}
	
	private int checkEventPool()
	{
		int count = 0;
		for(int i = 0; i < 10; i++)
		{
			if(poolOfEvents[i].eventName != null)
			{
				count ++;
				if(count == poolOfEvents.length) expandPool();
			}
			else return i;
		}
		System.out.println("Not working");
		return 0;
	}
	
	private void expandPool()
	{
		Event[] bufPool = poolOfEvents;
		poolOfEvents = new Event[bufPool.length + 5];
		
		for(int i = 0; i < poolOfEvents.length; i++)
		{
			poolOfEvents[i] = new Event();
		}
		
		for(int i = 0; i < bufPool.length; i++) poolOfEvents[i] = bufPool[i];
	}
	
	private void contractPool()
	{
		int count = 0;
		
		for(int i = poolOfEvents.length - 6; i < poolOfEvents.length; i++)
		{
			if(poolOfEvents[i].eventName == null) count++;
		}
		
		if(count >= 5)
		{
			Event[] bufPool = poolOfEvents;
			poolOfEvents = new Event[bufPool.length - 5];
			for(int i = 0; i < poolOfEvents.length; i++)
			{
				poolOfEvents[i] = new Event();
			}
			for(int i = 0; i < (bufPool.length - 5); i++) poolOfEvents[i] = bufPool[i];
		}
	}
	
	public void endLoop()
	{
		poolPrevState = poolOfEvents;
		cCount++;
		if(cCount > 20) contractPool();
	}
}

class Event
{
	int[] value;
	boolean flag = false;
	String eventName;
	
	
	void setName(String Name)
	{
		eventName = Name;
	}
	
	void setFlag(boolean Flag)
	{
		flag = Flag;
	}
	
	void setValue(int Value)
	{
		value = new int[1];
		value[0] = Value;
	}
	
	void setValue(int Value[])
	{
		value = Value;
	}
}