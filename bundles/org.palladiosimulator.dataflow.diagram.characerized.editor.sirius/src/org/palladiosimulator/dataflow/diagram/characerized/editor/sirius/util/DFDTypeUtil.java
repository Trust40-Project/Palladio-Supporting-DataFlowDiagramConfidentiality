package org.palladiosimulator.dataflow.diagram.characerized.editor.sirius.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.internal.C;
import org.palladiosimulator.dataflow.dictionary.DataDictionary.CompositeDataType;
import org.palladiosimulator.dataflow.dictionary.DataDictionary.DataType;
import org.palladiosimulator.dataflow.dictionary.DataDictionary.Entry;
import org.palladiosimulator.dataflow.dictionary.characterized.DataDictionaryCharacterized.DataDictionaryCharacterized;

/**
 * 
 * Utility class for interacting with data dictionary models.
 *
 */
public class DFDTypeUtil {
	private static final String DDC_CLASS = "DataDictionaryCharacterized";

	/**
	 * 
	 * The next two methods are directly copied from Palladio.
	 * 
	 */
	public static boolean uriAlreadyLoaded(URI uri, Session session) {
		return getResourceByURI(uri, session) != null;
	}

	public static Resource getResourceByURI(URI uri, Session session) {
		Collection<Resource> resources = session.getSemanticResources();
		for (Resource r : resources) {
			if (r.getURI().equals(uri))
				return r;
		}
		return null;
	}

//	public static List<Entry> refineDT(DataType type, Session session) {
//		List<DataType> definedTypes = new ArrayList<DataType>();
//		List<Entry> entries = new ArrayList<Entry>();
//		List<Resource> resources = session.getSemanticResources().stream()
//				.filter(r -> r.getURI().fileExtension().equals(DD_EXTENSTION)).collect(Collectors.toList());
//
//		for (Resource r : resources) {
//			for (EObject typeDefinition : r.getContents()) {
//				for (EObject datatype : typeDefinition.eContents()) {
//					definedTypes.add((DataType) datatype);
//				}
//			}
//
//		}
//		for (DataType dt : definedTypes) {
//			if (EcoreUtil.equals(type, dt)) {
//				entries.addAll(((CompositeDataType) dt).getComponents());
//				break;
//			}
//
//		}
//		return entries;
//	}
//

	public static List<EObject> getDataTypes(Session session) {
		List<EObject> types = new ArrayList<EObject>();
		List<Resource> resources = session.getSemanticResources().stream().filter(
				resource -> resource.getContents().stream().anyMatch(c -> c.eClass().getName().equals(DDC_CLASS)))
				.collect(Collectors.toList());

		for (Resource r : resources) {
			for (EObject typeDefinition : r.getContents()) {
				for (EObject datatype : typeDefinition.eContents()) {
					String dataTypeClass = datatype.eClass().getName();
					if (!dataTypeClass.equals("Enumeration") && !dataTypeClass.equals("BehaviorDefinition")) {
						types.add(datatype);

					}

				}
			}

		}
		return types;
	}

}
