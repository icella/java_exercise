package exercise.com.leo.base.effectjava.item2.javabeans;

public class NutritionFacts {
	private int servingSize; // (mL) required
	private int servings; // (per container) required
	private int calories; // optional
	private int fat; // (g) optional
	private int sodium; // (mg) optional
	private int carbohydrate; // (g) optional


	public void setServingSize(int servingSize) {
		this.servingSize = servingSize;
	}


	public void setServings(int servings) {
		this.servings = servings;
	}


	public void setCalories(int calories) {
		this.calories = calories;
	}


	public void setFat(int fat) {
		this.fat = fat;
	}


	public void setSodium(int sodium) {
		this.sodium = sodium;
	}


	public void setCarbohydrate(int carbohydrate) {
		this.carbohydrate = carbohydrate;
	}


	@Override
	public String toString() {
		return "NutritionFacts [servingSize=" + servingSize + ", servings=" + servings + ", calories=" + calories
				+ ", fat=" + fat + ", sodium=" + sodium + ", carbohydrate=" + carbohydrate + "]";
	}


	public static void main(String[] args) {
		NutritionFacts cocaCola = new NutritionFacts();
		cocaCola.setServingSize(240);
		cocaCola.setServings(8);
		cocaCola.setCalories(100);
		cocaCola.setSodium(35);
		cocaCola.setCarbohydrate(27);
		
		System.out.println(cocaCola);
	}

}
