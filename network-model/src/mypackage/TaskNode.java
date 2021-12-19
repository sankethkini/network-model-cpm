package mypackage;

public class TaskNode extends Node{

	private String desc;
	
	TaskNode(int duration, String id) {
		super(duration, id);
	}

	public String getDesc() {
		return desc;
	}

	public Node setDesc(String desc) {
		this.desc = desc;
		return this;
	}
	
}
