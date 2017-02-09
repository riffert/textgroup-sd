package com.riffert.bootstrap;

public class Nav
{
		private int id;
		private int displayId;
		private String style ;
		
		public Nav(int i,int current)
		{
			setId(i);
			setDisplayId(i+1);
			
			if ( i == current )
				setStyle("active");
			else
				setStyle("");
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}

		public int getDisplayId() {
			return displayId;
		}
		public void setDisplayId(int displayId) {
			this.displayId = displayId;
		}
		
		public String getStyle() {
			return style;
		}
		public void setStyle(String style) {
			this.style = style;
		}

}
