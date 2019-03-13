import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;


public class MainFunction {
	
	private Comparator<Status> statusComparator = new Comparator<Status>() {
		public int compare(Status s1, Status s2) {
			return (int)((s1.GetDv() + s1.GetEv()) - (s2.GetDv() + s2.GetEv()));
		}
	};
	
	public Status InitialStatus(long[][] zoberist) {
		Status firstStatus = new Status();
		
		int[] s = new int[Status.SIZE * 2 + 1];
		
		for (int i = 0; i < Status.SIZE; i ++) {
			s[i] = 1;
		}
		for (int i = Status.SIZE; i < 2*Status.SIZE; i ++) {
			s[i] = -1;
		}
		s[2 * Status.SIZE] = 0;
		
		long zob = 0;
		for (int i = 0; i < 2 * Status.SIZE + 1; i ++) {
			if (s[i] == 1) {
				zob = zob^zoberist[1][i];
			}
			if (s[i] == -1) {
				zob = zob^zoberist[0][i]; 
			}
		}
		
		firstStatus = new Status(s, 0, null, 0, zob);
		
		return firstStatus;
	}

	public static void main(String[] args) {
		System.out.println("\t\tWelcome to the Silding Block Game!\n");
		while (true) {
			Scanner in = new Scanner(System.in);
			System.out.println("1.Requirement_1\t\t2.Requirement_2\t\t3.Requirement_3\t\t4.SBG\t\t5.Exit");
			System.out.print("Please Choose the Function you need: ");
			int ChooseFunc;
			ChooseFunc = in.nextInt();
			if (ChooseFunc == 1) {
				R1();
			}else if (ChooseFunc == 2) {
				R2();
			}else if (ChooseFunc == 3) {
				R3();
			}else if (ChooseFunc == 4) {
				SBG();
			}else if (ChooseFunc == 5) {
				break;
			}else {
				System.out.println("Input Error!");
			}
		}	
		System.out.println("\n\t\tThe Game is ending, thanks for your palying!");
	}
	
	static public void R1() {
		System.out.print("Please Input the Block number: ");
		Scanner in = new Scanner(System.in);
		int N;
		N = in.nextInt();
		Status.SIZE = N;
		System.out.print("Please Input the Initial Status: ");
		int[] InitialStatus = new int[N * 2 + 1];
		for (int i = 0; i < N * 2 + 1; i ++) {
			InitialStatus[i] = in.nextInt();
		}
		
		Status currentStatus = new Status(InitialStatus, 0);
		int empty = currentStatus.GetEmpty();
		int currentStatusDv = currentStatus.GetDv();
		int increaseDv = 0;
		int[] s;
		
		System.out.println("Next Status\t\tDissipative Value\t\tEvaluation Value");
		
		for (int i = empty - 3; i <= empty + 3; i ++) {
			if (i >= 0 && i < Status.SIZE * 2 + 1 && i != empty) {
				increaseDv = (new Move()).CalculateDv(i, empty);
				
				s = Arrays.copyOf(currentStatus.GetStatus(), currentStatus.GetStatus().length);
				int temp = s[i];
				s[i] = s[empty];
				s[empty] = temp;
				
				Status nextStatus = new Status(s, currentStatusDv + increaseDv);
				nextStatus.PrintStatus();
			}
		}	
		System.out.println("\n");
	}
	
	static public void R2() {
		System.out.print("Please Input the Block number: ");
		Scanner in = new Scanner(System.in);
		int N;
		N = in.nextInt();
		Status.SIZE = N;
		System.out.print("Please Input the Initial Status: ");
		int[] InitialStatus = new int[N * 2 + 1];
		for (int i = 0; i < N * 2 + 1; i ++) {
			InitialStatus[i] = in.nextInt();
		}
		Status currentStatus = new Status(InitialStatus, 0);
		System.out.println("Next Status\t\tDissipative Value\t\tEvaluation Value");
		currentStatus.PrintStatus();	
		System.out.println("\n");
	}
	
