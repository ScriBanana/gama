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
package msi.gaml.descriptions;

import static msi.gama.common.interfaces.IKeyword.DO;
import java.util.*;
import msi.gama.common.interfaces.*;
import msi.gama.precompiler.GamlAnnotations.combination;
import msi.gama.precompiler.GamlAnnotations.facet;
import msi.gama.precompiler.*;
import msi.gaml.commands.*;
import msi.gaml.commands.Facets.Facet;
import msi.gaml.compilation.*;
import msi.gaml.expressions.IExpressionCompiler;
import msi.gaml.types.IType;

/**
 * Written by drogoul Modified on 8 févr. 2010
 * 
 * @todo Description
 * 
 */
// @facet(name = IKeyword.KEYWORD, type = IType.ID, optional = true)
public class SymbolMetaDescription {

	public static Set<String> nonVariableStatements = new HashSet();

	public static class FacetMetaDescription {

		public String name;
		public String[] types;
		public boolean optional;
		public boolean isLabel;
		public String[] values;

		private FacetMetaDescription() {}

		FacetMetaDescription(final facet f) {
			name = f.name();
			types = f.type();
			optional = f.optional();
			isLabel = ids.contains(types[0]);
			values = f.values();
		}

		static FacetMetaDescription DEPENDS_ON() {
			FacetMetaDescription f = new FacetMetaDescription();
			f.name = IKeyword.DEPENDS_ON;
			f.types = new String[] { IType.NONE_STR };
			f.optional = true;
			f.isLabel = true;
			f.values = new String[0];
			return f;
		}

		static FacetMetaDescription KEYWORD() {
			FacetMetaDescription f = new FacetMetaDescription();
			f.name = IKeyword.KEYWORD;
			f.types = new String[] { IType.ID };
			f.optional = true;
			f.isLabel = true;
			f.values = new String[0];
			return f;
		}
	}

	private Class instantiationClass = null;
	private ISymbolConstructor constructor;
	private Class baseClass = null;
	private boolean hasSequence = false;
	private boolean hasArgs = false;
	private boolean hasScope = true;
	private boolean isTopLevel = false;
	private boolean isRemoteContext = false;
	private final List<String> possibleContexts;
	private final Map<String, FacetMetaDescription> possibleFacets = new HashMap();
	private final List<String[]> combinations = new ArrayList();
	private final List<String> mandatoryFacets = new ArrayList();
	private final String omissibleFacet;

	private static final List<String> ids = Arrays.asList(IType.LABEL, IType.ID, IType.NEW_TEMP_ID,
		IType.NEW_VAR_ID, IType.TYPE_ID);

	public SymbolMetaDescription(final Class instantiationClass, final Class baseClass,
		final String keyword, final boolean hasSequence, final boolean hasArgs, final int kind,
		final boolean doesNotHaveScope, final List<facet> possibleFacets, final String omissible,
		final List<combination> possibleCombinations, final List<String> contexts,
		final boolean isRemoteContext) {
		setInstantiationClass(instantiationClass);
		setBaseClass(baseClass);
		setRemoteContext(isRemoteContext);
		setHasSequence(hasSequence);
		setHasArgs(hasArgs);
		this.omissibleFacet = omissible;
		this.isTopLevel = kind == ISymbolKind.BEHAVIOR;
		this.hasScope = !doesNotHaveScope;
		getPossibleFacets().put(IKeyword.KEYWORD, FacetMetaDescription.KEYWORD());
		getPossibleFacets().put(IKeyword.DEPENDS_ON, FacetMetaDescription.DEPENDS_ON());
		for ( facet f : possibleFacets ) {
			getPossibleFacets().put(f.name(), new FacetMetaDescription(f));
		}
		for ( FacetMetaDescription f : getPossibleFacets().values() ) {
			if ( !f.optional ) {
				getMandatoryFacets().add(f.name);
			}
		}
		possibleContexts = contexts;
	}

	public boolean isRemoteContext() {
		return isRemoteContext;
	}

	public void setRemoteContext(final boolean isRemoteContext) {
		this.isRemoteContext = isRemoteContext;
	}

	public boolean isFacetDeclaringANewTemp(final String s) {
		FacetMetaDescription f = getPossibleFacets().get(s);
		if ( f == null ) { return false; }
		return f.types[0].equals(IType.NEW_TEMP_ID);
	}

	public boolean isLabel(final String s) {
		FacetMetaDescription f = getPossibleFacets().get(s);
		if ( f == null ) { return false; }
		return f.isLabel;
	}

	public boolean isDefinition() {
		return isLabel(omissibleFacet);
	}

	public boolean isControl() {
		return !isDefinition();
	}

	public void setInstantiationClass(final Class instantiationClass) {
		this.instantiationClass = instantiationClass;
		constructor = GamlCompiler.getSymbolConstructor(instantiationClass);
	}

