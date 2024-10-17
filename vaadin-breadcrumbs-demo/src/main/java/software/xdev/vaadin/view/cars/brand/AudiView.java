package software.xdev.vaadin.view.cars.brand;

import java.util.List;

import com.vaadin.flow.router.Route;

import software.xdev.vaadin.view.MainView;
import software.xdev.vaadin.view.cars.AbstractCarView;
import software.xdev.vaadin.view.cars.Car;


@Route(value = "cars/audi", layout = MainView.class)
public class AudiView extends AbstractCarView
{
	@Override
	@SuppressWarnings("checkstyle:MagicNumber")
	protected List<Car> getCars()
	{
		return getCarsByBrand(AUDI);
	}
}
