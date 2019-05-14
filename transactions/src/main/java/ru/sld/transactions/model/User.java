package ru.sld.transactions.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "USERS")
public class User {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue	
	private Integer id;	
	@Pattern(regexp = ".+@.+\\.[a-z]+" , message = "Пожалуйста, введите email")
	private String login;
	@Size(min = 4, message = "Пароль должен быть не менее 4 знаков")
	private String password;	
	private String name;	
	private boolean admin;
	@OneToMany(	fetch = FetchType.LAZY,
				targetEntity = Account.class,
				cascade = CascadeType.MERGE,
				mappedBy="owner")
    private List<Account> accounts = new ArrayList<Account>();
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
}
