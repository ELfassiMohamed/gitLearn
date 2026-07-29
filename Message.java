import java.time.*;

public class Message {
	public final static void main (String[] args){
		var date1 = LocalDate.of(2022, Month.JANUARY, 20);
    var date2 = LocalDate.of(2022, 1, 20);

    System.out.println(date1);
    System.out.println(date2);
} }
