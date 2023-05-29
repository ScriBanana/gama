/*******************************************************************************************************
 *
 * ConceptManager.java, in msi.gama.documentation, is part of the source code of the GAMA modeling and simulation
 * platform (v.2.0.0).
 *
 * (c) 2007-2023 UMI 209 UMMISCO IRD/SU & Partners (IRIT, MIAT, TLU, CTU)
 *
 * Visit https://github.com/gama-platform/gama2 for license information and contacts.
 *
 ********************************************************************************************************/
package msi.gama.doc.websiteGen.utilClasses;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import msi.gama.precompiler.IConcept;

/**
 * The Class ConceptManager.
 */
public class ConceptManager {

	/** The concepts not for gaml ref. */
	static public String[] CONCEPTS_NOT_FOR_GAML_REF = { IConcept.AUTOSAVE, IConcept.BACKGROUND, IConcept.DISTRIBUTION,
			IConcept.ENUMERATION, IConcept.FACET, IConcept.GLOBAL, IConcept.HALT, IConcept.IMPORT, IConcept.INHERITANCE,
			IConcept.INIT, IConcept.LAYER, IConcept.MODEL, IConcept.OPENGL, IConcept.OPERATOR, IConcept.OUTPUT,
			IConcept.PAUSE, IConcept.PERMANENT, IConcept.PROBABILITY, IConcept.PSEUDO_VARIABLE, IConcept.REFLEX,
			IConcept.REFRESH, IConcept.SPORT, IConcept.TORUS, IConcept.UPDATE, IConcept.WRITE, IConcept.WORLD };

	/** The concepts not for model library. */
	static public String[] CONCEPTS_NOT_FOR_MODEL_LIBRARY = { IConcept.ACTION, IConcept.ATTRIBUTE, IConcept.AUTOSAVE,
			IConcept.BACKGROUND, IConcept.BEHAVIOR, IConcept.CONSTANT, IConcept.CYCLE, IConcept.DIMENSION,
			IConcept.DISPLAY, IConcept.DISTRIBUTION, IConcept.ENUMERATION, IConcept.EXPERIMENT, IConcept.FACET,
			IConcept.FILE, IConcept.GLOBAL, IConcept.GRAPHIC_UNIT, IConcept.HALT, IConcept.IMPORT, // concern the import
																									// of gaml files
			IConcept.INIT, IConcept.LAYER, IConcept.LENGTH_UNIT, IConcept.MODEL, IConcept.OPENGL, IConcept.OPERATOR,
			IConcept.OPTIMIZATION, IConcept.OUTPUT, IConcept.PARAMETER, IConcept.PAUSE, IConcept.PERMANENT,
			IConcept.PROBABILITY, IConcept.POINT, IConcept.PSEUDO_VARIABLE, IConcept.RANDOM, IConcept.RANDOM_OPERATOR,
			IConcept.REFLEX, IConcept.REFRESH, IConcept.SPECIES, IConcept.SURFACE_UNIT, IConcept.TIME,
			IConcept.TIME_UNIT, IConcept.TORUS, IConcept.TYPE, IConcept.UPDATE, IConcept.VOLUME_UNIT,
			IConcept.WEIGHT_UNIT, IConcept.WRITE, IConcept.WORLD };

	/** The concepts dedicated to syntax. */
	static public String[] CONCEPTS_DEDICATED_TO_SYNTAX = { IConcept.ARITHMETIC, IConcept.ATTRIBUTE, IConcept.CAST,
			IConcept.CONDITION, IConcept.CONTAINER, IConcept.FILTER, IConcept.LIST, IConcept.LOGICAL, IConcept.LOOP,
			IConcept.MAP, IConcept.MATRIX, IConcept.STRING, IConcept.TERNARY

	};

	/**
	 * The Enum WebsitePart.
	 */
	public enum WebsitePart {
		/** The documentation. */
		DOCUMENTATION,
		/** The gaml references. */
		GAML_REFERENCES,
		/** The model library. */
		MODEL_LIBRARY
	}

