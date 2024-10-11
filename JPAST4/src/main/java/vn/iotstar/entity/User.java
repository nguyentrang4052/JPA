package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UserId")
	private int userid;
	
	@Column(name="Name", columnDefinition ="NVARCHAR(255) NULL")
	private String name;
	
	@Column(name="Username", columnDefinition ="NVARCHAR(255) NULL")
	private String username;
	
	@Column(name="Password", columnDefinition ="NVARCHAR(MAX) NULL")
	private String password;
	
	@Column(name = "Images", columnDefinition = "NVARCHAR(255) NULL")
	private String images;
	
	@Column(name="Active")
	private boolean active;
}
