/* Name: Caroline Yao & Horng-Bin Justin Wei
 * EID: Chy253 & Hjw396
 * Section: Thursday 3:30-5:30pm, Friday 2-3:30pm
 * EE 422C Assignment 6
 */
package Assignment6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ThreadedTicketClient implements Runnable {
	String hostname = "127.0.0.1";
	String threadname = "X";
	TicketClient sc;
	int port;

	public ThreadedTicketClient(TicketClient sc, String hostname, String threadname, int port) {
		this.sc = sc;
		this.hostname = hostname;
		this.threadname = threadname;
		this.port = port;
	}

	public void run() {
		System.out.flush();
		//produces a number between 728 and 1000
		for(int people = (int) (Math.random()*272) + 728; people > 0; people--) {
			try {
				Socket echoSocket = new Socket(hostname, port);	//selects the server
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				out.println(threadname); //sends request to server
				if(in.readLine().equals("sold out")){
					echoSocket.close();
					break;
				}
				echoSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}

public class TicketClient {
	ThreadedTicketClient tc;
	String result = "dummy";
	String hostName = "";
	String threadName = "";

	TicketClient(String hostname, String threadname, int port) {
		tc = new ThreadedTicketClient(this, hostname, threadname, port);
		hostName = hostname;
		threadName = threadname;
	}

	TicketClient(String name, int port) {
		this("localhost", name, port);
	}

	TicketClient() {
		this("localhost", "unnamed client", 00000);
	}

	void requestTicket() {
		// TODO thread.run()
		tc.run();
	}

	void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
