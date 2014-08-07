/**
 *
 * $Id$
 */
package com.cisco.yangide.ext.model.validation;

import com.cisco.yangide.ext.model.Revision;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link com.cisco.yangide.ext.model.Module}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ModuleValidator {
    boolean validate();

    boolean validateNamespace(String value);
    boolean validateRevisions(EList<Revision> value);
}
