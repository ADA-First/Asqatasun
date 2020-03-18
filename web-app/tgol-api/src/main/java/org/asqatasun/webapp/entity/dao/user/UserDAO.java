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
package org.asqatasun.webapp.entity.dao.user;

import org.asqatasun.entity.dao.GenericDAO;
import org.asqatasun.webapp.entity.user.User;

/**
 *
 * @author jkowalczyk
 */
public interface UserDAO extends GenericDAO<User, Long> {

    /**
     *
     * @param email the user's email
     * @return
     *      the instance of User
     */
    User findUserFromEmail(String email);

    /**
     *
     * @param name the user's name
     * @return
     *      the instance of User
     */
    User findUserFromName(String name);

    /**
     * 
     * @param email
     * @return
     */
    boolean isAccountActivated(String email);

}
