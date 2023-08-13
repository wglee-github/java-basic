package hello.java.제네릭;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

class Mom2 {};
class Fruit3 extends Mom2	{ public String toString() { return "Fruit";}}
class Apple3 extends Fruit3 { public String toString() { return "Apple";}}
class Grape3 extends Fruit3 { public String toString() { return "Grape";}}

/**
 * 
 * 제네릭 클래스
 */
@Slf4j
class GenericClass<T>{
	private T object;
	private List<T> list;
	
	public GenericClass() {
	}

	public GenericClass(List<T> list) {
		this.list = list;
	}

	public T getGeneric(T object) {
		return object;
	}
	
	public void generalMethod(T object) {
		this.object = object;
		System.out.println("memberName=" + this.object);
	}
	
	public void printLIst() {
		this.list.forEach(list -> log.info("list={}", list));
	}
	
	/**
	 * 제네릭 메서드
	 * 제네릭 메서드에 선언된 타입 매개변수는 메서드 내에서만 유효하다. 
	 * 클래스에 선언된 타입 매개변수와는 전혀 상관없다. 심지어 이름이 같아도 상관없다. (이름이 같은 경우 사용은 되나 경고가 발생함) 
	 * 제네릭 메서드의 타입 매개변수 선언은 반환타입 앞에 해주면 된다. 
	 * 제네릭 메서드의 타입 매개변수는 반환타입과 파라미터에 영향을 준다.( Ex. public <E> E getList(E name){} )
	 */
	public <E> E getList(E name) {
		log.info("제네릭 메서드");
		return name;
	}
	
	/**
	 * 메서드의 매개변수로 제네릭 타입변수를 사용할 수 없다.
	 * Ex. public void makeJuice3(FruitBox3<T extends Mom2> box) <- 오류 발생
	 * 
	 * 그럼 사용하려면
	 * 1. 와일드카드로 변경해야한다.
	 * 2. 제네릭 메서드로 만들면 된다.
	 * 
	 * static 메서드의 매개변수에 제네릭 타입변수를 받을 수 없으나, 제네릭 메서드로 만들면 가능하다.
	 */
	public void makeJuice3(FruitBox3<? extends Mom2> box) {
		log.info("제한된 제네릭 메서드");
	}
	static <T extends Fruit3> void makeJuice2(FruitBox3<T> box) {
		log.info("제한된 제네릭 메서드");
	}
	
}

@Slf4j
public class MethodGeneric {

	public static void main(String[] args) {
		GenericClass<ContInfo> cont = new GenericClass<>();
		Integer name = cont.getList(123);
		log.info("name={}", name);
		
		
//		GenericClass<ContInfo> cont = new GenericClass<>();
//		ContInfo contInfo = new ContInfo("C1234",10000);
//		cont.generalMethod(contInfo);
//		
//		GenericClass<Saving> save = new GenericClass<>();
//		Saving saving = new Saving("S1234",20000);
//		save.generalMethod(saving);
		
//		List<ContInfo> listCont = new ArrayList<>();
//		listCont.add(new ContInfo("C0001", 10000));
//		listCont.add(new ContInfo("C0002", 20000));
//		listCont.add(new ContInfo("C0003", 30000));
//		GenericClass<ContInfo> genericCont = new GenericClass<>(listCont);
//		genericCont.printLIst();
//		
//		List<Saving> listSave = new ArrayList<>();
//		listSave.add(new Saving("S0001", 11000));
//		listSave.add(new Saving("S0002", 22000));
//		listSave.add(new Saving("S0003", 33000));
//		GenericClass<Saving> genericSaving = new GenericClass<>(listSave);
//		genericSaving.printLIst();
	}
	
}

@Data
class ContInfo {
	private String noContract;
	private Integer amtBalance;
	
	public ContInfo(String noContract, Integer amtBalance) {
		this.noContract = noContract;
		this.amtBalance = amtBalance;
	}
	
}

@Data
class Saving {
	private String noContract;
	private Integer amtBalance;
	public Saving(String noContract, Integer amtBalance) {
		this.noContract = noContract;
		this.amtBalance = amtBalance;
	}
	
}

/**
 * Box 클래스를 제한된 제네릭을 사용하기 위해 정의한 클래스 
 * 인터페이스를 추가하는 경우 & 를 사용한다. 
 */
class FruitBox3<T extends Fruit3> extends Box3<T> {}

/**
 * 공통 클래스  
 */
class Box3<T> {
	ArrayList<T> list = new ArrayList<>();
	void add(T item) { list.add(item); }
	T get(int i)	 { return list.get(i); }
	int size() 		 { return list.size(); }
	ArrayList<T> getList() {return list;}
	public String toString() { return list.toString(); }
}