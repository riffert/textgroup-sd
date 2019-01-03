package com.riffert.textgroup.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.persistence.Query;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="groop")
public class Group implements Serializable
{
		public Group(String name,String alpha2, String userName)
		{
			this();
			setName(name);
			setAlpha2(alpha2);
			setUserName(userName);
		}
		
		public Group(String name, String alpha2)
		{
			this(name,alpha2, "user"+name);
		}
		
		public Group()
		{
			super();
		}

		public Group(long id)
		{
			this();
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

		private String name;	// alpha3B (& random(4) if already present) 
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}


		/*________________________________________________________________________*/
		
		
		private String alpha2;
		
		//@Column(name = "alpha2", nullable = false, length = 50)
		public String getAlpha2() {
			return this.alpha2;
		}

		public void setAlpha2(String alpha2) {
			this.alpha2 = alpha2;
		}
		
		/*________________________________________________________________________*/
		
		@Column(name="username")
		private String userName;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		/*________________________________________________________________________*/
		
		@Transient
		private String selected;
		
		public String getSelected() {
			return selected;
		}

		public void setSelected(String selected) {
			this.selected = selected;
		}
		
		/*________________________________________________________________________*/

		@ManyToOne(optional = true)
		@NotFound(action=NotFoundAction.IGNORE)
		private Domain domain;

		public Domain getDomain() {
			return domain;
		}

		public void setDomain(Domain domain) {
			this.domain = domain;
		}

		/*________________________________________________________________________*/
		
		@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY,mappedBy="group")
		private List<Text> texts = new ArrayList<Text>();

		public List<Text> getTexts() {
			return texts;
		}
		
	
		public List<Text> getTextsFrom1()
		{
			long nextEquivalenceId = domain.getNextEquivalenceId();
			
			List<Text> txts = new ArrayList<Text>();
			
			if (nextEquivalenceId > 1)
			{
					for (long i=0;i<=nextEquivalenceId;i++)
					{
						txts.add(new Text(""));
					}
			}
			
			
			for (Text txt:texts)
			{
				int n = txt.getEquivalence().getUserId().intValue();
				txts.set(n, txt); // TODO (long)
			}
			
			return txts;
		}		
		

		public void setTexts(List<Text> texts) {
			this.texts = texts;
		}
		
		public void add(Text text)
		{
			texts.add(text);
			text.setGroup(this);
			text.setDomain(domain);
		}

		
		/*________________________________________________________________________*/
		
}
