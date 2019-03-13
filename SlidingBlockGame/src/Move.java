import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Move {
	public Move() {
		
	}
	
	public void GetNextBestMove(Status currentStatus, Queue<Status> priorityQueue, 
			Map<Long, Integer>hash_to_num, long[][] zoberist) {
		int empty = currentStatus.GetEmpty();
		int currentStatusDv = currentStatus.GetDv();
		int increaseDv = 0;
		long zob1 = currentStatus.GetZoberist();
		long zob2;
		int[] s;
		
		for (int i = empty - 3; i <= empty + 3; i ++) {
			if (i >= 0 && i < Status.SIZE * 2 + 1 && i != empty) {
				increaseDv = this.CalculateDv(i, empty);
				
				if (currentStatus.GetStatus()[i] == 1) {
					zob2 = zob1^zoberist[1][i]^zoberist[1][empty];
				}else {
					zob2 = zob1^zoberist[0][i]^zoberist[0][empty];
				}
				
				s = Arrays.copyOf(currentStatus.GetStatus(), currentStatus.GetStatus().length);
				int temp = s[i];
				s[i] = s[empty];
				s[empty] = temp;
				
				Status nextStatus = new Status(s, currentStatusDv + increaseDv, currentStatus, empty - i, zob2);
				
				if (hash_to_num.get(nextStatus.GetZoberist()) == null || hash_to_num.get(nextStatus.GetZoberist()) > nextStatus.GetDv()) {
					hash_to_num.put(nextStatus.GetZoberist(), nextStatus.GetDv());
					priorityQueue.add(nextStatus);
				}	
			}
		}		
	}
	private int CalculateDv(int start, int end) {
		int dv = 0;
		if (Math.abs(start - end) == 1 || Math.abs(start - end) == 2)
			dv = 1;
		if (Math.abs(start - end) == 3)
			dv = 2;
		return dv;
	}
}

//List.add()函数用法：
// java.util.ArrayList.add(int index, E elemen) 方法将指定的元素E在此列表中的指定位置。
// 它改变了目前元素在该位置(如果有的话)和所有后续元素向右移动(将添加一个到其索引)。

//List.get()函数用法:
// java.util.ArrayList.get(int index) 方法会返回元素在此列表中的指定位置。

//Arrays.copyOf()方法返回的数组是新的数组对象，原数组对象仍是原数组对象，不变，该拷贝不会影响原来的数组。
// 所以代码等价于：
/*
 for (int i = 0; i < currentStatus.status.length; i ++){
 	newStatus[i] = currentStatus.status[i]; 		
 }
*/
