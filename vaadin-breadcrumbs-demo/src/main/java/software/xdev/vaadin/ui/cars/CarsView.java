package software.xdev.vaadin.ui.cars;

import java.util.List;

import com.vaadin.flow.router.Route;

import software.xdev.vaadin.ui.MainLayout;


@Route(value = "cars", layout = MainLayout.class)
public class CarsView extends AbstractCarView
{
	@Override
	protected List<Car> getCars()
	{
		return getAllCars();
	}
}