	static public void R3() {
		System.out.print("Please Input the Block number: ");
		Scanner in = new Scanner(System.in);
		int N;
		N = in.nextInt();
		Status.SIZE = N;
		System.out.print("Please Input the Initial Status: ");
		int[] InitialStatus = new int[N * 2 + 1];
		for (int i = 0; i < N * 2 + 1; i ++) {
			InitialStatus[i] = in.nextInt();
		}
		
		Status currentStatus = new Status(InitialStatus, 0);
		int empty = currentStatus.GetEmpty();
		int currentStatusDv = currentStatus.GetDv();
		int increaseDv = 0;
		int[] s;
		
		System.out.println("Next Status\t\tDissipative Value\t\tEvaluation Value");
		
		int MAX = (N + 1) / 2 + 1;
		for (int i = empty - MAX; i <= empty + MAX; i ++) {
			if (i >= 0 && i < Status.SIZE * 2 + 1 && i != empty) {
				increaseDv = (new Move()).CalculateDv(i, empty);
				
				s = Arrays.copyOf(currentStatus.GetStatus(), currentStatus.GetStatus().length);
				int temp = s[i];
				s[i] = s[empty];
				s[empty] = temp;
				
				Status nextStatus = new Status(s, currentStatusDv + increaseDv);
				nextStatus.PrintStatus();
			}
		}	
		System.out.println("\n");
	}
	
	static public void SBG() {
		MainFunction mf = new MainFunction();
		Scanner in = new Scanner(System.in);
		System.out.print("Please Input the Block Number n: ");
		int N;
		N = in.nextInt();
		
		if (N < 2) {
			System.out.println("The n is illagal!\n");
			return;
		}
		Status.SIZE = N;
		
		long[][] zoberist = new long[2][2 * Status.SIZE + 1];
		Random random = new Random();
		
		for (int i = 0; i < 2; i ++) {
			for (int j = 0; j < 2 * Status.SIZE + 1; j ++) {
				zoberist[i][j] = random.nextLong();
			}
		}
		Status firstStatus = mf.InitialStatus(zoberist);
		if (firstStatus == null) {
			System.out.println("Some Error Occurred!");
			return;
		}
		
		Queue<Status>priorityQueue = new PriorityQueue<Status>(mf.statusComparator);
		 
		Move move = new Move();
		Map<Long, Integer> hash_to_num = new HashMap<Long, Integer>();
		priorityQueue.add(firstStatus);
		hash_to_num.put(firstStatus.GetZoberist(), firstStatus.GetDv());
		
		long startTime = System.currentTimeMillis();
		
		Status currentStatus;
		
		do {
			currentStatus = priorityQueue.poll();
			// poll是队列数据结构实现类的方法，从队首获取元素，同时获取的这个元素将从原队列删除； 
			// pop是栈结构的实现类的方法，表示返回栈顶的元素，同时该元素从栈中删除，当栈中没有元素时，调用该方法会发生异常
			move.GetNextBestMove(currentStatus, priorityQueue, hash_to_num, zoberist);
		}while(currentStatus.GetEv() != 0/*||currentStatus.GetStatus()[2 * Status.SIZE] != 0*/);
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("The Following are the steps:\n");
		System.out.println("Current Status\t\tDissipative Value\t\tEvaluation Value");
		Status ss = currentStatus;
		int Counter = 0;
		List<Status> statusRecord = new ArrayList<Status>();
		while (ss != null) {
			statusRecord.add(Counter, ss);
			Counter ++;
			ss = ss.GetFather();
		}
		for (int i = Counter - 1; i >= 0; i --) {
			Status temp = statusRecord.get(i);
			temp.PrintStatus();
		}
		System.out.println("Time Consuming：" + (endTime - startTime) + "ms");
		System.out.println("Number of Moves："+ Counter + "\n\n");
	}
}

/*
 *  Coder: 16020031075 WangGang
 *  Reference: https://blog.csdn.net/qq_40527427/article/details/80078682
 *  Date: 2019.3.12
 *  Time complexity: O(n*n*6^n) or O(n*6^n)
 */