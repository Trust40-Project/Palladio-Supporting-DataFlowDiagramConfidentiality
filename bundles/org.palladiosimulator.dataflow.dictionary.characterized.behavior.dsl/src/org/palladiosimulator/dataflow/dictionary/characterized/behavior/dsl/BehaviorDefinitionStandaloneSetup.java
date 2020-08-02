/*
 * generated by Xtext 2.22.0
 */
package org.palladiosimulator.dataflow.dictionary.characterized.behavior.dsl;

import org.palladiosimulator.dataflow.dictionary.characterized.DataDictionaryCharacterized.DataDictionaryCharacterizedPackage;

import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class BehaviorDefinitionStandaloneSetup extends BehaviorDefinitionStandaloneSetupGenerated {

	public static void doSetup() {
		new BehaviorDefinitionStandaloneSetup().createInjectorAndDoEMFRegistration();
	}

    @Override
    public Injector createInjectorAndDoEMFRegistration() {
        Injector injector = super.createInjectorAndDoEMFRegistration();
        DataDictionaryCharacterizedPackage.eINSTANCE.eClass();
        return injector;
    }
}