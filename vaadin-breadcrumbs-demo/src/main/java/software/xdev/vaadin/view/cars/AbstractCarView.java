package software.xdev.vaadin.view.cars;

import java.util.List;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public abstract class AbstractCarView extends VerticalLayout
{
	protected static final String ELECTRIC = "electric";
	protected static final String HYBRID = "hybrid";
	protected static final String GASOLINE = "gasoline";
	
	protected AbstractCarView()
	{
		final CarGrid carGrid = new CarGrid(this.getCars());
		carGrid.setAllRowsVisible(true);
		this.add(carGrid);
	}
	
	protected abstract List<Car> getCars();
	
	protected static List<Car> getCarsByFuel(final String fuel)
	{
		return getAllCars().stream()
			.filter(c -> fuel.equals(c.fuel()))
			.toList();
	}
	
	@SuppressWarnings("checkstyle:MagicNumber")
	protected static List<Car> getAllCars()
	{
		return List.of(
			new Car(ELECTRIC, "A4", 190),
			new Car(ELECTRIC, "A3", 150),
			new Car(ELECTRIC, "S4", 250),
			new Car(ELECTRIC, "Q5", 300),
			new Car(HYBRID, "318i", 190),
			new Car(HYBRID, "M4", 400),
			new Car(HYBRID, "420d", 200),
			new Car(HYBRID, "X5", 250),
			new Car(GASOLINE, "Cayenne", 300),
			new Car(GASOLINE, "911", 400),
			new Car(GASOLINE, "Carrera", 400),
			new Car(GASOLINE, "GT3RS", 600)
		);
	}
}
