package hello.java.제네릭;

import java.util.ArrayList;
class Mom {};
class Fruit2 extends Mom	{ public String toString() { return "Fruit";}}
class Apple2 extends Fruit2 { public String toString() { return "Apple";}}
class Grape2 extends Fruit2 { public String toString() { return "Grape";}}

class Juice {
	String name;
	
	Juice(String name){ this.name = name + "Juice"; }
	public String toString() {return name; }
}

class Juicer {
	static Juice makeJuice(FruitBox2<? extends Fruit2> box) {
		String tmp = "";
		
		for (Fruit2 f : box.getList()) {
			tmp += f + " ";
		}
		return new Juice(tmp);
	}
	/**
	 * 제네릭 메서드의 타입 선언은 반환타입 앞에 해주면 된다. 
	 * 제네릭 메서드에 제한된 제네릭 타입을 선언할 경우 ? 사용 안된다. 일반 제네릭 단어 사용 가능 Ex. T, E 등
	 * 
	 */
	static <T extends Fruit2> Juice makeJuice2(FruitBox2<T> box) {
		String tmp = "";
		
		// 
		for (T f : box.getList()) {
			tmp += f + " ";
		}
		return new Juice(tmp);
	}
}

/**
 * 와일드 카드란 하나의 참조 변수에 타입이 다른 객체가 참조가능하게 하도록 하기 위한 기능이다.
 * 기본
 * Ex. FruitBox2<Fruit2> fruitBox = new FruitBox2<Fruit2>();
 * 
 * 와일드 카드
 * <? extends T> : T와 그 자손들만 가능
 * Ex. FruitBox2<?> fruitBox = new FruitBox2<모든객체가능>();
 * 
 * <? super T> : T와 그 조상들만 가능
 * Ex. FruitBox2<? extends Fruit2> fruitBox = new FruitBox2<Fruit를포함한자손객체>();
 * 
 * <?> : 제한 없음. 모든 타입 가능
 * Ex. FruitBox2<? super Fruit2> fruitBox = new FruitBox2<Fruit2를포함한조상객체>();
 * 
 *
 */
public class WwildCardGeneric {
	
	public static void main(String[] args) {
		/**
		 *  참조변수의 변수타입에 <? extends Fruit2>를 주면 Fruit2 와 그 자손들 모두 참조가 가능해 진다.
		 *  
		 *  참조변수의 타입 변수 생성 기준
		 *  1. class FruitBox2<T extends Fruit2> 에서 벗어날 수 없다. 
		 *     Fruit2의 조상과 그 자손들로만 선언 가능 별개의 Toy 클랙스 선언 불가.
		 */
		FruitBox2<? extends Fruit2> allBox = new FruitBox2<Apple2>();
		allBox = new FruitBox2<Fruit2>();
		allBox = new FruitBox2<Apple2>();
		allBox = new FruitBox2<Grape2>();
		
		
		FruitBox2<Fruit2> fruitBox = new FruitBox2<Fruit2>();
		FruitBox2<Apple2> appleBox = new FruitBox2<Apple2>();
		
		fruitBox.add(new Apple2());
		fruitBox.add(new Grape2());
		appleBox.add(new Apple2());
		appleBox.add(new Apple2());
		
		// makeJuice의 매개변수의 타입 변수에 와이드 카드를 적용했기 때문에 fruit, apple 둘다 전달이 가능하다.
		System.out.println(Juicer.makeJuice(fruitBox).toString());
		System.out.println(Juicer.makeJuice(appleBox).toString());
	}
	
}

/**
 * Box 클래스를 제한된 제네릭을 사용하기 위해 정의한 클래스 
 * 인터페이스를 추가하는 경우 & 를 사용한다. 
 */
class FruitBox2<T extends Fruit2> extends Box2<T> {}

/**
 * 공통 클래스  
 */
class Box2<T> {
	ArrayList<T> list = new ArrayList<>();
	void add(T item) { list.add(item); }
	T get(int i)	 { return list.get(i); }
	int size() 		 { return list.size(); }
	ArrayList<T> getList() {return list;}
	public String toString() { return list.toString(); }
}
