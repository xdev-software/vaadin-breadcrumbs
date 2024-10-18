package software.xdev.vaadin.view;

import java.util.Objects;

import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;

import software.xdev.vaadin.breadcrumbs.Breadcrumbs;
import software.xdev.vaadin.view.cars.CarsView;
import software.xdev.vaadin.view.cars.brand.AudiView;
import software.xdev.vaadin.view.cars.brand.BMWView;
import software.xdev.vaadin.view.cars.brand.PorscheView;


@PageTitle("Breadcrumb Demo")
public class MainLayout extends AppLayout
{
	private static final String SIDE_NAV_I18N_PREFIX = "sidenav_";
	
	private final Breadcrumbs breadcrumbs = new Breadcrumbs();
	
	public MainLayout()
	{
		final SideNav sideNav = new SideNav();
		sideNav.addItem(new SideNavItem(
			this.getTranslation(SIDE_NAV_I18N_PREFIX + "home"),
			"",
			VaadinIcon.HOME_O.create()));
		
		final SideNavItem carsItem = new SideNavItem(
			this.getTranslation(SIDE_NAV_I18N_PREFIX + "cars"),
			CarsView.class,
			VaadinIcon.CAR.create());
		carsItem.addItem(new SideNavItem("Audi", AudiView.class));
		carsItem.addItem(new SideNavItem("BMW", BMWView.class));
		carsItem.addItem(new SideNavItem("Porsche", PorscheView.class));
		
		sideNav.addItem(Objects.requireNonNull(carsItem));
		
		this.setPrimarySection(Section.DRAWER);
		
		final VerticalLayout navWrapper = new VerticalLayout(sideNav);
		sideNav.setWidthFull();
		navWrapper.setSpacing(true);
		this.addToDrawer(navWrapper);
		
		final HorizontalLayout vlHeader = new HorizontalLayout(new DrawerToggle(), this.breadcrumbs);
		vlHeader.setSpacing(false);
		this.addToNavbar(vlHeader);
	}
	
	@Override
	public void showRouterLayoutContent(final HasElement content)
	{
		super.showRouterLayoutContent(content);
		this.breadcrumbs.updateFromCurrentPath();
	}
}
