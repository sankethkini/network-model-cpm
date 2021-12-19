package mypackage;

public class User {
	
	public static void main(String[] args) {
		NetworkModel md=new NetworkModel();
		md.addNode("a",6,"hardware selection");
		md.addNode("b",4,"system configuration");
		md.addNode("c", 3, "install hardware");
		md.addNode("d", 4, "data integration");
		md.addNode("e", 3, "draft office procedure");
		md.addNode("f", 10, "recruit staff");
		md.addNode("g", 3, "user training");
		md.addNode("h", 2, "install and test system");
		md.addDependency("a", "c");
		md.addDependency("b", "d");
		md.addDependency("b", "e");
		md.addDependency("e", "g");
		md.addDependency("f", "g");
		md.addDependency("c", "h");
		md.addDependency("d", "h");
		md.Start();
	}
}
