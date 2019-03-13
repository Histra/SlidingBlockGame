
public class Status {
	//public static int SIZE = 3;
	public static int SIZE;
	
	private int status[] = new int[SIZE * 2 + 1];
	
	private int dv; // 累计路径耗散值(Dissipative value)
	private int ev; // 评价函数(Evaluation function value)
	
	private Status father;
	private int direction;
	private long zoberist;
	private int empty;
	
	public Status() {
		
	}
	
	public Status(int[] s, int d) {
		this.SetCurrentStatus(s);
		this.SetDissipativeValue(d);
		this.SetEvalutionFunction();
		this.SetEmpty();
	}
	
	public Status(int[] s, int d, Status father, int direction, long zoberist) {
		if (s.length != SIZE * 2 + 1) {
			System.out.println("Please input the RIGHT data!");
		}else {
			this.SetCurrentStatus(s);
			this.SetDissipativeValue(d);
			this.SetEvalutionFunction();
			this.SetDirection(direction);
			this.SetFather(father);
			this.SetZoberist(zoberist);
			this.SetEmpty();
		}
	}
	
	public int[] GetStatus() {
		return this.status;
	}
	
	public int GetDv() {
		return this.dv;
	}
	
	public int GetEv() {
		return this.ev;
	}
	
	public Status GetFather() {
		return this.father;
	}
	
	public int GetDirection() {
		return this.direction;
	}
	
	public long GetZoberist() {
		return this.zoberist;
	}
	
	public int GetEmpty() {
		return this.empty;
	}
	
	public void SetCurrentStatus(int s[]) {
		this.status = s;
	}
	
	public void SetDissipativeValue(int d) {
		this.dv += d;
	}
	
	public void SetEvalutionFunction() {// Black 1; White -1; Space 0;
		/*this.ev = 0;
		for (int i = 0; i < this.status.length; i ++) {
			if (this.status[i] == 1) {
				for (int j = i + 1; j < this.status.length; j ++) {
					if (this.status[j] == -1) {
						this.ev ++;
					}
				}
			}
		}*/
		//降维写法
		int presum = 0;
		this.ev = 0;
		for (int i = 0; i < this.status.length; i ++){
			if(this.status[i] == 1)
				presum ++;
			if(this.status[i] == -1)
				this.ev += presum;
		}
	}// ev = 每块黑块之后白块的数量和
	
	public void SetFather(Status father) {
		this.father = father;
	}
	
	public void SetDirection(int direction) {
		this.direction = direction;
	}
	
	public void SetZoberist(long zoberist) {
		this.zoberist = zoberist;
	}
	
	public void SetEmpty() {
		for (int i = 0; i < 2 * SIZE + 1; i ++) {
			if (this.status[i] == 0) {
				this.empty = i;
				break;
			}
		}
	}
	
	public void PrintStatus() {// private : the Class MainFunction cannot use it.
		for (int i = 0; i < this.status.length; i ++) {
			if (this.status[i] == 1) {
				System.out.print("[黑]");
			}else if (this.status[i] == -1) {
				System.out.print("[白]");
			}else {
				System.out.print("[空]");
			}
		}
		
		System.out.print("\t" + this.dv);
		System.out.println("\t\t\t" + this.ev);
	}
	
}

