//package partc;

abstract public class Item {
	private int item_id;
	private String add_date;

	Item(int item_id,String add_date){
		this.setItem_id(item_id);
		this.setAdd_date(add_date);

	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}



}
///read about the