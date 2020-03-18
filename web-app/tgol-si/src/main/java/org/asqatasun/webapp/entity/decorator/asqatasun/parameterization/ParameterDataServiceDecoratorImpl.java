/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.webapp.entity.decorator.asqatasun.parameterization;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.parameterization.ParameterFamily;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.parameterization.ParameterElementDataService;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.asqatasun.webapp.entity.contract.ScopeEnum;
import org.asqatasun.webapp.entity.dao.asqatasun.parameterization.TgolParameterDAO;
import org.asqatasun.webapp.entity.option.OptionElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class ParameterDataServiceDecoratorImpl extends AbstractGenericDataService<Parameter, Long>
        implements ParameterDataServiceDecorator{

    private final ParameterDataService decoratedParameterDataService; // the ParameterDataService instance being decorated
    private final ParameterElementDataService parameterElementDataService; 

    @Autowired
    public ParameterDataServiceDecoratorImpl (
            ParameterDataService decoratedParameterDataService,
            ParameterElementDataService parameterElementDataService) {
        this.decoratedParameterDataService = decoratedParameterDataService;
        this.parameterElementDataService = parameterElementDataService;
    }

    @Override
    public String getLastParameterValueFromUser(Long idContract, ParameterElement parameterElement, ScopeEnum scope) {
        return ((TgolParameterDAO)entityDao).findLastParameterValueFromUser(idContract, parameterElement, scope);
    }
    
    @Override
    public String getLastParameterValueFromContractAndScenario(Long idContract, ParameterElement parameterElement, String scenarioName) {
        return ((TgolParameterDAO)entityDao).findLastParameterValueFromContractAndScenario(idContract, parameterElement, scenarioName);
    }

    @Override
    public Parameter create(ParameterElement pe, String string, Audit audit) {
        return decoratedParameterDataService.create(pe, string, audit);
    }

    @Override
    public Parameter getParameter(ParameterElement pe, String string) {
        return decoratedParameterDataService.getParameter(pe, string);
    }

    @Override
    public Set<Parameter> getParameterSet(ParameterFamily pf, Audit audit) {
        return decoratedParameterDataService.getParameterSet(pf, audit);
    }

    @Override
    public Set<Parameter> getDefaultParameterSet() {
        return decoratedParameterDataService.getDefaultParameterSet();
    }

    @Override
    public Parameter getDefaultParameter(ParameterElement pe) {
        return decoratedParameterDataService.getDefaultParameter(pe);
    }

    @Override
    public Set<Parameter> getParameterSetFromAudit(Audit audit) {
        return decoratedParameterDataService.getParameterSetFromAudit(audit);
    }

    @Override
    public Set<Parameter> updateParameterSet(Set<Parameter> set, Set<Parameter> set1) {
        return decoratedParameterDataService.updateParameterSet(set, set1);
    }

    @Override
    public Set<Parameter> updateParameter(Set<Parameter> set, Parameter prmtr) {
        return decoratedParameterDataService.updateParameter(set, prmtr);
    }

    @Override
    public Set<Parameter> getParameterSetFromAuditLevel(String ref, String level) {
        return decoratedParameterDataService.getParameterSetFromAuditLevel(ref, level);
    }

    @Override
    public Set<Parameter> getAuditPageParameterSet(Set<Parameter> defaultParameterSet) {
        return decoratedParameterDataService.getAuditPageParameterSet(defaultParameterSet);
    }

    @Override
    public Set<Parameter> getParameterSet(ParameterFamily parameterFamily, Collection<Parameter> paramSet) {
        return decoratedParameterDataService.getParameterSet(parameterFamily, paramSet);
    }
    
    @Override
    public String getLevelKeyFromAudit(Audit audit) {
        return decoratedParameterDataService.getLevelKeyFromAudit(audit);
    }
    
    @Override
    public String getReferentialKeyFromAudit(Audit audit) {
        return decoratedParameterDataService.getReferentialKeyFromAudit(audit);
    }
    
    @Override
    public Collection<Parameter> getParameterSetFromOptionElementSet(Collection<OptionElement> optionElementSet) {
        Set<Parameter> paramSet = new HashSet<>();
        for (OptionElement optionElement : optionElementSet) {
            ParameterElement pe = parameterElementDataService.getParameterElement(optionElement.getOption().getCode());
            if (pe != null) {
                Parameter p = decoratedParameterDataService.getParameter(pe, optionElement.getValue());
                p = saveOrUpdate(p);
                paramSet.add(p);
            }
        }
        return paramSet;
    }

    @Override
    public Parameter getParameter(Audit audit, String parameterElementCode) {
        return decoratedParameterDataService.getParameter(audit, parameterElementCode);
    }

    @Override
    public Parameter getLevelParameter(String levelKey) {
        return decoratedParameterDataService.getLevelParameter(levelKey);
    }

    @Override
    public Parameter getDefaultLevelParameter() {
        return decoratedParameterDataService.getDefaultLevelParameter();
    }

}
