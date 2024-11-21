package software.xdev.vaadin.ui;

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
import software.xdev.vaadin.ui.cars.CarsView;
import software.xdev.vaadin.ui.cars.brand.ElectricView;
import software.xdev.vaadin.ui.cars.brand.GasolineView;
import software.xdev.vaadin.ui.cars.brand.HybridView;


@PageTitle("Breadcrumb Demo")
public class MainLayout extends AppLayout
{
	private final Breadcrumbs breadcrumbs = new Breadcrumbs()
		.withHomeBreadcrumbName(this.getTranslation(TranslationKeys.HOME))
		.withBreadcrumbNameResolver((full, part) -> this.getTranslation(part));
	
	public MainLayout()
	{
		final SideNav sideNav = new SideNav();
		sideNav.addItem(new SideNavItem(
			this.getTranslation(TranslationKeys.HOME),
			"",
			VaadinIcon.HOME_O.create()));
		
		final SideNavItem carsItem = new SideNavItem(
			this.getTranslation(TranslationKeys.CARS),
			CarsView.class,
			VaadinIcon.CAR.create());
		carsItem.addItem(new SideNavItem(
			this.getTranslation(TranslationKeys.ELECTRIC),
			ElectricView.class,
			VaadinIcon.BOLT.create()));
		carsItem.addItem(new SideNavItem(
			this.getTranslation(TranslationKeys.HYBRID),
			HybridView.class,
			VaadinIcon.GLOBE.create()));
		carsItem.addItem(new SideNavItem(
			this.getTranslation(TranslationKeys.GASOLINE),
			GasolineView.class,
			VaadinIcon.FIRE.create()));
		
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
