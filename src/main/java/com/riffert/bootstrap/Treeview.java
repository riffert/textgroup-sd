package com.riffert.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.riffert.node.Node;
import com.riffert.node.TextNode;

@Component
public class Treeview
{
		private List<Node> nodes ;

		public Treeview()
		{
			nodes = new ArrayList<Node>();
			//TextNode node1 = new TextNode("parent 1");
			//nodes.add(node1);
		}
		
//		public Treeview()
//		{
//			nodes = new ArrayList<Node>();
//			
//			TextNode node1 = new TextNode("parent 1");
//			
//			TextNode test = node1.addChild("child1").addChild("subchild 1").addChild("sub-sub child1");
//			test.addChild("one more time 1");
//			test.addChild("one more time 2");
//			
//			node1.getNode(0).addChild("hello");
//			
//			node1.addChild("child2");
//			
//			TextNode node2 = new TextNode("parent 2");
//			node2.addChild("child 2.1");
//			node2.addChild("child 2.2");
//			
//			nodes.add(node1);
//			nodes.add(node2);
//
//			validate(); // don't forget
//		}
		
		public void validate()
		{
			if ( nodes != null && nodes.size() > 0 )
			{
				if ( nodes.size() > 0 && nodes.get(0).getClass().getName().equals(TextNode.class.getName()) )
				{
					((TextNode)nodes.get(nodes.size()-1)).setC(' ');
					nodes.forEach(node->node.validate());
					
					//nodes.stream().forEach(node->node.validate());
				}
			}
		}		
		
		public Treeview(List<Node> nodes)
		{
			this();
			setNodes(nodes);
			validate(); // don't forget
		}
		
		public Node addNode(Node node)
		{
			nodes.add(node);
			return node;
		}
		
		public void setNodes(List<Node> nodes)
		{
			this.nodes = nodes;
			validate();
		}
		
		public List<Node> getNodes() {
			return nodes;
		}		
		
}
