/*********************************************************************************************
 * 
 *
 * 'WaterLevel.java', in plugin 'cenres.gaml.extensions.hydro', is part of the source code of the 
 * GAMA modeling and simulation platform.
 * (c) 2007-2014 UMI 209 UMMISCO IRD/UPMC & Partners
 * 
 * Visit https://code.google.com/p/gama-platform/ for license information and developers contact.
 * 
 * 
 **********************************************************************************************/
package cenres.gaml.extensions.hydro.operators;

import msi.gama.metamodel.shape.*;
import msi.gama.precompiler.GamlAnnotations.doc;
import msi.gama.precompiler.GamlAnnotations.example;
import msi.gama.precompiler.GamlAnnotations.operator;
import msi.gama.util.GamaList;
import cenres.gaml.extensions.hydro.utils.WaterLevelUtils;
import com.vividsolutions.jts.geom.Coordinate;

public class WaterLevel {

	/*
	 * author: Philippe Caillou
	 */
	@operator(value = { "water_level_for" })
	@doc(special_cases = { "if the left operand is a polyline and the right operand a float for the area, returrns the y coordinate of the water (water level)" }, examples = { @example(value = "waterlevel <- my_river_polyline water_level_for my_area_value", isExecutable = false) })
	public static Double opWaterLevel(final IShape shape, final Double val) {
		if ( shape == null || val == null ) { return null; }
		return WaterLevelUtils.heigth(new GamaList<Coordinate>(shape.getInnerGeometry().getCoordinates()), val);
	}

	@operator(value = { "water_area_for" })
	@doc(special_cases = { "if the left operand is a polyline and the right operand a float for the water y coordinate, returrns the area of the water (water flow area)" }, examples = { @example(value = "waterarea <- my_river_polyline water_area_for my_height_value", isExecutable = false) })
	public static Double opWaterArea(final IShape shape, final Double val) {
		if ( shape == null || val == null ) { return null; }
		return WaterLevelUtils.area(new GamaList<Coordinate>(shape.getInnerGeometry().getCoordinates()), val);
	}

	@operator(value = { "water_polylines_for" })
	@doc(special_cases = { "if the left operand is a polyline and the right operand a float for the water y coordinate, returrns the shapes of the river sections (list of list of points)" }, examples = { @example(value = "waterarea <- my_river_polyline water_area_for my_height_value", isExecutable = false) })
	public static GamaList<GamaList<GamaPoint>> opWaterPolylines(final IShape shape, final Double val) {
		if ( shape == null || val == null ) { return null; }
		return WaterLevelUtils.areaPolylines(new GamaList<Coordinate>(shape.getInnerGeometry().getCoordinates()), val);
	}
}
