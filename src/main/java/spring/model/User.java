package spring.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User {
	
	private int id;
	private String username;
	private String password;
	private boolean enabled;
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	@Transient
	private List<String> userRoleString;

	public User() {
	}

	public User(int id,String username, String password, boolean enabled) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	public User(int id,String username, String password,
		boolean enabled, Set<UserRole> userRole) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.userRole = userRole;
	}

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="users_id_seq", initialValue=100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq-gen")
	@Column(name = "id", unique= true, nullable= false)
	public int getId(){
		return this.id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	@Column(name = "username", unique = true,
		nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password",
		nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled", nullable = false)
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRole> getUserRole() {
		return this.userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	public void setUserRoleString(List<String> userRoleString) {
		this.userRoleString = userRoleString;
	}
	
	@Transient
	public List<String> getUserRoleString() {
		return this.userRoleString;
	}

}
