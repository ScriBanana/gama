/*******************************************************************************************************
 *
 * FSTBinaryImplementation.java, in ummisco.gama.serialize, is part of the source code of the GAMA modeling and
 * simulation platform (v.1.9.2).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama for license information and contacts.
 *
 ********************************************************************************************************/
package ummisco.gama.serializer.implementations;

import org.nustaq.serialization.FSTConfiguration;

/**
 * The Class FSTImplementation. Allows to provide common initializations to FST Configurations and do the dirty work.
 * Not thread / simulation safe.
 *
 * @author Alexis Drogoul (alexis.drogoul@ird.fr)
 * @date 2 août 2023
 */
public class FSTBinaryImplementation extends AbstractFSTImplementation {

	/** A flag to use sun.unsafe.xxx classes. A bit faster, but more subject to depreciation */
	static final boolean USE_UNSAFE = true;

	/**
	 * Instantiates a new gama FST serialiser.
	 *
	 * @author Alexis Drogoul (alexis.drogoul@ird.fr)
	 * @param scope
	 *            the scope.
	 * @date 5 août 2023
	 */
	public FSTBinaryImplementation(final boolean zip, final boolean save) {
		super(USE_UNSAFE ? FSTConfiguration.createUnsafeBinaryConfiguration()
				: FSTConfiguration.createDefaultConfiguration(), zip, save);
	}

	@Override
	protected String getFormat() { return "binary"; }

}
