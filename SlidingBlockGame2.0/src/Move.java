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
	public int CalculateDv(int start, int end) {
		int dv = 0;
		if (Math.abs(start - end) == 1 || Math.abs(start - end) == 2)
			dv = 1;
		if (Math.abs(start - end) == 3)
			dv = 2;
		return dv;
	}
}

//List.add()鍑芥暟鐢ㄦ硶锛�
// java.util.ArrayList.add(int index, E elemen)聽鏂规硶灏嗘寚瀹氱殑鍏冪礌E鍦ㄦ鍒楄〃涓殑鎸囧畾浣嶇疆銆�
// 瀹冩敼鍙樹簡鐩墠鍏冪礌鍦ㄨ浣嶇疆(濡傛灉鏈夌殑璇�)鍜屾墍鏈夊悗缁厓绱犲悜鍙崇Щ鍔�(灏嗘坊鍔犱竴涓埌鍏剁储寮�)銆�

//List.get()鍑芥暟鐢ㄦ硶:
// java.util.ArrayList.get(int index)聽鏂规硶浼氳繑鍥炲厓绱犲湪姝ゅ垪琛ㄤ腑鐨勬寚瀹氫綅缃��

//Arrays.copyOf()鏂规硶杩斿洖鐨勬暟缁勬槸鏂扮殑鏁扮粍瀵硅薄锛屽師鏁扮粍瀵硅薄浠嶆槸鍘熸暟缁勫璞★紝涓嶅彉锛岃鎷疯礉涓嶄細褰卞搷鍘熸潵鐨勬暟缁勩��
// 鎵�浠ヤ唬鐮佺瓑浠蜂簬锛�
/*
 for (int i = 0; i < currentStatus.status.length; i ++){
 	newStatus[i] = currentStatus.status[i]; 		
 }
*/
