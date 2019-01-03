package com.riffert.textgroup.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="text")
public class Text implements Serializable
{
		private Text()
		{
			super();
		}

		public Text(String value)
		{
			this();
			setValue(value);
		}
		
		public Text(Long id,String value)
		{
			this();
			setValue(value);
			setId(id);
		}

	
		/*________________________________________________________________________*/

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
		/*________________________________________________________________________*/


		private String value;
		
		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}		
		
		/*________________________________________________________________________*/
		
		@ManyToOne
		private Group group;

		public Group getGroup() {
			return group;
		}

		public void setGroup(Group group) {
			this.group = group;
		}		

		/*________________________________________________________________________*/
		
		@ManyToOne
		private Equivalence equivalence;
		
		public Equivalence getEquivalence() {
			return equivalence;
		}

		public void setEquivalence(Equivalence equivalence) {
			this.equivalence = equivalence;
		}		

		/*________________________________________________________________________*/
		
		// quick search in all languages of same domain

		@ManyToOne
		private Domain domain;

		public Domain getDomain() {
			return domain;
		}

		public void setDomain(Domain domain) {
			this.domain = domain;
		}

}
