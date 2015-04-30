//package partc;

public class Admin extends User {
	private String id;
	private String password;
	private String name;
	private String email;
	Admin(String id,String password,String name,String email){
		super(id,password,name,email);
	}
	public void show_member(){
		System.out.println("Name   "+getName()+"  Email   "+getEmail());
	}


}
