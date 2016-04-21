/* Name: Caroline Yao & Horng-Bin Justin Wei
 * EID: Chy253 & Hjw396
 * Section: Thursday 3:30-5:30pm, Friday 2-3:30pm
 * EE 422C Assignment 6
 */
package Assignment6;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestTicketOffice {

	public static int score = 0;

	// @Test
	public void basicServerTest() {
		try {
			TicketServer.start(16789);
		} catch (Exception e) {
			fail();
		}
		TicketClient client = new TicketClient();
		client.requestTicket();
	}

	// @Test
	public void testServerCachedHardInstance() {
		try {
			TicketServer.start(16790);
		} catch (Exception e) {
			fail();
		}
		TicketClient client1 = new TicketClient("localhost", "c1", 16790);
		TicketClient client2 = new TicketClient("localhost", "c2", 16790);
		client1.requestTicket();
		client2.requestTicket();

	}

	// @Test
	public void threeNonConcurrentClientTest() {
		try {
			TicketServer.start(16791);
		} catch (Exception e) {
			fail();
		}
		TicketClient c1 = new TicketClient("nonconc1", 16791);
		TicketClient c2 = new TicketClient("nonconc2", 16791);
		TicketClient c3 = new TicketClient("nonconc3", 16791);
		c1.requestTicket();
		c2.requestTicket();
		c3.requestTicket();
	}

	// @Test
	public void fourNonConcurrentClientTest() {
		try {
			TicketServer.start(16791);
		} catch (Exception e) {
			fail();
		}
		TicketClient c1 = new TicketClient("nonconc1", 16791);
		TicketClient c2 = new TicketClient("nonconc2", 16791);
		TicketClient c3 = new TicketClient("nonconc3", 16791);
		TicketClient c4 = new TicketClient("nonconc4", 16791);
		c1.requestTicket();
		c2.requestTicket();
		c3.requestTicket();
		c4.requestTicket();
	}

	// @Test
	public void threeConcurrentClientTest() {
		try {
			TicketServer.start(16792);
		} catch (Exception e) {
			fail();
		}
		final TicketClient c1 = new TicketClient("conc1", 16792);
		final TicketClient c2 = new TicketClient("conc2", 16792);
		final TicketClient c3 = new TicketClient("conc3", 16792);
		Thread t1 = new Thread() {
			public void run() {
				c1.requestTicket();
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				c2.requestTicket();
			}
		};
		Thread t3 = new Thread() {
			public void run() {
				c3.requestTicket();
			}
		};
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// @Test
	public void twoConcurrentServerTest() {
		try {
			TicketServer.start(12345);
			TicketServer.start(17283);
		} catch (Exception e) {
			fail();
		}
		final TicketClient c1 = new TicketClient("conc1", 12345);
		final TicketClient c2 = new TicketClient("conc2", 17283);
		Thread t1 = new Thread() {
			public void run() {
				c1.requestTicket();
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				c2.requestTicket();
			}
		};
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void twoConcurrentServersWithMultipleClientsTest() {
		try {
			TicketServer.start(12345);
			TicketServer.start(17283);
		} catch (Exception e) {
			fail();
		}
		final TicketClient c1 = new TicketClient("conc1", 12345);
		final TicketClient c2 = new TicketClient("conc2", 12345);
		final TicketClient c3 = new TicketClient("conc3", 17283);
		final TicketClient c4 = new TicketClient("conc4", 17283);
		Thread t1 = new Thread() {
			public void run() {
				c1.requestTicket();
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				c2.requestTicket();
			}
		};
		Thread t3 = new Thread() {
			public void run() {
				c3.requestTicket();
			}
		};
		Thread t4 = new Thread() {
			public void run() {
				c4.requestTicket();
			}
		};
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
