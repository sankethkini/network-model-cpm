package mypackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
	private int start=0;
	private int end=0;
	private Map<Node,Integer> level;
	private Map<Node,List<Node>> reverse;
	private List<Node> nodes;
	private Map<String,Node> mapofnodes;
	private Map<Node,List<Node>> depends;
	
	Graph(List<Node> nodes,Map<String,Node> mapofnodes,Map<Node,List<Node>> depends){
		this.nodes=nodes;
		this.mapofnodes=mapofnodes;
		this.depends=depends;
		level=new HashMap<Node, Integer>();
	}
	
	public void Calculate()
	{
		Node a=new TaskNode(2,"a");
		Toposort(depends);
		//LablelLevels(sorted,depends);
		Forward();
		Map<Node,List<Node>> reverse=Reverse();
		Toposort(reverse);
		BackWard();
		
	}
	private void forwardpass(Node cur,Set<String> visited)
	{
		if(cur==null)
			return;
		
		List<Node> neigh=depends.get(cur);
		if(neigh==null)
			return;
		for(Node a:neigh)
		{
			a.setEst(Math.max(cur.getEet(),a.getEst()));
			this.end=Math.max(this.end,a.getEet());
			forwardpass(a, visited);
		}
	}
	
	public void Forward() {
		List<Node> firstlevel=new ArrayList<Node>();
		for(Node a:nodes) {
			Integer cur=level.getOrDefault(a,0);
			if(cur==0)
			{
				
				firstlevel.add(a);
				a.setEst(start);
				this.end=Math.max(this.end,a.getEet());
			}
		}
		for(int i=0;i<firstlevel.size();i++)
		{
			Set<String> visited=new HashSet<String>();
			forwardpass(firstlevel.get(i), visited);
		}
		
	}
	public Map<Node,List<Node>> Reverse() {
		this.reverse=new HashMap<Node, List<Node>>();
		for(Node a:nodes)
		{
			List<Node> neigh=depends.get(a);
			if(neigh==null)
				continue;
			for(Node b:neigh)
			{
				if(!reverse.containsKey(b))
				{
					List<Node> list=new ArrayList<Node>();
					list.add(a);
					reverse.put(b, list);
				}
				else
				{
					List<Node> cur=reverse.get(b);
					cur.add(a);
					reverse.put(b, cur);
				}
			}
		}
		return reverse;
	}
	
	private void backwardpass(Node cur,Set<String> visited) {
		
		if(cur==null)
			return;
		
		List<Node> neigh=reverse.get(cur);
		if(neigh==null)
			return;
		for(Node a:neigh)
		{
			a.setLet(Math.max(a.getLet(),cur.getLst()));
			backwardpass(a, visited);
		}
	}
	private void BackWard() {
		List<Node> firstlevel=new ArrayList<Node>();
		for(Node a:nodes) {
			Integer cur=level.getOrDefault(a,0);
			if(cur==0)
			{
				System.out.println(a.getId());
				firstlevel.add(a);
				a.setLet(end);
			}
		}
		for(int i=0;i<firstlevel.size();i++)
		{
			Set<String> visited=new HashSet<String>();
			backwardpass(firstlevel.get(i), visited);
		}
		
	}
	
	
	public void Toposort(Map<Node,List<Node>> map){
		level.clear();
		for(Node a:nodes)
		{
			List<Node> neigh=map.get(a);
			if(neigh==null)
				continue;
			for(Node n:neigh)
			{
				level.put(n,level.getOrDefault(n, 0)+1);
			}
		}
	}

}
