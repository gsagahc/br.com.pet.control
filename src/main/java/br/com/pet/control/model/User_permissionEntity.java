package br.com.pet.control.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_permission")
public class User_permissionEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id_user")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="id_permission")
	private int idpermission;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getIdpermission() {
		return idpermission;
	}
	public void setIdpermission(int idpermission) {
		this.idpermission = idpermission;
	}
	public User_permissionEntity(Long id, int idpermission) {
		super();
		this.id = id;
		this.idpermission = idpermission;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, idpermission);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User_permissionEntity other = (User_permissionEntity) obj;
		return Objects.equals(id, other.id) && idpermission == other.idpermission;
	}

}
