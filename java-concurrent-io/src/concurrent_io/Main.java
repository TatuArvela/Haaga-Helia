package concurrent_io;

public class Main {
	public static void main(String args[]) {
		// Console headers
		System.out.println("[ Java concurrency and I/O exercise, Tatu Arvela 2017 ]");
		System.out.println("Working Directory = " + System.getProperty("user.dir") + "\n");
		
		IO io1 = new IO(1);
		io1.start();

		IO io2 = new IO(2);
		io2.start();
	}
}
