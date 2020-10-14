//package partc;

/** 

 * Working of the program

 1. The TechStore class implements an application for purchasing books and Software via a Computer System.The books can be in the form of physical 

 book and e-book format and the software can be purchased in the form of CD and the download format.

 2.A book/software can be purchased by simply entering the initials of the book.The input string traverses through  all the different 

 objects of the item array .

 3.The program shows all the  matching items in the item array and prompts the user to enter the choice.

 4.The user is given the option of selecting the item out of all the matching items.

 5.The user gets the option of either selecting the item or cancel and go back to the main menu.

 6.If the Item is only available in the physical/CD format,the user is prompted to buy the physical/CD.

 7.If the item is available in both the format,the user is given the option of either buying a physical copy or CD or to buy a ebook/download.

 8.According to the item selected users are presented with the recommendations.Users have option of either buying a recommended item or canceling it.

 9.If a user buys a recommended title,the user is presented with more recommendations related to the last purchase.

 * 

 * This program is the original work of Mitul Manish(s3499818).

 * Email- mitul.manish@gmail.com 0451754624

 * 

 */



import java.util.*;



public class TechStore {

	static ArrayList<Item>sales= new ArrayList<Item>();

	static ArrayList<Item> list= new ArrayList<Item>();

	//static ArrayList<Item> rec_holder= new ArrayList<Item>();

	static HashMap<Integer,ArrayList<Item>> recommend=new HashMap<Integer,ArrayList<Item>>();

	static ArrayList<User> user_list= new ArrayList<User>();

	static ArrayList<Item> rec_list1= new ArrayList<Item>();

	static ArrayList<Item> rec_list2= new ArrayList<Item>();

	static ArrayList<Item> rec_list3= new ArrayList<Item>();

	static ArrayList<Item> rec_list4= new ArrayList<Item>();

	static ArrayList<Item> rec_list5= new ArrayList<Item>();

	static ArrayList<Item> rec_list6= new ArrayList<Item>();



	private static  double user_balance=0;

	static int count=-1;

	static Scanner input = new Scanner(System.in);



	public static void main(String[] args){

		boolean change_user=false;

		//System.out.println("beginning of main");

		initial();

		int my_user=authenticate(count);



		System.out.println(my_user);

		//testlbl:

		main_activity(my_user);

	}



