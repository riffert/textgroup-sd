package com.riffert.node;

import java.util.List;

public class TextNode extends Node
{
		// TODO setting from properties
		private static final String BG_COLOR = "#F9F9F9";
		
		private char c;
		
		private String text;
		private String equivalence;
		private String userId;
		private String bgColor;
		private String deleteLink;
		
		public TextNode()
		{
			this("","10", "", "", BG_COLOR);
		}

		public TextNode(String text,String equivalence, String userId, String deleteLink, String bgColor)
		{
			super();
			
			this.setC(',');
			this.setText(text);
			this.setEquivalence(equivalence);
			this.setUserId(userId);
			this.setDeleteLink(deleteLink);
			this.setBgColor(bgColor);
		}
	
		public TextNode(String text, String equivalence, String userId, String deleteLink)
		{
			this(text,equivalence, userId, deleteLink, BG_COLOR);
		}
		
		public TextNode getNode(int index)
		{
			return (TextNode)nodes.get(index);
		}
		
		public TextNode addChild(String text)
		{
			TextNode node = new TextNode(text,"10", "", "", "");
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

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getDeleteLink() {
			return deleteLink;
		}

		public void setDeleteLink(String deleteLink) {
			this.deleteLink = deleteLink;
		}
		
}
