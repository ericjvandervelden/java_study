package streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import static org.junit.Assert.*;

import org.junit.Test;

public class LambdaTests {
	
	@Test
	public void test() {
		Person person=new Person();
		boolean b=process(person,p->p.getAge()>18);
		assertTrue(b);
	}
	private boolean process(Person p,Predicate<Person>tester) {
		return tester.test(p);
	}
	
	@Test
	public void test1() {
		Person person=new Person();
		// omdat het 1ste arg een Person is, is het 2de arg een Predicate<Person>,
		// dus kun je p.getAg() geven	,
		boolean b=process1(person,p->p.getAge()>18);
		assertTrue(b);
	}
	// <X> moet voor boolean
	private <X> boolean process1(X p,Predicate<X>tester) {
		return tester.test(p);
	}
	
	@Test
	public void test2() {
		Person person=new Person();
		Predicate<Person>tester=(p->p.getAge()>18);
		Predicate<Person>tester2=((p)->p.getAge()>18);
		Predicate<Person>tester3=((Person p)->p.getAge()>18);
		boolean b=tester.test(person);
		assertTrue(b);
	}
	
	@Test
	public void test3() {
		List<Integer>integers=Arrays.asList(1,2,3);
		List<String>strings=Arrays.asList("foo","bar");
		
	}
	
	@Test
	public void test4() {
		// Arrays.asList geeft een List, is a Collection, is a Iterable
		Iterable<Person>source=Arrays.asList(new Person(),new Person());
		Predicate<Person>tester=(p->p.getAge()>18);
		Function<Person,Integer>mapper=(p->p.getAge());
		Consumer<Integer>block=(s->System.out.println(s));
		for(Person person:source) {
			if(tester.test(person)) {
				Integer age=mapper.apply(person);
				block.accept(age);
			}
		}		
	}
	
	@Test
	public void test5() {
		Iterable<Person>persons=Arrays.asList(new Person(),new Person());
		// eclipse ziet .getAge()niet, maar accepteert hem wel,
		process5(persons, p->p.getAge()>18, p->p.getAge(), p->System.out.println(p));
		
	}
	private <T,R> void process5(Iterable<T>source,Predicate<T>tester,
			Function<T, R>mapper,Consumer<R>block) {
		for(T t:source) {
			if(tester.test(t)) {
				R r=mapper.apply(t);
				block.accept(r);
			}
		}				
		
	}
	
	@Test
	public void test6() {
		// we moeten hem nu als List declare, voor .stream()	,
		List<Person>persons=Arrays.asList(new Person(),new Person());
		persons
			.stream()
			.filter(p->p.getAge()>18)
			.map(p->p.getAge())
			.forEach(i->System.out.println(i));
	}
	

}