	public static  Item find_my_book(String item_name,int user_index){

		//System.out.println("inside of find my book block");

		int ebook_option = 0;

		ArrayList<Item>  item_holder= new ArrayList<Item>();

		//System.out.println("reached find my book----------");

		Scanner buy=new Scanner(System.in);

		int counter=0;

		int i=1;

		boolean found=false;



		Iterator<Item> itr = list.iterator();

		while(itr.hasNext()) {

			// System.out.println("inside iterator----------");

			Item element = (Item) itr.next();



			if(element instanceof Book){

				if(((Book) element).get_title().startsWith(item_name.toLowerCase())){

					found=true;

					item_holder.add((Book)element);

					System.out.print((i)+ ". ");



					((Book) item_holder.get(counter)).show_book();



					System.out.println();

					counter++;

					i++;





				}

			}

			else if(element instanceof Software){

				if(((Software) element).get_title().toLowerCase().startsWith(item_name.toLowerCase())){

					found=true;

					item_holder.add((Software)element);



					System.out.print((i)+ ". ");



					((Software) item_holder.get(counter)).show_software();





					System.out.println();

					counter++;

					i++;





				}

			}

		}



		if (found){



			System.out.println("Press 99 to return to main menu");

			System.out.println("Which number item do you wish to purchase:");

			int item_number = buy.nextInt();

			if (item_number==99){

				return null;

			}

			item_number--;

			System.out.println("item number::"+item_number);

			if (item_holder.get(item_number) instanceof Book){

				System.out.println("item number::"+item_number);

				if(((Book) item_holder.get(item_number)).check_ebook_available()){

					Scanner read = new Scanner(System.in);

					book_option();
					boolean loop=true;


					do{

						try{
							System.out.println("\n Enter the option");
							ebook_option=read.nextInt();
							switch (ebook_option){

							case 5:
								loop=false;
								break;

							case 1:
								loop=false;
								System.out.println("Do you want to buy the ebook version of the book ?");

								System.out.println("Press 1 to buy the book");

								System.out.println("Press 0 to cancel");

								int option = buy.nextInt();



								switch(option){

								case 1:



									System.out.println("found and added to cart");

									System.out.println("item number::"+item_number);

									((Book) item_holder.get(item_number)).set_ebook_issue(true);



									//System.out.println("item_holder size"+item_holder.size());

									user_balance=user_balance+((Book) item_holder.get(item_number)).get_ebook_cost();

									//System.out.println(user_balance);

									if (user_list.get(user_index)instanceof Member){

										((Member) user_list.get(user_index)).addItems(item_holder.get(item_number));

										((Member) user_list.get(user_index)).setBalance(((Book) item_holder.get(item_number)).get_ebook_cost());

										System.out.println(((Member) user_list.get(user_index)).getBalance());

										find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

									else if  (user_list.get(user_index)instanceof NonMember){

										((NonMember) user_list.get(user_index)).addItems(item_holder.get(item_number));

										((NonMember) user_list.get(user_index)).setBalance(((Book) item_holder.get(item_number)).get_ebook_cost());

										System.out.println(((NonMember) user_list.get(user_index)).getBalance());

										find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

									return(item_holder.get(item_number));

								case 0:

									return null;

								}







							case 3:
								loop=false;
								System.out.println("Checking out");

								break;

							case 2:
								loop=false;

								if (((Book) item_holder.get(item_number)).get_number_of_copies()>0){

									//	System.out.println("item number::"+item_number);

									System.out.println("Do you want to buy the physical copy of the book ?");

									System.out.println("Press 1 to buy the book");

									System.out.println("Press 0 to cancel");



									int option1 = buy.nextInt();



									switch(option1){

									case 1:



										System.out.println("found and added to cart");

										((Book) item_holder.get(item_number)).set_physical_book_issue(true);

										((Book) item_holder.get(item_number)).reduce_copies();

										user_balance=user_balance+((Book) item_holder.get(item_number)).get_book_cost();



										//System.out.println(user_balance);

										if (user_list.get(user_index)instanceof Member){

											((Member) user_list.get(user_index)).addItems(item_holder.get(item_number));

											((Member) user_list.get(user_index)).setBalance(((Book) item_holder.get(item_number)).get_book_cost());

											System.out.println(((Member) user_list.get(user_index)).getBalance());

											find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

										else if  (user_list.get(user_index)instanceof NonMember){

											((NonMember) user_list.get(user_index)).addItems(item_holder.get(item_number));

											((NonMember) user_list.get(user_index)).setBalance(((Book) item_holder.get(item_number)).get_book_cost());

											System.out.println(((NonMember) user_list.get(user_index)).getBalance());

											find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

										return(item_holder.get(item_number));

									case 0:

										return null;

									}



								}

								else{

									System.out.println("no copies available");

									return null;}

							default:

								System.out.println("That's not a valid input");

								return null;

							}
						} catch (InputMismatchException e) {
							System.out.print("Enter the correct option");
						}
						read.nextLine();
					}while(loop);

				}





				else

					System.out.println("This book is only available in the physical format");

				if (((Book) item_holder.get(item_number)).get_number_of_copies()>0){

					System.out.println("item number::"+item_number);

					System.out.println("Do you want to buy the physical copy of the book ?");

					System.out.println("Press 1 to buy the book");

					System.out.println("Press 0 to cancel");

					int option = buy.nextInt();



					switch(option){

					case 1:



						System.out.println("found and added to cart");

						((Book) item_holder.get(item_number)).set_physical_book_issue(true);

						System.out.println(((Book) item_holder.get(item_number)).get_physical_book_issue());

						((Book) item_holder.get(item_number)).reduce_copies();

						System.out.println(((Book) item_holder.get(item_number)).get_book_cost());

						user_balance=user_balance+((Book) item_holder.get(item_number)).get_book_cost();

						if (user_list.get(user_index)instanceof Member){

							((Member) user_list.get(user_index)).addItems(item_holder.get(item_number));

							((Member) user_list.get(user_index)).setBalance(((Book) item_holder.get(item_number)).get_book_cost());

							System.out.println(((Member) user_list.get(user_index)).getBalance());

							find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

						else if  (user_list.get(user_index)instanceof NonMember){

							((NonMember) user_list.get(user_index)).addItems(item_holder.get(item_number));

							((NonMember) user_list.get(user_index)).setBalance(((Book) item_holder.get(item_number)).get_book_cost());

							System.out.println(((NonMember) user_list.get(user_index)).getBalance());

							find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

						//System.out.println(user_balance);

						return(item_holder.get(item_number));

					case 0:

						return null;

					}

				} 

				else{

					System.out.println("No copies available");

					return null;}

			}

			//System.out.println("Not available");

			//-------------------------checking for software----------------------------------------

			if (item_holder.get(item_number) instanceof Software){

				//System.out.println("item number::"+item_number);

				//System.out.println("inside software block");

				if(((Software) item_holder.get(item_number)).check_download_available()){

					Scanner read = new Scanner(System.in);

					software_option();
					boolean loop=true;
					int download_option=0;
					do{
						try{
							System.out.println("Enter the option");
							download_option=read.nextInt();
							switch (download_option){

							case 0:
								loop=false;
								break;

							case 1:
								loop=false;
								System.out.println("Do you want to  download the software ?");

								System.out.println("Press 1 to buy the software");

								System.out.println("Press 0 to cancel");

								int option = buy.nextInt();



								switch(option){

								case 1:



									System.out.println("found and added to cart");

									((Software) item_holder.get(item_number)).set_download_issue(true);

									user_balance=(int) (user_balance+((Software) item_holder.get(item_number)).get_download_cost());

									if (user_list.get(user_index)instanceof Member){

										((Member) user_list.get(user_index)).addItems(item_holder.get(item_number));

										((Member) user_list.get(user_index)).setBalance(((Software) item_holder.get(item_number)).get_download_cost());

										//System.out.println(((Member) user_list.get(user_index)).getBalance());

										find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

									else if  (user_list.get(user_index)instanceof NonMember){

										((NonMember) user_list.get(user_index)).addItems(item_holder.get(item_number));

										((NonMember) user_list.get(user_index)).setBalance(((Software) item_holder.get(item_number)).get_download_cost());

										//System.out.println(((NonMember) user_list.get(user_index)).getBalance());

										find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

									//System.out.println(user_balance);

									return(item_holder.get(item_number));

								case 0:

									return null;

								}







							case 3:System.out.println("Checking out");
							loop=false;
							break;

							case 2:
								loop=false;
								if (((Software) item_holder.get(item_number)).get_number_of_cd()>0){

									System.out.println("item number::"+item_number);

									System.out.println("Do you want to buy the CD version of the software ?");

									System.out.println("Press 1 to buy the CD");

									System.out.println("Press 0 to cancel");



									int option1 = buy.nextInt();



									switch(option1){

									case 1:



										System.out.println("found and added to cart");

										((Software) item_holder.get(item_number)).setCd_issue(true);

										((Software) item_holder.get(item_number)).reduce_copies();

										user_balance=(int) (user_balance+((Software) item_holder.get(item_number)).get_cd_cost());

										//System.out.println(user_balance);

										if (user_list.get(user_index)instanceof Member){

											((Member) user_list.get(user_index)).addItems(item_holder.get(item_number));

											((Member) user_list.get(user_index)).setBalance(((Software) item_holder.get(item_number)).get_cd_cost());

											//System.out.println(((Member) user_list.get(user_index)).getBalance());

											find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

										else if  (user_list.get(user_index)instanceof NonMember){

											((NonMember) user_list.get(user_index)).addItems(item_holder.get(item_number));

											((NonMember) user_list.get(user_index)).setBalance(((Software) item_holder.get(item_number)).get_cd_cost());

											//System.out.println(((NonMember) user_list.get(user_index)).getBalance());

											find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

										return(item_holder.get(item_number));

									case 0:

										return null;

									}



								}

								else{

									System.out.println("no copies available");

									return null;}

							default:

								System.out.println("That's not a valid input");

								return null;

							}
						} catch (InputMismatchException e) {
							System.out.print("Enter the correct option");
						}
						read.nextLine();
					}while(loop);

				}

				else

					System.out.println("This software is only available in the CD format");

				if (((Software) item_holder.get(item_number)).get_number_of_cd()>0){

					//System.out.println("item number::"+item_number);

					System.out.println("Do you want to buy the CD of the Software ?");

					System.out.println("Press 1 to buy the CD");

					System.out.println("Press 0 to cancel");

					int option= 0; 
					boolean loop=true;
					do{
						try{
							System.out.println("\n Print the option");
							option=buy.nextInt();
							switch(option){

							case 1:


								loop=false;
								System.out.println("found and added to cart");

								((Software) item_holder.get(item_number)).setCd_issue(true);

								((Software) item_holder.get(item_number)).reduce_copies();

								user_balance=(int) (user_balance+((Software) item_holder.get(item_number)).get_cd_cost());

								System.out.println(user_balance);

								if (user_list.get(user_index)instanceof Member){

									((Member) user_list.get(user_index)).addItems(item_holder.get(item_number));

									((Member) user_list.get(user_index)).setBalance(((Software) item_holder.get(item_number)).get_cd_cost());

									//System.out.println(((Member) user_list.get(user_index)).getBalance());

									find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

								else if  (user_list.get(user_index)instanceof NonMember){

									((NonMember) user_list.get(user_index)).addItems(item_holder.get(item_number));

									((NonMember) user_list.get(user_index)).setBalance(((Software) item_holder.get(item_number)).get_cd_cost());

									//System.out.println(((NonMember) user_list.get(user_index)).getBalance());

									find_recommendation(item_holder.get(item_number).getItem_id(),user_index);}

								return(item_holder.get(item_number));

							case 0:
								loop=false;
								return null;

							}
						} catch (InputMismatchException e) {
							System.out.print("Enter the correct option");
						}
						buy.nextLine();
					}while(loop);

				} 

				else{

					System.out.println("No copies available");

					return null;}

			}

			System.out.println("Not available");



		}//closing of if found

		return null;

	}





	private static void software_option() {

		System.out.println();

		System.out.print(

				" 1. Buy the downloadable version(Software) \n"+

" 2. Buy CD\n"+

" 3. Checkout\n" +

" 4. List all Software items in store\n" +

				" 5. Go back \n");



	}



	public static void show_all_items(ArrayList<Item> list1)

	{   

		Iterator<Item> itr = list1.iterator();

		int index=1;

		while(itr.hasNext()){

			Item element = (Item) itr.next();

			if(element!=null){



				if(element instanceof Book){

					System.out.print("\n"+index+" ");

					((Book) element).show_book();



					index++;

				}

				else if(element instanceof Software){

					System.out.print("\n"+index+" ");

					((Software) element).show_software();



					index++;

				}

			}

		}

	}







	//display the cart

	public static void show_sales (ArrayList<Item> cart)

	{     

		if (get_balance()==0){

			System.out.println("Cart Empty");

		}

		else{

			Iterator<Item> itr = cart.iterator();

			int count=1;

			while(itr.hasNext()){

				Item element = (Item) itr.next();

				if(element!=null){

					System.out.println();

					System.out.print(count+" ");

					if(element instanceof Book){

						if(((Book) element).get_ebook_issue()){

							((Book) element).ebook_sales();

						}

						if(((Book) element).get_physical_book_issue()){

							((Book) element).book_sales();

						}

						count++;

					}

					else if(element instanceof Software){

						if(((Software) element).get_download_issue()){

							((Software) element).download_sales();

						}

						if(((Software) element).isCd_issue()){

							((Software) element).cd_sales();

						}

						count++;

					}



				}



			}

			System.out.println();

			System.out.println(" Total Balance:$ "+get_balance());

		}

	}

	// returns the balance

	public static double get_balance ()

	{

		return user_balance;

	}

	public static void print_menu(){

		System.out.println();

		System.out.println("                                                           Welcome to TechBooks");

		System.out.println("----------------------------------------------------------------------"

+ "----------------------------------------------------------------------------");

		System.out.println("Please select an option:\n"+

"1. Add item to shopping cart\n"+ 

"2. View shopping cart\n"+ 

"3. Remove item from shopping cart\n"+ 

"4. Checkout\n" +

"5. List all items\n"+ 

"6. Print previous purchases\n"+ 

"7. Change User\n"+ 

				"0. Quit\n");

		//System.out.println("Enter a option");

	}



	public static void print_admin_menu(){

		System.out.println();

		System.out.println("                                                           Welcome to TechBooks");

		System.out.println("----------------------------------------------------------------------"

+ "----------------------------------------------------------------------------");

		System.out.print(

				"1. List all items (purchase statistics for each title)"+"\n"+

"2. Display all Users"+"\n"+

"3. Change price of item"+"\n"+

"4. Add new user"+"\n"+

"5.Add Copies to the items"+"\n"+

"6.Sales Analytics"+"\n"+

"7.Change User"+"\n"+"\n 0.Quit");

		//	System.out.println("Enter a option");

		//Welcome to TechStore, Robert MacQuillan. You are logged in as an Administrator.



		//Please select an option:

		//	1. List all items

		//2. Add copies to item 

		//	3. Change price of item 

		//	4. Add new user 

		//	5. Change user

		//	0. Quit 

	}

	public static void book_option(){

		System.out.println();

		System.out.print(

				" 1. Buy an ebook \n"+

" 2. Buy physical book\n"+

" 3. Checkout\n" +

" 4. List all books in store\n" +

				" 5. Go back \n");



	}



	public static void add_member(String id,String password,String name,String email){

		Member new_member=new Member(id,password,name,email);

		user_list.add(new_member);

	}

	public static void add_admin(String id,String password,String name,String email){

		Admin new_admin=new Admin(id,password,name,email);

		user_list.add(new_admin);

	}

	public static void add_non_member(String id,String password,String name,String email){

		NonMember new_non=new NonMember(id,password,name,email);

		user_list.add(new_non);

	}

	public static void initial(){



		Item[] book_array = new Book[5];

		Item[] soft_array = new Software[5];





		soft_array[0]=new Software(6,"24-04-2015","Sega Genesis Classics Game Pack","Sega",true,37.50,39.99,10);

		soft_array[1]=new Software(7,"24-04-2015","Civilization 5 for Mac","2K",true,58.99,65.99,5);

		soft_array[2]=new Software(8,"24-04-2015","Jake and the Neverland Pirates","LeapFrog",false,17.50,19.99,10);

		soft_array[3]=new Software(9,"24-04-2015","Call of Duty","Achvision",false,11.62,0,10);

		soft_array[4]=new Software(10,"24-04-2015","The Magic School Bus","LeapFrog",true,15.49,17.99,1);

		//Sega Genesis Classics Game Pack, Sega, CD and download, 10 copies, $37.50/$39.99

		//- Recommended: none

		//Civilization 5 for Mac, 2K, CD and download, 5 copies, $58.99/$65.99

		//- Recommended: Call of Duty (Achvision)

		//Jake and the Neverland Pirates, LeadFrog, CD and download, 0 copies, $17.50/$19.99

		//- Recommended: The Magic School Bus (LeapFrog)

		//Call of Duty, Achvision, CD only, 0 copies, $11.62

		//- Recommended: Civilization 5 for Mac (2K)

		//The Magic School Bus, LeapFrog, CD and download, 1 copy, $15.49/17.99

		//- Recommended: Jake and the Neverland Pirates (LeadFrog)



		book_array[0]=new Book(1,"24-04-2015","Absolute Java","Savitch",true,75,15,5);

		book_array[1]=new Book(2,"24-04-2015","JAVA: How to Program","Deitel and Deitel",true,65,12,1);

		book_array[2]=new Book(3,"24-04-2015","Computing Concepts with JAVA 3 Essentials","Hortsman",false,114.72,0,5);

		book_array[3]=new Book(4,"24-04-2015","Java Software Solutions","Lewis and Loftus",false,80.00,0,5);

		book_array[4]=new Book(5,"24-04-2015","Java Program Design","Cohoon and Davidson",true,51.00,10.00,1);

		rec_list1.add(book_array[0]);

		rec_list2.add(book_array[0]);

		rec_list2.add(book_array[1]);

		rec_list3.add(soft_array[3]);

		rec_list4.add(soft_array[4]);

		rec_list5.add(soft_array[1]);

		rec_list6.add(soft_array[2]);

		recommend.put(2,rec_list1);

		recommend.put(3,rec_list2);

		recommend.put(7,rec_list3);

		recommend.put(8,rec_list4);

		recommend.put(9,rec_list5);

		recommend.put(10,rec_list6);

		//list.addAll(recommend.get(2));

		//System.out.println("Size of the list  "+list.size());



		for(int i=0;i<book_array.length;i++){

			if(book_array[i] !=null){

				list.add((Book) book_array[i]);}

		}

		for(int i=0;i<soft_array.length;i++){

			if(book_array[i] !=null){

				list.add((Software) soft_array[i]);}

		}

		//System.out.println(((Book) list.get(1)).get_title());;

		//System.out.println(list.get(7).getClass());



		//	User nm1=new NonMember("mitul123","mitul2311","mitul","mitul.manish@gmail.com");

		//	User m2=new Member("bijin123","bijin2311","bijin","bijin.abraham@gmail.com");

		//	User m3=new Member("nithin123","nithin2311","nithin","nithin.hafeez@gmail.com");

		//	User m4=new Member("daisy123","daisy2311","daisy","daisy.sanders@gmail.com");

		//	User m5=new Member("qadir123","qadir2311","qadir","qadir.alam@gmail.com");

		User m7=new Member("dt","dtpw","Daryl Teo","teo@techbooks.com.au");

		//User m6=new Member("lawrence123","lawrence2311","lawrance","lawrence.cavadon@gmail.com");

		//	User nm2=new NonMember("zuma123","zuma2311","zuma","zuma@gmail.com");

		//User ad1=new Admin("jack123","jack2311","jack","jack@gmail.com");

		User ad2=new Admin("rmq","rmpw","Robert MacQualin","robq@techbooks.com.au");

		User nm3=new Admin("rmac","rmacqpw","Robert MacQualin","robq@techbooks.com.au");

		User nm4=new NonMember("lc","lcpasswd","Lawrence Cavedon","lawrence@techbooksl.com.au");







		//user_list.add(ad1);

		user_list.add(ad2);

		//	user_list.add(nm1);

		//user_list.add(nm2);

		user_list.add(nm3);

		user_list.add(nm4);

		//user_list.add(m2);

		//user_list.add(m3);

		//user_list.add(m4);

		//user_list.add(m5);

		//user_list.add(m6);

		user_list.add(m7);



	}



	public static int authenticate(int count){

		//int count=0;

		Iterator<User> itr = user_list.iterator();



		Scanner input = new Scanner(System.in);

		boolean user_found=false;

		boolean invalid=true;

		System.out.println("Enter UserName:");

		String user_name=input.nextLine();

		while(itr.hasNext()) {

			user_found=false;

			HashMap<String, String> map = new HashMap<String, String>();

			//System.out.println(user_list.size());

			//Iterator<User> itr = user_list.iterator();

			if(user_found==false){

				User element = (User) itr.next();

				map.put(element.getId(),element.getPassword());

			}



			while(hm.containsKey(user_name)){

				System.out.println("Enter PassWord");

				String password=input.nextLine();

				if((boolean) hm.get(user_name).equals(password)){

					System.out.println("Welcome::"+user_name);

					user_found=true;

					break;

				}

				else System.out.println("Wrong password");



			}

			count++;

			//System.out.println("printing count"+count);



			if(user_found){

				//user_found=false;

				invalid=false;

				return count;

				// break;

			}



		}

		if(!user_found){

			System.out.println("User not found in the database");

			return authenticate(-1);

		}





		return (Integer) 99;

	}



	public static void main_activity(int count){

		//System.out.println("inside main activity");

		while(count>user_list.size()){

			System.out.println("invalid  username");

			System.exit(0);

		}

		boolean change_user=false;

		while(true){

			if((user_list.get(count) instanceof Member) || (user_list.get(count) instanceof NonMember)){

				//System.out.println(user_list.get(count).getClass());

				if(user_list.get(count) instanceof NonMember){

					System.out.println();

					System.out.println("Hi  "+ user_list.get(count).getName()+"  You are signed in as a Non Member");

					//	((NonMember) user_list.get(count)).set_count();

					//System.out.println(((NonMember) user_list.get(count)).get_count());

				}

				else if(user_list.get(count) instanceof Member){

					System.out.println();

					System.out.println("Hi  "+ user_list.get(count).getName()+"  You are signed in as a Member");

					//((Member) user_list.get(count)).setLogin_count();

					//System.out.println(((Member) user_list.get(count)).getLogin_count());

				}



				System.out.println("Welcome to TechStore");



				print_menu();
				/*
				do {
				    try {
				        System.out.print("Enter the number of students: ");
				        students = input.nextInt();
				    } catch (InputMismatchException e) {
				        System.out.print("Enter the number of students");
				    }
				    input.nextLine(); // clears the buffer
				} while (students <= 0);
				 */
				//print menu function prints the menu

				Scanner reader=new Scanner(System.in);

				Integer option=0;



				//take input from the user
				boolean loop=true;
				do{

					try{
						System.out.println("Enter the option");
						option = reader.nextInt();
						switch(option){

						case 1:
							loop=false;  
							System.out.println("Enter the name of the item");



							String item_name= input.nextLine();

							sales.add(find_my_book(item_name,count));

							break;

						case 2:

							//show_cart(cart);


							loop=false;  
							if(user_list.get(count) instanceof Member){

								//System.out.println("Hi"+ user_list.get(count).getName()+"  You are signed in as a Member");

								//System.out.println(((Member) user_list.get(count)).getLogin_count());

								//System.out.println(user_list.get(count).getBalance());

								user_list.get(count).show_my_balance();

								((Member) user_list.get(count)).show_my_thing();

							}

							if(user_list.get(count) instanceof NonMember){



								System.out.println(((NonMember) user_list.get(count)).get_count());

								//System.out.println(user_list.get(count).getBalance());

								user_list.get(count).show_my_balance();

								( (NonMember) user_list.get(count)).show_my_thing();

							}

							//display the contents of the cart.

							break;

						case 6:

							//show_cart(cart);

							loop=false;  

							if(user_list.get(count) instanceof Member){

								System.out.println(((Member) user_list.get(count)).getLogin_count());

								System.out.println(user_list.get(count).getBalance());

								((Member) user_list.get(count)).show_my_thing();

							}

							if(user_list.get(count) instanceof NonMember){

								System.out.println(((NonMember) user_list.get(count)).get_count());

								System.out.println(user_list.get(count).getBalance());

								( (NonMember) user_list.get(count)).show_my_thing();

							}

							//display the contents of the cart.

							break;

						case 4:
							loop=false;  
							//show_cart(cart);



							if(user_list.get(count) instanceof Member){

								//System.out.println(((Member) user_list.get(count)).getLogin_count());

								//System.out.println(user_list.get(count).getBalance());

								((Member) user_list.get(count)).show_my_thing();

							}

							if(user_list.get(count) instanceof NonMember){

								//System.out.println(((NonMember) user_list.get(count)).get_count());

								//System.out.println(user_list.get(count).getBalance());

								( (NonMember) user_list.get(count)).show_my_thing();

							}

							//display the contents of the cart.

							break;

						case 3:
							loop=false;  
							if(user_list.get(count)instanceof Member){

								((Member) user_list.get(count)).remove_items();

								break;

							}

							if(user_list.get(count)instanceof NonMember){

								((NonMember) user_list.get(count)).remove_items();

								break;

							}



						case 5:
							loop=false;  
							show_all_items(list);

							//display all the books in the store.

							break;

						case 7: change_user=true;
						loop=false;  
						System.out.println("change user");

						if(user_list.get(count) instanceof Member){

							System.out.println("belongs to member");

							((Member) user_list.get(count)).setLogin_count();

							//System.out.println(((Member) user_list.get(count)).getLogin_count());

							//System.out.println(user_list.get(count).getBalance());

							//((Member) user_list.get(count)).show_my_thing();

						}

						if(user_list.get(count) instanceof NonMember){

							System.out.println("belongs to non member");

							((NonMember) user_list.get(count)).set_count();

							//System.out.println(((NonMember) user_list.get(count)).get_count());

							//System.out.println(user_list.get(count).getBalance());

							//( (NonMember) user_list.get(count)).show_my_thing();

							if ((((NonMember) user_list.get(count)).get_count()<=1) && (user_list.get(count).getBalance()>100)){

								System.out.println("You exceeded the limit");

								user_list.remove(count);

								user_list.trimToSize();

								System.out.println("Deleted from database");

							}

							else if((((NonMember) user_list.get(count)).get_count()>1) && (user_list.get(count).getBalance()>110)){

								System.out.println("\n\nWould yo like to become a member??");

								System.out.println("Press 1 to become a privileged member\n"+"Press 0 to discard the offer\n");

								Scanner inp=new Scanner(System.in);

								int get=inp.nextInt();

								if(get==1){

									String id,name,password,email;

									id=user_list.get(count).getId();

									name=user_list.get(count).getName();

									password=user_list.get(count).getPassword();

									email=user_list.get(count).getEmail();

									user_list.remove(count);

									Member new_member = new Member(id,password,name,email);

									System.out.println("You have been added to the system as a member.Enjoy shopping with more freedom");

									user_list.add(new_member);

									//main_activity(authenticate(-1));

								}

							}

						}

						break;

						case 0:System.exit(0);

						default:

							System.out.println("That is not a valid input");

							break;

						}
					}catch (InputMismatchException e) {
						System.out.print("Please Enter the correct input from the Menu above");
					}
					reader.nextLine();
				}while(loop);

			}






			if(user_list.get(count) instanceof Admin){

				//System.out.println(user_list.get(count).getClass());

				System.out.println();

				System.out.println("Welcome to TechStore");

				System.out.println("Hi  "+ user_list.get(count).getName()+"  You are signed in as a Admin");

				//user_list.get(count).setLogin_count();

				System.out.println(user_list.get(count).getName());

				//System.out.println(user_list.get(count).getLogin_count());

				print_admin_menu();

				//print menu function prints the menu

				Scanner reader=new Scanner(System.in);

				int option =0;
				boolean loop=true;
				do{

					try{
						System.out.println("Enter the option");
						option=reader.nextInt();

						//take input from the user

						switch(option){

						case 7: 
							loop=false;
							change_user=true;

							System.out.println("change user");

							break;

						case 1:
							loop=false;
							show_all_items(list);

							break;



						case 5:
							loop=false;
							add_copies(list);

							break;

						case 2:
							loop=false;
							show_all_users();

							break;

						case 3:
							loop=false;
							show_all_items(list);

							break;

						case 4:
							loop=false;
							Scanner take_input= new Scanner(System.in);

							String id = null;

							Iterator<User> i=user_list.iterator();

							boolean id_unique=false;

							while(true){

								System.out.println("Enter Id");

								String ID1=take_input.nextLine();



								while(i.hasNext()){

									if(i.next().getId().equals(ID1)){

										System.out.println("ID already exists");

										id_unique=false;

										break;

									}

									else{

										id_unique=true;

									}

								}

								if(id_unique){

									id=ID1;

									break;

								}

							}

							System.out.println("Enter name");

							String NAME=input.nextLine();

							System.out.println("Enter password");

							String PASSWORD=take_input.nextLine();

							System.out.println("Enter email");

							String EMAIL=input.nextLine();

							System.out.println("Press 1 to add this person as Member\n"+"Press 2 to add as Non Member\n"+"\nPress 3 to add as Admin");

							Scanner my_in=new Scanner(System.in);

							int input=my_in.nextInt();

							if(input==1){

								add_member(id,NAME,PASSWORD,EMAIL);

							}

							else if(input==2){

								add_non_member(id,NAME,PASSWORD,EMAIL);

							}

							else if(input==3){

								add_admin(id,NAME,PASSWORD,EMAIL);

							}

							else{

								System.out.println("Not a valid input");

							}

							//show_all_items(list);

							//display all the books in the store.

							break;

						case 6:
							loop=false;
							show_sales(sales);

							break;

						case 0:System.exit(0);

						default:

							System.out.println("That is not a valid input");

							break;

						}
					}
					catch (InputMismatchException e) {
						System.out.print("Please Enter the correct input from the Menu above");
					}
					reader.nextLine();
				}while(loop);

			}

			if(change_user){

				int local=authenticate(-1);

				if(local==-1){

					local--;}

				change_user=false;

				main_activity(local);

				//change_user=false;

			}

		}

	}

	public static void show_all_users(){

		Iterator<User> itr= user_list.iterator();

		while(((Iterator<User>) itr).hasNext()){

			User u=(User) itr.next();

			if(u instanceof Member){

				((Member) u).show_member();

			}

			if(u instanceof NonMember){

				((NonMember) u).show_member(); 

			}
			if(u instanceof Admin){

				((Admin) u).show_member(); 

			}




		}

	}



	public static void add_copies(ArrayList<Item> list2){

		show_all_items(list);

		Scanner input= new Scanner(System.in);

		System.out.println("\n\n"+"Mention the index to add copies");

		int my_num=input.nextInt();

		my_num--;

		if(list2.get(my_num) instanceof Book){

			((Book) list.get(my_num)).add_copies(); 

		}

		else if(list2.get(my_num) instanceof Software){

			((Software) list.get(my_num)).add_copies();  



		}

		else{

			System.out.println("Wrong input");

		}

		return;

	}

	public static void find_recommendation(int item_id,int user_number){

		//System.out.println("inside recommendation");

		ArrayList<Item> rec_holder=new ArrayList<Item>();

		if(recommend.containsKey(item_id)){

			rec_holder.addAll(recommend.get(item_id));

			Iterator<Item> itr = rec_holder.iterator();

			int in=0;

			while(itr.hasNext()){

				Item element = itr.next();

				if(element instanceof Book){

					in++;

					System.out.println();

					System.out.print(in+"   ");

					((Book) element).show_book();

				}

				if(element instanceof Software){

					in++;

					System.out.println();

					System.out.print(in+"   ");

					((Software) element).show_software();

				}

			}

			System.out.println("    Would you like to buy the recommened items?");

			//set up a loop ..1 t purchase 0 to exit..

			//outer input

			System.out.println("\nPress 1 to buy..\n Press 0 to quit ");

			Scanner input = new Scanner (System.in);

			int user_input=input.nextInt();

			while(user_input==1){

				//System.out.println("inside while loop");

				System.out.println("Wish item do you wish to purchase?\n Type the index of the item to purchase it.");

				int buy_item=input.nextInt();

				buy_item--;

				sales.add(buy_rec_item(rec_holder,buy_item,user_number));

				System.out.println("Press 0 to quit ");

				user_input=input.nextInt();

			}

		}

		else{

			System.out.println("No recommended titles present for this item");}

	}

	public static Item buy_rec_item(ArrayList<Item> rec,int item_number,int user_number)

	{

		if(rec.get(item_number)instanceof Book){

			System.out.println("Press 1 to buy the physical book"+"\n"+"Press 2 to buy the ebook of the book");

			Scanner input =new Scanner (System.in);

			int buy=input.nextInt();

			if(buy==1){

				if(((Book) rec.get(item_number)).get_number_of_copies()>0){

					System.out.println("found and added to cart");

					((Book) rec.get(item_number)).set_physical_book_issue(true);

					((Book) rec.get(item_number)).reduce_copies();

					//user_balance=user_balance+((Book) rec.get(item_number)).m_dis_book();



					//System.out.println(user_balance);

					if (user_list.get(user_number)instanceof Member){

						((Book) rec.get(item_number)).set_m_rec_book_issue(true);

						((Member) user_list.get(user_number)).addItems(rec.get(item_number));

						((Member) user_list.get(user_number)).setBalance(((Book) rec.get(item_number)).m_dis_book());

						System.out.println(((Member) user_list.get(user_number)).getBalance());

						find_recommendation(rec.get(item_number).getItem_id(),user_number);}

					else if  (user_list.get(user_number)instanceof NonMember){

						((Book) rec.get(item_number)).set_nm_rec_book_issue(true);

						((NonMember) user_list.get(user_number)).addItems(rec.get(item_number));

						((NonMember) user_list.get(user_number)).setBalance(((Book) rec.get(item_number)).nm_dis_book());

						System.out.println(((NonMember) user_list.get(user_number)).getBalance());

						find_recommendation(rec.get(item_number).getItem_id(),user_number);}

					return(rec.get(item_number));

				}

				else{

					System.out.println("Physical book not available");

				}

			}

			if(buy==2){

				if(((Book) rec.get(item_number)).check_ebook_available()){

					System.out.println("found and added to cart");

					System.out.println("item number::"+item_number);

					((Book) rec.get(item_number)).set_ebook_issue(true);



					//System.out.println("item_holder size"+item_holder.size());

					//user_balance=user_balance+((Book) rec.get(item_number)).m_dis_ebook();

					//System.out.println(user_balance);

					if (user_list.get(user_number)instanceof Member){

						((Book) rec.get(item_number)).set_m_rec_ebook_issue(true);

						((Member) user_list.get(user_number)).addItems(rec.get(item_number));

						((Member) user_list.get(user_number)).setBalance(((Book) rec.get(item_number)).m_dis_ebook());

						System.out.println(((Member) user_list.get(user_number)).getBalance());

						find_recommendation(rec.get(item_number).getItem_id(),user_number);}

					else if  (user_list.get(user_number)instanceof NonMember){

						((Book) rec.get(item_number)).set_nm_rec_ebook_issue(true);

						((NonMember) user_list.get(user_number)).addItems(rec.get(item_number));

						((NonMember) user_list.get(user_number)).setBalance(((Book) rec.get(item_number)).nm_dis_ebook());

						System.out.println(((NonMember) user_list.get(user_number)).getBalance());

						find_recommendation(rec.get(item_number).getItem_id(),user_number);}

					return(rec.get(item_number));

				}

				else{

					System.out.println("Ebook not available");

				}

			}

		}

		if(rec.get(item_number)instanceof Software){

			System.out.println("Press 1 to buy the CD"+"\n"+"Press 2 to buy the Download of the software");

			Scanner input =new Scanner (System.in);

			int buy=input.nextInt();

			if(buy==1){

				if(((Software) rec.get(item_number)).get_number_of_cd()>0){

					System.out.println("found and added to cart");

					((Software) rec.get(item_number)).setCd_issue(true);

					((Software) rec.get(item_number)).reduce_copies();

					//user_balance=(int) (user_balance+((Software) rec.get(item_number)).get_cd_cost());

					//System.out.println(user_balance);

					if (user_list.get(user_number)instanceof Member){

						((Software) rec.get(item_number)).set_m_rec_cd_issue(true);

						((Member) user_list.get(user_number)).addItems(rec.get(item_number));

						((Member) user_list.get(user_number)).setBalance(((Software) rec.get(item_number)).m_dis_cd());

						System.out.println(((Member) user_list.get(user_number)).getBalance());

						find_recommendation(rec.get(item_number).getItem_id(),user_number);}

					else if  (user_list.get(user_number)instanceof NonMember){

						((Software) rec.get(item_number)).set_nm_rec_cd_issue(true);

						((NonMember) user_list.get(user_number)).addItems(rec.get(item_number));

						((NonMember) user_list.get(user_number)).setBalance(((Software) rec.get(item_number)).nm_dis_cd());

						System.out.println(((NonMember) user_list.get(user_number)).getBalance());

						find_recommendation(rec.get(item_number).getItem_id(),user_number);}

					return(rec.get(item_number));

				}

				else{

					System.out.println("No CD's available");

				}

			}

			if(buy==2){

				if(((Software) rec.get(item_number)).check_download_available()){

					System.out.println("found and added to cart");

					((Software) rec.get(item_number)).set_download_issue(true);

					//user_balance=(int) (user_balance+((Software) rec.get(item_number)).get_download_cost());

					if (user_list.get(user_number)instanceof Member){

						((Software) rec.get(item_number)).set_m_rec_down_issue(true);

						((Member) user_list.get(user_number)).addItems(rec.get(item_number));

						((Member) user_list.get(user_number)).setBalance(((Software)rec.get(item_number)).m_dis_down());

						//System.out.println(((Member) user_list.get(user_number)).getBalance());

						find_recommendation(rec.get(item_number).getItem_id(),user_number);}

					else if  (user_list.get(user_number)instanceof NonMember){

						((Software) rec.get(item_number)).set_nm_rec_down_issue(true);

						((NonMember) user_list.get(user_number)).addItems(rec.get(item_number));

						((NonMember) user_list.get(user_number)).setBalance(((Software) rec.get(item_number)).nm_dis_down());

						//System.out.println(((NonMember) user_list.get(user_number)).getBalance());

						find_recommendation(rec.get(item_number).getItem_id(),user_number);}

					System.out.println(user_balance);

					return(rec.get(item_number));

				}

				else{

					System.out.println("Not available for download");

				}

			}

		}

		return null;

	}

}







