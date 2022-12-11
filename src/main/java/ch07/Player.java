package ch07;

import java.time.LocalDate;

public class Player {
	private int backNo;
	private String name;
	private String position;
	private LocalDate birthday;
	private int height;
	private int isDeleted;

	Player() {
	}

	public Player(int backNo, String name, String position, String birthday, int height) {
		super();
		this.backNo = backNo;
		this.name = name;
		this.position = position;
		this.birthday = LocalDate.parse(birthday);
		this.height = height;
	}
	
	public Player(int backNo, String name, String position, String birthday, int height, int isDeleted) {
		super();
		this.backNo = backNo;
		this.name = name;
		this.position = position;
		this.birthday = LocalDate.parse(birthday); // 생성자도 오버로딩 가능
		this.height = height;
		this.isDeleted = isDeleted;

	}

	public int getBackNo() {
		return backNo;
	}

	public void setBackNo(int backNo) {
		this.backNo = backNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "Player [backNo=" + backNo + ", name=" + name + ", position=" + position + ", birthday=" + birthday
				+ ", height=" + height + ", isDeleted=" + isDeleted + "]";
	}




}
