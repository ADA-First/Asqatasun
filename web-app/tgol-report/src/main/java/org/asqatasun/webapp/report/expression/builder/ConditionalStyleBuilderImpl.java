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
package org.asqatasun.webapp.report.expression.builder;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionStyleExpression;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionalStyle;
import java.util.Locale;

/**
 *
 * @author jkowalczyk
 */
public class ConditionalStyleBuilderImpl implements AbstractGenericConditionalStyleBuilder<ConditionalStyle>{

    private Style style;
    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    private AbstractGenericConditionStyleExpressionBuilder<ConditionStyleExpression> conditionalStyleExpressionBuilder;
    public AbstractGenericConditionStyleExpressionBuilder<ConditionStyleExpression> getConditionalStyleExpressionBuilder() {
        return conditionalStyleExpressionBuilder;
    }

    public void setConditionalStyleExpressionBuilder(
            AbstractGenericConditionStyleExpressionBuilder<ConditionStyleExpression> conditionalStyleExpressionBuilder) {
        this.conditionalStyleExpressionBuilder = conditionalStyleExpressionBuilder;
    }

    @Override
    public ConditionalStyle build(Locale locale) {
        return new ConditionalStyle(
                conditionalStyleExpressionBuilder.build(locale),
                style);
    }

}
