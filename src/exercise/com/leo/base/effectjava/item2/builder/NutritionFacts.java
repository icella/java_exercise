package exercise.com.leo.base.effectjava.item2.builder;

public class NutritionFacts {
	private final int servingSize;
	private final int servings;
	private final int calories;
	private final int fat;
	private final int sodium;
	private final int carbohydrate;

	public static class Builder {
		private final int servingSize; // (mL) required
		private final int servings; // (per container) required

		private int calories; // optional
		private int fat; // (g) optional
		private int sodium; // (mg) optional
		private int carbohydrate; // (g) optional

		public Builder(int servingSize, int servings) {
			this.servingSize = servingSize;
			this.servings = servings;
		}

		public Builder setCalories(int calories) {
			this.calories = calories;
			return this;
		}

		public Builder setFat(int fat) {
			this.fat = fat;
			return this;
		}

		public Builder setSodium(int sodium) {
			this.sodium = sodium;
			return this;
		}

		public Builder setCarbohydrate(int carbohydrate) {
			this.carbohydrate = carbohydrate;
			return this;
		}

		public NutritionFacts build() {
			return new NutritionFacts(this);
		}
	}

	private NutritionFacts(Builder builder) {
		servingSize = builder.servingSize;
		servings = builder.servings;
		calories = builder.calories;
		fat = builder.fat;
		sodium = builder.sodium;
		carbohydrate = builder.carbohydrate;
	}

	@Override
	public String toString() {
		return "NutritionFacts [servingSize=" + servingSize + ", servings=" + servings + ", calories=" + calories
				+ ", fat=" + fat + ", sodium=" + sodium + ", carbohydrate=" + carbohydrate + "]";
	}

	public static void main(String[] args) {
		NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).setCalories(100).setCarbohydrate(35)
				.setCarbohydrate(12).build();

		System.out.println(cocaCola);
	}

}
