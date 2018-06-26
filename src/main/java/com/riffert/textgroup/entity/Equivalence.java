package com.riffert.textgroup.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="equivalence")
public class Equivalence
{
		public Equivalence()
		{
			super();
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
		
		@Column(name="user_id")
		private Long userId;
		
		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			System.out.println("** setUserId VALUE :"+userId);
			this.userId = userId;
		}
		
		/*________________________________________________________________________*/
		
		@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY,mappedBy="equivalence")
		private List<Text> texts = new ArrayList<Text>();

		public List<Text> getTexts() {
			return texts;
		}

		public void setTexts(List<Text> texts) {
			this.texts = texts;
		}
		
		public void add(Text text)
		{
			texts.add(text);
			text.setEquivalence(this);
		}
		
		
		/*________________________________________________________________________*/
		
		@ManyToOne
		private Domain domain;

		public Domain getDomain() {
			return domain;
		}

		public void setDomain(Domain domain) {
			this.domain = domain;
		}

		/*________________________________________________________________________*/
		
}
