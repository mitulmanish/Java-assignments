//package partc;

public class Book extends Item {
	private String title;
	private String author;
	private boolean ebook;
	private  double b_cost;
	private  double e_cost;
	private boolean ebook_issue=false;
	private boolean physical_book_issue=false;
	private int number_of_copies;
	private int book_sold=0;

	private int ebook_sold=0;

	boolean m_rec_book=false;
	boolean m_rec_ebook=false;
	boolean nm_rec_book=false;
	boolean nm_rec_ebook=false;


	public Book( int item_id,String add_date,String title,String author,boolean ebook,double b_cost,double e_cost,int number_of_copies)
	{   super(item_id,add_date);
	this.title=title;
	this.author=author;
	this.ebook=ebook;
	this.b_cost=b_cost;
	this.e_cost=e_cost;
	this.number_of_copies=number_of_copies;
	}




	public void show_book(){
		System.out.print(" "+title +" by  "+ author+" | ebook available: " + ebook_available()+
				" | number of copies available: "+get_number_of_copies());
	}

	public void cart_for_ebook(){

		if(get_m_rec_ebook()){
			System.out.print(" "+title +" by  "+ author+" in physical book format" + " $ "+m_dis_ebook());
		}
		else if(get_nm_rec_ebook()){
			System.out.print(" "+title +" by  "+ author+" in physical book format" + " $ "+nm_dis_ebook());
		}
		else
			System.out.print(" "+title +" by  "+ author+" in ebook book format" + " $ "+get_ebook_cost()); 
	}

	public void cart_for_book(){

		if(get_m_rec_book()){
			System.out.print(" "+title +" by  "+ author+" in physical book format" + " $ "+m_dis_book());
		}
		else if(get_nm_rec_book()){
			System.out.print(" "+title +" by  "+ author+" in physical book format" + " $ "+nm_dis_book());
		}
		else
			System.out.print(" "+title +" by  "+ author+" in physical book format" + " $ "+get_book_cost());
	}

	public void ebook_sales(){
		System.out.print(" "+title +" by  "+ author+" in ebook book format" + " $ "+get_ebook_cost()+"  "+getEbook_sold()); 
	}

	public void book_sales(){
		System.out.print(" "+title +" by  "+ author+" in physical book format" + " $ "+get_book_cost()+"  "+getBook_sold());
	}

	public void show_my_cart(){
		System.out.print(" "+title +" by  "+ author );
	}
	public void show_details(){
		System.out.print(title +"  "+ author);
	}

	public String ebook_available()
	{
		if(ebook)
			return "Yes";
		else{
			return "No";
		} 
	}

	public void set_ebook_issue(boolean ebook_issue){
		this.ebook_issue=ebook_issue;
		setEbook_sold();
	}

	public void set_physical_book_issue(boolean physical_book_issue){
		this.physical_book_issue=physical_book_issue;
	}
	public boolean get_ebook_issue(){
		return this.ebook_issue;
	}

	public boolean get_physical_book_issue(){
		return  this.physical_book_issue;
	}
	public double get_ebook_cost(){
		return e_cost;
	}

	public   double  get_book_cost(){
		return b_cost;
	}

	public String get_title(){
		return title.toLowerCase();
	}

	public String get_author(){
		return author.toLowerCase();
	}

	public int get_number_of_copies()
	{
		return number_of_copies;
	}

	public void reduce_copies()
	{
		this.number_of_copies--;
		setBook_sold();
	}
	public boolean check_ebook_available(){
		return ebook;
	}

	public void add_copies(){
		number_of_copies++;

	}
	public int getEbook_sold() {
		return ebook_sold;
	}
	public void setEbook_sold() {
		ebook_sold++;
	}
	public int getBook_sold() {
		return book_sold;
	}
	public void setBook_sold() {
		book_sold++;
	}

	public double m_dis_book(){
		return (b_cost-((0.25)*(b_cost)));

	}
	public double m_dis_ebook(){
		return (e_cost-((0.25)*(e_cost)));

	}

	public double nm_dis_book(){
		return (b_cost-((0.15)*(b_cost)));

	}
	public double nm_dis_ebook(){
		return (e_cost-((0.15)*(e_cost)));

	}

	public void set_m_rec_book_issue(boolean b){
		m_rec_book=b;
	}
	public void set_m_rec_ebook_issue(boolean b){
		m_rec_ebook=b;
	}
	public void set_nm_rec_book_issue(boolean b){
		nm_rec_book=b;
	}
	public void set_nm_rec_ebook_issue(boolean b){
		m_rec_ebook=b;
	}

	public boolean get_m_rec_book(){
		return m_rec_book;
	}
	public boolean get_m_rec_ebook(){
		return m_rec_ebook;
	}
	public boolean get_nm_rec_book(){
		return nm_rec_book;
	}
	public boolean get_nm_rec_ebook(){
		return nm_rec_ebook;
	}





}

