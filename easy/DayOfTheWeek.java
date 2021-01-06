package leetcode.easy;

/*
 * Given a date, return the corresponding day of the week for that date.

The input is given as three integers representing the day, month and year respectively.

Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.

 

Example 1:

Input: day = 31, month = 8, year = 2019
Output: "Saturday"
Example 2:

Input: day = 18, month = 7, year = 1999
Output: "Sunday"
Example 3:

Input: day = 15, month = 8, year = 1993
Output: "Sunday"
 

Constraints:

The given dates are valid dates between the years 1971 and 2100.
 */
public class DayOfTheWeek {
    public static String dayOfTheWeek(int day, int month, int year) {
        String []strArr = {"Thursday","Friday", "Saturday","Sunday", "Monday", "Tuesday", "Wednesday"};
        int []monthDay = {31,28,31,30,31,30,31,31,30,31,30,31};
        int []yearDay = {365, 366};
        
        int iday = 0;
        
        for(int y=1971; y<year;y++){
           if((0==y%4&&0!=y%100) || 0==y%400){
               iday += yearDay[1];
           } 
           else{
               iday += yearDay[0];
           }
        }
        
        for(int m=0; m<month-1; m++){
            iday += monthDay[m];
        }
        
        if((0==year%4&&0!=year%100) || 0==year%400){
            if(month > 2){
                iday += 1;
            }
        }
        
        iday += day;
        
        return strArr[iday%7];
    }
    
    public static void main(String args[]) {
    	System.out.print(dayOfTheWeek(20, 7, 2020));
    }
}
