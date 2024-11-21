package software.xdev.vaadin.ui.cars;

import static software.xdev.vaadin.ui.TranslationKeys.ELECTRIC;
import static software.xdev.vaadin.ui.TranslationKeys.GASOLINE;
import static software.xdev.vaadin.ui.TranslationKeys.HYBRID;
import static software.xdev.vaadin.ui.TranslationKeys.PLUTONIUM;

import java.util.List;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;


public abstract class AbstractCarView extends VerticalLayout
{
	
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
			.filter(c -> fuel.equals(c.fuelI18N()))
			.toList();
	}
	
	@SuppressWarnings("checkstyle:MagicNumber")
	protected static List<Car> getAllCars()
	{
		return List.of(
			new Car(GASOLINE, "Opel Astra K", 110),
			new Car(GASOLINE, "Skoda Octavia 4", 150),
			new Car(GASOLINE, "VW Golf 7 GTI", 220),
			new Car(GASOLINE, "Porsche GT3 RS", 525),
			new Car(HYBRID, "Mercedes A250", 160),
			new Car(HYBRID, "VW Golf 8 eTSI", 150),
			new Car(ELECTRIC, "Tesla Model 3", 460),
			new Car(ELECTRIC, "BMW i4", 540),
			new Car(PLUTONIUM, "DeLorean DMC-12", 1_620_000)
		);
	}
}
