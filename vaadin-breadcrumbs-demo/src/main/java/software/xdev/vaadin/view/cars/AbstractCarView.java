package software.xdev.vaadin.view.cars;

import java.util.List;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public abstract class AbstractCarView extends VerticalLayout
{
	protected static final String AUDI = "Audi";
	protected static final String BMW = "BMW";
	protected static final String PORSCHE = "Porsche";
	
	protected AbstractCarView()
	{
		final CarGrid carGrid = new CarGrid(this.getCars());
		carGrid.setAllRowsVisible(true);
		this.add(carGrid);
	}
	
	protected abstract List<Car> getCars();
	
	protected static List<Car> getCarsByBrand(final String brand)
	{
		return getAllCars().stream()
			.filter(c -> brand.equals(c.brand()))
			.toList();
	}
	
	@SuppressWarnings("checkstyle:MagicNumber")
	protected static List<Car> getAllCars()
	{
		return List.of(
			new Car(AUDI, "A4", 190),
			new Car(AUDI, "A3", 150),
			new Car(AUDI, "S4", 250),
			new Car(AUDI, "Q5", 300),
			new Car(BMW, "318i", 190),
			new Car(BMW, "M4", 400),
			new Car(BMW, "420d", 200),
			new Car(BMW, "X5", 250),
			new Car(PORSCHE, "Cayenne", 300),
			new Car(PORSCHE, "911", 400),
			new Car(PORSCHE, "Carrera", 400),
			new Car(PORSCHE, "GT3RS", 600)
		);
	}
}
