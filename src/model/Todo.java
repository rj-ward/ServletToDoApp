package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "username")
	private String username;
	@Column(name = "description")
	private String description;
	@Column(name = "is_done")
	private boolean status;

	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Todo(int id2, String title, String username, String description, boolean status) {
		super();
		this.id = id2;
		this.title = title;
		this.username = username;
		this.description = description;

		this.status = status;
	}

	public Todo(String title, String username, String description, boolean status) {
		super();
		this.title = title;
		this.username = username;
		this.description = description;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", username=" + username + ", description=" + description
				+ ", status=" + status + "]";
	}

}