	public void setBaseClass(final Class baseClass) {
		this.baseClass = baseClass;
	}

	public Class getInstantiationClass() {
		return instantiationClass;
	}

	public Class getBaseClass() {
		return baseClass;
	}

	public void setHasSequence(final boolean hasSequence) {
		this.hasSequence = hasSequence;
	}

	public boolean hasSequence() {
		return hasSequence;
	}

	public void setHasArgs(final boolean hasArgs) {
		this.hasArgs = hasArgs;
	}

	public boolean hasArgs() {
		return hasArgs;
	}

	public boolean hasScope() {
		return hasScope;
	}

	public Map<String, FacetMetaDescription> getPossibleFacets() {
		return possibleFacets;
	}

	public List<String[]> getPossibleCombinations() {
		return combinations;
	}

	public List<String> getMandatoryFacets() {
		return mandatoryFacets;
	}

	public void verifyMandatoryFacets(final ISyntacticElement e, final Facets facets,
		final IDescription context) {
		for ( String s : mandatoryFacets ) {
			if ( !facets.containsKey(s) ) {
				context.flagError("Missing facet " + s, IGamlIssue.MISSING_FACET, e, s);
			}
		}
	}

	public void verifyFacetsValidity(final ISyntacticElement e, final Facets facets,
		final IDescription context) {
		// Special case for "do", which can accept (at parsing time) any facet
		if ( e.getKeyword().equals(DO) ) { return; }
		for ( Facet s : facets.entrySet() ) {
			if ( !possibleFacets.containsKey(s.getKey()) ) {
				context.flagError("Unknown facet " + s.getKey(), IGamlIssue.UNKNOWN_FACET, e,
					s.getKey());
			}
		}
	}

	public void verifyFacetsCombinations(final ISyntacticElement e, final Facets facets,
		final IDescription context) {
		if ( getPossibleCombinations().isEmpty() ) { return; }
		for ( String[] c : getPossibleCombinations() ) {
			boolean allPresent = true;
			for ( String s : c ) {
				allPresent = allPresent && facets.containsKey(s);
			}
			if ( allPresent ) { return; }
		}
		context.flagError("Wrong combination of facets " + facets, IGamlIssue.GENERAL, e);
	}

	public boolean verifyContext(final String context) {
		return possibleContexts.contains(context);
	}

	public void verifyFacetsIds(final ISyntacticElement e, final Facets facets,
		final IDescription context) {
		for ( Facet facet : facets.entrySet() ) {
			if ( facet == null ) {
				continue;
			}
			String facetName = facet.getKey();
			FacetMetaDescription f = possibleFacets.get(facetName);
			if ( f == null ) {
				continue;
			}
			if ( f.isLabel ) {
				facets.put(facetName, facets.get(facetName).compileAsLabel());
				if ( f.types[0].equals(IType.LABEL) ) {
					if ( f.values != null && f.values.length != 0 ) {
						boolean found = false;
						for ( String possibleValue : f.values ) {
							if ( facets.equals(facetName, possibleValue) ) {
								found = true;
								break;
							}
						}
						if ( !found ) {
							context.flagError("The value of facet " + facet.getKey() +
								" must be one of " + Arrays.toString(f.values),
								IGamlIssue.NOT_AMONG, e);

						} else {
							String facetValue = facets.getLabel(facetName).trim();
							if ( IExpressionCompiler.RESERVED.contains(facetValue) ) {
								context.flagError(facetValue +
									" is a reserved keyword. It cannot be used as an identifier",
									IGamlIssue.IS_RESERVED, e, facetValue);
							}
						}
					}

				}
			}
		}
	}

	public void verifyFacets(final ISyntacticElement e, final Facets facets,
		final IDescription context) {
		verifyMandatoryFacets(e, facets, context);
		verifyFacetsValidity(e, facets, context);
		verifyFacetsCombinations(e, facets, context);
		verifyFacetsIds(e, facets, context);
	}

	public boolean isTopLevel() {
		return isTopLevel;
	}

	public ISymbolConstructor getConstructor() {
		return constructor;
	}

	/**
	 * @return
	 */
	public String getOmissible() {
		return omissibleFacet;
	}

	/**
	 * @return
	 */
	public String getDocumentation() {
		// TODO Insert here the possibility to grab a @doc annotation in the symbol.
		StringBuilder sb = new StringBuilder();
		sb.append("<b>Facets allowed:</b><br><ul>");
		for ( FacetMetaDescription f : this.getPossibleFacets().values() ) {
			sb.append("<li><b>").append(f.name).append("</b> type: ").append(f.types[0])
				.append(" <i>[").append(f.optional ? "optional" : "required").append("]</i>");
			if ( f.values != null && f.values.length != 0 ) {
				sb.append(" among: ").append(Arrays.toString(f.values));
			}
			sb.append("</li>");
		}
		return sb.toString();
	}
}
