package software.xdev.vaadin.ui.cars;

import java.util.List;

import com.vaadin.flow.router.Route;


@Route("cars")
public class CarsView extends AbstractCarView
{
	@Override
	protected List<Car> getCars()
	{
		return getAllCars();
	}
}
