/*******************************************************************************************************
 *
 * FSTJsonImplementation.java, in ummisco.gama.serialize, is part of the source code of the GAMA modeling and simulation
 * platform (v.1.9.2).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama for license information and contacts.
 *
 ********************************************************************************************************/
package ummisco.gama.serializer.implementations;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.PrecisionModel;
import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;
import org.nustaq.serialization.coders.FSTJsonFieldNames;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import msi.gama.common.geometry.GamaCoordinateSequence;
import msi.gama.kernel.simulation.SimulationAgent;
import msi.gama.metamodel.agent.GamlAgent;
import msi.gama.metamodel.agent.MinimalAgent;
import msi.gama.metamodel.shape.GamaPoint;
import msi.gama.metamodel.shape.GamaShape;
import msi.gama.metamodel.shape.IShape;
import msi.gama.runtime.IScope;
import msi.gama.util.GamaList;
import msi.gama.util.GamaMap;
import msi.gama.util.graph.IGraph;

/**
 * The Class FSTImplementation. Allows to provide common initializations to FST Configurations and do the dirty work.
 * Not thread / simulation safe.
 *
 * @author Alexis Drogoul (alexis.drogoul@ird.fr)
 * @date 2 août 2023
 */
public class FSTJsonImplementation extends AbstractFSTImplementation {

	/** The saved graphs. JSON has difficulties to maintain shared references */
	BiMap<IGraph, String> savedGraphs = HashBiMap.create();

	/**
	 * Instantiates a new gama FST serialiser.
	 *
	 * @author Alexis Drogoul (alexis.drogoul@ird.fr)
	 * @param scope
	 *            the scope.
	 * @date 5 août 2023
	 */
	public FSTJsonImplementation(final boolean zip, final boolean save) {
		super(FSTConfiguration.createJsonConfiguration(false, false), zip, save);
	}

	/**
	 * Register serialisers.
	 *
	 * @author Alexis Drogoul (alexis.drogoul@ird.fr)
	 * @date 5 août 2023
	 */
	@Override
	public void registerSerialisers() {
		super.registerSerialisers();

		register(PrecisionModel.Type.class, new GamaFSTSerialiser<PrecisionModel.Type>() {
			// Necessary because of the JSON mode that requires it
			@Override
			public void serialise(final FSTObjectOutput out, final PrecisionModel.Type o) throws Exception {
				out.writeStringUTF(switch (o.toString()) {
					default -> "F";
					case "FIXED" -> "F";
					case "FLOATING" -> "D";
					case "FLOATING_SINGLE" -> "S";
				});
			}

			@Override
			public PrecisionModel.Type deserialise(final IScope scope, final FSTObjectInput in) throws Exception {
				return switch (in.readStringUTF()) {
					default -> PrecisionModel.FIXED;
					case "F" -> PrecisionModel.FIXED;
					case "D" -> PrecisionModel.FLOATING;
					case "S SINGLE" -> PrecisionModel.FLOATING_SINGLE;
				};
			}
		});

		register(IGraph.class, new GamaFSTSerialiser<IGraph>() {
			// This serializer is a way of "cheating" by considering that graphs are immutable throughout the
			// simulations -- To use only in JSON.
			int i;

			@Override
			public void serialise(final FSTObjectOutput out, final IGraph o) throws Exception {
				if (!savedGraphs.containsKey(o)) { savedGraphs.put(o, "graph" + i++); }
				out.writeStringUTF(savedGraphs.get(o));
			}

			@Override
			public IGraph deserialise(final IScope scope, final FSTObjectInput in) throws Exception {
				String key = in.readStringUTF();
				return savedGraphs.inverse().get(key);
			}

		});

		register(File.class, new GamaFSTSerialiser<File>() {
			// Required by JSON, which cannot use the built-in writeObject / readObject methods
			@Override
			public void serialise(final FSTObjectOutput out, final File o) throws Exception {
				out.writeStringUTF(o.getCanonicalPath());
			}

			@Override
			public File deserialise(final IScope scope, final FSTObjectInput in) throws Exception {
				return new File(in.readStringUTF());
			}

		});

	}

	/**
	 * Register.
	 *
	 * @author Alexis Drogoul (alexis.drogoul@ird.fr)
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @date 5 août 2023
	 */
	@Override
	public <T> void register(final Class<T> clazz, final GamaFSTSerialiser<T> ser) {
		super.register(clazz, ser);
		fst.registerCrossPlatformClassMapping(ser.shortName, clazz.getName());
	}

	/**
	 * Inits the common.
	 *
	 * @author Alexis Drogoul (alexis.drogoul@ird.fr)
	 * @param conf
	 *            the conf
	 * @return the FST configuration
	 * @date 2 août 2023
	 */
	@Override
	public FSTConfiguration initConfiguration(final FSTConfiguration conf) {
		super.initConfiguration(conf);
		conf.setJsonFieldNames(new FSTJsonFieldNames("t", "o", "st", "s", "e", "v", "r"));
		conf.registerCrossPlatformClassMappingUseSimpleName(GamaPoint.class, GamaPoint[].class, Integer[].class,
				String[].class, MinimalAgent.class, GamlAgent.class, SimulationAgent.Type.class, Envelope.class,
				LinearRing.class, LinearRing[].class, Polygon.class, GamaCoordinateSequence.class, IShape.Type.class,
				GamaShape.ShapeData.class, GamaList.class, GamaMap.class);
		conf.registerCrossPlatformClassMapping("PrecisionModel.Type", PrecisionModel.Type.class.getName());
		conf.registerCrossPlatformClassMapping("IShape.Type", IShape.Type.class.getName());
		conf.registerCrossPlatformClassMapping("SimulationAgent.Type", SimulationAgent.Type.class.getName());
		conf.setShareReferences(false);
		return conf;
	}

	/**
	 * Save.
	 *
	 * @author Alexis Drogoul (alexis.drogoul@ird.fr)
	 * @param sim
	 *            the sim
	 * @date 6 août 2023
	 */
	@Override
	protected byte[] write(final SerialisedAgent sa) {
		// Retrieving and putting apart the graphs
		Set<Map.Entry<String, Object>> entries = sa.attributes().entrySet();
		for (Map.Entry<String, Object> entry : entries) {
			if (entry.getValue() instanceof IGraph g) {
				savedGraphs.put(g, entry.getKey());
				entries.remove(entry.getKey());
			}
		}
		return super.write(sa);
	}

	@Override
	protected String getFormat() { return "json"; }

}
