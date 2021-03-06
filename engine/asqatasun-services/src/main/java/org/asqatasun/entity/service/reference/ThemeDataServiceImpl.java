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
package org.asqatasun.entity.service.reference;

import java.util.Collection;
import org.asqatasun.entity.reference.Criterion;
import org.asqatasun.entity.reference.Theme;
import org.asqatasun.entity.service.AbstractGenericDataService;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jkowalczyk
 */
@Service("themeDataService")
public class ThemeDataServiceImpl extends AbstractGenericDataService<Theme, Long> implements ThemeDataService {

    public ThemeDataServiceImpl() {
        super();
    }

    @Override
    public Theme read(Long key) {
        Theme entity = super.read(key);
        return entity;
    }

    @Override
    public Collection<Theme> findAll() {
        Collection<Theme> themeList = super.findAll() ;
        for (Theme theme : themeList) {
            for (Criterion cr : theme.getCriterionList()) {
                cr.getReference();
            }
        }
        return themeList;
    }

}
