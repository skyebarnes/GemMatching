package pkg;

public class GemList {
	int size;
	Node head;

	private class Node {
		private Gem gem;
		private Node next;
	}

	public GemList() {
		head = new Node();
	}

	public int size() {
		return size;
	}

	public void draw(double y) {
		Node current = head;
		double i = 0;
		if (current.gem != null) {
			current.gem.draw(i, y);
			i += .066;
		}
		while (current.next != null) {
			current.next.gem.draw(i, y);
			i += .066;
			current = current.next;
		}
	}

	public String toString() {
		Node current = head;
		if (current.gem == null)
			return "<none>";

		String gems = "" + current.gem.getType();
		while (current.next != null) {
			current = current.next;
			gems += " --> " + current.gem.getType();
		}
		return gems;
	}

	public void insertBefore(Gem gem, int index) {
		Node current = head;
		int i = 0;
		boolean added = false;

		Node add = new Node();
		add.gem = gem;

		if (head.gem == null) {
			head.gem = gem;
			added = true;
		}
		
		if (index == 0 && !added) {
			Node temp = new Node();
			temp.gem = head.gem;
			temp.next = head.next;
			add.next = head;
			head = add;
			added = true;
		}
		
		while (current.next != null && !added) {
			if (i == index - 1) {
				add.next = current.next;
				current.next = add;
				added = true;
			}
			i++;
			current = current.next;
		}

		if (!added) {
			current.next = add;
		}

		size++;
	}

	int score() {
		if (head.gem == null)
			return 0;
		else if (head.next == null)
			return head.gem.getPoints();

		Node current = head;
		int score = 0;

		while (current != null) {
			int multiple = 1;
			int score2 = current.gem.getPoints();

			try {
				while (current.gem.getType() == current.next.gem.getType()) {
					score2 += current.next.gem.getPoints();
					multiple++;
					current = current.next;
				}
			} catch (Exception e) {
			}

			score += score2 * multiple;
			current = current.next;
		}
		return score;
	}

	public static void main(String[] args) {
		GemList list = new GemList();
		System.out.println(list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);

		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);

		list.insertBefore(new Gem(GemType.BLUE, 20), 99); // not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);

		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);

		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);

		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);

		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);
	}
}