	/** The m concepts. */
	static private ArrayList<String> m_concepts;

	/** The m occurrence of concept. */
	static HashMap<String, Integer> m_occurrence_of_concept; // the key is the name of the concept, the value is the
																// number of occurrences.

	/** The m occurrence of concept in model library. */
	static HashMap<String, Integer> m_occurrence_of_concept_in_model_library; // the key is the name of the concept, the
																				// value is the number of occurrences.

	/** The m occurrence of concept in gaml ref. */
	static HashMap<String, Integer> m_occurrence_of_concept_in_gaml_ref; // the key is the name of the concept, the
																			// value is the number of occurrences.

	/** The m occurrence of concept in documentation. */
	static HashMap<String, Integer> m_occurrence_of_concept_in_documentation; // the key is the name of the concept, the
																				// value is the number of occurrences.

	/**
	 * Load concepts.
	 *
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 */
	static public void loadConcepts() throws IllegalArgumentException, IllegalAccessException {
		m_concepts = new ArrayList<>();
		m_occurrence_of_concept = new HashMap<>();
		m_occurrence_of_concept_in_model_library = new HashMap<>();
		m_occurrence_of_concept_in_gaml_ref = new HashMap<>();
		m_occurrence_of_concept_in_documentation = new HashMap<>();
		// get the list of predefined concept
		Field[] concept_fields = IConcept.class.getFields();
		for (Field concept_field : concept_fields) {
			String conceptName = concept_field.get(new Object()).toString();

			m_concepts.add(conceptName);
			m_occurrence_of_concept.put(conceptName, 0);
			m_occurrence_of_concept_in_model_library.put(conceptName, 0);
			m_occurrence_of_concept_in_gaml_ref.put(conceptName, 0);
			m_occurrence_of_concept_in_documentation.put(conceptName, 0);
		}
	}

	/**
	 * Concept is possible to add.
	 *
	 * @param concept
	 *            the concept
	 * @return true, if successful
	 */
	static public boolean conceptIsPossibleToAdd(final String concept) {
		return m_concepts.contains(concept);
	}

	/**
	 * Adds the occurrence of concept.
	 *
	 * @param concept
	 *            the concept
	 */
	static public void addOccurrenceOfConcept(final String concept) {
		addOccurrenceOfConcept(concept, "");
	}

	/**
	 * Adds the occurrence of concept.
	 *
	 * @param concept
	 *            the concept
	 * @param websitePart
	 *            the website part
	 */
	static public void addOccurrenceOfConcept(final String concept, final String websitePart) {
		if (m_concepts.contains(concept)) {
			// it is possible to add the concept. Update the number of occurrences of this concept in the library
			int oldValue = m_occurrence_of_concept.get(concept);
			m_occurrence_of_concept.put(concept, ++oldValue);
			if (websitePart.equals(WebsitePart.DOCUMENTATION.toString())) {
				oldValue = m_occurrence_of_concept_in_documentation.get(concept);
				m_occurrence_of_concept_in_documentation.put(concept, ++oldValue);
			}
			if (websitePart.equals(WebsitePart.GAML_REFERENCES.toString())) {
				oldValue = m_occurrence_of_concept_in_gaml_ref.get(concept);
				m_occurrence_of_concept_in_gaml_ref.put(concept, ++oldValue);
				if (Utils.IsInList(concept, CONCEPTS_NOT_FOR_GAML_REF)) {
					System.out.println(
							"WARNING : The concept " + concept + " is not supposed to be for GAML References !!");
				}
			}
			if (websitePart.equals(WebsitePart.MODEL_LIBRARY.toString())) {
				oldValue = m_occurrence_of_concept_in_model_library.get(concept);
				m_occurrence_of_concept_in_model_library.put(concept, ++oldValue);
				if (Utils.IsInList(concept, CONCEPTS_NOT_FOR_MODEL_LIBRARY)) {
					System.out.println(
							"WARNING : The concept " + concept + " is not supposed to be for the model library !!");
				}
			}
		}
	}

