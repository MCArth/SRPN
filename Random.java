import java.util.ArrayList;

/**
* Provides random number to ProcessCommand when 'r' is used by user
*
* @author Arthur Baker
* @version 1.0
* @release 23/11/2018
*/

public class Random {

	private DoStack myStack = new DoStack();
	ArrayList<Integer> randNums = new ArrayList<>();	
	static int numOfUses = -1; //Set as -1 as is used to get list index and is incremented before doing so
	
	
	/**
	* Constructor. Adds first 134 random numbers to randNums ArrayList.
	*/
	public Random() {
		//These random numbers are repeated twice
		for(int i=0;i<2;i++) {	
			randNums.add(1804289383);
			randNums.add(846930886);
			randNums.add(1681692777);
			randNums.add(1714636915);
			randNums.add(1957747793);
			randNums.add(424238335);
			randNums.add(719885386);
			randNums.add(1649760492);
			randNums.add(596516649);
			randNums.add(1189641421);
			randNums.add(1025202362);
			randNums.add(1350490027);
			randNums.add(783368690);
			randNums.add(1102520059);
			randNums.add(2044897763);
			randNums.add(1967513926);
			randNums.add(1365180540);
			randNums.add(1540383426);
			randNums.add(304089172);
			randNums.add(1303455736);
			randNums.add(35005211);
			randNums.add(521595368);
		}
		randNums.add(294702567);
		randNums.add(1726956429); 
		randNums.add(336465782);
		randNums.add(861021530);
		randNums.add(278722862);
		randNums.add(233665123);
		randNums.add(2145174067);
		randNums.add(468703135);
		randNums.add(1101513929);
		randNums.add(1801979802);
		randNums.add(1315634022);
		randNums.add(635723058);
		randNums.add(1369133069);
		randNums.add(1125898167);
		randNums.add(1059961393);
		randNums.add(2089018456);
		randNums.add(628175011);
		randNums.add(1656478042);
		randNums.add(1131176229);
		randNums.add(1653377373);
		randNums.add(859484421);
		randNums.add(1914544919);
		randNums.add(608413784);
		randNums.add(756898537);
		randNums.add(1734575198);
		randNums.add(1973594324);
		randNums.add(149798315);
		randNums.add(2038664370);
		randNums.add(1129566413);
		randNums.add(184803526);
		randNums.add(412776091);
		randNums.add(1424268980);
		randNums.add(1911759956);
		randNums.add(749241873);
		randNums.add(137806862);
		randNums.add(42999170);
		randNums.add(982906996);
		randNums.add(135497281);
		randNums.add(511702305);
		randNums.add(2084420925);
		randNums.add(1937477084);
		randNums.add(1827336327);
		randNums.add(572660336);
		randNums.add(1159126505);
		randNums.add(805750846);
		randNums.add(1632621729);
		randNums.add(1100661313);
		randNums.add(1433925857);
		randNums.add(1141616124);
		randNums.add(84353895);
		randNums.add(939819582);
		randNums.add(2001100545);
		randNums.add(1998898814);
		randNums.add(1548233367);
		randNums.add(610515434);
		randNums.add(1585990364);
		randNums.add(1374344043);
		randNums.add(760313750);
		randNums.add(1477171087);
		randNums.add(356426808);
		randNums.add(945117276);
		randNums.add(1889947178);
		randNums.add(1780695788);
		randNums.add(709393584);
		randNums.add(491705403);
		randNums.add(1918502651);
		randNums.add(752392754);
		randNums.add(1474612399);
		randNums.add(2053999932);
		randNums.add(1264095060);
		randNums.add(1411549676);
		randNums.add(1843993368);
		randNums.add(943947739);
		randNums.add(1984210012);
		randNums.add(855636226);
		randNums.add(1749698586);
		randNums.add(1469348094);
		randNums.add(1956297539);
		randNums.add(1036140795);
		randNums.add(463480570);
		randNums.add(2040651434);
		randNums.add(1975960378);
		randNums.add(317097467);
		randNums.add(1892066601);
		randNums.add(1376710097);
		randNums.add(927612902);
		randNums.add(1330573317);
		randNums.add(603570492);
	}
	
	/**
	* Function to be called when 'r' is entered.
	* Manages how many times it has been called and returns number based on that.
	* 
	* @return Returns the random number that the program has reached.
	*/
	public long getRandomNum(){
		
		//Checks stack isn't full
		if(myStack.getSize()<23) {
			numOfUses++;
		}
		
		//Makes numOfUses loop back round and not get index error if r is called more than 134 times
		//This will stop matching srpn random outputs but there has to be a limit of how many random numbers I put in (and this ensures no exception is thrown)
		if(numOfUses>133) {
			numOfUses=-1;
			System.err.println("No Longer matching srpn random outputs.");
		}
		
		return (long) randNums.get(numOfUses);
	}
}
