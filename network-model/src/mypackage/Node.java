package mypackage;

public abstract class Node {
	private int duration,est,lst,eet,let,buffer;
	private String id;
	Node(int duration,String id)
	{
		this.duration=duration;
		this.id=id;
		this.est=-1;
		this.let=-1;
	}

	public String getId() {
		return id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getEst() {
		return est;
	}

	public void setEst(int est) {
		this.est = est;
		this.eet = this.est+this.duration; 
		
	}

	public int getLst() {
		return lst;
	}
	
	public int getEet() {
		return eet;
	}

	public int getLet() {
		return let;
	}

	public void setLet(int let) {
		this.let = let;
		this.lst=this.let-this.duration;
		calcBuffer();
	}
	public int getBuffer() {
		return this.buffer;
	}
	private void calcBuffer() {
		this.buffer=this.lst-this.est;
	}
}
