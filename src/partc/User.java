//package partc;

public class User {
	private String id;
	private String password;
	private String name;
	private String email;
	private double balance=0;

	private int type;

	User(String id,String password,String name,String email){
		this.setId(id);
		this.setPassword(password);
		this.setName(name);
		this.setEmail(email);
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance =this.balance+ balance;
	}

	public void show_my_balance(){
		System.out.println("Your current balance is "+balance);
	}



}
