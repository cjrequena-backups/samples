package com.sample.model.jpa;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the `RIGHT` database table.
 * 
 */
@Entity
@Table(name="RIGHT")
@NamedQueries({
	@NamedQuery(name="RIGHT.findAll", query="SELECT r FROM Right r")
})
public class Right implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_right", unique=true, nullable=false)
	private int idRight;

	@Column(name="date_created", nullable=false)
	private Timestamp dateCreated;

	@Column(name="date_updated")
	private Timestamp dateUpdated;

	@Column(name="right_name", nullable=false, length=45)
	private String rightName;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="rights")
	private List<Role> roles;

	public Right() {
	}

	public int getIdRight() {
		return this.idRight;
	}

	public void setIdRight(int idRight) {
		this.idRight = idRight;
	}

	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Timestamp getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Timestamp dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getRightName() {
		return this.rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}