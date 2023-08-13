package hello.java.제네릭;

import java.util.ArrayList;

class Fruit implements Eatable {
	public String toString() { return "Fruit"; }
}
class Apple extends Fruit {public String toString() { return "Apple"; }}
class Grape extends Fruit {public String toString() { return "Grape"; }}
class Toy				  {public String toString() { return "Toy"; }}

interface Eatable {}
/**
 * 제한된 제네릭 클랙스
 * @author wglee
 *
 */
public class LimitedGenericTest {

	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox();
		FruitBox<Apple> appleBox = new FruitBox();
		FruitBox<Grape> grapeBox = new FruitBox();
		/**
		 * 컴파일 에러 발생한다. Toy는 Fruit의 자손이 아닌데, FruitBox는 <T extends Fruit & Eatable> Fruit 으로 제한되어 있기 때문에 컴파일 에러 발생.
		 * 이런 구조를 만드려면 설계를 잘 해야할 듯. 
		 */
//		FruitBox<Toy> toyBox = new FruitBox();	
		
		fruitBox.add(new Fruit());
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
//		fruitBox.add(new Toy());	// Toy는 넣을 수 없다. 컴파일 에러 발생.
		
		System.out.println(fruitBox.toString());
	}
}

/**
 * Box 클래스를 제한된 제네릭을 사용하기 위해 정의한 클래스 
 * 인터페이스를 추가하는 경우 & 를 사용한다. 
 */
class FruitBox<T extends Fruit & Eatable> extends Box<T> {}

/**
 * 공통 클래스  
 */
class Box<T> {
	ArrayList<T> list = new ArrayList<>();
	void add(T item) { list.add(item); }
	T get(int i)	 { return list.get(i); }
	int size() 		 { return list.size(); }
	public String toString() { return list.toString(); }
}

/**
 * 제레닉의 제약사항
 * 
 */
class Limitations {
	
	void genericLimitations() {
		// 타입 변수에 대입은 인스턴스 별로 다르게 가능하다.
		Box<Apple> appleBox = new Box();
		Box<Grape> grapeBox = new Box();
		
		/**
		 *  1. static 멤버에는 타입 변수 사용 불가.
		 *  static은 모든 인스턴스에서 공통으로 사욯되기 때문에 인스턴스 별로 다르게 대입가능한 제네릭 타입은 사용 불가.
		 */
//		class Box<T> {
//			static T item;	// 컴파일 에러 발생
//			static int compare(T t1, T t2); // 컴파일 에러 발생
//		}
		
		
		/**
		 * 배열 및 객체 생성 시 타입 변수 사용 불가. 즉, new 다음에는 타입 변수 사용 불가하다.
		 * 단, 타입 변수로 배열 선언은 가능하다.
		 */
//		class Box2<T> {
//			T[] itemArr; // 타입 변수로 배열 선언은 가능하다
//			T[] toArray() {
//				T[] tmpArr = new T[itemArr.length]; // 배열 시 타입 변수 사용 불가
//			}
//		}
	}
}
