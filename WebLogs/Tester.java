
/**
 * Tester class for all the methods in LogEntry and LogAnalyzer.
 * 
 * @author (Neeraj Kankani)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        //analyzer.printAll();
        HashMap<String, Integer> IPMap = analyzer.countVisitsPerIp();
        //System.out.println(IPMap+"\n");
        System.out.println(analyzer.iPsMostVisits(IPMap)+"\n");
        HashMap<String, ArrayList<String>> dayIPs = analyzer.iPsForDays();
        //System.out.println(dayIPs+"\n");
        System.out.println(analyzer.daysWithMostIPVisits(dayIPs)+"\n");
        System.out.println("most: "+analyzer.mostNumberVisitsByIP(IPMap)+"\n");
        System.out.println(analyzer.iPsWithMostVisitsOnDay(dayIPs, "Mar 17")+"\n");
        int unique = analyzer.countUniqueIps();
        System.out.println("Number of unique Ips are "+unique+"\n");
        //analyzer.printAllHigherThanNumber(400);
        System.out.println(analyzer.countUniqueIPsInRange(200,299)+"\n");
    }

    public void testUniqueIpVisitsOnDay () {
        LogAnalyzer analyzer = new LogAnalyzer();
        analyzer.readFile("weblog1_log");
        System.out.println(analyzer.uniqueIPVisitsOnDay("Mar 17"));
    }


    public static void main(String[] args) {
        Tester test = new Tester();
        test.testLogAnalyzer();
        //test.testLogEntry();
        //test.testUniqueIpVisitsOnDay();
    }
}
