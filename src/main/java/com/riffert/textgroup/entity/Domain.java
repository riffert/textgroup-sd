package com.riffert.textgroup.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="domain")
public class Domain implements Serializable
{
		public Domain(String name)
		{
			this();
			setName(name);
			setNextEquivalenceId((long)1);
		}
		
		private Domain()
		{
			super();
		}
	
		/*________________________________________________________________________*/
		
		private Long nextEquivalenceId;
		
		public Long getNextEquivalenceId() {
			return nextEquivalenceId;
		}

		public void setNextEquivalenceId(Long nextEquivalenceId) {
			this.nextEquivalenceId = nextEquivalenceId;
		}
		
		/*________________________________________________________________________*/
		
		public void incrementNextEquivalenceId()
		{
			nextEquivalenceId++;
			
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
		

		private String name;
		
		public String getName() {
			return name;
		}
	
		public void setName(String name) {
			this.name = name;
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
		

		@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER,mappedBy="domain")
		private List<Group> groups = new ArrayList<Group>();

		public List<Group> getGroups() {
			return groups;
		}

		public void setGroups(List<Group> groups) {
			this.groups = groups;
		}

		public void add(Group group)
		{
			groups.add(group);
			group.setDomain(this);
		}
		
		// TODO replace with java 8 version
		public Group getGroupByName(String groupName)
		{
			System.out.println("searched groupName : "+groupName);
			
			for (Group group:groups)
				if ( group.getName().equals(groupName) )
					return group;
			
			return null;
		}
		
		// java 8 version :
		public Group getGroupByName2(String groupName)
		{
			System.out.println("searched groupName Java8 : "+groupName);
			return (Group) groups.stream().filter(group->group.getName().equals(groupName));
		}

		/*________________________________________________________________________*/
		
		
		@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY,mappedBy="domain")
		private List<Equivalence> equivalences = new ArrayList<Equivalence>();

		public List<Equivalence> getEquivalences() {
			return equivalences;
		}

		public void setEquivalences(List<Equivalence> equivalences) {
			this.equivalences = equivalences;
		}
		
		public void add(Equivalence equivalence)
		{
			equivalences.add(equivalence);
			equivalence.setDomain(this);
		}


		
		/*________________________________________________________________________*/

}
