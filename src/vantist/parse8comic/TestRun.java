package vantist.parse8comic;

public class TestRun {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parse8Comic parse8Comic = new Parse8Comic();
		
		parse8Comic.refreshUpdateComic();
		System.out.println(parse8Comic.getUpdateComic().size());
	}

}
