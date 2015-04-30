//package partc;

public class Software extends Item  {

	private boolean download;
	private double cd_price;
	private double download_price;
	private boolean cd_issue=false;
	private boolean download_issue=false;
	private int number_of_cd;
	private String title;
	private String pub;
	boolean m_rec_cd=false;
	boolean m_rec_down=false;
	boolean nm_rec_cd=false;
	boolean nm_rec_down=false;


	private int cd_sold=0;

	private int download_sold=0;



	Software(int item_id,String add_date,String title,String pub,boolean download,double cd_price,double download_price,int number_of_cd){
		super(item_id,add_date);
		this.title=title;
		this.pub=pub;
		this.download=download;
		this.cd_price=cd_price;
		this.download_price=download_price;
		this.number_of_cd=number_of_cd;

	}
	public String get_title() {

		return title;
	}
	public void show_software(){
		System.out.print(" "+get_title() +" by  "+ get_publisher()+" | download available: " + download_available()+
				" | number of cds available: "+get_number_of_cd());
	}



	public void cart_for_download(){
		if(get_m_rec_down()){
			System.out.print(" "+get_title() +" by  "+ get_publisher()+" in cd format" + " $ "+m_dis_down());	
		}
		else if(get_nm_rec_down()){
			System.out.print(" "+get_title() +" by  "+ get_publisher()+" in cd format" + " $ "+nm_dis_down());
		}
		else
			System.out.print(" "+get_title() +" by  "+ get_publisher()+" in  download format" + " $ "+get_download_cost()); 
	}

	public void cart_for_cd(){
		if(get_m_rec_cd()){
			System.out.print(" "+get_title() +" by  "+ get_publisher()+" in cd format" + " $ "+m_dis_cd());	
		}
		else if(get_nm_rec_cd()){
			System.out.print(" "+get_title() +" by  "+ get_publisher()+" in cd format" + " $ "+nm_dis_cd());
		}
		else
			System.out.print(" "+get_title() +" by  "+ get_publisher()+" in cd format" + " $ "+get_cd_cost());
	}
	public void download_sales(){
		System.out.print(" "+get_title() +" by  "+ get_publisher()+" in  download format" + " $ "+get_download_cost()+"   "+getDownload_sold()); 
	}

	public void cd_sales(){
		System.out.print(" "+get_title() +" by  "+ get_publisher()+" in cd format" + " $ "+get_cd_cost()+"   "+getCd_sold());
	}

	public void show_my_cart(){
		System.out.print(" "+get_title() +" by  "+ get_publisher() );
	}
	public void show_details(){
		System.out.print(get_title() +"  "+ get_publisher());
	}

	public String download_available()
	{
		if(download)
			return "Yes";
		else{
			return "No";
		} 
	}
	public void add_copies(){
		number_of_cd++;
	}

	public String get_publisher(){
		return pub;
	}

	public int get_number_of_cd()
	{
		return number_of_cd;
	}

	public void reduce_copies()
	{
		this.number_of_cd--;
		setCd_sold();
	}
	public boolean check_download_available(){
		return download;
	}
	public double get_download_cost(){
		return download_price ;

	}

	public double get_dis_memeber_download_cost(){
		return download_price-(1) ;

	}

	public double get_cd_cost(){
		return cd_price ;

	}
	public void set_download_issue(boolean b) {
		// TODO Auto-generated method stub
		download_issue=b;
		setDownload_sold();
	}
	public boolean get_download_issue() {
		// TODO Auto-generated method stub
		return download_issue;
	}
	public boolean isCd_issue() {
		return cd_issue;
	}
	public void setCd_issue(boolean cd_issue) {
		this.cd_issue = cd_issue;
	}

	public int getCd_sold() {
		return cd_sold;
	}
	public void setCd_sold() {
		cd_sold++;
	}

	public int getDownload_sold() {
		return download_sold;
	}
	public void setDownload_sold() {
		download_sold++;
	}

	public double m_dis_cd(){
		return (cd_price-((0.25)*(cd_price)));

	}
	public double m_dis_down(){
		return (download_price-((0.25)*(download_price)));

	}

	public double nm_dis_cd(){
		return (cd_price-((0.15)*(cd_price)));

	}
	public double nm_dis_down(){
		return (download_price-((0.15)*(download_price)));

	}
	public void set_m_rec_cd_issue(boolean b){
		m_rec_cd=b;
	}
	public void set_m_rec_down_issue(boolean b){
		m_rec_down=b;
	}
	public void set_nm_rec_cd_issue(boolean b){
		nm_rec_cd=b;
	}
	public void set_nm_rec_down_issue(boolean b){
		m_rec_down=b;
	}

	public boolean get_m_rec_cd(){
		return m_rec_cd;
	}
	public boolean get_m_rec_down(){
		return m_rec_down;
	}
	public boolean get_nm_rec_cd(){
		return nm_rec_cd;
	}
	public boolean get_nm_rec_down(){
		return nm_rec_down;
	}


}

