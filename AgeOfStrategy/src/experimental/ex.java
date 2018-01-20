package experimental;

import java.util.ArrayList;

public class ex 
{
	public ex()
	{	
		ArrayList<Integer> arr1 = new ArrayList<Integer>();
		ArrayList<Integer> arr2 = new ArrayList<Integer>();
		
		arr1.ensureCapacity(3);
		arr1.add(0, 8);
		arr1.add(1, 5);
		arr1.add(2, 3);
		
		arr2.ensureCapacity(3);
		arr2.add(0, 9);
		arr2.add(1, 4);
		arr2.add(2, 1);
		
		merge(arr1, arr2);
	
	}
	
	void merge(ArrayList<Integer> list1, ArrayList<Integer> list2)
	{
		ArrayList<Integer> _list1 = list1;
		ArrayList<Integer> _list2 = list2;
		ArrayList<Integer> _mergedArrayList = new ArrayList<Integer>();
		int mSize = _list1.size() + _list2.size();
		
		_mergedArrayList.ensureCapacity(mSize);
		
		for(int l1Size = 0, l2Size = 0, count = 0; count <= mSize; count++)
		{
			if(_list1.get(l1Size) > _list2.get(l2Size) && l1Size < _list1.size() - 1)
			{
				//System.out.print("������ ������ �������");
				_mergedArrayList.add(count, _list1.get(l1Size));
				if(l1Size < _list1.size() - 1) l1Size++;
			}
			else if(_list1.get(l1Size) < _list2.get(l2Size) && l2Size < _list2.size()  - 1)
			{
				//System.out.println("������ ������ �������");
				_mergedArrayList.add(count, _list2.get(l2Size));
				if(l2Size < _list2.size() - 1) l2Size++;
			}
			else if(l1Size == _list1.size() - 1 && l2Size < _list2.size())
			{
				//System.out.println("������ �� ��������");
				_mergedArrayList.add(count, _list2.get(l2Size));
				l2Size++;
			}
			else if(l2Size == _list2.size() - 1 && l1Size < _list1.size())
			{
				//System.out.print("������ �� ��������");
				_mergedArrayList.add(count, _list1.get(l1Size));
				l1Size++;
			}
			
			System.out.println(_mergedArrayList.get(count));
		}
		
		list1 = _mergedArrayList;
	}
}

