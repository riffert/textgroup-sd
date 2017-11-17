package com.riffert.option;

public class FreeId
{
		private Long value;
		
		public FreeId()
		{
			setSelected("");
		}
		
		public FreeId(Long value)
		{
			this();
			this.value = value;
		}

		public Long getValue() {
			return value;
		}

		public void setValue(Long value) {
			this.value = value;
		}
		
		/*______________________________________*/
		
		private String selected;
		
		public String getSelected() {
			return selected;
		}

		public void setSelected(String selected) {
			this.selected = selected;
		}

}
