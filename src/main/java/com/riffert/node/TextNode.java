package com.riffert.node;

import java.util.List;

public class TextNode extends Node
{
		// TODO put in properties
		private static final String BG_COLOR = "#F9F9F9";
		
		private char c;
		
		private String text;
		private String equivalence;
		private String bgColor;
		
		public TextNode()
		{
			this("","10", BG_COLOR);
		}

		public TextNode(String text,String equivalence, String bgColor)
		{
			super();
			
			this.setC(',');
			this.setText(text);
			this.setEquivalence(equivalence);
			this.setBgColor(bgColor);
		}
	
		public TextNode(String text, String equivalence)
		{
			this(text,equivalence, BG_COLOR);
		}
		
		public TextNode getNode(int index)
		{
			return (TextNode)nodes.get(index);
		}
		
		public TextNode addChild(String text)
		{
			TextNode node = new TextNode(text,"10", "");
			return (TextNode)addChild(node);
		}
		
		public String getBegin()
		{
			if ( nodes.size() > 0 )
				return ",nodes:[";
			else
				return "";
		}

		public String getEnd()
		{
			if ( nodes.size() > 0 )
				return "]";
			else
				return "";
		}

		public void setC(char c) {
			this.c = c;
		}
		
		public char getC() {
			return c;
		}

		public void setText(String text) {
			this.text = text;
		}
		
		public String getText() {
			return text;
		}

		public String getEquivalence() {
			return equivalence;
		}

		public void setEquivalence(String equivalence) {
			this.equivalence = equivalence;
		}

		public void setBgColor(String bgColor) {
			this.bgColor = bgColor;
		}
		
		public String getBgColor() {
			return bgColor;
		}
		
}
