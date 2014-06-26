/*
 * Copyright (c) 2014 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package com.cisco.yangide.editor.preferences;

//import org.eclipse.ant.internal.ui.AntSourceViewerConfiguration;
//import org.eclipse.ant.internal.ui.editor.formatter.FormattingPreferences;
//import org.eclipse.ant.internal.ui.editor.formatter.XmlFormatter;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.texteditor.AbstractTextEditor;

import com.cisco.yangide.editor.editors.YangSourceViewerConfiguration;


    import org.eclipse.swt.events.DisposeEvent;
    import org.eclipse.swt.events.DisposeListener;
    import org.eclipse.swt.graphics.Font;

    import org.eclipse.core.runtime.Assert;

    import org.eclipse.jface.preference.IPreferenceStore;
    import org.eclipse.jface.resource.JFaceResources;
    import org.eclipse.jface.util.IPropertyChangeListener;
    import org.eclipse.jface.util.PropertyChangeEvent;

    import org.eclipse.jface.text.source.SourceViewer;

    import org.eclipse.jdt.ui.PreferenceConstants;
    import org.eclipse.jdt.ui.text.JavaSourceViewerConfiguration;

    /**
     * @author Alexey Kholupko
     */
    public class YangPreviewerUpdater {

        /**
         * Creates a Java source preview updater for the given viewer, configuration and preference store.
         *
         * @param viewer the viewer
         * @param configuration the configuration
         * @param preferenceStore the preference store
         */
        public YangPreviewerUpdater(final SourceViewer viewer, final YangSourceViewerConfiguration configuration, final IPreferenceStore preferenceStore) {
            Assert.isNotNull(viewer);
            Assert.isNotNull(configuration);
            Assert.isNotNull(preferenceStore);
            final IPropertyChangeListener fontChangeListener= new IPropertyChangeListener() {
                /*
                 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
                 */
                public void propertyChange(PropertyChangeEvent event) {
                    if (event.getProperty().equals(PreferenceConstants.EDITOR_TEXT_FONT)) {
                        Font font= JFaceResources.getFont(PreferenceConstants.EDITOR_TEXT_FONT);
                        viewer.getTextWidget().setFont(font);
                    }
                }
            };
            final IPropertyChangeListener propertyChangeListener= new IPropertyChangeListener() {
                /*
                 * @see org.eclipse.jface.util.IPropertyChangeListener#propertyChange(org.eclipse.jface.util.PropertyChangeEvent)
                 */
                public void propertyChange(PropertyChangeEvent event) {
                    if (configuration.affectsTextPresentation(event)) {
                        configuration.handlePropertyChangeEvent(event);
                        viewer.invalidateTextPresentation();
                    }
                }
            };
            viewer.getTextWidget().addDisposeListener(new DisposeListener() {
                /*
                 * @see org.eclipse.swt.events.DisposeListener#widgetDisposed(org.eclipse.swt.events.DisposeEvent)
                 */
                public void widgetDisposed(DisposeEvent e) {
                    preferenceStore.removePropertyChangeListener(propertyChangeListener);
                    JFaceResources.getFontRegistry().removeListener(fontChangeListener);
                }
            });
            JFaceResources.getFontRegistry().addListener(fontChangeListener);
            preferenceStore.addPropertyChangeListener(propertyChangeListener);
        }
    }
