package hello.java.제네릭;

import java.util.ArrayList;

class Product {}
class Tv extends Product {}
class Audio extends Product {}

public class ArrayLIstTest {

	public static void main(String[] args) {
		ArrayList<Product> producList = new ArrayList<>();
		ArrayList<Tv> tvList = new ArrayList<>();
		
		// 타입변수에 지정된 타입의 자손은 포함할 수 있다.
		producList.add(new Tv());
		producList.add(new Audio());
		
		tvList.add(new Tv());
		tvList.add(new Tv());
		
		printList(producList);
//		printList(tvList);	// 컴파일 에러가 발생한다. printList의 매개변수 타입이 ArrayList<Product> 이기 때문
		
		// 제네릭 타입으로 모든 ArrayList를 받을 수 있도록 printAll 메서드를 만들었다.
		printAll(producList);
		printAll(tvList);	// 컴파일 에러 없이 둘다 가능하다.
		
	}

	private static void printList(ArrayList<Product> producList) {
		for (Product product : producList) {
			System.out.println(product);
		}
	}
	
	/**
	 * static 메서드 및 변수에는 제네릭 타입변수를 사용할 수 없다.
	 */
	private static <E> void printAll(ArrayList<E> producList) {
		for (E product : producList) {
			System.out.println(product);
		}
	}
}
