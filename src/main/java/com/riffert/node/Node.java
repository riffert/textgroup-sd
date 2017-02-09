package com.riffert.node;

import java.util.ArrayList;
import java.util.List;

public class Node
{
		public Node() {}
	
		protected List<Node> nodes = new ArrayList<Node>();

		public void setNodes(List<Node> nodes) {
			this.nodes = nodes;
		}
		
		public List<Node> getNodes() {
			return nodes;
		}
		
		public Node getNode(int index)
		{
			return nodes.get(index);
		}
		
		public Node addChild(Node node)
		{
			nodes.add(node);
			return this;
		}
		
		public void validate()
		{
			if ( nodes.size() > 0)
			{
				((TextNode)nodes.get(nodes.size()-1)).setC(' ');
				nodes.stream().forEach(node->node.validate());
			}
		}
}
