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
@Table(name="pet_cad")
public class PetEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="pet_name", nullable = false, length=80)
	private String petName;
	@Column(name="pet_breed", nullable = false, length=50)
	private String petBreed;
	@Column(name="pet_kind", nullable =  false, length=50)
	private String petKind;
	@Column( nullable = false)
	private String gender;
	@Column(name="pet_owner", nullable = false, length=80)
	private String petOwner;
	@Column( nullable = false, length=100)
	private String address;
	@Column(name="phone_number")
	private String phoneNumber;
	@Column
	private String email;
	
	public PetEntity() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getPetBreed() {
		return petBreed;
	}
	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}
	public String getPetKind() {
		return petKind;
	}
	public void setPetKind(String petKind) {
		this.petKind = petKind;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPetOwner() {
		return petOwner;
	}
	public void setPetOwner(String petOwner) {
		this.petOwner = petOwner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public int hashCode() {
		return Objects.hash(address, email, gender, id, petBreed, petKind, petName, petOwner, phoneNumber);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PetEntity other = (PetEntity) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(gender, other.gender) && Objects.equals(id, other.id)
				&& Objects.equals(petBreed, other.petBreed) && Objects.equals(petKind, other.petKind)
				&& Objects.equals(petName, other.petName) && Objects.equals(petOwner, other.petOwner)
				&& Objects.equals(phoneNumber, other.phoneNumber);
	}
	public PetEntity(Long id, String petName, String petBreed, String petKind, String gender, String petOwner,
			String address, String phoneNumber, String email) {
		super();
		this.id = id;
		this.petName = petName;
		this.petBreed = petBreed;
		this.petKind = petKind;
		this.gender = gender;
		this.petOwner = petOwner;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	
}
