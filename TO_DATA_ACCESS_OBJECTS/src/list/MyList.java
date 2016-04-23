package list;

public class MyList {

	private Node head = null;

	public void reverse() {
		if (head == null || head.getChild() == null) {
			return;
		}

		boolean end = false;
		Node hold = head;
		while (end == false) {
			head = head.getChild();
			if (head.getChild() == null)
				end = true;
			head.setChild(hold);
			hold = head;
		}
	}

	public void add(String data) {
		if (head == null) {
			head = new Node(data);
			return;
		}

		Node hold = head;
		while (hold.getChild() != null) {
			hold = hold.getChild();
		}
		hold.setChild(new Node(data));
	}

	public void print() {
		Node hold = head;
		while (hold != null) {
			System.out.println(hold.getData());
			hold = hold.getChild();
		}
	}

	public static void main(String[] args) {
		MyList test = new MyList();

		test.add("test 1");
		test.add("test 2");
		test.add("test 3");

		test.print();
		test.reverse();
		test.print();
	}
}
