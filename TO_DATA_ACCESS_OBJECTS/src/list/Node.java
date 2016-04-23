package list;

public class Node {

	private Node child;
	private String data;

	Node(String input) {
		data = input;
	}

	public String getData() {
		return data;
	}

	public Node getChild() {
		return child;
	}

	public void setChild(Node child) {
		this.child = child;
	}

	public void setData(String data) {
		this.data = data;
	}

}
