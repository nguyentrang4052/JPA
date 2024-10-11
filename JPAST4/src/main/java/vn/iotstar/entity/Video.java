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
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="VideoId")
	private int videoid;
	
	@Column(name="Title", columnDefinition ="NVARCHAR(255) NULL")
	@NotEmpty(message = "Khong duoc phep rong")
	private String title;
	
	@Column(name="Poster", columnDefinition ="NVARCHAR(255) NULL")
	private String poster;
	
	@Column(name="Description", columnDefinition ="NVARCHAR(MAX) NULL")
	private String description;
	
	@Column(name="Views")
	private int views;
	
	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CategoryId")
	
	private Category categories;
	
	public void setCategories(Category categories) {
		this.categories = categories;
	}
	@Column(name="Active")
	private boolean active;

}
