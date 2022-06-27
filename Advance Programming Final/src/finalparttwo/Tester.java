package finalparttwo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;



public class Tester {

	public static void main(String[] args) {

		List<Customer> cus = new ArrayList<Customer>();
		cus.add(new Customer("Muhammad","Rivalsyah", 2020, "Istanbul", 5));
		cus.add(new Customer("Batuhan","Satilmis", 2018, "Istanbul", 22));
		cus.add(new Customer("Riski","Mudafarsyah", 2018, "jakarta", 3));
		cus.add(new Customer("George","Petterson", 2011, "New York", 7));
		cus.add(new Customer("Emma","Watson", 2011, "Sydney", 2));
		cus.add(new Customer("Takashi","Oshiro", 2006, "Tokyo", 11));
		cus.add(new Customer("Amelia","Watson", 2020, "London", 8));
		cus.add(new Customer("Inomae","Ina", 2020, "Tokyo", 17));
		cus.add(new Customer("Sindy","Barbie", 2010, "Sydney", 5));
		cus.add(new Customer("Adem","Ozyavas", 2003, "Istanbul", 10));

		List<Customer> trancinyear2011 = new ArrayList<Customer>();
		List<Integer> value = new ArrayList<Integer>();
		
//1
		for(Customer d: cus)			
			if(d.getYear() == 2011)
				trancinyear2011.add(d);
		
		for(Customer d: cus)
			value.add(d.getTransaction());
		

		List<Integer> integ = 
				cus.stream()
				.filter(d-> d.getYear() == 2011)
				.map(d-> d.getTransaction())
				.sorted((a,b) -> a.compareTo(b))
				.collect(Collectors.toList());
		System.out.println("Transaction in 2011 : " + integ);
				
				
			
		
		
//2
		List<String> cities = new ArrayList<String>();
		for(Customer d: cus)
			cities.add(d.getCity());
		
		List<String> uniquecities =
				cus.stream()
				.map(d-> d.getCity())
				.distinct()
				.collect(Collectors.toList());
		System.out.println("Unique cities :" + uniquecities);
		
//3
		
		
		List<String> istanbulcus =
				cus.stream()
				.filter(d -> d.getCity() == "Istanbul")
				.map(d-> d.getName())
				.sorted((a,b) -> a.compareTo(b))
				.collect(Collectors.toList());
		System.out.println("Customers from Istanbul : "+ istanbulcus);
		
//4
		List<Customer> cusname = new ArrayList<Customer>();
		for(Customer d : cus)
			cusname.add(d);
	
		List<String> allnames =
				cus.stream()
				.map(d -> d.getName())
				.sorted((a,b) -> a.compareTo(b))
				.collect(Collectors.toList());
		
		System.out.println("Customers names : "+ allnames);
		
//5
		List<String> ankara = new ArrayList<String>();
		
		for(Customer d: cus)
			if(d.getCity() == "Ankara")
				ankara.add(d.getCity());
		
		List<String> cusankara =
				cus.stream()
				.filter(d -> d.getCity() == "Ankara")
				.map(d -> d.getName())
				.collect(Collectors.toList());
		
		if (cusankara != null)
			System.out.println("No customers are from Ankara");
		else
			System.out.println("Customers from Ankara : " + cusankara);
		
		
//6		
		List<Integer> valuesfromistanbul =
				cus.stream()
				.filter(d -> d.getCity() == "Istanbul")
				.map(Customer::getTransaction)
				.collect(Collectors.toList());
		System.out.println("Transaction Values from Istanbul : " + valuesfromistanbul);
		
//7
		
		int max = value.stream()
				.collect(Collectors.summarizingInt(Integer::intValue)).getMax();
		System.out.println("Max transaction value is "+ max);
		
//8
		int min = value.stream()
				.collect(Collectors.summarizingInt(Integer::intValue)).getMin();
		System.out.println("Min transaction value is "+ min);
		
//9
		
		List<Customer> valuelessthan10 = new ArrayList<Customer>();
		for (Customer c : cus)
			if (c.getTransaction() < 10)
				valuelessthan10.add(c);
		
		List<Integer> lessthan10 =
				cus.stream()
				.filter(d -> d.getTransaction() < 10)
				.map(d -> d.getTransaction())
				.collect(Collectors.toList());
		
		System.out.println("Transaction less than 10 :" + lessthan10);
	}
	

}

@FunctionalInterface
interface MyPredicate<T>{
	boolean mytest(T arg);
}

class SomeFilter<T> implements Predicate<T>{
	public boolean mytest(T arg) {
		return true;
	}

	@Override
	public boolean test(T t) {
		// TODO Auto-generated method stub
		return false;
	}
}

class Customer {
	private String name;
	private String surname;
	private int year;
	private String city;
	private int transaction;
	public Customer (String n, String s, int y, String c, int t)
	{
		this.name = n;
		this.surname = s;
		this.year = y;
		this.city = c;
		this.transaction = t;
				
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public int getYear() {
		return year;
	}
	public String getCity() {
		return city;
	}
	public int getTransaction() {
		return transaction;
	}

	
}
