/*
 * GAMA - V1.4 http://gama-platform.googlecode.com
 * 
 * (c) 2007-2011 UMI 209 UMMISCO IRD/UPMC & Partners (see below)
 * 
 * Developers :
 * 
 * - Alexis Drogoul, UMI 209 UMMISCO, IRD/UPMC (Kernel, Metamodel, GAML), 2007-2012
 * - Vo Duc An, UMI 209 UMMISCO, IRD/UPMC (SWT, multi-level architecture), 2008-2012
 * - Patrick Taillandier, UMR 6228 IDEES, CNRS/Univ. Rouen (Batch, GeoTools & JTS), 2009-2012
 * - Beno�t Gaudou, UMR 5505 IRIT, CNRS/Univ. Toulouse 1 (Documentation, Tests), 2010-2012
 * - Phan Huy Cuong, DREAM team, Univ. Can Tho (XText-based GAML), 2012
 * - Pierrick Koch, UMI 209 UMMISCO, IRD/UPMC (XText-based GAML), 2010-2011
 * - Romain Lavaud, UMI 209 UMMISCO, IRD/UPMC (RCP environment), 2010
 * - Francois Sempe, UMI 209 UMMISCO, IRD/UPMC (EMF model, Batch), 2007-2009
 * - Edouard Amouroux, UMI 209 UMMISCO, IRD/UPMC (C++ initial porting), 2007-2008
 * - Chu Thanh Quang, UMI 209 UMMISCO, IRD/UPMC (OpenMap integration), 2007-2008
 */
package msi.gama.outputs.layers;

import msi.gama.metamodel.shape.*;
import msi.gama.runtime.*;
import msi.gama.runtime.exceptions.GamaRuntimeException;
import msi.gaml.expressions.*;
import msi.gaml.operators.Cast;

/**
 * Written by drogoul Modified on 16 nov. 2010
 * 
 * @todo Description
 * 
 */
public class LayerBox implements IDisplayLayerBox {

	IExpression transparency = new ConstantExpression(0d);
	IExpression position = new ConstantExpression(new GamaPoint(0, 0, 0));
	IExpression size = new ConstantExpression(new GamaPoint(1, 1, 1));
	IExpression refresh = new ConstantExpression(true);

	Double currentTransparency = 0d;
	ILocation currentPosition;
	ILocation currentSize;
	Boolean currentRefresh;

	ILocation constantPosition = null;
	ILocation constantSize = null;
	Double constantTransparency = null;
	Boolean constantRefresh = null;

	boolean constantBoundingBox = false;

	public LayerBox(final IExpression transp, final IExpression pos, final IExpression ext, final IExpression refr)
		throws GamaRuntimeException {
		IScope scope = GAMA.obtainNewScope();
		setTransparency(scope, transp == null ? transparency : transp);
		setPosition(scope, pos == null ? position : pos);
		setSize(scope, ext == null ? size : ext);
		setRefresh(scope, refr == null ? refresh : refr);
	}

	@Override
	public void compute(final IScope scope) throws GamaRuntimeException {
		try {
			currentTransparency =
				constantTransparency == null ? 1d - Math.min(
					Math.max(Cast.asFloat(scope, transparency.value(scope)), 0d), 1d) : constantTransparency;
			if ( !constantBoundingBox ) {
				currentPosition =
					constantPosition == null ? Cast.asPoint(scope, position.value(scope)) : constantPosition;
				currentSize = constantSize == null ? Cast.asPoint(scope, size.value(scope)) : constantSize;
				currentRefresh = constantRefresh == null ? Cast.asBool(scope, refresh.value(scope)) : constantRefresh;
			}
		} catch (Exception e) {
			throw GamaRuntimeException.create(e);
		}
	}

	@Override
	public void setTransparency(final IScope scope, final IExpression t) throws GamaRuntimeException {
		if ( t != null ) {
			transparency = t;
			if ( t.isConst() ) {
				setTransparency(Cast.asFloat(scope, t.value(scope)));
			}
		}
	}

	@Override
	public void setPosition(final IScope scope, final IExpression p) throws GamaRuntimeException {
		if ( p != null ) {
			position = p;
			if ( p.isConst() ) {
				setPosition(Cast.asPoint(scope, position.value(scope)));
			}
		}
	}

	@Override
	public void setSize(final IScope scope, final IExpression e) throws GamaRuntimeException {
		if ( e != null ) {
			size = e;
			if ( e.isConst() ) {
				setSize(Cast.asPoint(scope, size.value(scope)));
			}
		}
	}

	@Override
	public void setRefresh(final IScope scope, final IExpression r) throws GamaRuntimeException {
		if ( r != null ) {
			refresh = r;
			if ( r.isConst() ) {
				setRefresh(Cast.asBool(scope, r.value(scope)));
			}
		}

	}

	@Override
	public void setTransparency(final double f) {
		currentTransparency = constantTransparency = 1d - Math.min(Math.max(f, 0d), 1d);
	}

	@Override
	public void setSize(final ILocation p) {
		setSize(p.getX(), p.getY(), p.getZ());
	}

	@Override
	public void setSize(final double width, final double height, final double depth) {
		currentSize = constantSize = new GamaPoint(width, height, depth);
		if ( constantPosition != null ) {
			constantBoundingBox = true;
		}
	}

	@Override
	public void setPosition(final ILocation p) {
		setPosition(p.getX(), p.getY(), p.getZ());
	}

	@Override
	public void setPosition(final double x, final double y, final double z) {
		currentPosition = constantPosition = new GamaPoint(x, y, z);
		if ( constantSize != null ) {
			constantBoundingBox = true;
		}
	}

	@Override
	public void setRefresh(final Boolean r) {
		currentRefresh = constantRefresh = r;

	}

	@Override
	public final Double getTransparency() {
		return currentTransparency;
	}

	@Override
	public ILocation getPosition() {
		return currentPosition;
	}

	@Override
	public ILocation getSize() {
		return currentSize;
	}

	@Override
	public Boolean getRefresh() {
		return currentRefresh;
	}

}
