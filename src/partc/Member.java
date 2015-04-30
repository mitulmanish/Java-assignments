//package partc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Member extends User {
	private int login_count=0;

	private ArrayList<Item> mycart=new ArrayList<Item>();
	Member(String id,String password,String name,String email){
		super(id,password,name,email);
	}


	public void addItems(Item i){
		mycart.add(i);
	}
	public void show_my_thing(){
		if(mycart.size()==0){
			System.out.println("Cart Empty");
			return;
		}
		Iterator<Item> itr = mycart.iterator();
		int cart_index=1;
		while(itr.hasNext()){
			Item element = (Item) itr.next();
			if(element instanceof Book){
				if(((Book) element).get_ebook_issue()){
					System.out.print("\n"+cart_index+"  ");
					((Book) element).cart_for_ebook();
					cart_index++;
				}
				if(((Book) element).get_physical_book_issue()){
					System.out.print("\n"+cart_index+"  ");
					((Book) element).cart_for_book();
					cart_index++;
				}
			}
			if(element instanceof Software){
				if(((Software) element).get_download_issue()){
					System.out.print("\n"+cart_index+"  ");
					((Software) element).cart_for_download();
					cart_index++;
				}
				else if(((Software) element).isCd_issue()){
					System.out.print("\n"+cart_index+"  ");
					((Software) element).cart_for_cd();
					cart_index++;
				}


			}
		}
	}
	public void remove_items(){
		show_my_thing();
		if(mycart.size()==0){
			//System.out.println("Cart Empty");
			return;
		}
		System.out.println("Which item you wish to remove?\n");
		Scanner in= new Scanner(System.in);
		int remove_index=in.nextInt();
		remove_index--;
		mycart.remove(remove_index);

	}

	public void show_member(){
		System.out.println("Name "+getName()+" Email  "+getEmail());
	}


	public int getLogin_count() {
		return login_count;
	}


	public void setLogin_count() {
		login_count=login_count+1;;
	}



}
