/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.forms.crud.client.component.formDisplay.embedded;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import io.crysknife.ui.templates.client.annotation.DataField;
import io.crysknife.ui.templates.client.annotation.EventHandler;
import io.crysknife.ui.templates.client.annotation.Templated;
import io.crysknife.ui.translation.api.spi.TranslationService;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtproject.dom.client.Document;
import org.gwtproject.dom.client.Element;
import org.gwtproject.event.dom.client.ClickEvent;
import org.gwtproject.user.client.ui.Composite;
import org.gwtproject.user.client.ui.SimplePanel;
import org.kie.workbench.common.forms.crud.client.component.formDisplay.IsFormView;
import org.kie.workbench.common.forms.crud.client.resources.i18n.CrudComponentConstants;

@Dependent
@Templated
public class EmbeddedFormDisplayerViewImpl extends Composite implements EmbeddedFormDisplayer.EmbeddedFormDisplayerView {

    @DataField
    private Element heading = Document.get().createHElement(3);

    @DataField
    private SimplePanel content = new SimplePanel();

    @Inject
    @DataField
    private Button accept;

    @Inject
    @DataField
    private Button cancel;

    private EmbeddedFormDisplayer presenter;

    private TranslationService translationService;

    @Inject
    public EmbeddedFormDisplayerViewImpl(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostConstruct
    protected void initialize() {
        accept.setText(translationService.getTranslation(CrudComponentConstants.ModalFormDisplayerViewImplAccept));
        cancel.setText(translationService.getTranslation(CrudComponentConstants.ModalFormDisplayerViewImplCancel));
    }

    @Override
    public void setPresenter(EmbeddedFormDisplayer presenter) {
        this.presenter = presenter;
    }

    @Override
    public void show(String title,
                     IsFormView formView) {
        heading.setInnerHTML(title);
        content.add(formView);
    }

    @Override
    public void clear() {
        content.clear();
    }

    @EventHandler("accept")
    public void doAccept(ClickEvent event) {
        presenter.submitForm();
    }

    @EventHandler("cancel")
    public void doCancel(ClickEvent event) {
        presenter.cancel();
    }
}