	/**
	 * Prints the statistics.
	 */
	static public void printStatistics() {
		ArrayList<String> concept_not_represented = new ArrayList<>();
		// ArrayList<String> concept_too_much_represented = new ArrayList<String>();
		for (String id : m_occurrence_of_concept.keySet()) {
			int number_of_occurrences = m_occurrence_of_concept.get(id);
			System.out.println("concept " + id + " : " + number_of_occurrences + " occurrences.");
			if (number_of_occurrences == 0) { concept_not_represented.add(id); }
		}
		System.out.println("_____________________________");
		for (String concept : concept_not_represented) {
			System.out.println("WARNING : No occurrence for concept " + concept + ".");
		}
	}

	/**
	 * Gets the extended statistics.
	 *
	 * @return the extended statistics
	 */
	static public String getExtendedStatistics() {
		StringBuilder result = new StringBuilder();
		Collections.sort(m_concepts, (s1, s2) -> s1.compareToIgnoreCase(s2));

		// write the header with the date
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		result.append("\n\n_The following text has been automatically generated from \"mainCheckConcepts\"_\n\n");
		result.append("______________ _last update : ").append(dateFormat.format(date)).append("_\n\n");

		// write the lists of concepts
		// write the list of concepts to use in model library (except Syntax)
		result.append("**List of concepts to use for model library (except Syntax):**\n\n");
		boolean isFirstElement = true;
		for (String concept : m_concepts) {
			if (!Utils.IsInList(concept, CONCEPTS_DEDICATED_TO_SYNTAX)
					&& !Utils.IsInList(concept, CONCEPTS_NOT_FOR_MODEL_LIBRARY)) {
				if (isFirstElement) {
					result.append(concept);
					isFirstElement = false;
				} else {
					result.append(", ").append(concept);
				}
			}
		}
		result.append("\n\n");
		// write the list of concepts to use exclusively in Syntax
		result.append("**List of concepts to use exclusively in Syntax models:**\n\n");
		isFirstElement = true;
		for (String concept : m_concepts) {
			if (Utils.IsInList(concept, CONCEPTS_DEDICATED_TO_SYNTAX)) {
				if (isFirstElement) {
					result.append(concept);
					isFirstElement = false;
				} else {
					result.append(", ").append(concept);
				}
			}
		}
		result.append("\n\n");
		// write the list of concepts to use in GAML Reference
		result.append("**List of concepts to use for GAML worlds:**\n\n");
		isFirstElement = true;
		for (String concept : m_concepts) {
			if (!Utils.IsInList(concept, CONCEPTS_NOT_FOR_GAML_REF)) {
				if (isFirstElement) {
					result.append(concept);
					isFirstElement = false;
				} else {
					result.append(", ").append(concept);
				}
			}
		}
		result.append("\n\n");

		// write array
		result.append("| **Concept name** | **in Doc** | **in GAML Ref** | **in Model Lib** | **TOTAL** |\n");
		result.append("|:----------------------------|:-------------|:-------------|:-------------|:-------------|\n");
		for (String id : m_concepts) {
			String number_of_occurrences_total = Integer.toString(m_occurrence_of_concept.get(id));
			String number_of_occurrences_in_doc = Integer.toString(m_occurrence_of_concept_in_documentation.get(id));
			String number_of_occurrences_in_ref = Integer.toString(m_occurrence_of_concept_in_gaml_ref.get(id));
			if (Utils.IsInList(id, CONCEPTS_NOT_FOR_GAML_REF)) { number_of_occurrences_in_ref = "_"; }
			String number_of_occurrences_in_model = Integer.toString(m_occurrence_of_concept_in_model_library.get(id));
			if (Utils.IsInList(id, CONCEPTS_NOT_FOR_MODEL_LIBRARY)) { number_of_occurrences_in_model = "_"; }
			result.append("| ").append(id).append(" | ").append(number_of_occurrences_in_doc).append(" | ")
					.append(number_of_occurrences_in_ref).append(" | ").append(number_of_occurrences_in_model)
					.append(" | ").append(number_of_occurrences_total).append(" |\n");
		}

		return result.toString();
	}
}
