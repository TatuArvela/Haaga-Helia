package concurrent_io;

import java.io.*;
import java.sql.Timestamp;
import java.util.Random;

public class IO extends Thread {
	private Thread t;
	private String threadName;
	private int threadNumber;

	/**
     * Concurrent I/O test.
     * Loads an input file (input#.txt) and saves its contents to an output file (output#.txt).
     * @param num The thread's number (should be unique)
     */
	IO(int num) {
		threadNumber = num;
		threadName = "I/O Thread " + num;
		System.out.println(threadName + ": Created");
	}

    /**
     * Initializes the file I/O streams and executes the program.
     */
	public void run() {
		System.out.println(threadName + ": Started");
		
		PrintWriter clear = null;
		BufferedReader in = null;
		BufferedWriter out = null;
		
		try {
			String inputFileName = "input" + threadNumber + ".txt";
			String outputFileName = "output" + threadNumber + ".txt";
			Random rnd = new Random();
			
			// Clear output file contents
			clear = new PrintWriter(outputFileName);
			clear.close();
			
			in = new BufferedReader(new FileReader(inputFileName));
			out = new BufferedWriter(new FileWriter(outputFileName, true));

			// Write starting timestamp
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			out.write("[Start: " + timestamp.toString() + "]");
			
			// Copy input file contents to output file and wait for n milliseconds
			String line;
			while ((line = in.readLine()) != null) {
				out.append("\n" + line);
			}
			int waitFor = rnd.nextInt(500);
			Thread.sleep(0);

			// Write ending timestamp
			timestamp = new Timestamp(System.currentTimeMillis());
			out.append("\n[End: " + timestamp.toString() + "]");
			
			System.out.println(threadName + ": Copied data from '" + inputFileName + "' to '" + outputFileName + "'");
		} catch (IOException e) {
			System.out.println(threadName + ": Error!\n");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println(threadName + ": Error!\n");
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(threadName + ": Exiting");
	}

	/**
     * Starts a new I/O thread.
     */
	public void start() {
		System.out.println(threadName + ": Starting...");
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
