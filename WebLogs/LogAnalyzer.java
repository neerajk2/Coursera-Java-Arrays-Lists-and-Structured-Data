
/**
 * Analyzes and extracts various datasets from collection of LogEntry objects.
 * 
 * @author (Neeraj Kankani)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }

     public int countUniqueIps () {
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for (LogEntry le : records) {
             String currIp = le.getIpAddress();
             if (!uniqueIps.contains(currIp)) {
                 uniqueIps.add(currIp);
             }
         }
         return uniqueIps.size();
     }

     public void printAllHigherThanNumber(int num) {
         for (LogEntry le : records) {
             if (le.getStatusCode() > num) {
                 System.out.println(le);
             }
         }
     }

     public ArrayList<String> uniqueIPVisitsOnDay (String someday) {
         ArrayList<String> uniqueIpOnDay = new ArrayList<String>();
         for (LogEntry le : records) {
             String currDate = le.getAccessTime().toString();
             if (currDate.contains(someday)){
                 String currIp = le.getIpAddress();
                 if (!uniqueIpOnDay.contains(currIp)) {
                     uniqueIpOnDay.add(currIp);
                 }
             }
         }
         return uniqueIpOnDay;
     }

     public HashMap<String,Integer> countVisitsPerIp() {
         HashMap<String, Integer> IpMap = new HashMap<String, Integer>();
         for (LogEntry le : records){
             String currIP = le.getIpAddress();
             if (!IpMap.containsKey(currIP)) {
                 IpMap.put(currIP, 1);
             } else {
                 IpMap.put(currIP, IpMap.get(currIP)+1);
             }
         }
         return IpMap;
    }

    public int mostNumberVisitsByIP (HashMap<String, Integer> myMap) {
         int maxVisits = 0;
         for (String ip : myMap.keySet()) {
             if (myMap.get(ip) > maxVisits) maxVisits = myMap.get(ip);
         }
         return maxVisits;
    }

    public ArrayList<String> iPsMostVisits (HashMap<String,Integer> IPMap) {
         int mostVisits = mostNumberVisitsByIP(IPMap);
         ArrayList<String> mostVisitedIP = new ArrayList<String>();
        for (String ip : IPMap.keySet()) {
            if (IPMap.get(ip) == mostVisits) {
                mostVisitedIP.add(ip);
            }
        }
        return mostVisitedIP;
    }

    public HashMap<String, ArrayList<String>> iPsForDays (){
         HashMap<String, ArrayList<String>> daysIP = new HashMap<String, ArrayList<String>>();
         for (LogEntry le : records) {
             String currDate = le.getAccessTime().toString();
             currDate = currDate.substring(4, 10);
             if (!daysIP.containsKey(currDate)){
                 ArrayList<String> IPonDay = new ArrayList<String>();
                 for (LogEntry les : records) {
                     if (les.getAccessTime().toString().substring(4,10).equals(currDate)){
                         IPonDay.add(les.getIpAddress());
                     }
                 }
                 daysIP.put(currDate, IPonDay);
             }
         }
         return daysIP;
    }

    public String daysWithMostIPVisits (HashMap<String, ArrayList<String>> myMap){
         int maxVisits = 0;
         String busyDay = "";
         for (String currDate : myMap.keySet()){
             if (myMap.get(currDate).size() > maxVisits) {
                 maxVisits = myMap.get(currDate).size();
                 busyDay = currDate;
             }
         }
         return busyDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay (HashMap<String, ArrayList<String>> IPMap, String day) {
         ArrayList<String> currDateIPs = IPMap.get(day);
         ArrayList<String> mostOnDay = new ArrayList<String>();
         HashMap<String, Integer> IpOnSomeday = new HashMap<String, Integer>();
         for (int i = 0; i<currDateIPs.size(); i++) {
             String IP = currDateIPs.get(i);
             if (IpOnSomeday.isEmpty()){
                 IpOnSomeday.put(IP, 1);
                 continue;
             }
             if (!IpOnSomeday.containsKey(IP)){
                 IpOnSomeday.put(IP, 1);
             } else {
                 IpOnSomeday.put(IP, IpOnSomeday.get(IP)+1);
             }
         }
         mostOnDay = iPsMostVisits(IpOnSomeday);
         return mostOnDay;
    }

     public int countUniqueIPsInRange (int low, int high) {
         ArrayList<String> uniqueIpInRange = new ArrayList<String>();
         for( LogEntry le : records){
             if (le.getStatusCode() >= low && le.getStatusCode() <= high) {
                 String currIp = le.getIpAddress();
                 if (!uniqueIpInRange.contains(currIp)) {
                     uniqueIpInRange.add(currIp);
                 }
             }
         }
         return uniqueIpInRange.size();
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
