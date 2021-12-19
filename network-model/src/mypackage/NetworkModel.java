package mypackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class NetworkModel {
	
	private List<Node> nodes;
	private Map<String,Node> mapofnodes;
	private Map<Node,List<Node>> depends;
	private Graph graph;
	public NetworkModel() {
		nodes=new ArrayList<Node>();
		mapofnodes=new HashMap<String, Node>();
		depends=new HashMap<Node, List<Node>>();
	}
	public Node addNode(String id,int duration,String description)
	{
		TaskNode cur=new TaskNode(duration,id);
		cur.setDesc(description);
		nodes.add(cur);
		mapofnodes.put(id, cur);
		return cur;
	}
	public void addDependency(String id1,String id2)
	{
		Node node1=mapofnodes.get(id1);
		Node node2=mapofnodes.get(id2);
		if(!depends.containsKey(node1))
		{
			List<Node> list=new ArrayList<Node>();
			list.add(node2);
			depends.put(node1, list);
		}
		else
		{
			List<Node> cur=depends.get(node1);
			cur.add(node2);
			depends.put(node1, cur);
		}
	}
	public void Start() {
		graph=new Graph(nodes,mapofnodes,depends);
		graph.Calculate();
		PrintAll();
		PrintCritical();
	}
	
	public void PrintAll() {
		for(Node a:nodes)
		{
			TaskNode b=(TaskNode) a;
			System.out.println("\n\n\n");
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("|            ");
			System.out.print(a.getEst()+"           ");
			System.out.print("|            ");
			System.out.print(a.getDuration()+"           ");
			System.out.print("|             ");
			System.out.println(a.getEet()+"            |");
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("| ID: "+a.getId());
			System.out.println("    Desc:  "+b.getDesc());
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("|            ");
			System.out.print(a.getLst()+"           ");
			System.out.print("|            ");
			System.out.print(a.getBuffer()+"           ");
			System.out.print("|             ");
			System.out.println(a.getLet()+"            |");
			System.out.println("--------------------------------------------------------------------------------");
		}
	}
	
	
	public void PrintCritical() {
		System.out.println("\n\n");
		System.out.println("Critical nodes are");
		List<Node> crc=new ArrayList<Node>();
		for(Node a:nodes)
		{
			if(a.getBuffer()==0)
			crc.add(a);
		}
		for(Node c:crc)
		{
			System.out.println(c.getId());
		}
	}
}
