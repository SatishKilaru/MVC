package satish;

import java.sql.Timestamp;

public class CalFare {
	public int calfare(Ticket t) {
		int nop = (t.getPl()).size();
		int x = 100;
		int fare;
		fare = nop * x;
		return fare;
	}

	public String getpnr() {
		Timestamp t = new Timestamp(System.currentTimeMillis());
		long p = t.getTime();
		String pnr = String.valueOf(p);
		return pnr;
	}
}
